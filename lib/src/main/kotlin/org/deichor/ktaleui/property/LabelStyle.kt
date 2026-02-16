package org.deichor.ktaleui.property

import org.deichor.ktaleui.enums.LabelAlignment

data class LabelStyle(
    var horizontalAlignment: LabelAlignment? = null,
    var verticalAlignment: LabelAlignment? = null,
    var wrap: Boolean? = null,
    var fontName: String? = null,
    var fontSize: Float? = null,
    var textColor: String? = null,
    var outlineColor: String? = null,
    var letterSpacing: Float? = null,
    var renderUppercase: Boolean? = null,
    var renderBold: Boolean? = null,
    var renderItalics: Boolean? = null,
    var renderUnderlined: Boolean? = null,
    var alignment: LabelAlignment? = null,
)
