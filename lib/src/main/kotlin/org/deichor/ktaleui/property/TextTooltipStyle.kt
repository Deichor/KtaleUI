package org.deichor.ktaleui.property

import org.deichor.ktaleui.enums.TooltipAlignment

data class TextTooltipStyle(
    var labelStyle: LabelStyle? = null,
    var padding: Padding? = null,
    var background: Any? = null,
    var maxWidth: Int? = null,
    var alignment: TooltipAlignment? = null,
)
