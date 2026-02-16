package org.deichor.ktaleui.property

data class ScrollbarStyle(
    var size: Int? = null,
    var spacing: Int? = null,
    var onlyVisibleWhenHovered: Boolean? = null,
    var background: Any? = null,
    var handle: Any? = null,
    var hoveredHandle: Any? = null,
    var draggedHandle: Any? = null,
)
