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

## Dynamic Images

KtaleUI can send images to the client at runtime via Hytale's asset transfer protocol. This is useful for player avatars, remote images, or any content not available in the static asset pack.

### How It Works

1. Server downloads image bytes from a URL (with in-memory caching)
2. Claims one of 25 per-player asset slots
3. Sends the PNG bytes to the client using the asset protocol (AssetInitialize → AssetPart → AssetFinalize → RequestCommonAssetsRebuild)
4. Updates the UI element's `Background` property with the slot's texture path

### Page Helper: setDynamicImage()

The simplest way to load a remote image into a UI element:

```kotlin
val page = myUI.open(playerRef)

// Load image into element — handles download, sending, and UI update
page.setDynamicImage(
    elementId = "playerAvatar",
    url = "https://hyvatar.io/render/PlayerName?size=128",
    ttlSeconds = 300,        // cache TTL (default 5 min)
    onReady = { path ->      // optional callback when image is ready
        println("Avatar loaded at $path")
    },
)
```

If the image was already sent to this player, it updates immediately. Otherwise it downloads async and updates the element when ready.

### Low-Level: DynamicImageManager

For more control, use `DynamicImageManager` directly:

```kotlin
import org.deichor.ktaleui.asset.DynamicImageManager

// Synchronous: download + send (blocks until complete)
val assetInfo = DynamicImageManager.sendImage(playerRef, url, ttlSeconds = 300)
if (assetInfo != null) {
    page.update { set("#avatar.Background", assetInfo.path) }
}

// Asynchronous: download + send with callback
DynamicImageManager.sendImageAsync(playerRef, url, ttlSeconds = 300) { assetInfo ->
    if (assetInfo != null) {
        page.update { set("#avatar.Background", assetInfo.path) }
    }
}

// Check if already sent to player
val cached = DynamicImageManager.getCachedAssetInfo(playerRef.uuid, url)

// Pre-download bytes into cache (warm up before player needs it)
DynamicImageManager.preDownload(url)

// Release all slots on player disconnect
DynamicImageManager.releasePlayer(playerRef.uuid)
```

### Slot Limits

Each player has **25 dynamic image slots**. Slots are claimed on send and released when:
- The TTL expires and the image is requested again
- `releasePlayer()` is called (e.g., on disconnect)

### Path Format

Dynamic images use asset paths relative to `Common/` for the protocol, but UI `Background` values are relative to `Common/UI/Custom/`. The `DynamicImageManager` handles this automatically — the returned `CachedAssetInfo.path` is already in the correct UI format (e.g., `Pages/Elements/DynamicImage1.png`).

### Example: Avatar Wrapper

```kotlin
class AvatarManager(private val cacheTTLSeconds: Long) {
    private fun buildUrl(username: String) =
        "https://hyvatar.io/render/$username?size=128&rotate=0"

    fun ensureAvatar(
        playerRef: PlayerRef,
        username: String,
        onReady: ((String) -> Unit)? = null,
    ): String? {
        val url = buildUrl(username)
        val existing = DynamicImageManager.getCachedAssetInfo(playerRef.uuid, url)
        if (existing != null) return existing.path

        DynamicImageManager.sendImageAsync(playerRef, url, cacheTTLSeconds) { assetInfo ->
            if (assetInfo != null) onReady?.invoke(assetInfo.path)
        }
        return null // download in progress
    }

    fun releasePlayer(playerUuid: java.util.UUID) {
        DynamicImageManager.releasePlayer(playerUuid)
    }
}

// Usage in a page controller:
val path = avatarManager.ensureAvatar(playerRef, username) { freshPath ->
    page.update { set("#AvatarImage.Background", freshPath) }
}
if (path != null) {
    page.update { set("#AvatarImage.Background", path) }
}
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
