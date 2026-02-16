package org.deichor.ktaleui.dsl

import org.deichor.ktaleui.property.*

// Style builder DSL functions
fun labelStyle(block: LabelStyle.() -> Unit): LabelStyle = LabelStyle().apply(block)
fun buttonStyle(block: ButtonStyle.() -> Unit): ButtonStyle = ButtonStyle().apply(block)
fun buttonStyleState(block: ButtonStyleState.() -> Unit): ButtonStyleState = ButtonStyleState().apply(block)
fun textButtonStyle(block: TextButtonStyle.() -> Unit): TextButtonStyle = TextButtonStyle().apply(block)
fun textButtonStyleState(block: TextButtonStyleState.() -> Unit): TextButtonStyleState = TextButtonStyleState().apply(block)
fun toggleButtonStyle(block: ToggleButtonStyle.() -> Unit): ToggleButtonStyle = ToggleButtonStyle().apply(block)
fun toggleButtonStyleState(block: ToggleButtonStyleState.() -> Unit): ToggleButtonStyleState = ToggleButtonStyleState().apply(block)
fun checkBoxStyle(block: CheckBoxStyle.() -> Unit): CheckBoxStyle = CheckBoxStyle().apply(block)
fun checkBoxStyleState(block: CheckBoxStyleState.() -> Unit): CheckBoxStyleState = CheckBoxStyleState().apply(block)
fun labeledCheckBoxStyle(block: LabeledCheckBoxStyle.() -> Unit): LabeledCheckBoxStyle = LabeledCheckBoxStyle().apply(block)
fun subMenuItemStyle(block: SubMenuItemStyle.() -> Unit): SubMenuItemStyle = SubMenuItemStyle().apply(block)
fun sliderStyle(block: SliderStyle.() -> Unit): SliderStyle = SliderStyle().apply(block)
fun scrollbarStyle(block: ScrollbarStyle.() -> Unit): ScrollbarStyle = ScrollbarStyle().apply(block)
fun inputFieldStyle(block: InputFieldStyle.() -> Unit): InputFieldStyle = InputFieldStyle().apply(block)
fun inputFieldDecorationStyle(block: InputFieldDecorationStyle.() -> Unit): InputFieldDecorationStyle = InputFieldDecorationStyle().apply(block)
fun dropdownBoxStyle(block: DropdownBoxStyle.() -> Unit): DropdownBoxStyle = DropdownBoxStyle().apply(block)
fun popupStyle(block: PopupStyle.() -> Unit): PopupStyle = PopupStyle().apply(block)
fun tabNavigationStyle(block: TabNavigationStyle.() -> Unit): TabNavigationStyle = TabNavigationStyle().apply(block)
fun tabStyle(block: TabStyle.() -> Unit): TabStyle = TabStyle().apply(block)
fun colorPickerStyle(block: ColorPickerStyle.() -> Unit): ColorPickerStyle = ColorPickerStyle().apply(block)
fun colorPickerDropdownBoxStyle(block: ColorPickerDropdownBoxStyle.() -> Unit): ColorPickerDropdownBoxStyle = ColorPickerDropdownBoxStyle().apply(block)
fun colorOptionGridStyle(block: ColorOptionGridStyle.() -> Unit): ColorOptionGridStyle = ColorOptionGridStyle().apply(block)
fun itemGridStyle(block: ItemGridStyle.() -> Unit): ItemGridStyle = ItemGridStyle().apply(block)
fun blockSelectorStyle(block: BlockSelectorStyle.() -> Unit): BlockSelectorStyle = BlockSelectorStyle().apply(block)
fun textTooltipStyle(block: TextTooltipStyle.() -> Unit): TextTooltipStyle = TextTooltipStyle().apply(block)
fun patchStyle(block: PatchStyle.() -> Unit): PatchStyle = PatchStyle().apply(block)
fun soundStyle(block: SoundStyle.() -> Unit): SoundStyle = SoundStyle().apply(block)
fun numberFieldFormat(block: NumberFieldFormat.() -> Unit): NumberFieldFormat = NumberFieldFormat().apply(block)

// Nested state builders for ButtonStyle
fun ButtonStyle.default(block: ButtonStyleState.() -> Unit) { default = ButtonStyleState().apply(block) }
fun ButtonStyle.hovered(block: ButtonStyleState.() -> Unit) { hovered = ButtonStyleState().apply(block) }
fun ButtonStyle.pressed(block: ButtonStyleState.() -> Unit) { pressed = ButtonStyleState().apply(block) }
fun ButtonStyle.disabled(block: ButtonStyleState.() -> Unit) { disabled = ButtonStyleState().apply(block) }

// Nested state builders for TextButtonStyle
fun TextButtonStyle.default(block: TextButtonStyleState.() -> Unit) { default = TextButtonStyleState().apply(block) }
fun TextButtonStyle.hovered(block: TextButtonStyleState.() -> Unit) { hovered = TextButtonStyleState().apply(block) }
fun TextButtonStyle.pressed(block: TextButtonStyleState.() -> Unit) { pressed = TextButtonStyleState().apply(block) }
fun TextButtonStyle.disabled(block: TextButtonStyleState.() -> Unit) { disabled = TextButtonStyleState().apply(block) }

// Nested state builders for ToggleButtonStyle
fun ToggleButtonStyle.default(block: ToggleButtonStyleState.() -> Unit) { default = ToggleButtonStyleState().apply(block) }
fun ToggleButtonStyle.hovered(block: ToggleButtonStyleState.() -> Unit) { hovered = ToggleButtonStyleState().apply(block) }
fun ToggleButtonStyle.pressed(block: ToggleButtonStyleState.() -> Unit) { pressed = ToggleButtonStyleState().apply(block) }
fun ToggleButtonStyle.disabled(block: ToggleButtonStyleState.() -> Unit) { disabled = ToggleButtonStyleState().apply(block) }

// Nested state builders for CheckBoxStyle
fun CheckBoxStyle.checked(block: CheckBoxStyleState.() -> Unit) { checked = CheckBoxStyleState().apply(block) }
fun CheckBoxStyle.unchecked(block: CheckBoxStyleState.() -> Unit) { unchecked = CheckBoxStyleState().apply(block) }

// Tab builder
fun tab(block: Tab.() -> Unit): Tab = Tab().apply(block)
