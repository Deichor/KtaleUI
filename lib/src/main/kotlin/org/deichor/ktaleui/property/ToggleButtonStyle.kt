package org.deichor.ktaleui.property

data class ToggleButtonStyle(
    var default: ToggleButtonStyleState? = null,
    var hovered: ToggleButtonStyleState? = null,
    var pressed: ToggleButtonStyleState? = null,
    var disabled: ToggleButtonStyleState? = null,
    var sounds: ButtonSounds? = null,
)
