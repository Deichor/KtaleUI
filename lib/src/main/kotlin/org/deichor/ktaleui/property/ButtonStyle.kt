package org.deichor.ktaleui.property

data class ButtonStyle(
    var default: ButtonStyleState? = null,
    var hovered: ButtonStyleState? = null,
    var pressed: ButtonStyleState? = null,
    var disabled: ButtonStyleState? = null,
    var sounds: ButtonSounds? = null,
)
