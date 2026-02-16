package org.deichor.ktaleui.property

data class InputFieldDecorationStyleState(
    var outlineSize: Int? = null,
    var background: Any? = null,
    var outlineColor: String? = null,
    var icon: InputFieldIcon? = null,
    var clearButtonStyle: InputFieldButtonStyle? = null,
    var toggleVisibilityButtonStyle: InputFieldButtonStyle? = null,
)
