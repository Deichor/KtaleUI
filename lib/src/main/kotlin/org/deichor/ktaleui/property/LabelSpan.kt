package org.deichor.ktaleui.property

data class LabelSpan(
    var text: String? = null,
    var isUppercase: Boolean? = null,
    var isBold: Boolean? = null,
    var isItalics: Boolean? = null,
    var isMonospace: Boolean? = null,
    var isUnderlined: Boolean? = null,
    var color: String? = null,
    var outlineColor: String? = null,
    var link: String? = null,
    var params: Map<String, String>? = null,
)
