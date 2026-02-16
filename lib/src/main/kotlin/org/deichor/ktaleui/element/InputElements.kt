package org.deichor.ktaleui.element

import org.deichor.ktaleui.enums.CodeEditorLanguage
import org.deichor.ktaleui.property.*

// TextField
class TextFieldElement : LeafElement("TextField") {
    var value: String? = null
    var placeholderText: String? = null
    var passwordChar: Char? = null
    var style: InputFieldStyle? = null
    var placeholderStyle: InputFieldStyle? = null
    var decoration: InputFieldDecorationStyle? = null
    var autoFocus: Boolean? = null
    var autoSelectAll: Boolean? = null
    var isReadOnly: Boolean? = null
    var maxLength: Int? = null

    fun onRightClick(handler: EventHandler) { eventHandlers["RightClicking"] = handler }
    fun onValidating(handler: EventHandler) { eventHandlers["Validating"] = handler }
    fun onDismissing(handler: EventHandler) { eventHandlers["Dismissing"] = handler }
    fun onFocusLost(handler: EventHandler) { eventHandlers["FocusLost"] = handler }
    fun onFocusGained(handler: EventHandler) { eventHandlers["FocusGained"] = handler }
    fun onValueChanged(handler: EventHandler) { eventHandlers["ValueChanged"] = handler }

    override fun collectProperties(): Map<String, Any?> {
        return super.collectProperties() + buildMap {
            value?.let { put("Value", it) }
            placeholderText?.let { put("PlaceholderText", it) }
            passwordChar?.let { put("PasswordChar", it) }
            style?.let { put("Style", it) }
            placeholderStyle?.let { put("PlaceholderStyle", it) }
            decoration?.let { put("Decoration", it) }
            autoFocus?.let { put("AutoFocus", it) }
            autoSelectAll?.let { put("AutoSelectAll", it) }
            isReadOnly?.let { put("IsReadOnly", it) }
            maxLength?.let { put("MaxLength", it) }
        }
    }
}

// CompactTextField - same as TextField
class CompactTextFieldElement : LeafElement("CompactTextField") {
    var value: String? = null
    var placeholderText: String? = null
    var passwordChar: Char? = null
    var style: InputFieldStyle? = null
    var placeholderStyle: InputFieldStyle? = null
    var decoration: InputFieldDecorationStyle? = null
    var autoFocus: Boolean? = null
    var autoSelectAll: Boolean? = null
    var isReadOnly: Boolean? = null
    var maxLength: Int? = null

    fun onRightClick(handler: EventHandler) { eventHandlers["RightClicking"] = handler }
    fun onValidating(handler: EventHandler) { eventHandlers["Validating"] = handler }
    fun onDismissing(handler: EventHandler) { eventHandlers["Dismissing"] = handler }
    fun onFocusLost(handler: EventHandler) { eventHandlers["FocusLost"] = handler }
    fun onFocusGained(handler: EventHandler) { eventHandlers["FocusGained"] = handler }
    fun onValueChanged(handler: EventHandler) { eventHandlers["ValueChanged"] = handler }

    override fun collectProperties(): Map<String, Any?> {
        return super.collectProperties() + buildMap {
            value?.let { put("Value", it) }
            placeholderText?.let { put("PlaceholderText", it) }
            passwordChar?.let { put("PasswordChar", it) }
            style?.let { put("Style", it) }
            placeholderStyle?.let { put("PlaceholderStyle", it) }
            decoration?.let { put("Decoration", it) }
            autoFocus?.let { put("AutoFocus", it) }
            autoSelectAll?.let { put("AutoSelectAll", it) }
            isReadOnly?.let { put("IsReadOnly", it) }
            maxLength?.let { put("MaxLength", it) }
        }
    }
}

// MultilineTextField
class MultilineTextFieldElement : LeafElement("MultilineTextField") {
    var scrollbarStyle: ScrollbarStyle? = null
    var value: String? = null
    var style: InputFieldStyle? = null
    var placeholderStyle: InputFieldStyle? = null
    var decoration: InputFieldDecorationStyle? = null
    var autoFocus: Boolean? = null
    var autoSelectAll: Boolean? = null
    var isReadOnly: Boolean? = null
    var maxLength: Int? = null
    var maxLines: Int? = null
    var maxVisibleLines: Int? = null
    var autoGrow: Boolean? = null
    var contentPadding: Padding? = null
    var placeholderText: String? = null

    fun onValidating(handler: EventHandler) { eventHandlers["Validating"] = handler }
    fun onDismissing(handler: EventHandler) { eventHandlers["Dismissing"] = handler }
    fun onBlurred(handler: EventHandler) { eventHandlers["Blurred"] = handler }
    fun onFocused(handler: EventHandler) { eventHandlers["Focused"] = handler }
    fun onValueChanged(handler: EventHandler) { eventHandlers["ValueChanged"] = handler }

    override fun collectProperties(): Map<String, Any?> {
        return super.collectProperties() + buildMap {
            scrollbarStyle?.let { put("ScrollbarStyle", it) }
            value?.let { put("Value", it) }
            style?.let { put("Style", it) }
            placeholderStyle?.let { put("PlaceholderStyle", it) }
            decoration?.let { put("Decoration", it) }
            autoFocus?.let { put("AutoFocus", it) }
            autoSelectAll?.let { put("AutoSelectAll", it) }
            isReadOnly?.let { put("IsReadOnly", it) }
            maxLength?.let { put("MaxLength", it) }
            maxLines?.let { put("MaxLines", it) }
            maxVisibleLines?.let { put("MaxVisibleLines", it) }
            autoGrow?.let { put("AutoGrow", it) }
            contentPadding?.let { put("ContentPadding", it) }
            placeholderText?.let { put("PlaceholderText", it) }
        }
    }
}

// NumberField
class NumberFieldElement : LeafElement("NumberField") {
    var value: Double? = null
    var passwordChar: Char? = null
    var format: NumberFieldFormat? = null
    var style: InputFieldStyle? = null
    var placeholderStyle: InputFieldStyle? = null
    var decoration: InputFieldDecorationStyle? = null
    var autoFocus: Boolean? = null
    var autoSelectAll: Boolean? = null
    var isReadOnly: Boolean? = null
    var maxLength: Int? = null

    fun onRightClick(handler: EventHandler) { eventHandlers["RightClicking"] = handler }
    fun onValidating(handler: EventHandler) { eventHandlers["Validating"] = handler }
    fun onDismissing(handler: EventHandler) { eventHandlers["Dismissing"] = handler }
    fun onFocusLost(handler: EventHandler) { eventHandlers["FocusLost"] = handler }
    fun onFocusGained(handler: EventHandler) { eventHandlers["FocusGained"] = handler }
    fun onValueChanged(handler: EventHandler) { eventHandlers["ValueChanged"] = handler }

    override fun collectProperties(): Map<String, Any?> {
        return super.collectProperties() + buildMap {
            value?.let { put("Value", it) }
            passwordChar?.let { put("PasswordChar", it) }
            format?.let { put("Format", it) }
            style?.let { put("Style", it) }
            placeholderStyle?.let { put("PlaceholderStyle", it) }
            decoration?.let { put("Decoration", it) }
            autoFocus?.let { put("AutoFocus", it) }
            autoSelectAll?.let { put("AutoSelectAll", it) }
            isReadOnly?.let { put("IsReadOnly", it) }
            maxLength?.let { put("MaxLength", it) }
        }
    }
}

// CodeEditor
class CodeEditorElement : LeafElement("CodeEditor") {
    var scrollbarStyle: ScrollbarStyle? = null
    var value: String? = null
    var language: CodeEditorLanguage? = null
    var lineNumberWidth: Int? = null
    var lineNumberBackground: Any? = null // PatchStyle or String
    var lineNumberTextColor: String? = null
    var lineNumberPadding: Int? = null
    var style: InputFieldStyle? = null
    var placeholderStyle: InputFieldStyle? = null
    var decoration: InputFieldDecorationStyle? = null
    var autoFocus: Boolean? = null
    var autoSelectAll: Boolean? = null
    var isReadOnly: Boolean? = null
    var maxLength: Int? = null
    var maxLines: Int? = null
    var maxVisibleLines: Int? = null
    var autoGrow: Boolean? = null
    var contentPadding: Padding? = null
    var placeholderText: String? = null

    fun onValidating(handler: EventHandler) { eventHandlers["Validating"] = handler }
    fun onDismissing(handler: EventHandler) { eventHandlers["Dismissing"] = handler }
    fun onBlurred(handler: EventHandler) { eventHandlers["Blurred"] = handler }
    fun onFocused(handler: EventHandler) { eventHandlers["Focused"] = handler }
    fun onValueChanged(handler: EventHandler) { eventHandlers["ValueChanged"] = handler }

    override fun collectProperties(): Map<String, Any?> {
        return super.collectProperties() + buildMap {
            scrollbarStyle?.let { put("ScrollbarStyle", it) }
            value?.let { put("Value", it) }
            language?.let { put("Language", it) }
            lineNumberWidth?.let { put("LineNumberWidth", it) }
            lineNumberBackground?.let { put("LineNumberBackground", it) }
            lineNumberTextColor?.let { put("LineNumberTextColor", it) }
            lineNumberPadding?.let { put("LineNumberPadding", it) }
            style?.let { put("Style", it) }
            placeholderStyle?.let { put("PlaceholderStyle", it) }
            decoration?.let { put("Decoration", it) }
            autoFocus?.let { put("AutoFocus", it) }
            autoSelectAll?.let { put("AutoSelectAll", it) }
            isReadOnly?.let { put("IsReadOnly", it) }
            maxLength?.let { put("MaxLength", it) }
            maxLines?.let { put("MaxLines", it) }
            maxVisibleLines?.let { put("MaxVisibleLines", it) }
            autoGrow?.let { put("AutoGrow", it) }
            contentPadding?.let { put("ContentPadding", it) }
            placeholderText?.let { put("PlaceholderText", it) }
        }
    }
}
