package org.deichor.ktaleui.property

data class PopupStyle(
    var background: Any? = null,
    var padding: Padding? = null,
    var width: Int? = null,
    var buttonPadding: Padding? = null,
    var buttonStyle: SubMenuItemStyle? = null,
    var selectedButtonStyle: SubMenuItemStyle? = null,
    var tooltipStyle: TextTooltipStyle? = null,
)
