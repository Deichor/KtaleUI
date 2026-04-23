package org.deichor.ktaleui.ref

// Panel
class PanelRef @PublishedApi internal constructor(id: String) : ContainerRef(id)

// Group
class GroupRef @PublishedApi internal constructor(id: String) : ContainerRef(id)

// Button
class ButtonRef @PublishedApi internal constructor(id: String) : ContainerRef(id) {
    val disabled get() = UISelector("#$id.Disabled")
    val style get() = UISelector("#$id.Style")
}

// AssetImage
class AssetImageRef @PublishedApi internal constructor(id: String) : ContainerRef(id) {
    val assetPath get() = UISelector("#$id.AssetPath")
}

// CheckBoxContainer
class CheckBoxContainerRef @PublishedApi internal constructor(id: String) : ContainerRef(id) {
    val value get() = UISelector("#$id.Value")
    val disabled get() = UISelector("#$id.Disabled")
    val style get() = UISelector("#$id.Style")
}

// DynamicPane
class DynamicPaneRef @PublishedApi internal constructor(id: String) : ContainerRef(id) {
    val minSize get() = UISelector("#$id.MinSize")
    val resizeAt get() = UISelector("#$id.ResizeAt")
    val resizerSize get() = UISelector("#$id.ResizerSize")
    val resizerBackground get() = UISelector("#$id.ResizerBackground")
}

// DynamicPaneContainer
class DynamicPaneContainerRef @PublishedApi internal constructor(id: String) : ContainerRef(id)

// DropdownBox
class DropdownBoxRef @PublishedApi internal constructor(id: String) : ContainerRef(id) {
    val value get() = UISelector("#$id.Value")
    val selectedValues get() = UISelector("#$id.SelectedValues")
    val disabled get() = UISelector("#$id.Disabled")
    val style get() = UISelector("#$id.Style")
    val panelTitleText get() = UISelector("#$id.PanelTitleText")
    val isReadOnly get() = UISelector("#$id.IsReadOnly")
    val maxSelection get() = UISelector("#$id.MaxSelection")
    val showSearchInput get() = UISelector("#$id.ShowSearchInput")
    val showLabel get() = UISelector("#$id.ShowLabel")
    val forcedLabel get() = UISelector("#$id.ForcedLabel")
    val noItemsText get() = UISelector("#$id.NoItemsText")
    val displayNonExistingValue get() = UISelector("#$id.DisplayNonExistingValue")
}

// ItemSlotButton
class ItemSlotButtonRef @PublishedApi internal constructor(id: String) : ContainerRef(id) {
    val disabled get() = UISelector("#$id.Disabled")
    val style get() = UISelector("#$id.Style")
}

// MenuItem
class MenuItemRef @PublishedApi internal constructor(id: String) : ContainerRef(id) {
    val text get() = UISelector("#$id.Text")
    val textSpans get() = UISelector("#$id.TextSpans")
    val popupStyle get() = UISelector("#$id.PopupStyle")
    val selectedStyle get() = UISelector("#$id.SelectedStyle")
    val isSelected get() = UISelector("#$id.IsSelected")
    val iconAnchor get() = UISelector("#$id.IconAnchor")
    val icon get() = UISelector("#$id.Icon")
    val disabled get() = UISelector("#$id.Disabled")
    val style get() = UISelector("#$id.Style")
}

// ReorderableList
class ReorderableListRef @PublishedApi internal constructor(id: String) : ContainerRef(id)

// ReorderableListGrip
class ReorderableListGripRef @PublishedApi internal constructor(id: String) : ContainerRef(id)

// TabNavigation
class TabNavigationRef @PublishedApi internal constructor(id: String) : ContainerRef(id) {
    val tabs get() = UISelector("#$id.Tabs")
    val style get() = UISelector("#$id.Style")
    val selectedTab get() = UISelector("#$id.SelectedTab")
    val allowUnselection get() = UISelector("#$id.AllowUnselection")
}

// ToggleButton
class ToggleButtonRef @PublishedApi internal constructor(id: String) : ContainerRef(id) {
    val isChecked get() = UISelector("#$id.IsChecked")
    val checkedStyle get() = UISelector("#$id.CheckedStyle")
    val disabled get() = UISelector("#$id.Disabled")
    val style get() = UISelector("#$id.Style")
}

// CharacterPreviewComponent
class CharacterPreviewComponentRef @PublishedApi internal constructor(id: String) : ContainerRef(id)

// ItemPreviewComponent
class ItemPreviewComponentRef @PublishedApi internal constructor(id: String) : ContainerRef(id)

// DynamicImage (serializes as Group, used with setDynamicImage)
class DynamicImageRef @PublishedApi internal constructor(id: String) : ContainerRef(id)
