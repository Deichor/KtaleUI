package org.deichor.ktaleui

import com.hypixel.hytale.component.Ref
import com.hypixel.hytale.component.Store
import com.hypixel.hytale.protocol.packets.interface_.CustomPageLifetime
import com.hypixel.hytale.server.core.entity.entities.Player
import com.hypixel.hytale.server.core.universe.PlayerRef
import com.hypixel.hytale.server.core.universe.world.storage.EntityStore
import org.deichor.ktaleui.element.UIElement
import org.deichor.ktaleui.runtime.KtaleUIHud
import org.deichor.ktaleui.runtime.KtaleUIPage
import org.deichor.ktaleui.serializer.UIFileSerializer

class KtaleUIDefinition(
    val name: String,
    val rootElements: List<UIElement>,
) {
    fun serialize(): String = UIFileSerializer.serialize(this)

    fun open(
        playerRef: PlayerRef,
        lifetime: CustomPageLifetime = CustomPageLifetime.CanDismiss,
    ): KtaleUIPage {
        val page = KtaleUIPage(playerRef, lifetime, this)
        val ref: Ref<EntityStore> = playerRef.reference ?: return page
        val store: Store<EntityStore> = ref.store
        val player = store.getComponent(ref, Player.getComponentType()) ?: return page
        player.pageManager.openCustomPage(ref, store, page)
        return page
    }

    fun showAsHud(playerRef: PlayerRef): KtaleUIHud {
        val hud = KtaleUIHud(playerRef, this)
        hud.show()
        return hud
    }
}
