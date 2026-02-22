package org.deichor.ktaleui.asset

import com.hypixel.hytale.common.util.ArrayUtil
import com.hypixel.hytale.logger.HytaleLogger
import com.hypixel.hytale.protocol.Asset
import com.hypixel.hytale.protocol.ToClientPacket
import com.hypixel.hytale.protocol.packets.setup.AssetFinalize
import com.hypixel.hytale.protocol.packets.setup.AssetInitialize
import com.hypixel.hytale.protocol.packets.setup.AssetPart
import com.hypixel.hytale.protocol.packets.setup.RequestCommonAssetsRebuild
import com.hypixel.hytale.server.core.io.PacketHandler
import java.util.UUID
import java.util.concurrent.ConcurrentHashMap
import java.util.logging.Level

/**
 * Pre-defined dynamic image asset slots that can be sent to players at runtime.
 * Each slot has a unique fake hash and a fixed path that the UI can reference.
 *
 * The client receives these as normal common assets via the asset transfer protocol,
 * allowing PNG bytes to be pushed to the client after the initial login asset load.
 */
object DynamicImageAsset {

    private val LOGGER = HytaleLogger.get("KtaleUI-DynamicImage")
    private const val MAX_SLOTS = 25
    private const val CHUNK_SIZE = 2_621_440 // Same as CommonAssetModule.MAX_FRAME

    /**
     * Asset names for each slot (relative to Common/).
     * These are sent via the asset protocol's Asset.name field.
     */
    private val SLOT_ASSET_NAMES: Array<String> = Array(MAX_SLOTS) { i ->
        "UI/Custom/Pages/Elements/DynamicImage${i + 1}.png"
    }

    /**
     * UI-facing paths for each slot (relative to Common/UI/Custom/).
     * These are used in UI property commands like set("#Element.Background", path).
     * The client's UI system prepends "UI/Custom/" when looking up assets,
     * so the UI path is the asset name minus the "UI/Custom/" prefix.
     */
    val SLOT_PATHS: Array<String> = Array(MAX_SLOTS) { i ->
        "Pages/Elements/DynamicImage${i + 1}.png"
    }

    /**
     * Pre-defined unique 64-char hex hashes for each slot.
     * These are fake hashes — they just need to be unique per slot
     * so the client treats each as a distinct asset.
     */
    private val SLOT_HASHES: Array<String> = Array(MAX_SLOTS) { i ->
        val base = "d1a2b3c4e5f6a7b8c9d0e1f2a3b4c5d6e7f8a9b0c1d2e3f4a5b6c7d8e9f0a1b2"
        // Modify the last 2 chars to make each hash unique
        base.substring(0, 62) + String.format("%02x", i + 1)
    }

    /** Tracks which slots are in use per player. */
    private val playerSlots = ConcurrentHashMap<UUID, BooleanArray>()

    /**
     * Claims the next available slot for a player.
     * @return the slot index, or -1 if all slots are in use.
     */
    @Synchronized
    fun claimSlot(playerUuid: UUID): Int {
        val slots = playerSlots.getOrPut(playerUuid) { BooleanArray(MAX_SLOTS) }
        for (i in slots.indices) {
            if (!slots[i]) {
                slots[i] = true
                return i
            }
        }
        return -1
    }

    /**
     * Releases a specific slot for a player.
     */
    @Synchronized
    fun releaseSlot(playerUuid: UUID, slotIndex: Int) {
        val slots = playerSlots[playerUuid] ?: return
        if (slotIndex in slots.indices) {
            slots[slotIndex] = false
        }
    }

    /**
     * Releases all slots for a player (call on disconnect).
     */
    @Synchronized
    fun releaseAllSlots(playerUuid: UUID) {
        playerSlots.remove(playerUuid)
    }

    /**
     * Returns the asset path for the given slot index.
     */
    fun getPath(slotIndex: Int): String = SLOT_PATHS[slotIndex]

    /**
     * Sends image bytes to a player via the Hytale asset transfer protocol.
     *
     * Protocol sequence: AssetInitialize -> AssetPart(s) -> AssetFinalize, then RequestCommonAssetsRebuild via writeNoCache.
     *
     * @param packetHandler the player's packet handler
     * @param slotIndex the slot index to use (determines asset path and hash)
     * @param imageBytes the raw PNG image bytes to send
     */
    fun sendToPlayer(packetHandler: PacketHandler, slotIndex: Int, imageBytes: ByteArray) {
        val assetName = SLOT_ASSET_NAMES[slotIndex]
        val uiPath = SLOT_PATHS[slotIndex]
        val hash = SLOT_HASHES[slotIndex]
        LOGGER.at(Level.INFO).log(
            "Sending dynamic image: slot=%d, assetName=%s, uiPath=%s, hash=%s, bytes=%d",
            slotIndex, assetName, uiPath, hash, imageBytes.size,
        )

        val assetPacket = Asset(hash, assetName)
        val chunks = ArrayUtil.split(imageBytes, CHUNK_SIZE)

        val packets = mutableListOf<ToClientPacket>()

        // 1. Initialize: tell client which asset and total size
        packets.add(AssetInitialize(assetPacket, imageBytes.size))

        // 2. Parts: send data chunks
        for (chunk in chunks) {
            packets.add(AssetPart(chunk))
        }

        // 3. Finalize: signal end of this asset transfer
        packets.add(AssetFinalize())

        LOGGER.at(Level.INFO).log(
            "Writing %d packets (1 init + %d parts + 1 finalize) for slot %d",
            packets.size, chunks.size, slotIndex,
        )

        // Send asset data packets together (matches server's CommonAssetModule pattern)
        packetHandler.write(*packets.toTypedArray())

        // 4. Rebuild: tell client to rebuild its asset cache
        // MUST use writeNoCache — same as server's CommonAssetModule.sendAssetsToPlayer()
        packetHandler.writeNoCache(RequestCommonAssetsRebuild())
    }
}
