package org.deichor.ktaleui.property

data class TabStyleState(
    var background: Any? = null,
    var overlay: Any? = null,
    var anchor: Anchor? = null,
    var padding: Padding? = null,
    var iconAnchor: Anchor? = null,
    var iconOpacity: Float? = null,
    var labelStyle: LabelStyle? = null,
    var flexWeight: Int? = null,
    var contentMaskTexturePath: String? = null,
    var tooltipStyle: TextTooltipStyle? = null,
)
