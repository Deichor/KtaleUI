package org.deichor.ktaleui.property

import org.deichor.ktaleui.enums.InputFieldIconSide

data class InputFieldIcon(
    var texture: Any? = null,
    var width: Int? = null,
    var height: Int? = null,
    var offset: Int? = null,
    var side: InputFieldIconSide? = null,
)
