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
import org.deichor.ktaleui.KtaleUIDefinition
import org.deichor.ktaleui.element.EventHandler

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
        // Send the .ui layout inline
        commandBuilder.appendInline(null, definition.serialize())

        // Register event bindings for all elements that have handlers
        val elements = collectElementsWithEvents(definition.rootElements)
        for (element in elements) {
            val elementId = element.id ?: continue
            for ((eventName, _) in element.eventHandlers) {
                val bindingType = CustomUIEventBindingType.valueOf(eventName)
                val action = "$elementId:$eventName"
                eventBuilder.addEventBinding(
                    bindingType,
                    "#$elementId",
                    EventData.of("Action", action),
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

    class KtaleEventData {
        var action: String? = null

        fun toMap(): Map<String, String> = buildMap {
            action?.let { put("Action", it) }
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
                .build()
        }
    }
}
