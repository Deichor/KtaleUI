package org.deichor.ktaleui.property

data class ColorOptionGridStyle(
    var optionSize: Int? = null,
    var optionSpacingHorizontal: Int? = null,
    var optionSpacingVertical: Int? = null,
    var highlightSize: Int? = null,
    var highlightOffsetLeft: Int? = null,
    var highlightOffsetTop: Int? = null,
    var highlightBackground: Any? = null,
    var maskTexturePath: String? = null,
    var frameBackground: Any? = null,
    var sounds: ButtonSounds? = null,
)
