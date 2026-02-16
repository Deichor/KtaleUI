# Getting Started

KtaleUI is a Kotlin DSL that simplifies Hytale custom UI creation. Instead of manually writing `.ui` files and wiring up Java APIs, you describe your entire UI in a single Kotlin DSL block.

## Quick Start

```kotlin
import org.deichor.ktaleui.dsl.*
import org.deichor.ktaleui.enums.*

val myUI = ktaleUI("MyFirstUI") {
    panel(id = "root") {
        layoutMode = LayoutMode.Full

        label(id = "title") {
            text = "Hello Hytale!"
            style = labelStyle {
                fontSize = 24f
                textColor = "#FFFFFF"
                horizontalAlignment = LabelAlignment.Center
            }
            anchor { top = 20; width = 300; height = 40 }
        }

        button(id = "clickMe") {
            anchor { top = 80; width = 200; height = 50 }
            style = buttonStyle {
                default { background = "textures/ui/btn_default.png" }
                hovered { background = "textures/ui/btn_hover.png" }
                pressed { background = "textures/ui/btn_pressed.png" }
            }
            onClick { ctx -> println("Button clicked!") }
        }
    }
}

// Open as a page for a player
val page = myUI.open(playerRef)

// Or show as a HUD
val hud = myUI.showAsHud(playerRef)
```

## DSL Basics

### Entry Point

Every UI definition starts with `ktaleUI`:

```kotlin
val definition = ktaleUI("UIName") {
    // root-level elements here
}
```

This returns a `KtaleUIDefinition` that holds the element tree.

### Element IDs

Every element can have an optional `id` used for event routing and runtime property updates:

```kotlin
label(id = "myLabel") { text = "Hello" }
label { text = "No ID" }  // anonymous element
```

### Nesting

Container elements can hold child elements:

```kotlin
panel(id = "outer") {
    panel(id = "inner") {
        label(id = "deep") { text = "Nested!" }
        button(id = "btn") { onClick { ctx -> } }
    }
}
```

### Nullable Properties

All properties are optional (nullable). Only properties you explicitly set will appear in the serialized output:

```kotlin
label(id = "minimal") {
    text = "Just text"
    // style, anchor, padding etc. are all null -> not serialized
}
```

## Serialization

Call `serialize()` on any `KtaleUIDefinition` to produce the Hytale `.ui` file format:

```kotlin
val ui = ktaleUI("MyUI") { /* ... */ }
val uiContent = ui.serialize()
```

The serializer:
- Only outputs properties that are explicitly set (non-null)
- Formats nested objects as `(Key: value, Key2: value2)`
- Quotes string values
- Writes enum values by name (e.g., `Full`, `Center`, `Horizontal`)
- Handles boolean, int, float, double natively
