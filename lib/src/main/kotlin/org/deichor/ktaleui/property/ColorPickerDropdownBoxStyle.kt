package org.deichor.ktaleui.property

data class ColorPickerDropdownBoxStyle(
    var background: ColorPickerDropdownBoxStateBackground? = null,
    var colorPickerStyle: ColorPickerStyle? = null,
    var overlay: ColorPickerDropdownBoxStateBackground? = null,
    var arrowBackground: ColorPickerDropdownBoxStateBackground? = null,
    var arrowAnchor: Anchor? = null,
    var sounds: ButtonSounds? = null,
    var panelBackground: Any? = null,
    var panelWidth: Int? = null,
    var panelHeight: Int? = null,
    var panelPadding: Padding? = null,
    var panelOffset: Int? = null,
)
