package org.deichor.ktaleui.dsl

import org.deichor.ktaleui.element.*

// Helper to add element to UIBuilder (root level)
private fun sanitizeId(id: String?): String? {
    if (id.isNullOrBlank()) return null
    return id.replace(Regex("[^a-zA-Z0-9_\\-]"), "")
        .takeIf { it.isNotEmpty() }
}

private fun <T : UIElement> UIBuilder.addChild(element: T, id: String?, block: T.() -> Unit): T {
    element.id = sanitizeId(id)
    element.block()
    addElement(element)
    return element
}

// Helper to add element to ContainerElement
private fun <T : UIElement> ContainerElement.addChild(element: T, id: String?, block: T.() -> Unit): T {
    element.id = sanitizeId(id)
    element.block()
    children.add(element)
    return element
}

// ============================================================
// Container elements - available on both UIBuilder and ContainerElement
// ============================================================

// Panel
fun UIBuilder.panel(id: String? = null, block: PanelElement.() -> Unit) = addChild(PanelElement(), id, block)
fun ContainerElement.panel(id: String? = null, block: PanelElement.() -> Unit) = addChild(PanelElement(), id, block)

// Group
fun UIBuilder.group(id: String? = null, block: GroupElement.() -> Unit) = addChild(GroupElement(), id, block)
fun ContainerElement.group(id: String? = null, block: GroupElement.() -> Unit) = addChild(GroupElement(), id, block)

// Button
fun UIBuilder.button(id: String? = null, block: ButtonElement.() -> Unit) = addChild(ButtonElement(), id, block)
fun ContainerElement.button(id: String? = null, block: ButtonElement.() -> Unit) = addChild(ButtonElement(), id, block)

// AssetImage
fun UIBuilder.assetImage(id: String? = null, block: AssetImageElement.() -> Unit) = addChild(AssetImageElement(), id, block)
fun ContainerElement.assetImage(id: String? = null, block: AssetImageElement.() -> Unit) = addChild(AssetImageElement(), id, block)

// CheckBoxContainer
fun UIBuilder.checkBoxContainer(id: String? = null, block: CheckBoxContainerElement.() -> Unit) = addChild(CheckBoxContainerElement(), id, block)
fun ContainerElement.checkBoxContainer(id: String? = null, block: CheckBoxContainerElement.() -> Unit) = addChild(CheckBoxContainerElement(), id, block)

// DynamicPane
fun UIBuilder.dynamicPane(id: String? = null, block: DynamicPaneElement.() -> Unit) = addChild(DynamicPaneElement(), id, block)
fun ContainerElement.dynamicPane(id: String? = null, block: DynamicPaneElement.() -> Unit) = addChild(DynamicPaneElement(), id, block)

// DynamicPaneContainer
fun UIBuilder.dynamicPaneContainer(id: String? = null, block: DynamicPaneContainerElement.() -> Unit) = addChild(DynamicPaneContainerElement(), id, block)
fun ContainerElement.dynamicPaneContainer(id: String? = null, block: DynamicPaneContainerElement.() -> Unit) = addChild(DynamicPaneContainerElement(), id, block)

// DropdownBox
fun UIBuilder.dropdownBox(id: String? = null, block: DropdownBoxElement.() -> Unit) = addChild(DropdownBoxElement(), id, block)
fun ContainerElement.dropdownBox(id: String? = null, block: DropdownBoxElement.() -> Unit) = addChild(DropdownBoxElement(), id, block)

// ItemSlotButton
fun UIBuilder.itemSlotButton(id: String? = null, block: ItemSlotButtonElement.() -> Unit) = addChild(ItemSlotButtonElement(), id, block)
fun ContainerElement.itemSlotButton(id: String? = null, block: ItemSlotButtonElement.() -> Unit) = addChild(ItemSlotButtonElement(), id, block)

// MenuItem
fun UIBuilder.menuItem(id: String? = null, block: MenuItemElement.() -> Unit) = addChild(MenuItemElement(), id, block)
fun ContainerElement.menuItem(id: String? = null, block: MenuItemElement.() -> Unit) = addChild(MenuItemElement(), id, block)

// ReorderableList
fun UIBuilder.reorderableList(id: String? = null, block: ReorderableListElement.() -> Unit) = addChild(ReorderableListElement(), id, block)
fun ContainerElement.reorderableList(id: String? = null, block: ReorderableListElement.() -> Unit) = addChild(ReorderableListElement(), id, block)

// ReorderableListGrip
fun UIBuilder.reorderableListGrip(id: String? = null, block: ReorderableListGripElement.() -> Unit) = addChild(ReorderableListGripElement(), id, block)
fun ContainerElement.reorderableListGrip(id: String? = null, block: ReorderableListGripElement.() -> Unit) = addChild(ReorderableListGripElement(), id, block)

// TabNavigation
fun UIBuilder.tabNavigation(id: String? = null, block: TabNavigationElement.() -> Unit) = addChild(TabNavigationElement(), id, block)
fun ContainerElement.tabNavigation(id: String? = null, block: TabNavigationElement.() -> Unit) = addChild(TabNavigationElement(), id, block)

// ToggleButton
fun UIBuilder.toggleButton(id: String? = null, block: ToggleButtonElement.() -> Unit) = addChild(ToggleButtonElement(), id, block)
fun ContainerElement.toggleButton(id: String? = null, block: ToggleButtonElement.() -> Unit) = addChild(ToggleButtonElement(), id, block)

// CharacterPreviewComponent
fun UIBuilder.characterPreviewComponent(id: String? = null, block: CharacterPreviewComponentElement.() -> Unit) = addChild(CharacterPreviewComponentElement(), id, block)
fun ContainerElement.characterPreviewComponent(id: String? = null, block: CharacterPreviewComponentElement.() -> Unit) = addChild(CharacterPreviewComponentElement(), id, block)

// ItemPreviewComponent
fun UIBuilder.itemPreviewComponent(id: String? = null, block: ItemPreviewComponentElement.() -> Unit) = addChild(ItemPreviewComponentElement(), id, block)
fun ContainerElement.itemPreviewComponent(id: String? = null, block: ItemPreviewComponentElement.() -> Unit) = addChild(ItemPreviewComponentElement(), id, block)

// ============================================================
// Leaf elements - available on both UIBuilder and ContainerElement
// ============================================================

// Label
fun UIBuilder.label(id: String? = null, block: LabelElement.() -> Unit) = addChild(LabelElement(), id, block)
fun ContainerElement.label(id: String? = null, block: LabelElement.() -> Unit) = addChild(LabelElement(), id, block)

// ProgressBar
fun UIBuilder.progressBar(id: String? = null, block: ProgressBarElement.() -> Unit) = addChild(ProgressBarElement(), id, block)
fun ContainerElement.progressBar(id: String? = null, block: ProgressBarElement.() -> Unit) = addChild(ProgressBarElement(), id, block)

// CircularProgressBar
fun UIBuilder.circularProgressBar(id: String? = null, block: CircularProgressBarElement.() -> Unit) = addChild(CircularProgressBarElement(), id, block)
fun ContainerElement.circularProgressBar(id: String? = null, block: CircularProgressBarElement.() -> Unit) = addChild(CircularProgressBarElement(), id, block)

// Sprite
fun UIBuilder.sprite(id: String? = null, block: SpriteElement.() -> Unit) = addChild(SpriteElement(), id, block)
fun ContainerElement.sprite(id: String? = null, block: SpriteElement.() -> Unit) = addChild(SpriteElement(), id, block)

// TimerLabel
fun UIBuilder.timerLabel(id: String? = null, block: TimerLabelElement.() -> Unit) = addChild(TimerLabelElement(), id, block)
fun ContainerElement.timerLabel(id: String? = null, block: TimerLabelElement.() -> Unit) = addChild(TimerLabelElement(), id, block)

// HotkeyLabel
fun UIBuilder.hotkeyLabel(id: String? = null, block: HotkeyLabelElement.() -> Unit) = addChild(HotkeyLabelElement(), id, block)
fun ContainerElement.hotkeyLabel(id: String? = null, block: HotkeyLabelElement.() -> Unit) = addChild(HotkeyLabelElement(), id, block)

// SceneBlur
fun UIBuilder.sceneBlur(id: String? = null, block: SceneBlurElement.() -> Unit) = addChild(SceneBlurElement(), id, block)
fun ContainerElement.sceneBlur(id: String? = null, block: SceneBlurElement.() -> Unit) = addChild(SceneBlurElement(), id, block)

// TextField
fun UIBuilder.textField(id: String? = null, block: TextFieldElement.() -> Unit) = addChild(TextFieldElement(), id, block)
fun ContainerElement.textField(id: String? = null, block: TextFieldElement.() -> Unit) = addChild(TextFieldElement(), id, block)

// CompactTextField
fun UIBuilder.compactTextField(id: String? = null, block: CompactTextFieldElement.() -> Unit) = addChild(CompactTextFieldElement(), id, block)
fun ContainerElement.compactTextField(id: String? = null, block: CompactTextFieldElement.() -> Unit) = addChild(CompactTextFieldElement(), id, block)

// MultilineTextField
fun UIBuilder.multilineTextField(id: String? = null, block: MultilineTextFieldElement.() -> Unit) = addChild(MultilineTextFieldElement(), id, block)
fun ContainerElement.multilineTextField(id: String? = null, block: MultilineTextFieldElement.() -> Unit) = addChild(MultilineTextFieldElement(), id, block)

// NumberField
fun UIBuilder.numberField(id: String? = null, block: NumberFieldElement.() -> Unit) = addChild(NumberFieldElement(), id, block)
fun ContainerElement.numberField(id: String? = null, block: NumberFieldElement.() -> Unit) = addChild(NumberFieldElement(), id, block)

// CodeEditor
fun UIBuilder.codeEditor(id: String? = null, block: CodeEditorElement.() -> Unit) = addChild(CodeEditorElement(), id, block)
fun ContainerElement.codeEditor(id: String? = null, block: CodeEditorElement.() -> Unit) = addChild(CodeEditorElement(), id, block)

// CheckBox
fun UIBuilder.checkBox(id: String? = null, block: CheckBoxElement.() -> Unit) = addChild(CheckBoxElement(), id, block)
fun ContainerElement.checkBox(id: String? = null, block: CheckBoxElement.() -> Unit) = addChild(CheckBoxElement(), id, block)

// LabeledCheckBox
fun UIBuilder.labeledCheckBox(id: String? = null, block: LabeledCheckBoxElement.() -> Unit) = addChild(LabeledCheckBoxElement(), id, block)
fun ContainerElement.labeledCheckBox(id: String? = null, block: LabeledCheckBoxElement.() -> Unit) = addChild(LabeledCheckBoxElement(), id, block)

// Slider
fun UIBuilder.slider(id: String? = null, block: SliderElement.() -> Unit) = addChild(SliderElement(), id, block)
fun ContainerElement.slider(id: String? = null, block: SliderElement.() -> Unit) = addChild(SliderElement(), id, block)

// FloatSlider
fun UIBuilder.floatSlider(id: String? = null, block: FloatSliderElement.() -> Unit) = addChild(FloatSliderElement(), id, block)
fun ContainerElement.floatSlider(id: String? = null, block: FloatSliderElement.() -> Unit) = addChild(FloatSliderElement(), id, block)

// SliderNumberField
fun UIBuilder.sliderNumberField(id: String? = null, block: SliderNumberFieldElement.() -> Unit) = addChild(SliderNumberFieldElement(), id, block)
fun ContainerElement.sliderNumberField(id: String? = null, block: SliderNumberFieldElement.() -> Unit) = addChild(SliderNumberFieldElement(), id, block)

// FloatSliderNumberField
fun UIBuilder.floatSliderNumberField(id: String? = null, block: FloatSliderNumberFieldElement.() -> Unit) = addChild(FloatSliderNumberFieldElement(), id, block)
fun ContainerElement.floatSliderNumberField(id: String? = null, block: FloatSliderNumberFieldElement.() -> Unit) = addChild(FloatSliderNumberFieldElement(), id, block)

// ColorPicker
fun UIBuilder.colorPicker(id: String? = null, block: ColorPickerElement.() -> Unit) = addChild(ColorPickerElement(), id, block)
fun ContainerElement.colorPicker(id: String? = null, block: ColorPickerElement.() -> Unit) = addChild(ColorPickerElement(), id, block)

// ColorPickerDropdownBox
fun UIBuilder.colorPickerDropdownBox(id: String? = null, block: ColorPickerDropdownBoxElement.() -> Unit) = addChild(ColorPickerDropdownBoxElement(), id, block)
fun ContainerElement.colorPickerDropdownBox(id: String? = null, block: ColorPickerDropdownBoxElement.() -> Unit) = addChild(ColorPickerDropdownBoxElement(), id, block)

// ColorOptionGrid
fun UIBuilder.colorOptionGrid(id: String? = null, block: ColorOptionGridElement.() -> Unit) = addChild(ColorOptionGridElement(), id, block)
fun ContainerElement.colorOptionGrid(id: String? = null, block: ColorOptionGridElement.() -> Unit) = addChild(ColorOptionGridElement(), id, block)

// TextButton
fun UIBuilder.textButton(id: String? = null, block: TextButtonElement.() -> Unit) = addChild(TextButtonElement(), id, block)
fun ContainerElement.textButton(id: String? = null, block: TextButtonElement.() -> Unit) = addChild(TextButtonElement(), id, block)

// BackButton
fun UIBuilder.backButton(id: String? = null, block: BackButtonElement.() -> Unit) = addChild(BackButtonElement(), id, block)
fun ContainerElement.backButton(id: String? = null, block: BackButtonElement.() -> Unit) = addChild(BackButtonElement(), id, block)

// ActionButton
fun UIBuilder.actionButton(id: String? = null, block: ActionButtonElement.() -> Unit) = addChild(ActionButtonElement(), id, block)
fun ContainerElement.actionButton(id: String? = null, block: ActionButtonElement.() -> Unit) = addChild(ActionButtonElement(), id, block)

// TabButton
fun UIBuilder.tabButton(id: String? = null, block: TabButtonElement.() -> Unit) = addChild(TabButtonElement(), id, block)
fun ContainerElement.tabButton(id: String? = null, block: TabButtonElement.() -> Unit) = addChild(TabButtonElement(), id, block)

// DropdownEntry
fun UIBuilder.dropdownEntry(id: String? = null, block: DropdownEntryElement.() -> Unit) = addChild(DropdownEntryElement(), id, block)
fun ContainerElement.dropdownEntry(id: String? = null, block: DropdownEntryElement.() -> Unit) = addChild(DropdownEntryElement(), id, block)

// ItemGrid
fun UIBuilder.itemGrid(id: String? = null, block: ItemGridElement.() -> Unit) = addChild(ItemGridElement(), id, block)
fun ContainerElement.itemGrid(id: String? = null, block: ItemGridElement.() -> Unit) = addChild(ItemGridElement(), id, block)

// ItemSlot
fun UIBuilder.itemSlot(id: String? = null, block: ItemSlotElement.() -> Unit) = addChild(ItemSlotElement(), id, block)
fun ContainerElement.itemSlot(id: String? = null, block: ItemSlotElement.() -> Unit) = addChild(ItemSlotElement(), id, block)

// ItemIcon
fun UIBuilder.itemIcon(id: String? = null, block: ItemIconElement.() -> Unit) = addChild(ItemIconElement(), id, block)
fun ContainerElement.itemIcon(id: String? = null, block: ItemIconElement.() -> Unit) = addChild(ItemIconElement(), id, block)

// BlockSelector
fun UIBuilder.blockSelector(id: String? = null, block: BlockSelectorElement.() -> Unit) = addChild(BlockSelectorElement(), id, block)
fun ContainerElement.blockSelector(id: String? = null, block: BlockSelectorElement.() -> Unit) = addChild(BlockSelectorElement(), id, block)

// DynamicImage - runtime-loaded image element (serializes as Group)
fun UIBuilder.dynamicImage(id: String? = null, block: DynamicImageElement.() -> Unit) = addChild(DynamicImageElement(), id, block)
fun ContainerElement.dynamicImage(id: String? = null, block: DynamicImageElement.() -> Unit) = addChild(DynamicImageElement(), id, block)
