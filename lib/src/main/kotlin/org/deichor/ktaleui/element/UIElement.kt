package org.deichor.ktaleui.element

import org.deichor.ktaleui.enums.LayoutMode
import org.deichor.ktaleui.enums.MouseWheelScrollBehaviourType
import org.deichor.ktaleui.property.*

typealias EventHandler = (eventData: Map<String, Any?>) -> Unit

abstract class UIElement(val elementType: String) {
    var id: String? = null

    // Common properties shared by all elements
    var visible: Boolean? = null
    var hitTestVisible: Boolean? = null
    var tooltipText: String? = null
    var tooltipTextSpans: List<LabelSpan>? = null
    var textTooltipStyle: TextTooltipStyle? = null
    var textTooltipShowDelay: Float? = null
    var anchor: Anchor? = null
    var padding: Padding? = null
    var flexWeight: Int? = null
    var contentWidth: Int? = null
    var contentHeight: Int? = null
    var autoScrollDown: Boolean? = null
    var keepScrollPosition: Boolean? = null
    var mouseWheelScrollBehaviour: MouseWheelScrollBehaviourType? = null
    var background: Any? = null // PatchStyle or String
    var maskTexturePath: String? = null
    var outlineColor: String? = null
    var outlineSize: Float? = null
    var overscroll: Boolean? = null

    // Event handlers (runtime)
    internal val eventHandlers = mutableMapOf<String, EventHandler>()

    // DSL helpers
    fun anchor(block: Anchor.() -> Unit) {
        anchor = (anchor ?: Anchor()).apply(block)
    }

    fun padding(block: Padding.() -> Unit) {
        padding = (padding ?: Padding()).apply(block)
    }

    fun textTooltipStyle(block: TextTooltipStyle.() -> Unit) {
        textTooltipStyle = (textTooltipStyle ?: TextTooltipStyle()).apply(block)
    }

    // Collect all element-specific properties for serialization
    open fun collectProperties(): Map<String, Any?> {
        return buildMap {
            visible?.let { put("Visible", it) }
            hitTestVisible?.let { put("HitTestVisible", it) }
            tooltipText?.let { put("TooltipText", it) }
            tooltipTextSpans?.let { put("TooltipTextSpans", it) }
            textTooltipStyle?.let { put("TextTooltipStyle", it) }
            textTooltipShowDelay?.let { put("TextTooltipShowDelay", it) }
            anchor?.let { put("Anchor", it) }
            padding?.let { put("Padding", it) }
            flexWeight?.let { put("FlexWeight", it) }
            contentWidth?.let { put("ContentWidth", it) }
            contentHeight?.let { put("ContentHeight", it) }
            autoScrollDown?.let { put("AutoScrollDown", it) }
            keepScrollPosition?.let { put("KeepScrollPosition", it) }
            mouseWheelScrollBehaviour?.let { put("MouseWheelScrollBehaviour", it) }
            background?.let { put("Background", it) }
            maskTexturePath?.let { put("MaskTexturePath", it) }
            outlineColor?.let { put("OutlineColor", it) }
            outlineSize?.let { put("OutlineSize", it) }
            overscroll?.let { put("Overscroll", it) }
        }
    }
}
