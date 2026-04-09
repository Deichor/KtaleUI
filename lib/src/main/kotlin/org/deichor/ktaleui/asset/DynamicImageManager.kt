package org.deichor.ktaleui.asset

import com.hypixel.hytale.logger.HytaleLogger
import com.hypixel.hytale.server.core.universe.PlayerRef
import java.io.File
import java.net.URI
import java.net.http.HttpClient
import java.net.http.HttpRequest
import java.net.http.HttpResponse
import java.nio.file.Files
import java.time.Duration
import java.util.UUID
import java.util.concurrent.CompletableFuture
import java.util.concurrent.ConcurrentHashMap
import java.util.logging.Level

/**
 * High-level manager for downloading images from URLs or reading them from local
 * files and sending them to players as dynamic image assets at runtime.
 *
 * Features:
 * - In-memory byte cache with configurable TTL (for URL downloads)
 * - Per-player asset tracking (which sources have been sent to which players)
 * - Automatic slot management via [DynamicImageAsset]
 * - Async download / disk read and send operations
 * - Local file cache is keyed by absolute path + last-modified time so replacing
 *   a file on disk automatically invalidates the cache on the next call.
 */
object DynamicImageManager {

    private val LOGGER = HytaleLogger.get("KtaleUI-DynamicImage")
    private const val HTTP_TIMEOUT_MS = 5000L

    /** Cached download result keyed by URL. */
    private data class CacheEntry(
        val bytes: ByteArray,
        val createdAtMs: Long,
    )

    /** Tracks a dynamic image that has been sent to a specific player. */
    private data class PlayerAssetEntry(
        val bytes: ByteArray,
        val expiresAtMs: Long,
        val slotIndex: Int,
        val path: String,
    )

    /** Key for per-player asset cache (generic — URL or file pseudo-URL). */
    private data class PlayerUrlKey(
        val playerUuid: UUID,
        val url: String,
    )

    /** Return type for callers: the asset path the UI can reference, plus the slot index. */
    data class CachedAssetInfo(
        val path: String,
        val slotIndex: Int,
    )

    private val httpClient: HttpClient = HttpClient.newBuilder()
        .connectTimeout(Duration.ofMillis(HTTP_TIMEOUT_MS))
        .followRedirects(HttpClient.Redirect.NORMAL)
        .build()

    /** URL → downloaded bytes cache. */
    private val downloadCache = ConcurrentHashMap<String, CacheEntry>()

    /** (player, source-key) → sent asset info. */
    private val playerAssetCache = ConcurrentHashMap<PlayerUrlKey, PlayerAssetEntry>()

    // ──────────────────────────────────────────────────────────────────────
    // URL-based API
    // ──────────────────────────────────────────────────────────────────────

    /**
     * Downloads an image from [url] (or uses cache), claims a slot, sends it to
     * the player, and returns the asset info. Returns `null` on failure.
     *
     * @param playerRef the player to send the image to
     * @param url the remote image URL
     * @param ttlSeconds how long the cached image bytes are valid
     * @return asset info with the path the UI can reference, or null on failure
     */
    fun sendImage(playerRef: PlayerRef, url: String, ttlSeconds: Long = 300): CachedAssetInfo? {
        val existing = getCachedAssetInfo(playerRef.uuid, url)
        if (existing != null) return existing

        val bytes = downloadBytes(url, ttlSeconds) ?: return null
        return sendBytesToPlayer(playerRef, url, bytes, ttlSeconds)
    }

    /**
     * Async version of [sendImage]. Runs on a CompletableFuture and calls
     * [callback] with the result on completion.
     */
    fun sendImageAsync(
        playerRef: PlayerRef,
        url: String,
        ttlSeconds: Long = 300,
        callback: ((CachedAssetInfo?) -> Unit)? = null,
    ): CompletableFuture<CachedAssetInfo?> {
        return CompletableFuture.supplyAsync {
            sendImage(playerRef, url, ttlSeconds)
        }.whenComplete { result, _ ->
            callback?.invoke(result)
        }
    }

    /**
     * Checks if an image has already been sent to a player for the given URL
     * and is still valid.
     *
     * @return the cached asset info, or null if not cached or expired
     */
    fun getCachedAssetInfo(playerUuid: UUID, url: String): CachedAssetInfo? {
        return getCachedEntry(playerUuid, url)
    }

    /**
     * Pre-downloads image bytes into the byte cache without sending to any player.
     * Useful for warming up the cache before a player joins.
     */
    fun preDownload(url: String, ttlSeconds: Long = 300) {
        CompletableFuture.runAsync {
            downloadBytes(url, ttlSeconds)
        }
    }

    // ──────────────────────────────────────────────────────────────────────
    // Local file API
    // ──────────────────────────────────────────────────────────────────────

    /**
     * Reads a local image file from disk, claims a slot, sends it to the player,
     * and returns the asset info. Returns `null` on failure (file missing,
     * unreadable, empty, or no slots available).
     *
     * The cache key combines absolute path + last-modified time, so replacing the
     * file on disk automatically invalidates the cache on the next call.
     *
     * @param playerRef the player to send the image to
     * @param file the local image file on disk
     * @param ttlSeconds how long the sent asset stays valid for this player
     */
    fun sendLocalImage(
        playerRef: PlayerRef,
        file: File,
        ttlSeconds: Long = 300,
    ): CachedAssetInfo? {
        val key = localCacheKey(file)
        if (key == null) {
            LOGGER.at(Level.WARNING).log("Local image not found or unreadable: %s", file.absolutePath)
            return null
        }

        val existing = getCachedEntry(playerRef.uuid, key)
        if (existing != null) return existing

        val bytes = try {
            Files.readAllBytes(file.toPath())
        } catch (e: Exception) {
            LOGGER.at(Level.WARNING).log("Failed to read local image %s: %s", file.absolutePath, e.message)
            return null
        }

        if (bytes.isEmpty()) {
            LOGGER.at(Level.WARNING).log("Local image is empty: %s", file.absolutePath)
            return null
        }

        return sendBytesToPlayer(playerRef, key, bytes, ttlSeconds)
    }

    /**
     * Async version of [sendLocalImage]. Disk I/O runs on a CompletableFuture
     * and calls [callback] with the result on completion.
     */
    fun sendLocalImageAsync(
        playerRef: PlayerRef,
        file: File,
        ttlSeconds: Long = 300,
        callback: ((CachedAssetInfo?) -> Unit)? = null,
    ): CompletableFuture<CachedAssetInfo?> {
        return CompletableFuture.supplyAsync {
            sendLocalImage(playerRef, file, ttlSeconds)
        }.whenComplete { result, _ ->
            callback?.invoke(result)
        }
    }

    /**
     * Checks if a local file has already been sent to this player and is still
     * valid. Returns null if not cached, expired, or if the file on disk has
     * been modified since it was last sent.
     */
    fun getCachedLocalAssetInfo(playerUuid: UUID, file: File): CachedAssetInfo? {
        val key = localCacheKey(file) ?: return null
        return getCachedEntry(playerUuid, key)
    }

    // ──────────────────────────────────────────────────────────────────────
    // Shared
    // ──────────────────────────────────────────────────────────────────────

    /**
     * Releases all dynamic image slots and clears the player cache for the given player.
     * Call this on player disconnect.
     */
    fun releasePlayer(playerUuid: UUID) {
        DynamicImageAsset.releaseAllSlots(playerUuid)

        val keysToRemove = playerAssetCache.keys.filter { it.playerUuid == playerUuid }
        keysToRemove.forEach { playerAssetCache.remove(it) }
    }

    /**
     * Claims a slot, pushes [bytes] to the player, and tracks the asset in the
     * per-player cache. Shared by URL and local-file paths.
     */
    private fun sendBytesToPlayer(
        playerRef: PlayerRef,
        cacheKey: String,
        bytes: ByteArray,
        ttlSeconds: Long,
    ): CachedAssetInfo? {
        val slotIndex = DynamicImageAsset.claimSlot(playerRef.uuid)
        if (slotIndex == -1) {
            LOGGER.at(Level.WARNING).log("No dynamic image slots available for player=%s", playerRef.username)
            return null
        }

        val path = DynamicImageAsset.getPath(slotIndex)
        DynamicImageAsset.sendToPlayer(playerRef.packetHandler, slotIndex, bytes)

        val key = PlayerUrlKey(playerRef.uuid, cacheKey)
        val entry = PlayerAssetEntry(
            bytes = bytes,
            expiresAtMs = System.currentTimeMillis() + (ttlSeconds * 1000),
            slotIndex = slotIndex,
            path = path,
        )
        playerAssetCache[key] = entry

        return CachedAssetInfo(path, slotIndex)
    }

    /**
     * Looks up a cached asset for the given (player, source-key) pair. Returns
     * null if not cached or expired; releases the slot on expiry.
     */
    private fun getCachedEntry(playerUuid: UUID, cacheKey: String): CachedAssetInfo? {
        val key = PlayerUrlKey(playerUuid, cacheKey)
        val entry = playerAssetCache[key] ?: return null

        if (System.currentTimeMillis() > entry.expiresAtMs) {
            DynamicImageAsset.releaseSlot(playerUuid, entry.slotIndex)
            playerAssetCache.remove(key)
            return null
        }

        return CachedAssetInfo(entry.path, entry.slotIndex)
    }

    /**
     * Builds a stable cache key for a local file: `file://<abspath>#<mtime>`.
     * Returns null if the file does not exist or is not a regular file.
     */
    private fun localCacheKey(file: File): String? {
        if (!file.isFile) return null
        return "file://${file.absolutePath}#${file.lastModified()}"
    }

    /**
     * Downloads bytes from URL, using cache if available and fresh.
     */
    private fun downloadBytes(url: String, ttlSeconds: Long): ByteArray? {
        val cached = downloadCache[url]
        if (cached != null) {
            val age = System.currentTimeMillis() - cached.createdAtMs
            if (age < ttlSeconds * 1000) return cached.bytes
        }

        return try {
            val request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .timeout(Duration.ofMillis(HTTP_TIMEOUT_MS))
                .GET()
                .build()

            val response = httpClient.send(request, HttpResponse.BodyHandlers.ofByteArray())

            if (response.statusCode() == 200) {
                val bytes = response.body()
                downloadCache[url] = CacheEntry(bytes, System.currentTimeMillis())
                bytes
            } else {
                LOGGER.at(Level.WARNING).log("HTTP %d for %s", response.statusCode(), url)
                null
            }
        } catch (e: Exception) {
            LOGGER.at(Level.WARNING).log("Download failed for %s: %s", url, e.message)
            null
        }
    }
}
