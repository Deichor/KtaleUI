package org.deichor.ktaleui.runtime

import com.hypixel.hytale.component.Ref
import com.hypixel.hytale.component.Store
import com.hypixel.hytale.server.core.universe.PlayerRef
import com.hypixel.hytale.server.core.universe.world.storage.EntityStore

data class EventContext(
    val playerRef: PlayerRef,
    val ref: Ref<EntityStore>,
    val store: Store<EntityStore>,
    val page: KtaleUIPage,
    val eventData: Map<String, String>,
)
