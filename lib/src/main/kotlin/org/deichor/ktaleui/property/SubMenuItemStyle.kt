package org.deichor.ktaleui.property

data class SubMenuItemStyle(
    var default: SubMenuItemStyleState? = null,
    var hovered: SubMenuItemStyleState? = null,
    var pressed: SubMenuItemStyleState? = null,
    var disabled: SubMenuItemStyleState? = null,
    var sounds: ButtonSounds? = null,
)
