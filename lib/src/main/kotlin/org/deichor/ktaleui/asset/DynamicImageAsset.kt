package org.deichor.ktaleui.asset

import com.hypixel.hytale.common.util.ArrayUtil
import com.hypixel.hytale.protocol.Asset
import com.hypixel.hytale.protocol.ToClientPacket
import com.hypixel.hytale.protocol.packets.setup.AssetFinalize
import com.hypixel.hytale.protocol.packets.setup.AssetInitialize
import com.hypixel.hytale.protocol.packets.setup.AssetPart
import com.hypixel.hytale.protocol.packets.setup.RequestCommonAssetsRebuild
import com.hypixel.hytale.server.core.io.PacketHandler
import java.util.UUID
import java.util.concurrent.ConcurrentHashMap

/**
 * Pre-defined dynamic image asset slots that can be sent to players at runtime.
 * Each slot has a unique fake hash and a fixed path that the UI can reference.
 *
 * The client receives these as normal common assets via the asset transfer protocol,
 * allowing PNG bytes to be pushed to the client after the initial login asset load.
 */
object DynamicImageAsset {

    private const val DEFAULT_MAX_SLOTS = 25
    private const val CHUNK_SIZE = 2_621_440 // Same as CommonAssetModule.MAX_FRAME

    private var maxSlots = DEFAULT_MAX_SLOTS
    private lateinit var slotAssetNames: Array<String>
    private lateinit var slotPaths: Array<String>
    private lateinit var slotHashes: Array<String>
    private var initialized = false

    /**
     * UI-facing paths for each slot (relative to Common/UI/Custom/).
     * These are used in UI property commands like set("#Element.Background", path).
     */
    val SLOT_PATHS: Array<String>
        get() {
            ensureInitialized()
            return slotPaths
        }

    /**
     * Configures the maximum number of dynamic image slots available per player.
     * Call this once during plugin setup, before any images are sent.
     * Defaults to [DEFAULT_MAX_SLOTS] if not called.
     *
     * @param maxSlots the maximum number of concurrent dynamic images per player
     */
    fun configure(maxSlots: Int) {
        require(maxSlots > 0) { "maxSlots must be positive" }
        this.maxSlots = maxSlots
        buildSlotArrays()
    }

    private fun ensureInitialized() {
        if (!initialized) buildSlotArrays()
    }

    private fun buildSlotArrays() {
        slotAssetNames = Array(maxSlots) { i ->
            "UI/Custom/Pages/Elements/DynamicImage${i + 1}.png"
        }
        slotPaths = Array(maxSlots) { i ->
            "Pages/Elements/DynamicImage${i + 1}.png"
        }
        slotHashes = Array(maxSlots) { i ->
            val base = "d1a2b3c4e5f6a7b8c9d0e1f2a3b4c5d6e7f8a9b0c1d2e3f4a5b6c7d8e9f0"
            base + String.format("%04x", i + 1)
        }
        initialized = true
    }

    /** Tracks which slots are in use per player. */
    private val playerSlots = ConcurrentHashMap<UUID, BooleanArray>()

    /**
     * Claims the next available slot for a player.
     * @return the slot index, or -1 if all slots are in use.
     */
    @Synchronized
    fun claimSlot(playerUuid: UUID): Int {
        ensureInitialized()
        val slots = playerSlots.getOrPut(playerUuid) { BooleanArray(maxSlots) }
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
    fun getPath(slotIndex: Int): String {
        ensureInitialized()
        return slotPaths[slotIndex]
    }

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
        ensureInitialized()
        val assetName = slotAssetNames[slotIndex]
        val assetPacket = Asset(slotHashes[slotIndex], assetName)
        val chunks = ArrayUtil.split(imageBytes, CHUNK_SIZE)

        val packets = mutableListOf<ToClientPacket>()
        packets.add(AssetInitialize(assetPacket, imageBytes.size))
        for (chunk in chunks) {
            packets.add(AssetPart(chunk))
        }
        packets.add(AssetFinalize())

        // Send asset data packets together (matches server's CommonAssetModule pattern)
        packetHandler.write(*packets.toTypedArray())

        // MUST use writeNoCache — same as server's CommonAssetModule.sendAssetsToPlayer()
        packetHandler.writeNoCache(RequestCommonAssetsRebuild())
    }
}
