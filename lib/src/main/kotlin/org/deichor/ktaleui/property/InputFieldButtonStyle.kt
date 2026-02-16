package org.deichor.ktaleui.property

import org.deichor.ktaleui.enums.InputFieldButtonSide

data class InputFieldButtonStyle(
    var texture: Any? = null,
    var hoveredTexture: Any? = null,
    var pressedTexture: Any? = null,
    var width: Int? = null,
    var height: Int? = null,
    var offset: Int? = null,
    var side: InputFieldButtonSide? = null,
)
