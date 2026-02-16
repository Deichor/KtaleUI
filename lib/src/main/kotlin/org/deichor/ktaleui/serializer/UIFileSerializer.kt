package org.deichor.ktaleui.serializer

import org.deichor.ktaleui.KtaleUIDefinition
import org.deichor.ktaleui.element.ContainerElement
import org.deichor.ktaleui.element.UIElement
import org.deichor.ktaleui.property.*

object UIFileSerializer {

    fun serialize(definition: KtaleUIDefinition): String {
        val sb = StringBuilder()
        for (element in definition.rootElements) {
            serializeElement(element, sb, 0)
        }
        return sb.toString()
    }

    private fun serializeElement(element: UIElement, sb: StringBuilder, indent: Int) {
        val prefix = "  ".repeat(indent)
        val idSuffix = element.id?.let { " #$it" } ?: ""
        val properties = element.collectProperties()
        val children = if (element is ContainerElement) element.children else emptyList()

        if (properties.isEmpty() && children.isEmpty()) {
            sb.appendLine("$prefix${element.elementType}$idSuffix {}")
            return
        }

        sb.appendLine("$prefix${element.elementType}$idSuffix {")

        for ((key, value) in properties) {
            if (value != null) {
                sb.appendLine("$prefix  $key: ${serializeValue(value)};")
            }
        }

        for (child in children) {
            serializeElement(child, sb, indent + 1)
        }

        sb.appendLine("$prefix}")
    }

    private fun serializeValue(value: Any?): String {
        return when (value) {
            null -> "null"
            is String -> "\"$value\""
            is Boolean -> if (value) "true" else "false"
            is Int -> value.toString()
            is Float -> formatFloat(value)
            is Double -> formatDouble(value)
            is Char -> "\"$value\""
            is Enum<*> -> value.name

            // Property types - serialize as structured values
            is Anchor -> serializeDataClass(value, mapOf(
                "Left" to value.left, "Right" to value.right,
                "Top" to value.top, "Bottom" to value.bottom,
                "Height" to value.height, "Width" to value.width,
                "Full" to value.full, "Horizontal" to value.horizontal,
                "Vertical" to value.vertical, "MinWidth" to value.minWidth,
                "MaxWidth" to value.maxWidth,
            ))
            is Padding -> serializeDataClass(value, mapOf(
                "Left" to value.left, "Right" to value.right,
                "Top" to value.top, "Bottom" to value.bottom,
                "Full" to value.full, "Horizontal" to value.horizontal,
                "Vertical" to value.vertical,
            ))
            is PatchStyle -> serializeDataClass(value, mapOf(
                "Area" to value.area, "Color" to value.color,
                "Anchor" to value.anchor, "HorizontalBorder" to value.horizontalBorder,
                "VerticalBorder" to value.verticalBorder, "TexturePath" to value.texturePath,
                "Border" to value.border,
            ))
            is SoundStyle -> serializeDataClass(value, mapOf(
                "SoundPath" to value.soundPath, "Volume" to value.volume,
                "MinPitch" to value.minPitch, "MaxPitch" to value.maxPitch,
                "StopExistingPlayback" to value.stopExistingPlayback,
            ))
            is SpriteFrame -> serializeDataClass(value, mapOf(
                "Width" to value.width, "Height" to value.height,
                "PerRow" to value.perRow, "Count" to value.count,
            ))
            is LabelStyle -> serializeDataClass(value, mapOf(
                "HorizontalAlignment" to value.horizontalAlignment,
                "VerticalAlignment" to value.verticalAlignment,
                "Wrap" to value.wrap, "FontName" to value.fontName,
                "FontSize" to value.fontSize, "TextColor" to value.textColor,
                "OutlineColor" to value.outlineColor, "LetterSpacing" to value.letterSpacing,
                "RenderUppercase" to value.renderUppercase, "RenderBold" to value.renderBold,
                "RenderItalics" to value.renderItalics, "RenderUnderlined" to value.renderUnderlined,
                "Alignment" to value.alignment,
            ))
            is LabelSpan -> serializeDataClass(value, mapOf(
                "Text" to value.text, "IsUppercase" to value.isUppercase,
                "IsBold" to value.isBold, "IsItalics" to value.isItalics,
                "IsMonospace" to value.isMonospace, "IsUnderlined" to value.isUnderlined,
                "Color" to value.color, "OutlineColor" to value.outlineColor,
                "Link" to value.link,
            ))
            is InputFieldStyle -> serializeDataClass(value, mapOf(
                "FontName" to value.fontName, "FontSize" to value.fontSize,
                "TextColor" to value.textColor, "RenderUppercase" to value.renderUppercase,
                "RenderBold" to value.renderBold, "RenderItalics" to value.renderItalics,
            ))
            is ButtonSounds -> serializeDataClass(value, mapOf(
                "Activate" to value.activate, "Context" to value.context,
                "MouseHover" to value.mouseHover,
            ))
            is ButtonStyleState -> serializeDataClass(value, mapOf(
                "Background" to value.background,
            ))
            is ButtonStyle -> serializeDataClass(value, mapOf(
                "Default" to value.default, "Hovered" to value.hovered,
                "Pressed" to value.pressed, "Disabled" to value.disabled,
                "Sounds" to value.sounds,
            ))
            is TextButtonStyleState -> serializeDataClass(value, mapOf(
                "Background" to value.background, "LabelStyle" to value.labelStyle,
                "LabelMaskTexturePath" to value.labelMaskTexturePath,
            ))
            is TextButtonStyle -> serializeDataClass(value, mapOf(
                "Default" to value.default, "Hovered" to value.hovered,
                "Pressed" to value.pressed, "Disabled" to value.disabled,
                "Sounds" to value.sounds,
            ))
            is ToggleButtonStyleState -> serializeDataClass(value, mapOf(
                "Background" to value.background,
            ))
            is ToggleButtonStyle -> serializeDataClass(value, mapOf(
                "Default" to value.default, "Hovered" to value.hovered,
                "Pressed" to value.pressed, "Disabled" to value.disabled,
                "Sounds" to value.sounds,
            ))
            is CheckBoxStyleState -> serializeDataClass(value, mapOf(
                "DefaultBackground" to value.defaultBackground,
                "HoveredBackground" to value.hoveredBackground,
                "PressedBackground" to value.pressedBackground,
                "DisabledBackground" to value.disabledBackground,
                "ChangedSound" to value.changedSound,
                "HoveredSound" to value.hoveredSound,
            ))
            is CheckBoxStyle -> serializeDataClass(value, mapOf(
                "Checked" to value.checked, "Unchecked" to value.unchecked,
            ))
            is LabeledCheckBoxStyleState -> serializeDataClass(value, mapOf(
                "DefaultLabelStyle" to value.defaultLabelStyle,
                "HoveredLabelStyle" to value.hoveredLabelStyle,
                "PressedLabelStyle" to value.pressedLabelStyle,
                "DisabledLabelStyle" to value.disabledLabelStyle,
                "Text" to value.text,
                "DefaultBackground" to value.defaultBackground,
                "HoveredBackground" to value.hoveredBackground,
                "PressedBackground" to value.pressedBackground,
                "DisabledBackground" to value.disabledBackground,
                "ChangedSound" to value.changedSound,
                "HoveredSound" to value.hoveredSound,
            ))
            is LabeledCheckBoxStyle -> serializeDataClass(value, mapOf(
                "Checked" to value.checked, "Unchecked" to value.unchecked,
            ))
            is SubMenuItemStyleState -> serializeDataClass(value, mapOf(
                "Background" to value.background, "LabelStyle" to value.labelStyle,
                "BindingLabelStyle" to value.bindingLabelStyle,
                "LabelMaskTexturePath" to value.labelMaskTexturePath,
            ))
            is SubMenuItemStyle -> serializeDataClass(value, mapOf(
                "Default" to value.default, "Hovered" to value.hovered,
                "Pressed" to value.pressed, "Disabled" to value.disabled,
                "Sounds" to value.sounds,
            ))
            is SliderStyle -> serializeDataClass(value, mapOf(
                "Background" to value.background, "Fill" to value.fill,
                "Handle" to value.handle, "HandleWidth" to value.handleWidth,
                "HandleHeight" to value.handleHeight, "Sounds" to value.sounds,
            ))
            is ScrollbarStyle -> serializeDataClass(value, mapOf(
                "Size" to value.size, "Spacing" to value.spacing,
                "OnlyVisibleWhenHovered" to value.onlyVisibleWhenHovered,
                "Background" to value.background, "Handle" to value.handle,
                "HoveredHandle" to value.hoveredHandle, "DraggedHandle" to value.draggedHandle,
            ))
            is NumberFieldFormat -> serializeDataClass(value, mapOf(
                "MaxDecimalPlaces" to value.maxDecimalPlaces, "Step" to value.step,
                "MinValue" to value.minValue, "MaxValue" to value.maxValue,
                "DefaultValue" to value.defaultValue, "Suffix" to value.suffix,
            ))
            is InputFieldIcon -> serializeDataClass(value, mapOf(
                "Texture" to value.texture, "Width" to value.width,
                "Height" to value.height, "Offset" to value.offset,
                "Side" to value.side,
            ))
            is InputFieldButtonStyle -> serializeDataClass(value, mapOf(
                "Texture" to value.texture, "HoveredTexture" to value.hoveredTexture,
                "PressedTexture" to value.pressedTexture, "Width" to value.width,
                "Height" to value.height, "Offset" to value.offset,
                "Side" to value.side,
            ))
            is InputFieldDecorationStyleState -> serializeDataClass(value, mapOf(
                "OutlineSize" to value.outlineSize, "Background" to value.background,
                "OutlineColor" to value.outlineColor, "Icon" to value.icon,
                "ClearButtonStyle" to value.clearButtonStyle,
                "ToggleVisibilityButtonStyle" to value.toggleVisibilityButtonStyle,
            ))
            is InputFieldDecorationStyle -> serializeDataClass(value, mapOf(
                "Default" to value.default, "Focused" to value.focused,
            ))
            is TextTooltipStyle -> serializeDataClass(value, mapOf(
                "LabelStyle" to value.labelStyle, "Padding" to value.padding,
                "Background" to value.background, "MaxWidth" to value.maxWidth,
                "Alignment" to value.alignment,
            ))
            is Tab -> serializeDataClass(value, mapOf(
                "Id" to value.id, "Icon" to value.icon,
                "IconSelected" to value.iconSelected, "IconAnchor" to value.iconAnchor,
                "Text" to value.text, "TooltipText" to value.tooltipText,
            ))
            is TabStyleState -> serializeDataClass(value, mapOf(
                "Background" to value.background, "Overlay" to value.overlay,
                "Anchor" to value.anchor, "Padding" to value.padding,
                "IconAnchor" to value.iconAnchor, "IconOpacity" to value.iconOpacity,
                "LabelStyle" to value.labelStyle, "FlexWeight" to value.flexWeight,
                "ContentMaskTexturePath" to value.contentMaskTexturePath,
                "TooltipStyle" to value.tooltipStyle,
            ))
            is TabStyle -> serializeDataClass(value, mapOf(
                "Default" to value.default, "Hovered" to value.hovered,
                "Pressed" to value.pressed,
            ))
            is TabNavigationStyle -> serializeDataClass(value, mapOf(
                "TabStyle" to value.tabStyle, "SelectedTabStyle" to value.selectedTabStyle,
                "SeparatorAnchor" to value.separatorAnchor,
                "SeparatorBackground" to value.separatorBackground,
                "TabSounds" to value.tabSounds,
            ))
            is PopupStyle -> serializeDataClass(value, mapOf(
                "Background" to value.background, "Padding" to value.padding,
                "Width" to value.width, "ButtonPadding" to value.buttonPadding,
                "ButtonStyle" to value.buttonStyle, "SelectedButtonStyle" to value.selectedButtonStyle,
                "TooltipStyle" to value.tooltipStyle,
            ))
            is ItemGridStyle -> serializeDataClass(value, mapOf(
                "SlotSize" to value.slotSize, "SlotIconSize" to value.slotIconSize,
                "SlotSpacing" to value.slotSpacing,
                "DurabilityBarBackground" to value.durabilityBarBackground,
                "DurabilityBar" to value.durabilityBar,
                "DurabilityBarAnchor" to value.durabilityBarAnchor,
                "DurabilityBarColorStart" to value.durabilityBarColorStart,
                "DurabilityBarColorEnd" to value.durabilityBarColorEnd,
                "CursedIconPatch" to value.cursedIconPatch,
                "CursedIconAnchor" to value.cursedIconAnchor,
                "SlotBackground" to value.slotBackground,
                "QuantityPopupSlotOverlay" to value.quantityPopupSlotOverlay,
                "BrokenSlotBackgroundOverlay" to value.brokenSlotBackgroundOverlay,
                "BrokenSlotIconOverlay" to value.brokenSlotIconOverlay,
                "DefaultItemIcon" to value.defaultItemIcon,
                "ItemStackActivateSound" to value.itemStackActivateSound,
                "ItemStackHoveredSound" to value.itemStackHoveredSound,
            ))
            is BlockSelectorStyle -> serializeDataClass(value, mapOf(
                "ItemGridStyle" to value.itemGridStyle,
                "SlotDropIcon" to value.slotDropIcon,
                "SlotDeleteIcon" to value.slotDeleteIcon,
                "SlotHoverOverlay" to value.slotHoverOverlay,
            ))
            is ColorPickerStyle -> serializeDataClass(value, mapOf(
                "ButtonBackground" to value.buttonBackground,
                "ButtonFill" to value.buttonFill,
                "OpacitySelectorBackground" to value.opacitySelectorBackground,
                "TextFieldDecoration" to value.textFieldDecoration,
                "TextFieldInputStyle" to value.textFieldInputStyle,
                "TextFieldPadding" to value.textFieldPadding,
                "TextFieldHeight" to value.textFieldHeight,
            ))
            is ColorOptionGridStyle -> serializeDataClass(value, mapOf(
                "OptionSize" to value.optionSize,
                "OptionSpacingHorizontal" to value.optionSpacingHorizontal,
                "OptionSpacingVertical" to value.optionSpacingVertical,
                "HighlightSize" to value.highlightSize,
                "HighlightOffsetLeft" to value.highlightOffsetLeft,
                "HighlightOffsetTop" to value.highlightOffsetTop,
                "HighlightBackground" to value.highlightBackground,
                "MaskTexturePath" to value.maskTexturePath,
                "FrameBackground" to value.frameBackground,
                "Sounds" to value.sounds,
            ))

            // List types
            is List<*> -> {
                val items = value.filterNotNull().joinToString(", ") { serializeValue(it) }
                "($items)"
            }

            // Fallback
            else -> value.toString()
        }
    }

    @Suppress("UNUSED_PARAMETER")
    private fun serializeDataClass(obj: Any, properties: Map<String, Any?>): String {
        val nonNull = properties.filterValues { it != null }
        if (nonNull.isEmpty()) return "()"
        return nonNull.entries.joinToString(", ", "(", ")") { (key, value) ->
            "$key: ${serializeValue(value)}"
        }
    }

    private fun formatFloat(value: Float): String {
        return if (value == value.toLong().toFloat()) value.toLong().toString()
        else value.toString()
    }

    private fun formatDouble(value: Double): String {
        return if (value == value.toLong().toDouble()) value.toLong().toString()
        else value.toString()
    }
}
