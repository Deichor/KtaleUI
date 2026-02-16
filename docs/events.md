# Events

## EventContext

All event handlers receive an `EventContext` with runtime information:

```kotlin
data class EventContext(
    val playerRef: PlayerRef,       // player who triggered the event
    val ref: Ref<EntityStore>,      // entity reference
    val store: Store<EntityStore>,  // entity store
    val page: KtaleUIPage,          // the page instance (for updates/closing)
    val eventData: Map<String, String>, // raw event data
)
```

## Registering Events

Events are registered directly on elements using DSL helpers:

```kotlin
button(id = "btn") {
    onClick { ctx ->
        // ctx.playerRef - oyuncuya erişim
        // ctx.page.update { ... } - UI güncelleme
        // ctx.page.closePage() - sayfayı kapat
    }
}
```

## Available Events

### Click Events

```kotlin
button(id = "btn") {
    onClick { ctx -> }         // Activating
    onDoubleClick { ctx -> }   // DoubleClicking
    onRightClick { ctx -> }    // RightClicking
}
```

### Mouse Events

```kotlin
panel(id = "area") {
    onMouseEntered { ctx -> }
    onMouseExited { ctx -> }
    onMouseButtonReleased { ctx -> }
}
```

### Value Events

```kotlin
textField(id = "input") {
    onValueChanged { ctx -> }
    onValidating { ctx -> }
}

slider(id = "slider") {
    onValueChanged { ctx -> }
    onMouseButtonReleased { ctx -> }
}

checkBox(id = "check") {
    onValueChanged { ctx -> }
}

dropdownBox(id = "dropdown") {
    onValueChanged { ctx -> }
}
```

### Focus Events

```kotlin
textField(id = "input") {
    onFocusGained { ctx -> }
    onFocusLost { ctx -> }
}
```

### Item/Slot Events

```kotlin
itemGrid(id = "grid") {
    onSlotClicking { ctx -> }
    onSlotDoubleClicking { ctx -> }
    onSlotMouseEntered { ctx -> }
    onSlotMouseExited { ctx -> }
    onDragCancelled { ctx -> }
    onDropped { ctx -> }
}
```

### Navigation Events

```kotlin
tabNavigation(id = "tabs") {
    onSelectedTabChanged { ctx -> }
}
```

### Other Events

```kotlin
panel(id = "page") {
    onDismissing { ctx -> }
    onKeyDown { ctx -> }
}
```

## Event Types (CustomUIEventBindingType)

| DSL Helper | Hytale Event Type |
|---|---|
| `onClick` | `Activating` |
| `onRightClick` | `RightClicking` |
| `onDoubleClick` | `DoubleClicking` |
| `onMouseEntered` | `MouseEntered` |
| `onMouseExited` | `MouseExited` |
| `onValueChanged` | `ValueChanged` |
| `onValidating` | `Validating` |
| `onDismissing` | `Dismissing` |
| `onFocusGained` | `FocusGained` |
| `onFocusLost` | `FocusLost` |
| `onKeyDown` | `KeyDown` |
| `onMouseButtonReleased` | `MouseButtonReleased` |
| `onSlotClicking` | `SlotClicking` |
| `onSlotDoubleClicking` | `SlotDoubleClicking` |
| `onSlotMouseEntered` | `SlotMouseEntered` |
| `onSlotMouseExited` | `SlotMouseExited` |
| `onDragCancelled` | `DragCancelled` |
| `onDropped` | `Dropped` |
| `onSelectedTabChanged` | `SelectedTabChanged` |

## Using EventContext

```kotlin
button(id = "buyBtn") {
    onClick { ctx ->
        // Update UI text
        ctx.page.update {
            set("#statusLabel.Text", "Purchased!")
            set("#buyBtn.Disabled", true)
        }
    }
}

button(id = "closeBtn") {
    onClick { ctx ->
        ctx.page.closePage()
    }
}
```
