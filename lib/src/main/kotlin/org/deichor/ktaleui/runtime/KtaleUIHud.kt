package org.deichor.ktaleui.runtime

import com.hypixel.hytale.server.core.entity.entities.player.hud.CustomUIHud
import com.hypixel.hytale.server.core.ui.builder.UICommandBuilder
import com.hypixel.hytale.server.core.universe.PlayerRef
import org.deichor.ktaleui.KtaleUIDefinition

class KtaleUIHud(
    playerRef: PlayerRef,
    private val definition: KtaleUIDefinition,
) : CustomUIHud(playerRef) {

    override fun build(commandBuilder: UICommandBuilder) {
        commandBuilder.appendInline(null, definition.serialize())
    }

    fun updateProperty(block: UICommandBuilder.() -> Unit) {
        val cmd = UICommandBuilder()
        cmd.block()
        update(false, cmd)
    }
}
