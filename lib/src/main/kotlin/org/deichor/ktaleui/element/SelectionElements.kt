package org.deichor.ktaleui.element

import org.deichor.ktaleui.enums.ColorFormat
import org.deichor.ktaleui.property.*

// CheckBox
class CheckBoxElement : LeafElement("CheckBox") {
    var value: Boolean? = null
    var disabled: Boolean? = null
    var style: CheckBoxStyle? = null

    fun onValueChanged(handler: EventHandler) { eventHandlers["ValueChanged"] = handler }

    override fun collectProperties(): Map<String, Any?> {
        return super.collectProperties() + buildMap {
            value?.let { put("Value", it) }
            disabled?.let { put("Disabled", it) }
            style?.let { put("Style", it) }
        }
    }
}

// LabeledCheckBox
class LabeledCheckBoxElement : LeafElement("LabeledCheckBox") {
    var value: Boolean? = null
    var disabled: Boolean? = null
    var style: LabeledCheckBoxStyle? = null

    fun onValueChanged(handler: EventHandler) { eventHandlers["ValueChanged"] = handler }

    override fun collectProperties(): Map<String, Any?> {
        return super.collectProperties() + buildMap {
            value?.let { put("Value", it) }
            disabled?.let { put("Disabled", it) }
            style?.let { put("Style", it) }
        }
    }
}

// Slider
class SliderElement : LeafElement("Slider") {
    var value: Int? = null
    var min: Int? = null
    var max: Int? = null
    var step: Int? = null
    var isReadOnly: Boolean? = null
    var style: SliderStyle? = null

    fun onMouseButtonReleased(handler: EventHandler) { eventHandlers["MouseButtonReleased"] = handler }
    fun onValueChanged(handler: EventHandler) { eventHandlers["ValueChanged"] = handler }

    override fun collectProperties(): Map<String, Any?> {
        return super.collectProperties() + buildMap {
            value?.let { put("Value", it) }
            min?.let { put("Min", it) }
            max?.let { put("Max", it) }
            step?.let { put("Step", it) }
            isReadOnly?.let { put("IsReadOnly", it) }
            style?.let { put("Style", it) }
        }
    }
}

// FloatSlider
class FloatSliderElement : LeafElement("FloatSlider") {
    var value: Float? = null
    var min: Float? = null
    var max: Float? = null
    var step: Float? = null
    var style: SliderStyle? = null

    fun onMouseButtonReleased(handler: EventHandler) { eventHandlers["MouseButtonReleased"] = handler }
    fun onValueChanged(handler: EventHandler) { eventHandlers["ValueChanged"] = handler }

    override fun collectProperties(): Map<String, Any?> {
        return super.collectProperties() + buildMap {
            value?.let { put("Value", it) }
            min?.let { put("Min", it) }
            max?.let { put("Max", it) }
            step?.let { put("Step", it) }
            style?.let { put("Style", it) }
        }
    }
}

// SliderNumberField
class SliderNumberFieldElement : LeafElement("SliderNumberField") {
    var value: Int? = null
    var min: Int? = null
    var max: Int? = null
    var step: Int? = null
    var isReadOnly: Boolean? = null
    var style: SliderStyle? = null

    fun onMouseButtonReleased(handler: EventHandler) { eventHandlers["MouseButtonReleased"] = handler }
    fun onValueChanged(handler: EventHandler) { eventHandlers["ValueChanged"] = handler }

    override fun collectProperties(): Map<String, Any?> {
        return super.collectProperties() + buildMap {
            value?.let { put("Value", it) }
            min?.let { put("Min", it) }
            max?.let { put("Max", it) }
            step?.let { put("Step", it) }
            isReadOnly?.let { put("IsReadOnly", it) }
            style?.let { put("Style", it) }
        }
    }
}

// FloatSliderNumberField
class FloatSliderNumberFieldElement : LeafElement("FloatSliderNumberField") {
    var value: Float? = null
    var min: Float? = null
    var max: Float? = null
    var step: Float? = null
    var style: SliderStyle? = null

    fun onMouseButtonReleased(handler: EventHandler) { eventHandlers["MouseButtonReleased"] = handler }
    fun onValueChanged(handler: EventHandler) { eventHandlers["ValueChanged"] = handler }

    override fun collectProperties(): Map<String, Any?> {
        return super.collectProperties() + buildMap {
            value?.let { put("Value", it) }
            min?.let { put("Min", it) }
            max?.let { put("Max", it) }
            step?.let { put("Step", it) }
            style?.let { put("Style", it) }
        }
    }
}

// ColorPicker
class ColorPickerElement : LeafElement("ColorPicker") {
    var value: String? = null
    var format: ColorFormat? = null
    var displayTextField: Boolean? = null
    var style: ColorPickerStyle? = null
    var resetTransparencyWhenChangingColor: Boolean? = null

    fun onValueChanged(handler: EventHandler) { eventHandlers["ValueChanged"] = handler }

    override fun collectProperties(): Map<String, Any?> {
        return super.collectProperties() + buildMap {
            value?.let { put("Value", it) }
            format?.let { put("Format", it) }
            displayTextField?.let { put("DisplayTextField", it) }
            style?.let { put("Style", it) }
            resetTransparencyWhenChangingColor?.let { put("ResetTransparencyWhenChangingColor", it) }
        }
    }
}

// ColorPickerDropdownBox
class ColorPickerDropdownBoxElement : LeafElement("ColorPickerDropdownBox") {
    var value: String? = null
    var format: ColorFormat? = null
    var style: ColorPickerDropdownBoxStyle? = null

    fun onValueChanged(handler: EventHandler) { eventHandlers["ValueChanged"] = handler }

    override fun collectProperties(): Map<String, Any?> {
        return super.collectProperties() + buildMap {
            value?.let { put("Value", it) }
            format?.let { put("Format", it) }
            style?.let { put("Style", it) }
        }
    }
}

// ColorOptionGrid
class ColorOptionGridElement : LeafElement("ColorOptionGrid") {
    var value: String? = null
    var style: ColorOptionGridStyle? = null

    fun onValueChanged(handler: EventHandler) { eventHandlers["ValueChanged"] = handler }

    override fun collectProperties(): Map<String, Any?> {
        return super.collectProperties() + buildMap {
            value?.let { put("Value", it) }
            style?.let { put("Style", it) }
        }
    }
}
