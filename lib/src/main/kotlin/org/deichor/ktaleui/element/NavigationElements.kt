package org.deichor.ktaleui.element

import org.deichor.ktaleui.enums.ActionButtonAlignment
import org.deichor.ktaleui.property.*

// TextButton
class TextButtonElement : LeafElement("TextButton") {
    var text: String? = null
    var textSpans: List<LabelSpan>? = null
    var disabled: Boolean? = null
    var style: TextButtonStyle? = null

    fun onClick(handler: EventHandler) { eventHandlers["Activating"] = handler }
    fun onDoubleClick(handler: EventHandler) { eventHandlers["DoubleClicking"] = handler }
    fun onRightClick(handler: EventHandler) { eventHandlers["RightClicking"] = handler }
    fun onMouseEntered(handler: EventHandler) { eventHandlers["MouseEntered"] = handler }
    fun onMouseExited(handler: EventHandler) { eventHandlers["MouseExited"] = handler }

    override fun collectProperties(): Map<String, Any?> {
        return super.collectProperties() + buildMap {
            text?.let { put("Text", it) }
            textSpans?.let { put("TextSpans", it) }
            disabled?.let { put("Disabled", it) }
            style?.let { put("Style", it) }
        }
    }
}

// BackButton
class BackButtonElement : LeafElement("BackButton") {
    var disabled: Boolean? = null
    var style: ButtonStyle? = null

    fun onClick(handler: EventHandler) { eventHandlers["Activating"] = handler }
    fun onDoubleClick(handler: EventHandler) { eventHandlers["DoubleClicking"] = handler }
    fun onRightClick(handler: EventHandler) { eventHandlers["RightClicking"] = handler }
    fun onMouseEntered(handler: EventHandler) { eventHandlers["MouseEntered"] = handler }
    fun onMouseExited(handler: EventHandler) { eventHandlers["MouseExited"] = handler }

    override fun collectProperties(): Map<String, Any?> {
        return super.collectProperties() + buildMap {
            disabled?.let { put("Disabled", it) }
            style?.let { put("Style", it) }
        }
    }
}

// ActionButton
class ActionButtonElement : LeafElement("ActionButton") {
    var actionName: String? = null
    var keyBindingLabel: String? = null
    var bindingModifier1Label: String? = null
    var bindingModifier2Label: String? = null
    var isAvailable: Boolean? = null
    var isHoldBinding: Boolean? = null
    var alignment: ActionButtonAlignment? = null
    var disabled: Boolean? = null
    var style: ButtonStyle? = null

    fun onClick(handler: EventHandler) { eventHandlers["Activating"] = handler }
    fun onDoubleClick(handler: EventHandler) { eventHandlers["DoubleClicking"] = handler }
    fun onRightClick(handler: EventHandler) { eventHandlers["RightClicking"] = handler }
    fun onMouseEntered(handler: EventHandler) { eventHandlers["MouseEntered"] = handler }
    fun onMouseExited(handler: EventHandler) { eventHandlers["MouseExited"] = handler }

    override fun collectProperties(): Map<String, Any?> {
        return super.collectProperties() + buildMap {
            actionName?.let { put("ActionName", it) }
            keyBindingLabel?.let { put("KeyBindingLabel", it) }
            bindingModifier1Label?.let { put("BindingModifier1Label", it) }
            bindingModifier2Label?.let { put("BindingModifier2Label", it) }
            isAvailable?.let { put("IsAvailable", it) }
            isHoldBinding?.let { put("IsHoldBinding", it) }
            alignment?.let { put("Alignment", it) }
            disabled?.let { put("Disabled", it) }
            style?.let { put("Style", it) }
        }
    }
}

// TabButton
class TabButtonElement : LeafElement("TabButton") {
    var disabled: Boolean? = null
    var style: ButtonStyle? = null

    fun onClick(handler: EventHandler) { eventHandlers["Activating"] = handler }
    fun onDoubleClick(handler: EventHandler) { eventHandlers["DoubleClicking"] = handler }
    fun onRightClick(handler: EventHandler) { eventHandlers["RightClicking"] = handler }
    fun onMouseEntered(handler: EventHandler) { eventHandlers["MouseEntered"] = handler }
    fun onMouseExited(handler: EventHandler) { eventHandlers["MouseExited"] = handler }

    override fun collectProperties(): Map<String, Any?> {
        return super.collectProperties() + buildMap {
            disabled?.let { put("Disabled", it) }
            style?.let { put("Style", it) }
        }
    }
}

// DropdownEntry
class DropdownEntryElement : LeafElement("DropdownEntry") {
    var value: String? = null
    var text: String? = null
    var iconTexturePath: String? = null

    override fun collectProperties(): Map<String, Any?> {
        return super.collectProperties() + buildMap {
            value?.let { put("Value", it) }
            text?.let { put("Text", it) }
            iconTexturePath?.let { put("IconTexturePath", it) }
        }
    }
}
