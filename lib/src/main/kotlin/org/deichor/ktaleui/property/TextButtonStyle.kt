package org.deichor.ktaleui.property

data class TextButtonStyle(
    var default: TextButtonStyleState? = null,
    var hovered: TextButtonStyleState? = null,
    var pressed: TextButtonStyleState? = null,
    var disabled: TextButtonStyleState? = null,
    var sounds: ButtonSounds? = null,
)
