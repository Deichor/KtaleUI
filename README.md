# KtaleUI

A Kotlin DSL library for building Hytale server-side UI. Define your UI elements in Kotlin code instead of writing `.ui` files by hand.

## Installation

Add the GitHub Packages repository and dependency to your `build.gradle.kts`:

```kotlin
repositories {
    maven {
        url = uri("https://maven.pkg.github.com/deichor/KtaleUI")
        credentials {
            username = System.getenv("GITHUB_ACTOR") ?: project.findProperty("gpr.user") as String? ?: ""
            password = System.getenv("GITHUB_TOKEN") ?: project.findProperty("gpr.key") as String? ?: ""
        }
    }
}

dependencies {
    implementation("org.deichor:ktaleui:<version>")
}
```

## Quick Start

### 1. Initialize KtaleUI

Call `KtaleUI.init` in your plugin's `onEnable` with all the UI definitions you want to use. This registers the generated `.ui` files into your plugin JAR so Hytale can load them.

```kotlin
class MyPlugin : JavaPlugin() {
    override fun onEnable() {
        KtaleUI.init(this, myPage, myHud)
    }
}
```

### 2. Define a UI

Use the `ktaleUI` DSL to declare your UI elements:

```kotlin
val myPage = ktaleUI("my-page") {
    panel("root") {
        anchor {
            left = 0.3f
            right = 0.3f
            top = 0.3f
            bottom = 0.3f
        }
        background = patchStyle {
            texturePath = "UI/Shared/panel_bg"
        }

        label("title") {
            text = "Hello World"
            style = labelStyle {
                fontSize = 24
                fontColor = "#FFFFFF"
            }
        }

        button("close-btn") {
            style = buttonStyle {
                default { background = "UI/Shared/btn_default" }
                hovered { background = "UI/Shared/btn_hover" }
            }
            onClick { ctx ->
                ctx.page.closePage()
            }

            label {
                text = "Close"
            }
        }
    }
}
```

### 3. Open as Page or HUD

Open the UI as an interactive page for a player:

```kotlin
val page = myPage.open(playerRef)
```

Or show it as a persistent HUD element:

```kotlin
val hud = myPage.showAsHud(playerRef)
```

### 4. Update at Runtime

Update element properties after the UI is open:

```kotlin
// Page
page.update {
    setProperty("#title", "Text", "Updated!")
}

// HUD
hud.updateProperty {
    setProperty("#title", "Text", "Score: 100")
}
```

### 5. Handle Events

Attach event handlers directly in the DSL:

```kotlin
textField("name-input") {
    placeholderText = "Enter your name..."
    onValueChanged { ctx ->
        val value = ctx.eventData["Value"]
        // handle input
    }
}

slider("volume") {
    minValue = 0f
    maxValue = 100f
    onValueChanged { ctx ->
        // handle slider change
    }
}
```

## Available Elements

**Containers:** Panel, Group, Button, DropdownBox, TabNavigation, ToggleButton, MenuItem, ReorderableList, DynamicPane, CheckBoxContainer, ItemSlotButton, CharacterPreviewComponent, ItemPreviewComponent

**Display:** Label, ProgressBar, CircularProgressBar, Sprite, TimerLabel, HotkeyLabel, SceneBlur, AssetImage

**Input:** TextField, CompactTextField, MultilineTextField, NumberField, CodeEditor, CheckBox, LabeledCheckBox, Slider, FloatSlider, SliderNumberField, FloatSliderNumberField, ColorPicker, ColorPickerDropdownBox, ColorOptionGrid

**Actions:** TextButton, BackButton, ActionButton, TabButton, DropdownEntry

**Items:** ItemGrid, ItemSlot, ItemIcon, BlockSelector

## License

MIT
