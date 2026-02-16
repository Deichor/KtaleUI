package org.deichor.ktaleui

import org.deichor.ktaleui.element.UIElement
import org.deichor.ktaleui.serializer.UIFileSerializer

class KtaleUIDefinition(
    val name: String,
    val rootElements: List<UIElement>,
) {
    fun serialize(): String = UIFileSerializer.serialize(this)
}
