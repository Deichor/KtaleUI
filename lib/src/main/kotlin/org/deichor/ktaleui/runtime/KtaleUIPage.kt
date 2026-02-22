package org.deichor.ktaleui.runtime

import com.hypixel.hytale.codec.Codec
import com.hypixel.hytale.codec.KeyedCodec
import com.hypixel.hytale.codec.builder.BuilderCodec
import com.hypixel.hytale.component.Ref
import com.hypixel.hytale.component.Store
import com.hypixel.hytale.protocol.packets.interface_.CustomPageLifetime
import com.hypixel.hytale.protocol.packets.interface_.CustomUIEventBindingType
import com.hypixel.hytale.server.core.entity.entities.player.pages.InteractiveCustomUIPage
import com.hypixel.hytale.server.core.ui.builder.EventData
import com.hypixel.hytale.server.core.ui.builder.UICommandBuilder
import com.hypixel.hytale.server.core.ui.builder.UIEventBuilder
import com.hypixel.hytale.server.core.universe.PlayerRef
import com.hypixel.hytale.server.core.universe.world.storage.EntityStore
import org.deichor.ktaleui.KtaleUI
import org.deichor.ktaleui.KtaleUIDefinition
import org.deichor.ktaleui.asset.DynamicImageManager
import org.deichor.ktaleui.element.EventHandler
import java.util.concurrent.CompletableFuture

class KtaleUIPage(
    playerRef: PlayerRef,
    lifetime: CustomPageLifetime,
    private val definition: KtaleUIDefinition,
) : InteractiveCustomUIPage<KtaleUIPage.KtaleEventData>(playerRef, lifetime, KtaleEventData.CODEC) {

    private val handlers: Map<String, Map<String, EventHandler>> = collectHandlers(definition.rootElements)

    override fun build(
        ref: Ref<EntityStore>,
        commandBuilder: UICommandBuilder,
        eventBuilder: UIEventBuilder,
        store: Store<EntityStore>,
    ) {
        commandBuilder.append(KtaleUI.getPath(definition))

        // Register event bindings for all elements that have handlers
        val elements = collectElementsWithEvents(definition.rootElements)
        for (element in elements) {
            val elementId = element.id ?: continue
            for ((eventName, _) in element.eventHandlers) {
                val bindingType = CustomUIEventBindingType.valueOf(eventName)
                val action = "$elementId:$eventName"
                val eventData = EventData.of("Action", action)

                // For ValueChanged on text inputs, include the element's current value as string
                val textTypes = setOf("TextField", "CompactTextField", "MultilineTextField", "NumberField", "CodeEditor")
                if (eventName == "ValueChanged" && element.elementType in textTypes) {
                    eventData.append("@Value", "#$elementId.Value")
                }

                eventBuilder.addEventBinding(
                    bindingType,
                    "#$elementId",
                    eventData,
                    false,
                )
            }
        }
    }

    override fun handleDataEvent(ref: Ref<EntityStore>, store: Store<EntityStore>, data: KtaleEventData) {
        val action = data.action ?: return
        val parts = action.split(":", limit = 2)
        if (parts.size != 2) return

        val elementId = parts[0]
        val eventName = parts[1]

        val handler = handlers[elementId]?.get(eventName) ?: return
        val ctx = EventContext(
            playerRef = playerRef,
            ref = ref,
            store = store,
            page = this,
            eventData = data.toMap(),
        )
        handler.invoke(ctx)
    }

    fun update(block: UICommandBuilder.() -> Unit) {
        val cmd = UICommandBuilder()
        cmd.block()
        sendUpdate(cmd)
    }

    fun closePage() {
        close()
    }

    /**
     * Downloads an image from [url], sends it to the player as a dynamic asset,
     * and updates the element's background texture path.
     *
     * @param elementId the UI element ID to update (without # prefix)
     * @param url the remote image URL to download and send
     * @param ttlSeconds how long the cached image is valid (default 5 minutes)
     * @param onReady optional callback with the asset path after the image is loaded
     */
    fun setDynamicImage(
        elementId: String,
        url: String,
        ttlSeconds: Long = 300,
        onReady: ((String) -> Unit)? = null,
    ) {
        // Check if already sent to this player
        val existing = DynamicImageManager.getCachedAssetInfo(playerRef.uuid, url)
        if (existing != null) {
            update { set("#$elementId.Background.TexturePath", existing.path) }
            onReady?.invoke(existing.path)
            return
        }

        // Async download + send
        CompletableFuture.supplyAsync {
            DynamicImageManager.sendImage(playerRef, url, ttlSeconds)
        }.thenAccept { assetInfo ->
            if (assetInfo != null) {
                update { set("#$elementId.Background.TexturePath", assetInfo.path) }
                onReady?.invoke(assetInfo.path)
            }
        }
    }

    class KtaleEventData {
        var action: String? = null
        var value: String? = null

        fun toMap(): Map<String, String> = buildMap {
            action?.let { put("Action", it) }
            value?.let { put("Value", it) }
        }

        companion object {
            val CODEC: BuilderCodec<KtaleEventData> = BuilderCodec.builder(
                KtaleEventData::class.java,
                ::KtaleEventData,
            )
                .append(
                    KeyedCodec("Action", Codec.STRING),
                    { data, value -> data.action = value },
                    { data -> data.action },
                )
                .add()
                .append(
                    KeyedCodec("@Value", Codec.STRING),
                    { data, value -> data.value = value },
                    { data -> data.value },
                )
                .add()
                .build()
        }
    }
}
