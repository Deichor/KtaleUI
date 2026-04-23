package org.deichor.ktaleui.ref

// CheckBox
class CheckBoxRef @PublishedApi internal constructor(id: String) : ElementRef(id) {
    val value get() = UISelector("#$id.Value")
    val disabled get() = UISelector("#$id.Disabled")
    val style get() = UISelector("#$id.Style")
}

// LabeledCheckBox
class LabeledCheckBoxRef @PublishedApi internal constructor(id: String) : ElementRef(id) {
    val value get() = UISelector("#$id.Value")
    val disabled get() = UISelector("#$id.Disabled")
    val style get() = UISelector("#$id.Style")
}

// Slider
class SliderRef @PublishedApi internal constructor(id: String) : ElementRef(id) {
    val value get() = UISelector("#$id.Value")
    val min get() = UISelector("#$id.Min")
    val max get() = UISelector("#$id.Max")
    val step get() = UISelector("#$id.Step")
    val isReadOnly get() = UISelector("#$id.IsReadOnly")
    val style get() = UISelector("#$id.Style")
}

// FloatSlider
class FloatSliderRef @PublishedApi internal constructor(id: String) : ElementRef(id) {
    val value get() = UISelector("#$id.Value")
    val min get() = UISelector("#$id.Min")
    val max get() = UISelector("#$id.Max")
    val step get() = UISelector("#$id.Step")
    val style get() = UISelector("#$id.Style")
}

// SliderNumberField
class SliderNumberFieldRef @PublishedApi internal constructor(id: String) : ElementRef(id) {
    val value get() = UISelector("#$id.Value")
    val min get() = UISelector("#$id.Min")
    val max get() = UISelector("#$id.Max")
    val step get() = UISelector("#$id.Step")
    val isReadOnly get() = UISelector("#$id.IsReadOnly")
    val style get() = UISelector("#$id.Style")
}

// FloatSliderNumberField
class FloatSliderNumberFieldRef @PublishedApi internal constructor(id: String) : ElementRef(id) {
    val value get() = UISelector("#$id.Value")
    val min get() = UISelector("#$id.Min")
    val max get() = UISelector("#$id.Max")
    val step get() = UISelector("#$id.Step")
    val style get() = UISelector("#$id.Style")
}

// ColorPicker
class ColorPickerRef @PublishedApi internal constructor(id: String) : ElementRef(id) {
    val value get() = UISelector("#$id.Value")
    val format get() = UISelector("#$id.Format")
    val displayTextField get() = UISelector("#$id.DisplayTextField")
    val style get() = UISelector("#$id.Style")
    val resetTransparencyWhenChangingColor get() = UISelector("#$id.ResetTransparencyWhenChangingColor")
}

// ColorPickerDropdownBox
class ColorPickerDropdownBoxRef @PublishedApi internal constructor(id: String) : ElementRef(id) {
    val value get() = UISelector("#$id.Value")
    val format get() = UISelector("#$id.Format")
    val style get() = UISelector("#$id.Style")
}

// ColorOptionGrid
class ColorOptionGridRef @PublishedApi internal constructor(id: String) : ElementRef(id) {
    val value get() = UISelector("#$id.Value")
    val style get() = UISelector("#$id.Style")
}
