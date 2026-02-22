package org.deichor.ktaleui.asset

import com.hypixel.hytale.server.core.universe.PlayerRef
import java.net.URI
import java.net.http.HttpClient
import java.net.http.HttpRequest
import java.net.http.HttpResponse
import java.time.Duration
import java.util.UUID
import java.util.concurrent.CompletableFuture
import java.util.concurrent.ConcurrentHashMap

/**
 * High-level manager for downloading images from URLs and sending them to players
 * as dynamic image assets at runtime.
 *
 * Features:
 * - In-memory byte cache with configurable TTL
 * - Per-player asset tracking (which URLs have been sent to which players)
 * - Automatic slot management via [DynamicImageAsset]
 * - Async download and send operations
 */
object DynamicImageManager {

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

    /** Key for per-player asset cache. */
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

    /** (player, url) → sent asset info. */
    private val playerAssetCache = ConcurrentHashMap<PlayerUrlKey, PlayerAssetEntry>()

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
        // Check if already sent to this player and still valid
        val existing = getCachedAssetInfo(playerRef.uuid, url)
        if (existing != null) return existing

        // Download (or use byte cache)
        val bytes = downloadBytes(url, ttlSeconds) ?: return null

        // Claim a slot
        val slotIndex = DynamicImageAsset.claimSlot(playerRef.uuid)
        if (slotIndex == -1) return null

        // Send to player
        val path = DynamicImageAsset.getPath(slotIndex)
        DynamicImageAsset.sendToPlayer(playerRef.packetHandler, slotIndex, bytes)

        // Track in player cache
        val key = PlayerUrlKey(playerRef.uuid, url)
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
        val key = PlayerUrlKey(playerUuid, url)
        val entry = playerAssetCache[key] ?: return null

        if (System.currentTimeMillis() > entry.expiresAtMs) {
            // Expired — release slot and remove
            DynamicImageAsset.releaseSlot(playerUuid, entry.slotIndex)
            playerAssetCache.remove(key)
            return null
        }

        return CachedAssetInfo(entry.path, entry.slotIndex)
    }

    /**
     * Releases all dynamic image slots and clears the player cache for the given player.
     * Call this on player disconnect.
     */
    fun releasePlayer(playerUuid: UUID) {
        DynamicImageAsset.releaseAllSlots(playerUuid)

        // Remove all entries for this player from the player asset cache
        val keysToRemove = playerAssetCache.keys.filter { it.playerUuid == playerUuid }
        keysToRemove.forEach { playerAssetCache.remove(it) }
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

    /**
     * Downloads bytes from URL, using cache if available and fresh.
     */
    private fun downloadBytes(url: String, ttlSeconds: Long): ByteArray? {
        // Check byte cache
        val cached = downloadCache[url]
        if (cached != null) {
            val age = System.currentTimeMillis() - cached.createdAtMs
            if (age < ttlSeconds * 1000) {
                return cached.bytes
            }
        }

        // Download fresh
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
                null
            }
        } catch (_: Exception) {
            null
        }
    }
}
