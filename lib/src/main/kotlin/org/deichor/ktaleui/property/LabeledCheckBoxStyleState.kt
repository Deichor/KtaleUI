package org.deichor.ktaleui.property

data class LabeledCheckBoxStyleState(
    var defaultLabelStyle: LabelStyle? = null,
    var hoveredLabelStyle: LabelStyle? = null,
    var pressedLabelStyle: LabelStyle? = null,
    var disabledLabelStyle: LabelStyle? = null,
    var text: String? = null,
    var defaultBackground: Any? = null,
    var hoveredBackground: Any? = null,
    var pressedBackground: Any? = null,
    var disabledBackground: Any? = null,
    var changedSound: SoundStyle? = null,
    var hoveredSound: SoundStyle? = null,
)
