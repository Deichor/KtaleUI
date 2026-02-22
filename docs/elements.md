# Elements Reference

## Containers

### Panel / Group

Basic containers that arrange children:

```kotlin
panel(id = "main") {
    layoutMode = LayoutMode.Top
    scrollbarStyle = scrollbarStyle {
        size = 8
        spacing = 2
        background = "textures/ui/scrollbar_bg.png"
        handle = "textures/ui/scrollbar_handle.png"
        hoveredHandle = "textures/ui/scrollbar_handle_hover.png"
    }
    label { text = "Item 1" }
    label { text = "Item 2" }
}
```

### DynamicPane / DynamicPaneContainer

Resizable panes:

```kotlin
dynamicPaneContainer(id = "split") {
    layoutMode = LayoutMode.Left
    dynamicPane(id = "left") {
        minSize = 200
        resizeAt = ResizeType.End
        resizerSize = 4
        resizerBackground = "#666666"
        label { text = "Left Panel" }
    }
    dynamicPane(id = "right") {
        label { text = "Right Panel" }
    }
}
```

### ReorderableList

```kotlin
reorderableList(id = "todoList") {
    layoutMode = LayoutMode.TopScrolling
    panel(id = "item1") {
        reorderableListGrip(id = "grip1") {}
        label { text = "Task 1" }
    }
    panel(id = "item2") {
        reorderableListGrip(id = "grip2") {}
        label { text = "Task 2" }
    }
}
```

## Text & Labels

### Label

```kotlin
label(id = "simple") { text = "Simple text" }

label(id = "styled") {
    text = "Styled"
    style = labelStyle {
        fontSize = 32f
        textColor = "#FF0000"
        horizontalAlignment = LabelAlignment.Center
    }
    anchor { horizontal = 0; top = 10; height = 50 }
}
```

### TimerLabel

```kotlin
timerLabel(id = "countdown") {
    seconds = 300
    direction = TimerDirection.CountDown
    paused = false
    style = labelStyle {
        fontSize = 20f
        textColor = "#FF4444"
    }
}
```

### HotkeyLabel

```kotlin
hotkeyLabel(id = "hotkey") {
    text = "Press to interact"
    anchor { width = 200; height = 30 }
}
```

## Buttons

### Button (Container)

A button that can contain child elements:

```kotlin
button(id = "iconBtn") {
    style = buttonStyle {
        default { background = "textures/ui/btn.png" }
        hovered { background = "textures/ui/btn_hover.png" }
    }
    anchor { width = 48; height = 48 }

    assetImage(id = "icon") {
        assetPath = "textures/icons/sword.png"
        anchor { full = 4 }
    }

    onClick { ctx -> println("Icon button clicked!") }
    onRightClick { ctx -> println("Right clicked!") }
}
```

### TextButton (Leaf)

```kotlin
textButton(id = "accept") {
    text = "Accept"
    style = textButtonStyle {
        default {
            background = "textures/ui/btn_green.png"
            labelStyle = labelStyle { textColor = "#FFFFFF"; fontSize = 16f }
        }
        hovered {
            background = "textures/ui/btn_green_hover.png"
            labelStyle = labelStyle { textColor = "#FFFFAA"; fontSize = 16f }
        }
    }
    onClick { ctx -> println("Accepted!") }
}
```

### ActionButton

Binds to a game action:

```kotlin
actionButton(id = "jump") {
    actionName = "Jump"
    alignment = ActionButtonAlignment.Right
    style = buttonStyle {
        default { background = "textures/ui/action_btn.png" }
    }
}
```

### BackButton

```kotlin
backButton(id = "back") {
    style = buttonStyle {
        default { background = "textures/ui/back.png" }
        hovered { background = "textures/ui/back_hover.png" }
    }
    onClick { ctx -> println("Going back") }
}
```

## Input Fields

### TextField

```kotlin
textField(id = "username") {
    value = ""
    placeholderText = "Enter username..."
    maxLength = 32
    autoFocus = true
    style = inputFieldStyle {
        fontName = "default"
        fontSize = 14f
        textColor = "#FFFFFF"
    }
    decoration = inputFieldDecorationStyle {
        default = InputFieldDecorationStyleState(
            background = "textures/ui/input_bg.png",
            outlineColor = "#555555",
            outlineSize = 1,
        )
        focused = InputFieldDecorationStyleState(
            background = "textures/ui/input_bg_focus.png",
            outlineColor = "#4488FF",
            outlineSize = 2,
        )
    }
    onValueChanged { ctx -> println("New value entered") }
}
```

### Password Field

```kotlin
textField(id = "password") {
    passwordChar = '*'
    placeholderText = "Enter password..."
    maxLength = 64
}
```

### MultilineTextField

```kotlin
multilineTextField(id = "description") {
    placeholderText = "Write a description..."
    maxLines = 10
    maxVisibleLines = 5
    autoGrow = true
    contentPadding = Padding(full = 8)
    onValueChanged { ctx -> println("Text changed") }
}
```

### NumberField

```kotlin
numberField(id = "quantity") {
    value = 1.0
    format = numberFieldFormat {
        minValue = 1.0
        maxValue = 99.0
        step = 1.0
        maxDecimalPlaces = 0
        suffix = "x"
    }
    onValueChanged { ctx -> println("Quantity changed") }
}
```

### CodeEditor

```kotlin
codeEditor(id = "jsonEditor") {
    value = "{}"
    language = CodeEditorLanguage.Json
    lineNumberWidth = 40
    lineNumberBackground = "#1E1E1E"
    lineNumberTextColor = "#858585"
    maxVisibleLines = 20
    autoGrow = false
    style = inputFieldStyle {
        fontName = "monospace"
        fontSize = 13f
        textColor = "#D4D4D4"
    }
    onValueChanged { ctx -> println("Code changed") }
}
```

## Selection Controls

### CheckBox

```kotlin
checkBox(id = "agree") {
    value = false
    style = checkBoxStyle {
        checked {
            defaultBackground = "textures/ui/checkbox_checked.png"
            hoveredBackground = "textures/ui/checkbox_checked_hover.png"
        }
        unchecked {
            defaultBackground = "textures/ui/checkbox.png"
            hoveredBackground = "textures/ui/checkbox_hover.png"
        }
    }
    onValueChanged { ctx -> println("Checkbox toggled") }
}
```

### LabeledCheckBox

```kotlin
labeledCheckBox(id = "tos") {
    value = false
    style = labeledCheckBoxStyle { }
    onValueChanged { ctx -> println("TOS accepted") }
}
```

### ToggleButton

```kotlin
toggleButton(id = "mute") {
    isChecked = false
    style = toggleButtonStyle {
        default { background = "textures/ui/toggle_off.png" }
        hovered { background = "textures/ui/toggle_off_hover.png" }
    }
    checkedStyle = toggleButtonStyle {
        default { background = "textures/ui/toggle_on.png" }
        hovered { background = "textures/ui/toggle_on_hover.png" }
    }
    onValueChanged { ctx -> println("Toggled") }
}
```

### Slider

```kotlin
slider(id = "volume") {
    value = 75
    min = 0
    max = 100
    step = 5
    style = sliderStyle {
        background = "textures/ui/slider_bg.png"
        fill = "textures/ui/slider_fill.png"
        handle = "textures/ui/slider_handle.png"
        handleWidth = 16
        handleHeight = 24
    }
    onValueChanged { ctx -> println("Volume changed") }
}
```

### FloatSlider

```kotlin
floatSlider(id = "opacity") {
    value = 1.0f
    min = 0.0f
    max = 1.0f
    step = 0.05f
    style = sliderStyle {
        background = "textures/ui/slider_bg.png"
        fill = "textures/ui/slider_fill.png"
        handle = "textures/ui/slider_handle.png"
    }
    onValueChanged { ctx -> println("Opacity changed") }
}
```

### ColorPicker

```kotlin
colorPicker(id = "color") {
    value = "#FF0000"
    format = ColorFormat.Rgb
    displayTextField = true
    style = colorPickerStyle {
        buttonBackground = "textures/ui/color_btn.png"
        buttonFill = "textures/ui/color_fill.png"
    }
    onValueChanged { ctx -> println("Color changed") }
}
```

### DropdownBox

```kotlin
dropdownBox(id = "difficulty") {
    value = "normal"
    showLabel = true

    dropdownEntry { value = "easy"; text = "Easy" }
    dropdownEntry { value = "normal"; text = "Normal" }
    dropdownEntry { value = "hard"; text = "Hard" }
    dropdownEntry { value = "hardcore"; text = "Hardcore" }

    onValueChanged { ctx -> println("Difficulty changed") }
}
```

## Item System

### ItemGrid

```kotlin
itemGrid(id = "inventory") {
    slotsPerRow = 9
    displayItemQuantity = true
    areItemsDraggable = true
    showScrollbar = true
    infoDisplay = ItemGridInfoDisplayMode.Tooltip
    style = itemGridStyle {
        slotSize = 48
        slotIconSize = 40
        slotSpacing = 4
        slotBackground = "textures/ui/slot_bg.png"
        durabilityBar = "textures/ui/durability.png"
        durabilityBarColorStart = "#00FF00"
        durabilityBarColorEnd = "#FF0000"
    }
    anchor { horizontal = 10; top = 100; height = 250 }

    onSlotDoubleClicking { ctx -> println("Double clicked slot") }
    onSlotMouseEntered { ctx -> println("Hovering slot") }
}
```

### ItemGrid with Pre-filled Slots

```kotlin
itemGrid(id = "shopItems") {
    slotsPerRow = 5
    slots = listOf(
        ItemGridSlot(
            itemStack = ClientItemStack(id = "hytale:iron_sword", quantity = 1, durability = 100.0, maxDurability = 100.0),
            name = "Iron Sword",
            description = "A sturdy iron sword",
        ),
        ItemGridSlot(
            itemStack = ClientItemStack(id = "hytale:health_potion", quantity = 5),
            name = "Health Potion",
            description = "Restores 50 HP",
        ),
    )
}
```

### ItemSlot

```kotlin
itemSlot(id = "equippedWeapon") {
    itemId = "hytale:iron_sword"
    quantity = 1
    showQuantity = false
    showQualityBackground = true
    showDurabilityBar = true
    anchor { width = 48; height = 48 }
}
```

### ItemIcon

```kotlin
itemIcon(id = "rewardIcon") {
    itemId = "hytale:golden_apple"
    showItemTooltip = true
    anchor { width = 32; height = 32 }
}
```

### BlockSelector

```kotlin
blockSelector(id = "blockPicker") {
    capacity = 10
    style = blockSelectorStyle {
        itemGridStyle = itemGridStyle {
            slotSize = 48
            slotSpacing = 2
        }
    }
    onValueChanged { ctx -> println("Selected block changed") }
}
```

## Navigation

### TabNavigation

```kotlin
tabNavigation(id = "tabs") {
    selectedTab = "inventory"
    tabs = listOf(
        Tab(id = "inventory", text = "Inventory", icon = "textures/ui/tab_inv.png"),
        Tab(id = "crafting", text = "Crafting", icon = "textures/ui/tab_craft.png"),
        Tab(id = "stats", text = "Stats", icon = "textures/ui/tab_stats.png"),
    )
    style = tabNavigationStyle {
        tabSounds = ButtonSounds(
            activate = SoundStyle(soundPath = "sounds/ui/tab_click.ogg"),
        )
    }
    onSelectedTabChanged { ctx -> println("Tab changed") }

    // Tab content panels
    panel(id = "inventoryContent") { label { text = "Inventory content" } }
    panel(id = "craftingContent") { label { text = "Crafting content" } }
    panel(id = "statsContent") { label { text = "Stats content" } }
}
```

### MenuItem

```kotlin
menuItem(id = "settings") {
    text = "Settings"
    icon = "textures/ui/icon_settings.png"
    style = textButtonStyle {
        default {
            background = "textures/ui/menu_item.png"
            labelStyle = labelStyle { textColor = "#CCCCCC"; fontSize = 14f }
        }
        hovered {
            background = "textures/ui/menu_item_hover.png"
            labelStyle = labelStyle { textColor = "#FFFFFF"; fontSize = 14f }
        }
    }
    onClick { ctx -> println("Open settings") }
}
```

## Display Elements

### ProgressBar

```kotlin
progressBar(id = "health") {
    value = 0.75f
    alignment = ProgressBarAlignment.Horizontal
    direction = ProgressBarDirection.Start
    background = "textures/ui/bar_bg.png"
    bar = "textures/ui/bar_fill_red.png"
    anchor { left = 10; top = 10; width = 200; height = 20 }
}
```

### CircularProgressBar

```kotlin
circularProgressBar(id = "loading") {
    value = 0.5f
    color = "#4488FF"
    anchor { width = 64; height = 64 }
}
```

### Sprite

```kotlin
sprite(id = "explosion") {
    texturePath = "textures/effects/explosion_sheet.png"
    frame = SpriteFrame(width = 64, height = 64, perRow = 8, count = 24)
    framesPerSecond = 30
    autoPlay = true
    repeatCount = 1
    anchor { width = 64; height = 64 }
}
```

### SceneBlur

Blurs the game scene behind the UI:

```kotlin
sceneBlur(id = "blur") {
    anchor { full = 0 }
}
```

### AssetImage

```kotlin
assetImage(id = "banner") {
    assetPath = "textures/ui/banner.png"
    anchor { horizontal = 20; top = 10; height = 120 }
}
```

### CharacterPreviewComponent / ItemPreviewComponent

```kotlin
characterPreviewComponent(id = "avatar") {
    anchor { width = 200; height = 300 }
}

itemPreviewComponent(id = "itemPreview") {
    anchor { width = 100; height = 100 }
}
```

### DynamicImage

A container element for displaying images downloaded at runtime (e.g., player avatars from a web API). Sends PNG bytes directly to the client via Hytale's asset transfer protocol, bypassing the static asset pack system.

```kotlin
dynamicImage(id = "playerAvatar") {
    imageUrl = "https://hyvatar.io/render/PlayerName?size=128"
    anchor { width = 64; height = 64 }
    outlineColor = "#FFFFFF"
    outlineSize = 1f
}
```

Serializes as a `Group` in .ui output. The `imageUrl` and `imageFilePath` properties are metadata — actual image loading is handled at runtime via `setDynamicImage()` or `DynamicImageManager`.

| Property | Type | Description |
|---|---|---|
| `imageUrl` | `String?` | Remote URL to load at runtime (metadata only) |
| `imageFilePath` | `String?` | Local file path alternative (metadata only) |

See [Runtime Integration - Dynamic Images](runtime.md#dynamic-images) for loading images at runtime.
