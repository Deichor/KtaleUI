package org.deichor.ktaleui.dsl

import org.deichor.ktaleui.KtaleUIDefinition
import org.deichor.ktaleui.element.ContainerElement
import org.deichor.ktaleui.element.UIElement

@DslMarker
annotation class KtaleUIDsl

@KtaleUIDsl
class UIBuilder {
    internal val rootElements = mutableListOf<UIElement>()

    internal fun addElement(element: UIElement) {
        rootElements.add(element)
    }
}

fun ktaleUI(name: String, block: UIBuilder.() -> Unit): KtaleUIDefinition {
    val builder = UIBuilder()
    builder.block()
    return KtaleUIDefinition(name, builder.rootElements)
}
