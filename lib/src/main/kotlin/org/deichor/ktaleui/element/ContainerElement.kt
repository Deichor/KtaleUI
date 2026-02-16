package org.deichor.ktaleui.element

import org.deichor.ktaleui.enums.LayoutMode
import org.deichor.ktaleui.property.ScrollbarStyle

abstract class ContainerElement(elementType: String) : UIElement(elementType) {
    val children = mutableListOf<UIElement>()
    var layoutMode: LayoutMode? = null
    var scrollbarStyle: ScrollbarStyle? = null

    override fun collectProperties(): Map<String, Any?> {
        return super.collectProperties() + buildMap {
            layoutMode?.let { put("LayoutMode", it) }
            scrollbarStyle?.let { put("ScrollbarStyle", it) }
        }
    }
}
