# Layout & Positioning

## Anchor

Controls element position and size within its parent:

```kotlin
button(id = "centered") {
    anchor {
        top = 100
        left = 50
        width = 200
        height = 40
    }
}

// Full-size element
panel(id = "fullscreen") {
    anchor { full = 0 }  // fills entire parent
}

// Horizontal stretch
label(id = "wide") {
    anchor {
        horizontal = 10  // 10px margin on both sides
        top = 20
        height = 30
    }
}
```

**Anchor properties:** `left`, `right`, `top`, `bottom`, `width`, `height`, `full`, `horizontal`, `vertical`, `minWidth`, `maxWidth`

## Padding

Inner spacing for container elements:

```kotlin
panel(id = "padded") {
    padding {
        left = 10
        right = 10
        top = 5
        bottom = 5
    }
    // or shorthand:
    padding { full = 10 }
    padding { horizontal = 10; vertical = 5 }
}
```

## Layout Mode

Controls how children are arranged in a container:

```kotlin
panel(id = "layout") {
    layoutMode = LayoutMode.Full           // children fill the container
    layoutMode = LayoutMode.Left           // left-aligned horizontal stack
    layoutMode = LayoutMode.Center         // center-aligned horizontal stack
    layoutMode = LayoutMode.Top            // top-aligned vertical stack
    layoutMode = LayoutMode.Middle         // middle-aligned vertical stack
    layoutMode = LayoutMode.CenterMiddle   // centered both ways
    layoutMode = LayoutMode.LeftScrolling  // scrollable left-aligned
    layoutMode = LayoutMode.TopScrolling   // scrollable top-aligned
    layoutMode = LayoutMode.LeftCenterWrap // wrapping horizontal layout
}
```

## Flex Weight

When using layout modes, `flexWeight` controls how space is distributed:

```kotlin
panel(id = "flex") {
    layoutMode = LayoutMode.Left
    panel(id = "sidebar") { flexWeight = 1 }
    panel(id = "content") { flexWeight = 3 }  // takes 3x more space
}
```
