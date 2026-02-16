package org.deichor.ktaleui.property

data class CheckBoxStyleState(
    var defaultBackground: Any? = null,
    var hoveredBackground: Any? = null,
    var pressedBackground: Any? = null,
    var disabledBackground: Any? = null,
    var changedSound: SoundStyle? = null,
    var hoveredSound: SoundStyle? = null,
)
