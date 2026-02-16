package org.deichor.ktaleui.element

import org.deichor.ktaleui.enums.ResizeType
import org.deichor.ktaleui.property.*

// Panel - basic container with layout
class PanelElement : ContainerElement("Panel") {
    fun onValidating(handler: EventHandler) { eventHandlers["Validating"] = handler }
    fun onDismissing(handler: EventHandler) { eventHandlers["Dismissing"] = handler }
    fun onScrolled(handler: EventHandler) { eventHandlers["Scrolled"] = handler }
}

// Group - same as Panel
class GroupElement : ContainerElement("Group") {
    fun onValidating(handler: EventHandler) { eventHandlers["Validating"] = handler }
    fun onDismissing(handler: EventHandler) { eventHandlers["Dismissing"] = handler }
    fun onScrolled(handler: EventHandler) { eventHandlers["Scrolled"] = handler }
}

// Button - clickable container
class ButtonElement : ContainerElement("Button") {
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

// AssetImage - displays an asset image, accepts children
class AssetImageElement : ContainerElement("AssetImage") {
    var assetPath: String? = null

    override fun collectProperties(): Map<String, Any?> {
        return super.collectProperties() + buildMap {
            assetPath?.let { put("AssetPath", it) }
        }
    }
}

// CheckBoxContainer - container variant of checkbox
class CheckBoxContainerElement : ContainerElement("CheckBoxContainer") {
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

// DynamicPane - resizable pane
class DynamicPaneElement : ContainerElement("DynamicPane") {
    var minSize: Int? = null
    var resizeAt: ResizeType? = null
    var resizerSize: Int? = null
    var resizerBackground: Any? = null // PatchStyle or String

    fun onMouseButtonReleased(handler: EventHandler) { eventHandlers["MouseButtonReleased"] = handler }
    fun onValidating(handler: EventHandler) { eventHandlers["Validating"] = handler }
    fun onDismissing(handler: EventHandler) { eventHandlers["Dismissing"] = handler }
    fun onScrolled(handler: EventHandler) { eventHandlers["Scrolled"] = handler }

    override fun collectProperties(): Map<String, Any?> {
        return super.collectProperties() + buildMap {
            minSize?.let { put("MinSize", it) }
            resizeAt?.let { put("ResizeAt", it) }
            resizerSize?.let { put("ResizerSize", it) }
            resizerBackground?.let { put("ResizerBackground", it) }
        }
    }
}

// DynamicPaneContainer - container for DynamicPanes
class DynamicPaneContainerElement : ContainerElement("DynamicPaneContainer")

// DropdownBox - dropdown selector with children
class DropdownBoxElement : ContainerElement("DropdownBox") {
    var value: String? = null
    var selectedValues: List<String>? = null
    var disabled: Boolean? = null
    var style: DropdownBoxStyle? = null
    var panelTitleText: String? = null
    var isReadOnly: Boolean? = null
    var maxSelection: Int? = null
    var showSearchInput: Boolean? = null
    var showLabel: Boolean? = null
    var forcedLabel: String? = null
    var noItemsText: String? = null
    var displayNonExistingValue: Boolean? = null

    fun onValueChanged(handler: EventHandler) { eventHandlers["ValueChanged"] = handler }
    fun onDropdownToggled(handler: EventHandler) { eventHandlers["DropdownToggled"] = handler }

    override fun collectProperties(): Map<String, Any?> {
        return super.collectProperties() + buildMap {
            value?.let { put("Value", it) }
            selectedValues?.let { put("SelectedValues", it) }
            disabled?.let { put("Disabled", it) }
            style?.let { put("Style", it) }
            panelTitleText?.let { put("PanelTitleText", it) }
            isReadOnly?.let { put("IsReadOnly", it) }
            maxSelection?.let { put("MaxSelection", it) }
            showSearchInput?.let { put("ShowSearchInput", it) }
            showLabel?.let { put("ShowLabel", it) }
            forcedLabel?.let { put("ForcedLabel", it) }
            noItemsText?.let { put("NoItemsText", it) }
            displayNonExistingValue?.let { put("DisplayNonExistingValue", it) }
        }
    }
}

// ItemSlotButton - button with auto item tooltip handling
class ItemSlotButtonElement : ContainerElement("ItemSlotButton") {
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

// MenuItem - menu item with text and popup
class MenuItemElement : ContainerElement("MenuItem") {
    var text: String? = null
    var textSpans: List<LabelSpan>? = null
    var popupStyle: PopupStyle? = null
    var selectedStyle: TextButtonStyle? = null
    var isSelected: Boolean? = null
    var iconAnchor: Anchor? = null
    var icon: Any? = null // PatchStyle or String
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
            popupStyle?.let { put("PopupStyle", it) }
            selectedStyle?.let { put("SelectedStyle", it) }
            isSelected?.let { put("IsSelected", it) }
            iconAnchor?.let { put("IconAnchor", it) }
            icon?.let { put("Icon", it) }
            disabled?.let { put("Disabled", it) }
            style?.let { put("Style", it) }
        }
    }
}

// ReorderableList
class ReorderableListElement : ContainerElement("ReorderableList") {
    fun onValidating(handler: EventHandler) { eventHandlers["Validating"] = handler }
    fun onDismissing(handler: EventHandler) { eventHandlers["Dismissing"] = handler }
    fun onScrolled(handler: EventHandler) { eventHandlers["Scrolled"] = handler }
}

// ReorderableListGrip
class ReorderableListGripElement : ContainerElement("ReorderableListGrip")

// TabNavigation - tab-based navigation container
class TabNavigationElement : ContainerElement("TabNavigation") {
    var tabs: List<Tab>? = null
    var style: TabNavigationStyle? = null
    var selectedTab: String? = null
    var allowUnselection: Boolean? = null

    fun onSelectedTabChanged(handler: EventHandler) { eventHandlers["SelectedTabChanged"] = handler }

    override fun collectProperties(): Map<String, Any?> {
        return super.collectProperties() + buildMap {
            tabs?.let { put("Tabs", it) }
            style?.let { put("Style", it) }
            selectedTab?.let { put("SelectedTab", it) }
            allowUnselection?.let { put("AllowUnselection", it) }
        }
    }
}

// ToggleButton - toggle container
class ToggleButtonElement : ContainerElement("ToggleButton") {
    var isChecked: Boolean? = null
    var checkedStyle: ToggleButtonStyle? = null
    var disabled: Boolean? = null
    var style: ToggleButtonStyle? = null

    fun onValueChanged(handler: EventHandler) { eventHandlers["ValueChanged"] = handler }
    fun onClick(handler: EventHandler) { eventHandlers["Activating"] = handler }
    fun onDoubleClick(handler: EventHandler) { eventHandlers["DoubleClicking"] = handler }
    fun onRightClick(handler: EventHandler) { eventHandlers["RightClicking"] = handler }
    fun onMouseEntered(handler: EventHandler) { eventHandlers["MouseEntered"] = handler }
    fun onMouseExited(handler: EventHandler) { eventHandlers["MouseExited"] = handler }

    override fun collectProperties(): Map<String, Any?> {
        return super.collectProperties() + buildMap {
            isChecked?.let { put("IsChecked", it) }
            checkedStyle?.let { put("CheckedStyle", it) }
            disabled?.let { put("Disabled", it) }
            style?.let { put("Style", it) }
        }
    }
}

// CharacterPreviewComponent
class CharacterPreviewComponentElement : ContainerElement("CharacterPreviewComponent")

// ItemPreviewComponent
class ItemPreviewComponentElement : ContainerElement("ItemPreviewComponent")
