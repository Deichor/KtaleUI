package org.deichor.ktaleui.dsl

import org.deichor.ktaleui.element.*
import org.deichor.ktaleui.ref.*

// Helper to sanitize IDs
private fun sanitizeId(id: String?): String? {
    if (id.isNullOrBlank()) return null
    return id.replace(Regex("[^a-zA-Z0-9_\\-]"), "")
        .takeIf { it.isNotEmpty() }
}

// Internal helpers to add element and set ID
private fun <T : UIElement> UIBuilder.addChild(element: T, id: String?, block: T.() -> Unit): T {
    element.id = sanitizeId(id)
    element.block()
    addElement(element)
    return element
}

private fun <T : UIElement> ContainerElement.addChild(element: T, id: String?, block: T.() -> Unit): T {
    element.id = sanitizeId(id)
    element.block()
    children.add(element)
    return element
}

// ============================================================
// Container elements
// ============================================================

// Panel
fun UIBuilder.panel(id: String, block: PanelElement.() -> Unit): PanelRef { addChild(PanelElement(), id, block); return PanelRef(sanitizeId(id)!!) }
fun UIBuilder.panel(block: PanelElement.() -> Unit) = addChild(PanelElement(), null, block)
fun ContainerElement.panel(id: String, block: PanelElement.() -> Unit): PanelRef { addChild(PanelElement(), id, block); return PanelRef(sanitizeId(id)!!) }
fun ContainerElement.panel(block: PanelElement.() -> Unit) = addChild(PanelElement(), null, block)

// Group
fun UIBuilder.group(id: String, block: GroupElement.() -> Unit): GroupRef { addChild(GroupElement(), id, block); return GroupRef(sanitizeId(id)!!) }
fun UIBuilder.group(block: GroupElement.() -> Unit) = addChild(GroupElement(), null, block)
fun ContainerElement.group(id: String, block: GroupElement.() -> Unit): GroupRef { addChild(GroupElement(), id, block); return GroupRef(sanitizeId(id)!!) }
fun ContainerElement.group(block: GroupElement.() -> Unit) = addChild(GroupElement(), null, block)

// Button
fun UIBuilder.button(id: String, block: ButtonElement.() -> Unit): ButtonRef { addChild(ButtonElement(), id, block); return ButtonRef(sanitizeId(id)!!) }
fun UIBuilder.button(block: ButtonElement.() -> Unit) = addChild(ButtonElement(), null, block)
fun ContainerElement.button(id: String, block: ButtonElement.() -> Unit): ButtonRef { addChild(ButtonElement(), id, block); return ButtonRef(sanitizeId(id)!!) }
fun ContainerElement.button(block: ButtonElement.() -> Unit) = addChild(ButtonElement(), null, block)

// AssetImage
fun UIBuilder.assetImage(id: String, block: AssetImageElement.() -> Unit): AssetImageRef { addChild(AssetImageElement(), id, block); return AssetImageRef(sanitizeId(id)!!) }
fun UIBuilder.assetImage(block: AssetImageElement.() -> Unit) = addChild(AssetImageElement(), null, block)
fun ContainerElement.assetImage(id: String, block: AssetImageElement.() -> Unit): AssetImageRef { addChild(AssetImageElement(), id, block); return AssetImageRef(sanitizeId(id)!!) }
fun ContainerElement.assetImage(block: AssetImageElement.() -> Unit) = addChild(AssetImageElement(), null, block)

// CheckBoxContainer
fun UIBuilder.checkBoxContainer(id: String, block: CheckBoxContainerElement.() -> Unit): CheckBoxContainerRef { addChild(CheckBoxContainerElement(), id, block); return CheckBoxContainerRef(sanitizeId(id)!!) }
fun UIBuilder.checkBoxContainer(block: CheckBoxContainerElement.() -> Unit) = addChild(CheckBoxContainerElement(), null, block)
fun ContainerElement.checkBoxContainer(id: String, block: CheckBoxContainerElement.() -> Unit): CheckBoxContainerRef { addChild(CheckBoxContainerElement(), id, block); return CheckBoxContainerRef(sanitizeId(id)!!) }
fun ContainerElement.checkBoxContainer(block: CheckBoxContainerElement.() -> Unit) = addChild(CheckBoxContainerElement(), null, block)

// DynamicPane
fun UIBuilder.dynamicPane(id: String, block: DynamicPaneElement.() -> Unit): DynamicPaneRef { addChild(DynamicPaneElement(), id, block); return DynamicPaneRef(sanitizeId(id)!!) }
fun UIBuilder.dynamicPane(block: DynamicPaneElement.() -> Unit) = addChild(DynamicPaneElement(), null, block)
fun ContainerElement.dynamicPane(id: String, block: DynamicPaneElement.() -> Unit): DynamicPaneRef { addChild(DynamicPaneElement(), id, block); return DynamicPaneRef(sanitizeId(id)!!) }
fun ContainerElement.dynamicPane(block: DynamicPaneElement.() -> Unit) = addChild(DynamicPaneElement(), null, block)

// DynamicPaneContainer
fun UIBuilder.dynamicPaneContainer(id: String, block: DynamicPaneContainerElement.() -> Unit): DynamicPaneContainerRef { addChild(DynamicPaneContainerElement(), id, block); return DynamicPaneContainerRef(sanitizeId(id)!!) }
fun UIBuilder.dynamicPaneContainer(block: DynamicPaneContainerElement.() -> Unit) = addChild(DynamicPaneContainerElement(), null, block)
fun ContainerElement.dynamicPaneContainer(id: String, block: DynamicPaneContainerElement.() -> Unit): DynamicPaneContainerRef { addChild(DynamicPaneContainerElement(), id, block); return DynamicPaneContainerRef(sanitizeId(id)!!) }
fun ContainerElement.dynamicPaneContainer(block: DynamicPaneContainerElement.() -> Unit) = addChild(DynamicPaneContainerElement(), null, block)

// DropdownBox
fun UIBuilder.dropdownBox(id: String, block: DropdownBoxElement.() -> Unit): DropdownBoxRef { addChild(DropdownBoxElement(), id, block); return DropdownBoxRef(sanitizeId(id)!!) }
fun UIBuilder.dropdownBox(block: DropdownBoxElement.() -> Unit) = addChild(DropdownBoxElement(), null, block)
fun ContainerElement.dropdownBox(id: String, block: DropdownBoxElement.() -> Unit): DropdownBoxRef { addChild(DropdownBoxElement(), id, block); return DropdownBoxRef(sanitizeId(id)!!) }
fun ContainerElement.dropdownBox(block: DropdownBoxElement.() -> Unit) = addChild(DropdownBoxElement(), null, block)

// ItemSlotButton
fun UIBuilder.itemSlotButton(id: String, block: ItemSlotButtonElement.() -> Unit): ItemSlotButtonRef { addChild(ItemSlotButtonElement(), id, block); return ItemSlotButtonRef(sanitizeId(id)!!) }
fun UIBuilder.itemSlotButton(block: ItemSlotButtonElement.() -> Unit) = addChild(ItemSlotButtonElement(), null, block)
fun ContainerElement.itemSlotButton(id: String, block: ItemSlotButtonElement.() -> Unit): ItemSlotButtonRef { addChild(ItemSlotButtonElement(), id, block); return ItemSlotButtonRef(sanitizeId(id)!!) }
fun ContainerElement.itemSlotButton(block: ItemSlotButtonElement.() -> Unit) = addChild(ItemSlotButtonElement(), null, block)

// MenuItem
fun UIBuilder.menuItem(id: String, block: MenuItemElement.() -> Unit): MenuItemRef { addChild(MenuItemElement(), id, block); return MenuItemRef(sanitizeId(id)!!) }
fun UIBuilder.menuItem(block: MenuItemElement.() -> Unit) = addChild(MenuItemElement(), null, block)
fun ContainerElement.menuItem(id: String, block: MenuItemElement.() -> Unit): MenuItemRef { addChild(MenuItemElement(), id, block); return MenuItemRef(sanitizeId(id)!!) }
fun ContainerElement.menuItem(block: MenuItemElement.() -> Unit) = addChild(MenuItemElement(), null, block)

// ReorderableList
fun UIBuilder.reorderableList(id: String, block: ReorderableListElement.() -> Unit): ReorderableListRef { addChild(ReorderableListElement(), id, block); return ReorderableListRef(sanitizeId(id)!!) }
fun UIBuilder.reorderableList(block: ReorderableListElement.() -> Unit) = addChild(ReorderableListElement(), null, block)
fun ContainerElement.reorderableList(id: String, block: ReorderableListElement.() -> Unit): ReorderableListRef { addChild(ReorderableListElement(), id, block); return ReorderableListRef(sanitizeId(id)!!) }
fun ContainerElement.reorderableList(block: ReorderableListElement.() -> Unit) = addChild(ReorderableListElement(), null, block)

// ReorderableListGrip
fun UIBuilder.reorderableListGrip(id: String, block: ReorderableListGripElement.() -> Unit): ReorderableListGripRef { addChild(ReorderableListGripElement(), id, block); return ReorderableListGripRef(sanitizeId(id)!!) }
fun UIBuilder.reorderableListGrip(block: ReorderableListGripElement.() -> Unit) = addChild(ReorderableListGripElement(), null, block)
fun ContainerElement.reorderableListGrip(id: String, block: ReorderableListGripElement.() -> Unit): ReorderableListGripRef { addChild(ReorderableListGripElement(), id, block); return ReorderableListGripRef(sanitizeId(id)!!) }
fun ContainerElement.reorderableListGrip(block: ReorderableListGripElement.() -> Unit) = addChild(ReorderableListGripElement(), null, block)

// TabNavigation
fun UIBuilder.tabNavigation(id: String, block: TabNavigationElement.() -> Unit): TabNavigationRef { addChild(TabNavigationElement(), id, block); return TabNavigationRef(sanitizeId(id)!!) }
fun UIBuilder.tabNavigation(block: TabNavigationElement.() -> Unit) = addChild(TabNavigationElement(), null, block)
fun ContainerElement.tabNavigation(id: String, block: TabNavigationElement.() -> Unit): TabNavigationRef { addChild(TabNavigationElement(), id, block); return TabNavigationRef(sanitizeId(id)!!) }
fun ContainerElement.tabNavigation(block: TabNavigationElement.() -> Unit) = addChild(TabNavigationElement(), null, block)

// ToggleButton
fun UIBuilder.toggleButton(id: String, block: ToggleButtonElement.() -> Unit): ToggleButtonRef { addChild(ToggleButtonElement(), id, block); return ToggleButtonRef(sanitizeId(id)!!) }
fun UIBuilder.toggleButton(block: ToggleButtonElement.() -> Unit) = addChild(ToggleButtonElement(), null, block)
fun ContainerElement.toggleButton(id: String, block: ToggleButtonElement.() -> Unit): ToggleButtonRef { addChild(ToggleButtonElement(), id, block); return ToggleButtonRef(sanitizeId(id)!!) }
fun ContainerElement.toggleButton(block: ToggleButtonElement.() -> Unit) = addChild(ToggleButtonElement(), null, block)

// CharacterPreviewComponent
fun UIBuilder.characterPreviewComponent(id: String, block: CharacterPreviewComponentElement.() -> Unit): CharacterPreviewComponentRef { addChild(CharacterPreviewComponentElement(), id, block); return CharacterPreviewComponentRef(sanitizeId(id)!!) }
fun UIBuilder.characterPreviewComponent(block: CharacterPreviewComponentElement.() -> Unit) = addChild(CharacterPreviewComponentElement(), null, block)
fun ContainerElement.characterPreviewComponent(id: String, block: CharacterPreviewComponentElement.() -> Unit): CharacterPreviewComponentRef { addChild(CharacterPreviewComponentElement(), id, block); return CharacterPreviewComponentRef(sanitizeId(id)!!) }
fun ContainerElement.characterPreviewComponent(block: CharacterPreviewComponentElement.() -> Unit) = addChild(CharacterPreviewComponentElement(), null, block)

// ItemPreviewComponent
fun UIBuilder.itemPreviewComponent(id: String, block: ItemPreviewComponentElement.() -> Unit): ItemPreviewComponentRef { addChild(ItemPreviewComponentElement(), id, block); return ItemPreviewComponentRef(sanitizeId(id)!!) }
fun UIBuilder.itemPreviewComponent(block: ItemPreviewComponentElement.() -> Unit) = addChild(ItemPreviewComponentElement(), null, block)
fun ContainerElement.itemPreviewComponent(id: String, block: ItemPreviewComponentElement.() -> Unit): ItemPreviewComponentRef { addChild(ItemPreviewComponentElement(), id, block); return ItemPreviewComponentRef(sanitizeId(id)!!) }
fun ContainerElement.itemPreviewComponent(block: ItemPreviewComponentElement.() -> Unit) = addChild(ItemPreviewComponentElement(), null, block)

// ============================================================
// Leaf elements
// ============================================================

// Label
fun UIBuilder.label(id: String, block: LabelElement.() -> Unit): LabelRef { addChild(LabelElement(), id, block); return LabelRef(sanitizeId(id)!!) }
fun UIBuilder.label(block: LabelElement.() -> Unit) = addChild(LabelElement(), null, block)
fun ContainerElement.label(id: String, block: LabelElement.() -> Unit): LabelRef { addChild(LabelElement(), id, block); return LabelRef(sanitizeId(id)!!) }
fun ContainerElement.label(block: LabelElement.() -> Unit) = addChild(LabelElement(), null, block)

// ProgressBar
fun UIBuilder.progressBar(id: String, block: ProgressBarElement.() -> Unit): ProgressBarRef { addChild(ProgressBarElement(), id, block); return ProgressBarRef(sanitizeId(id)!!) }
fun UIBuilder.progressBar(block: ProgressBarElement.() -> Unit) = addChild(ProgressBarElement(), null, block)
fun ContainerElement.progressBar(id: String, block: ProgressBarElement.() -> Unit): ProgressBarRef { addChild(ProgressBarElement(), id, block); return ProgressBarRef(sanitizeId(id)!!) }
fun ContainerElement.progressBar(block: ProgressBarElement.() -> Unit) = addChild(ProgressBarElement(), null, block)

// CircularProgressBar
fun UIBuilder.circularProgressBar(id: String, block: CircularProgressBarElement.() -> Unit): CircularProgressBarRef { addChild(CircularProgressBarElement(), id, block); return CircularProgressBarRef(sanitizeId(id)!!) }
fun UIBuilder.circularProgressBar(block: CircularProgressBarElement.() -> Unit) = addChild(CircularProgressBarElement(), null, block)
fun ContainerElement.circularProgressBar(id: String, block: CircularProgressBarElement.() -> Unit): CircularProgressBarRef { addChild(CircularProgressBarElement(), id, block); return CircularProgressBarRef(sanitizeId(id)!!) }
fun ContainerElement.circularProgressBar(block: CircularProgressBarElement.() -> Unit) = addChild(CircularProgressBarElement(), null, block)

// Sprite
fun UIBuilder.sprite(id: String, block: SpriteElement.() -> Unit): SpriteRef { addChild(SpriteElement(), id, block); return SpriteRef(sanitizeId(id)!!) }
fun UIBuilder.sprite(block: SpriteElement.() -> Unit) = addChild(SpriteElement(), null, block)
fun ContainerElement.sprite(id: String, block: SpriteElement.() -> Unit): SpriteRef { addChild(SpriteElement(), id, block); return SpriteRef(sanitizeId(id)!!) }
fun ContainerElement.sprite(block: SpriteElement.() -> Unit) = addChild(SpriteElement(), null, block)

// TimerLabel
fun UIBuilder.timerLabel(id: String, block: TimerLabelElement.() -> Unit): TimerLabelRef { addChild(TimerLabelElement(), id, block); return TimerLabelRef(sanitizeId(id)!!) }
fun UIBuilder.timerLabel(block: TimerLabelElement.() -> Unit) = addChild(TimerLabelElement(), null, block)
fun ContainerElement.timerLabel(id: String, block: TimerLabelElement.() -> Unit): TimerLabelRef { addChild(TimerLabelElement(), id, block); return TimerLabelRef(sanitizeId(id)!!) }
fun ContainerElement.timerLabel(block: TimerLabelElement.() -> Unit) = addChild(TimerLabelElement(), null, block)

// HotkeyLabel
fun UIBuilder.hotkeyLabel(id: String, block: HotkeyLabelElement.() -> Unit): HotkeyLabelRef { addChild(HotkeyLabelElement(), id, block); return HotkeyLabelRef(sanitizeId(id)!!) }
fun UIBuilder.hotkeyLabel(block: HotkeyLabelElement.() -> Unit) = addChild(HotkeyLabelElement(), null, block)
fun ContainerElement.hotkeyLabel(id: String, block: HotkeyLabelElement.() -> Unit): HotkeyLabelRef { addChild(HotkeyLabelElement(), id, block); return HotkeyLabelRef(sanitizeId(id)!!) }
fun ContainerElement.hotkeyLabel(block: HotkeyLabelElement.() -> Unit) = addChild(HotkeyLabelElement(), null, block)

// SceneBlur
fun UIBuilder.sceneBlur(id: String, block: SceneBlurElement.() -> Unit): SceneBlurRef { addChild(SceneBlurElement(), id, block); return SceneBlurRef(sanitizeId(id)!!) }
fun UIBuilder.sceneBlur(block: SceneBlurElement.() -> Unit) = addChild(SceneBlurElement(), null, block)
fun ContainerElement.sceneBlur(id: String, block: SceneBlurElement.() -> Unit): SceneBlurRef { addChild(SceneBlurElement(), id, block); return SceneBlurRef(sanitizeId(id)!!) }
fun ContainerElement.sceneBlur(block: SceneBlurElement.() -> Unit) = addChild(SceneBlurElement(), null, block)

// TextField
fun UIBuilder.textField(id: String, block: TextFieldElement.() -> Unit): TextFieldRef { addChild(TextFieldElement(), id, block); return TextFieldRef(sanitizeId(id)!!) }
fun UIBuilder.textField(block: TextFieldElement.() -> Unit) = addChild(TextFieldElement(), null, block)
fun ContainerElement.textField(id: String, block: TextFieldElement.() -> Unit): TextFieldRef { addChild(TextFieldElement(), id, block); return TextFieldRef(sanitizeId(id)!!) }
fun ContainerElement.textField(block: TextFieldElement.() -> Unit) = addChild(TextFieldElement(), null, block)

// CompactTextField
fun UIBuilder.compactTextField(id: String, block: CompactTextFieldElement.() -> Unit): CompactTextFieldRef { addChild(CompactTextFieldElement(), id, block); return CompactTextFieldRef(sanitizeId(id)!!) }
fun UIBuilder.compactTextField(block: CompactTextFieldElement.() -> Unit) = addChild(CompactTextFieldElement(), null, block)
fun ContainerElement.compactTextField(id: String, block: CompactTextFieldElement.() -> Unit): CompactTextFieldRef { addChild(CompactTextFieldElement(), id, block); return CompactTextFieldRef(sanitizeId(id)!!) }
fun ContainerElement.compactTextField(block: CompactTextFieldElement.() -> Unit) = addChild(CompactTextFieldElement(), null, block)

// MultilineTextField
fun UIBuilder.multilineTextField(id: String, block: MultilineTextFieldElement.() -> Unit): MultilineTextFieldRef { addChild(MultilineTextFieldElement(), id, block); return MultilineTextFieldRef(sanitizeId(id)!!) }
fun UIBuilder.multilineTextField(block: MultilineTextFieldElement.() -> Unit) = addChild(MultilineTextFieldElement(), null, block)
fun ContainerElement.multilineTextField(id: String, block: MultilineTextFieldElement.() -> Unit): MultilineTextFieldRef { addChild(MultilineTextFieldElement(), id, block); return MultilineTextFieldRef(sanitizeId(id)!!) }
fun ContainerElement.multilineTextField(block: MultilineTextFieldElement.() -> Unit) = addChild(MultilineTextFieldElement(), null, block)

// NumberField
fun UIBuilder.numberField(id: String, block: NumberFieldElement.() -> Unit): NumberFieldRef { addChild(NumberFieldElement(), id, block); return NumberFieldRef(sanitizeId(id)!!) }
fun UIBuilder.numberField(block: NumberFieldElement.() -> Unit) = addChild(NumberFieldElement(), null, block)
fun ContainerElement.numberField(id: String, block: NumberFieldElement.() -> Unit): NumberFieldRef { addChild(NumberFieldElement(), id, block); return NumberFieldRef(sanitizeId(id)!!) }
fun ContainerElement.numberField(block: NumberFieldElement.() -> Unit) = addChild(NumberFieldElement(), null, block)

// CodeEditor
fun UIBuilder.codeEditor(id: String, block: CodeEditorElement.() -> Unit): CodeEditorRef { addChild(CodeEditorElement(), id, block); return CodeEditorRef(sanitizeId(id)!!) }
fun UIBuilder.codeEditor(block: CodeEditorElement.() -> Unit) = addChild(CodeEditorElement(), null, block)
fun ContainerElement.codeEditor(id: String, block: CodeEditorElement.() -> Unit): CodeEditorRef { addChild(CodeEditorElement(), id, block); return CodeEditorRef(sanitizeId(id)!!) }
fun ContainerElement.codeEditor(block: CodeEditorElement.() -> Unit) = addChild(CodeEditorElement(), null, block)

// CheckBox
fun UIBuilder.checkBox(id: String, block: CheckBoxElement.() -> Unit): CheckBoxRef { addChild(CheckBoxElement(), id, block); return CheckBoxRef(sanitizeId(id)!!) }
fun UIBuilder.checkBox(block: CheckBoxElement.() -> Unit) = addChild(CheckBoxElement(), null, block)
fun ContainerElement.checkBox(id: String, block: CheckBoxElement.() -> Unit): CheckBoxRef { addChild(CheckBoxElement(), id, block); return CheckBoxRef(sanitizeId(id)!!) }
fun ContainerElement.checkBox(block: CheckBoxElement.() -> Unit) = addChild(CheckBoxElement(), null, block)

// LabeledCheckBox
fun UIBuilder.labeledCheckBox(id: String, block: LabeledCheckBoxElement.() -> Unit): LabeledCheckBoxRef { addChild(LabeledCheckBoxElement(), id, block); return LabeledCheckBoxRef(sanitizeId(id)!!) }
fun UIBuilder.labeledCheckBox(block: LabeledCheckBoxElement.() -> Unit) = addChild(LabeledCheckBoxElement(), null, block)
fun ContainerElement.labeledCheckBox(id: String, block: LabeledCheckBoxElement.() -> Unit): LabeledCheckBoxRef { addChild(LabeledCheckBoxElement(), id, block); return LabeledCheckBoxRef(sanitizeId(id)!!) }
fun ContainerElement.labeledCheckBox(block: LabeledCheckBoxElement.() -> Unit) = addChild(LabeledCheckBoxElement(), null, block)

// Slider
fun UIBuilder.slider(id: String, block: SliderElement.() -> Unit): SliderRef { addChild(SliderElement(), id, block); return SliderRef(sanitizeId(id)!!) }
fun UIBuilder.slider(block: SliderElement.() -> Unit) = addChild(SliderElement(), null, block)
fun ContainerElement.slider(id: String, block: SliderElement.() -> Unit): SliderRef { addChild(SliderElement(), id, block); return SliderRef(sanitizeId(id)!!) }
fun ContainerElement.slider(block: SliderElement.() -> Unit) = addChild(SliderElement(), null, block)

// FloatSlider
fun UIBuilder.floatSlider(id: String, block: FloatSliderElement.() -> Unit): FloatSliderRef { addChild(FloatSliderElement(), id, block); return FloatSliderRef(sanitizeId(id)!!) }
fun UIBuilder.floatSlider(block: FloatSliderElement.() -> Unit) = addChild(FloatSliderElement(), null, block)
fun ContainerElement.floatSlider(id: String, block: FloatSliderElement.() -> Unit): FloatSliderRef { addChild(FloatSliderElement(), id, block); return FloatSliderRef(sanitizeId(id)!!) }
fun ContainerElement.floatSlider(block: FloatSliderElement.() -> Unit) = addChild(FloatSliderElement(), null, block)

// SliderNumberField
fun UIBuilder.sliderNumberField(id: String, block: SliderNumberFieldElement.() -> Unit): SliderNumberFieldRef { addChild(SliderNumberFieldElement(), id, block); return SliderNumberFieldRef(sanitizeId(id)!!) }
fun UIBuilder.sliderNumberField(block: SliderNumberFieldElement.() -> Unit) = addChild(SliderNumberFieldElement(), null, block)
fun ContainerElement.sliderNumberField(id: String, block: SliderNumberFieldElement.() -> Unit): SliderNumberFieldRef { addChild(SliderNumberFieldElement(), id, block); return SliderNumberFieldRef(sanitizeId(id)!!) }
fun ContainerElement.sliderNumberField(block: SliderNumberFieldElement.() -> Unit) = addChild(SliderNumberFieldElement(), null, block)

// FloatSliderNumberField
fun UIBuilder.floatSliderNumberField(id: String, block: FloatSliderNumberFieldElement.() -> Unit): FloatSliderNumberFieldRef { addChild(FloatSliderNumberFieldElement(), id, block); return FloatSliderNumberFieldRef(sanitizeId(id)!!) }
fun UIBuilder.floatSliderNumberField(block: FloatSliderNumberFieldElement.() -> Unit) = addChild(FloatSliderNumberFieldElement(), null, block)
fun ContainerElement.floatSliderNumberField(id: String, block: FloatSliderNumberFieldElement.() -> Unit): FloatSliderNumberFieldRef { addChild(FloatSliderNumberFieldElement(), id, block); return FloatSliderNumberFieldRef(sanitizeId(id)!!) }
fun ContainerElement.floatSliderNumberField(block: FloatSliderNumberFieldElement.() -> Unit) = addChild(FloatSliderNumberFieldElement(), null, block)

// ColorPicker
fun UIBuilder.colorPicker(id: String, block: ColorPickerElement.() -> Unit): ColorPickerRef { addChild(ColorPickerElement(), id, block); return ColorPickerRef(sanitizeId(id)!!) }
fun UIBuilder.colorPicker(block: ColorPickerElement.() -> Unit) = addChild(ColorPickerElement(), null, block)
fun ContainerElement.colorPicker(id: String, block: ColorPickerElement.() -> Unit): ColorPickerRef { addChild(ColorPickerElement(), id, block); return ColorPickerRef(sanitizeId(id)!!) }
fun ContainerElement.colorPicker(block: ColorPickerElement.() -> Unit) = addChild(ColorPickerElement(), null, block)

// ColorPickerDropdownBox
fun UIBuilder.colorPickerDropdownBox(id: String, block: ColorPickerDropdownBoxElement.() -> Unit): ColorPickerDropdownBoxRef { addChild(ColorPickerDropdownBoxElement(), id, block); return ColorPickerDropdownBoxRef(sanitizeId(id)!!) }
fun UIBuilder.colorPickerDropdownBox(block: ColorPickerDropdownBoxElement.() -> Unit) = addChild(ColorPickerDropdownBoxElement(), null, block)
fun ContainerElement.colorPickerDropdownBox(id: String, block: ColorPickerDropdownBoxElement.() -> Unit): ColorPickerDropdownBoxRef { addChild(ColorPickerDropdownBoxElement(), id, block); return ColorPickerDropdownBoxRef(sanitizeId(id)!!) }
fun ContainerElement.colorPickerDropdownBox(block: ColorPickerDropdownBoxElement.() -> Unit) = addChild(ColorPickerDropdownBoxElement(), null, block)

// ColorOptionGrid
fun UIBuilder.colorOptionGrid(id: String, block: ColorOptionGridElement.() -> Unit): ColorOptionGridRef { addChild(ColorOptionGridElement(), id, block); return ColorOptionGridRef(sanitizeId(id)!!) }
fun UIBuilder.colorOptionGrid(block: ColorOptionGridElement.() -> Unit) = addChild(ColorOptionGridElement(), null, block)
fun ContainerElement.colorOptionGrid(id: String, block: ColorOptionGridElement.() -> Unit): ColorOptionGridRef { addChild(ColorOptionGridElement(), id, block); return ColorOptionGridRef(sanitizeId(id)!!) }
fun ContainerElement.colorOptionGrid(block: ColorOptionGridElement.() -> Unit) = addChild(ColorOptionGridElement(), null, block)

// TextButton
fun UIBuilder.textButton(id: String, block: TextButtonElement.() -> Unit): TextButtonRef { addChild(TextButtonElement(), id, block); return TextButtonRef(sanitizeId(id)!!) }
fun UIBuilder.textButton(block: TextButtonElement.() -> Unit) = addChild(TextButtonElement(), null, block)
fun ContainerElement.textButton(id: String, block: TextButtonElement.() -> Unit): TextButtonRef { addChild(TextButtonElement(), id, block); return TextButtonRef(sanitizeId(id)!!) }
fun ContainerElement.textButton(block: TextButtonElement.() -> Unit) = addChild(TextButtonElement(), null, block)

// BackButton
fun UIBuilder.backButton(id: String, block: BackButtonElement.() -> Unit): BackButtonRef { addChild(BackButtonElement(), id, block); return BackButtonRef(sanitizeId(id)!!) }
fun UIBuilder.backButton(block: BackButtonElement.() -> Unit) = addChild(BackButtonElement(), null, block)
fun ContainerElement.backButton(id: String, block: BackButtonElement.() -> Unit): BackButtonRef { addChild(BackButtonElement(), id, block); return BackButtonRef(sanitizeId(id)!!) }
fun ContainerElement.backButton(block: BackButtonElement.() -> Unit) = addChild(BackButtonElement(), null, block)

// ActionButton
fun UIBuilder.actionButton(id: String, block: ActionButtonElement.() -> Unit): ActionButtonRef { addChild(ActionButtonElement(), id, block); return ActionButtonRef(sanitizeId(id)!!) }
fun UIBuilder.actionButton(block: ActionButtonElement.() -> Unit) = addChild(ActionButtonElement(), null, block)
fun ContainerElement.actionButton(id: String, block: ActionButtonElement.() -> Unit): ActionButtonRef { addChild(ActionButtonElement(), id, block); return ActionButtonRef(sanitizeId(id)!!) }
fun ContainerElement.actionButton(block: ActionButtonElement.() -> Unit) = addChild(ActionButtonElement(), null, block)

// TabButton
fun UIBuilder.tabButton(id: String, block: TabButtonElement.() -> Unit): TabButtonRef { addChild(TabButtonElement(), id, block); return TabButtonRef(sanitizeId(id)!!) }
fun UIBuilder.tabButton(block: TabButtonElement.() -> Unit) = addChild(TabButtonElement(), null, block)
fun ContainerElement.tabButton(id: String, block: TabButtonElement.() -> Unit): TabButtonRef { addChild(TabButtonElement(), id, block); return TabButtonRef(sanitizeId(id)!!) }
fun ContainerElement.tabButton(block: TabButtonElement.() -> Unit) = addChild(TabButtonElement(), null, block)

// DropdownEntry
fun UIBuilder.dropdownEntry(id: String, block: DropdownEntryElement.() -> Unit): DropdownEntryRef { addChild(DropdownEntryElement(), id, block); return DropdownEntryRef(sanitizeId(id)!!) }
fun UIBuilder.dropdownEntry(block: DropdownEntryElement.() -> Unit) = addChild(DropdownEntryElement(), null, block)
fun ContainerElement.dropdownEntry(id: String, block: DropdownEntryElement.() -> Unit): DropdownEntryRef { addChild(DropdownEntryElement(), id, block); return DropdownEntryRef(sanitizeId(id)!!) }
fun ContainerElement.dropdownEntry(block: DropdownEntryElement.() -> Unit) = addChild(DropdownEntryElement(), null, block)

// ItemGrid
fun UIBuilder.itemGrid(id: String, block: ItemGridElement.() -> Unit): ItemGridRef { addChild(ItemGridElement(), id, block); return ItemGridRef(sanitizeId(id)!!) }
fun UIBuilder.itemGrid(block: ItemGridElement.() -> Unit) = addChild(ItemGridElement(), null, block)
fun ContainerElement.itemGrid(id: String, block: ItemGridElement.() -> Unit): ItemGridRef { addChild(ItemGridElement(), id, block); return ItemGridRef(sanitizeId(id)!!) }
fun ContainerElement.itemGrid(block: ItemGridElement.() -> Unit) = addChild(ItemGridElement(), null, block)

// ItemSlot
fun UIBuilder.itemSlot(id: String, block: ItemSlotElement.() -> Unit): ItemSlotRef { addChild(ItemSlotElement(), id, block); return ItemSlotRef(sanitizeId(id)!!) }
fun UIBuilder.itemSlot(block: ItemSlotElement.() -> Unit) = addChild(ItemSlotElement(), null, block)
fun ContainerElement.itemSlot(id: String, block: ItemSlotElement.() -> Unit): ItemSlotRef { addChild(ItemSlotElement(), id, block); return ItemSlotRef(sanitizeId(id)!!) }
fun ContainerElement.itemSlot(block: ItemSlotElement.() -> Unit) = addChild(ItemSlotElement(), null, block)

// ItemIcon
fun UIBuilder.itemIcon(id: String, block: ItemIconElement.() -> Unit): ItemIconRef { addChild(ItemIconElement(), id, block); return ItemIconRef(sanitizeId(id)!!) }
fun UIBuilder.itemIcon(block: ItemIconElement.() -> Unit) = addChild(ItemIconElement(), null, block)
fun ContainerElement.itemIcon(id: String, block: ItemIconElement.() -> Unit): ItemIconRef { addChild(ItemIconElement(), id, block); return ItemIconRef(sanitizeId(id)!!) }
fun ContainerElement.itemIcon(block: ItemIconElement.() -> Unit) = addChild(ItemIconElement(), null, block)

// BlockSelector
fun UIBuilder.blockSelector(id: String, block: BlockSelectorElement.() -> Unit): BlockSelectorRef { addChild(BlockSelectorElement(), id, block); return BlockSelectorRef(sanitizeId(id)!!) }
fun UIBuilder.blockSelector(block: BlockSelectorElement.() -> Unit) = addChild(BlockSelectorElement(), null, block)
fun ContainerElement.blockSelector(id: String, block: BlockSelectorElement.() -> Unit): BlockSelectorRef { addChild(BlockSelectorElement(), id, block); return BlockSelectorRef(sanitizeId(id)!!) }
fun ContainerElement.blockSelector(block: BlockSelectorElement.() -> Unit) = addChild(BlockSelectorElement(), null, block)

// DynamicImage - runtime-loaded image element (serializes as Group)
fun UIBuilder.dynamicImage(id: String, block: DynamicImageElement.() -> Unit): DynamicImageRef { addChild(DynamicImageElement(), id, block); return DynamicImageRef(sanitizeId(id)!!) }
fun UIBuilder.dynamicImage(block: DynamicImageElement.() -> Unit) = addChild(DynamicImageElement(), null, block)
fun ContainerElement.dynamicImage(id: String, block: DynamicImageElement.() -> Unit): DynamicImageRef { addChild(DynamicImageElement(), id, block); return DynamicImageRef(sanitizeId(id)!!) }
fun ContainerElement.dynamicImage(block: DynamicImageElement.() -> Unit) = addChild(DynamicImageElement(), null, block)
