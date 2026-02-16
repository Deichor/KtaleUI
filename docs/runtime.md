# Runtime Integration

KtaleUI definitions can be shown to players as interactive pages or HUD overlays.

## Opening a Page

Pages are interactive UI screens that capture input (like inventory, shop, settings). Players can dismiss them with ESC.

```kotlin
val shopUI = ktaleUI("MyShop") {
    panel(id = "root") {
        layoutMode = LayoutMode.Full
        label(id = "title") { text = "Shop" }
        button(id = "buyBtn") {
            onClick { ctx ->
                ctx.page.update {
                    set("#title.Text", "Bought!")
                }
            }
        }
        button(id = "closeBtn") {
            onClick { ctx -> ctx.page.closePage() }
        }
    }
}

// Open for a player (ESC ile kapatılabilir)
val page = shopUI.open(playerRef)

// Open with custom lifetime
val page = shopUI.open(playerRef, CustomPageLifetime.CantClose) // ESC ile kapatılamaz
val page = shopUI.open(playerRef, CustomPageLifetime.CanDismissOrCloseThroughInteraction)
```

### CustomPageLifetime

| Value | Description |
|---|---|
| `CanDismiss` | Player can close with ESC (default) |
| `CantClose` | Player cannot close the page |
| `CanDismissOrCloseThroughInteraction` | Can close via ESC or in-page interaction |

## Showing a HUD

HUDs are non-interactive overlays displayed on screen (health bar, score, notifications). They don't capture input and don't support events.

```kotlin
val scoreHud = ktaleUI("ScoreHUD") {
    label(id = "score") {
        text = "Score: 0"
        style = labelStyle {
            fontSize = 20f
            textColor = "#FFFFFF"
        }
        anchor { right = 20; top = 20; width = 150; height = 30 }
    }
}

val hud = scoreHud.showAsHud(playerRef)
```

## Updating Properties at Runtime

Both pages and HUDs support runtime property updates using `UICommandBuilder` selectors.

### Page Updates

```kotlin
val page = myUI.open(playerRef)

// Update properties
page.update {
    set("#title.Text", "New Title")
    set("#healthBar.Value", 0.5f)
    set("#buyBtn.Disabled", true)
    set("#panel.Visible", false)
}
```

### HUD Updates

```kotlin
val hud = scoreHud.showAsHud(playerRef)

// Update properties
hud.updateProperty {
    set("#score.Text", "Score: 42")
    set("#healthBar.Value", 0.8f)
}
```

### Selector Format

Selectors follow the pattern `#elementId.PropertyName`:

```kotlin
page.update {
    set("#myLabel.Text", "Hello")           // String
    set("#myLabel.Visible", true)           // Boolean
    set("#mySlider.Value", 50)              // Int
    set("#myBar.Value", 0.75f)              // Float
    set("#myBtn.Disabled", true)            // Boolean
}
```

## Closing a Page

```kotlin
// From inside an event handler
button(id = "close") {
    onClick { ctx -> ctx.page.closePage() }
}

// From outside (server-side)
val page = myUI.open(playerRef)
// ... later
page.closePage()
```

## Full Runtime Example

```kotlin
val counterUI = ktaleUI("Counter") {
    sceneBlur(id = "blur") { anchor { full = 0 } }
    panel(id = "root") {
        anchor { left = 200; right = 200; top = 150; bottom = 150 }
        background = patchStyle {
            texturePath = "textures/ui/panel.png"
            border = 6
        }
        layoutMode = LayoutMode.CenterMiddle

        label(id = "count") {
            text = "0"
            style = labelStyle {
                fontSize = 48f
                textColor = "#FFFFFF"
                horizontalAlignment = LabelAlignment.Center
            }
            anchor { horizontal = 0; height = 60 }
        }

        panel(id = "buttons") {
            layoutMode = LayoutMode.Center
            anchor { horizontal = 20; height = 50 }

            textButton(id = "decBtn") {
                text = "-"
                anchor { width = 60; height = 40 }
                style = textButtonStyle {
                    default {
                        background = "textures/ui/btn_red.png"
                        labelStyle = labelStyle { fontSize = 24f; textColor = "#FFFFFF" }
                    }
                }
                onClick { ctx ->
                    // Server-side state management
                    counter--
                    ctx.page.update {
                        set("#count.Text", counter.toString())
                    }
                }
            }

            textButton(id = "incBtn") {
                text = "+"
                anchor { width = 60; height = 40 }
                style = textButtonStyle {
                    default {
                        background = "textures/ui/btn_green.png"
                        labelStyle = labelStyle { fontSize = 24f; textColor = "#FFFFFF" }
                    }
                }
                onClick { ctx ->
                    counter++
                    ctx.page.update {
                        set("#count.Text", counter.toString())
                    }
                }
            }
        }

        textButton(id = "closeBtn") {
            text = "Close"
            anchor { width = 100; height = 36 }
            style = textButtonStyle {
                default {
                    background = "textures/ui/btn.png"
                    labelStyle = labelStyle { fontSize = 14f; textColor = "#CCCCCC" }
                }
            }
            onClick { ctx -> ctx.page.closePage() }
        }
    }
}

var counter = 0
val page = counterUI.open(playerRef)
```
