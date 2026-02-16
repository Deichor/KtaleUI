# Styling

## PatchStyle (9-patch backgrounds)

Many background properties accept either a plain texture path or a `PatchStyle` for 9-patch textures:

```kotlin
// Simple texture path
panel(id = "simple") {
    background = "textures/ui/panel_bg.png"
}

// 9-patch with border settings
panel(id = "ninePatch") {
    background = patchStyle {
        texturePath = "textures/ui/panel_bg.png"
        border = 4
        color = "#333333"
    }
}

// PatchStyle with separate borders and area
panel(id = "detailed") {
    background = patchStyle {
        texturePath = "textures/ui/frame.png"
        horizontalBorder = 8
        verticalBorder = 6
        area = Padding(left = 4, right = 4, top = 4, bottom = 4)
        anchor = Anchor(full = 0)
    }
}
```

## Label Style

```kotlin
label(id = "styled") {
    text = "Fancy Text"
    style = labelStyle {
        fontName = "default"
        fontSize = 18f
        textColor = "#FFD700"
        outlineColor = "#000000"
        horizontalAlignment = LabelAlignment.Center
        verticalAlignment = LabelAlignment.Center
        letterSpacing = 1.5f
        wrap = true
        renderBold = true
        renderItalics = false
        renderUppercase = false
        renderUnderlined = false
    }
}
```

## Rich Text (Label Spans)

```kotlin
label(id = "rich") {
    textSpans = listOf(
        LabelSpan(text = "Normal text "),
        LabelSpan(text = "bold", isBold = true),
        LabelSpan(text = " and ", color = "#AAAAAA"),
        LabelSpan(text = "colored", color = "#FF0000", isItalics = true),
        LabelSpan(text = " link", link = "https://hytale.com", isUnderlined = true),
    )
}
```

## Button Style (State-based)

Buttons support different visual states:

```kotlin
button(id = "styledBtn") {
    style = buttonStyle {
        default { background = "textures/ui/btn.png" }
        hovered { background = "textures/ui/btn_hover.png" }
        pressed { background = "textures/ui/btn_pressed.png" }
        disabled { background = "textures/ui/btn_disabled.png" }
        sounds = ButtonSounds(
            activate = SoundStyle(soundPath = "sounds/ui/click.ogg", volume = 0.8f),
            mouseHover = SoundStyle(soundPath = "sounds/ui/hover.ogg", volume = 0.3f),
        )
    }
}
```

## Text Button Style

TextButtons have label styling per state:

```kotlin
textButton(id = "textBtn") {
    text = "Click Me"
    style = textButtonStyle {
        default {
            background = "textures/ui/text_btn.png"
            labelStyle = labelStyle {
                textColor = "#FFFFFF"
                fontSize = 16f
            }
        }
        hovered {
            background = "textures/ui/text_btn_hover.png"
            labelStyle = labelStyle {
                textColor = "#FFD700"
                fontSize = 16f
            }
        }
    }
}
```

## Sound Style

```kotlin
val clickSound = soundStyle {
    soundPath = "sounds/ui/click.ogg"
    volume = 0.8f
    minPitch = 0.9f
    maxPitch = 1.1f
    stopExistingPlayback = false
}
```
