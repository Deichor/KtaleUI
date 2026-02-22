# Full Examples

## Shop UI

```kotlin
val shopUI = ktaleUI("ShopPage") {
    sceneBlur(id = "blur") { anchor { full = 0 } }

    panel(id = "shopPanel") {
        anchor { left = 100; right = 100; top = 50; bottom = 50 }
        background = patchStyle {
            texturePath = "textures/ui/panel_dark.png"
            border = 6
        }
        layoutMode = LayoutMode.Top

        label(id = "shopTitle") {
            text = "Merchant Shop"
            style = labelStyle {
                fontSize = 28f
                textColor = "#FFD700"
                horizontalAlignment = LabelAlignment.Center
                renderBold = true
            }
            anchor { horizontal = 0; height = 50 }
        }

        itemGrid(id = "shopItems") {
            slotsPerRow = 5
            displayItemQuantity = true
            infoDisplay = ItemGridInfoDisplayMode.Tooltip
            style = itemGridStyle {
                slotSize = 56
                slotIconSize = 48
                slotSpacing = 6
                slotBackground = "textures/ui/slot.png"
            }
            anchor { horizontal = 20; height = 300 }

            onSlotDoubleClicking { ctx ->
                println("Buying item")
            }
        }

        panel(id = "footer") {
            layoutMode = LayoutMode.Center
            anchor { height = 60 }

            textButton(id = "buyBtn") {
                text = "Buy Selected"
                style = textButtonStyle {
                    default {
                        background = patchStyle {
                            texturePath = "textures/ui/btn_gold.png"
                            border = 4
                        }
                        labelStyle = labelStyle {
                            fontSize = 18f
                            textColor = "#FFFFFF"
                            horizontalAlignment = LabelAlignment.Center
                        }
                    }
                    hovered {
                        background = patchStyle {
                            texturePath = "textures/ui/btn_gold_hover.png"
                            border = 4
                        }
                        labelStyle = labelStyle {
                            fontSize = 18f
                            textColor = "#FFFFAA"
                            horizontalAlignment = LabelAlignment.Center
                        }
                    }
                }
                anchor { width = 200; height = 44 }
                onClick { ctx ->
                    ctx.page.update {
                        set("#shopTitle.Text", "Purchased!")
                    }
                }
            }
        }

        backButton(id = "closeBtn") {
            style = buttonStyle {
                default { background = "textures/ui/close.png" }
                hovered { background = "textures/ui/close_hover.png" }
            }
            anchor { right = 8; top = 8; width = 24; height = 24 }
            onClick { ctx -> ctx.page.closePage() }
        }
    }
}

// Open for player
val page = shopUI.open(playerRef)
```

## Settings UI

```kotlin
val settingsUI = ktaleUI("SettingsPage") {
    panel(id = "settings") {
        anchor { left = 200; right = 200; top = 80; bottom = 80 }
        background = patchStyle {
            texturePath = "textures/ui/settings_bg.png"
            border = 8
        }
        layoutMode = LayoutMode.Top

        label(id = "title") {
            text = "Settings"
            style = labelStyle { fontSize = 24f; textColor = "#FFFFFF"; horizontalAlignment = LabelAlignment.Center }
            anchor { horizontal = 0; height = 50 }
        }

        // Volume slider
        panel(id = "volumeRow") {
            layoutMode = LayoutMode.Left
            anchor { horizontal = 20; height = 40 }

            label(id = "volumeLabel") {
                text = "Volume"
                style = labelStyle { fontSize = 14f; textColor = "#CCCCCC" }
                anchor { width = 120; height = 40 }
            }
            slider(id = "volumeSlider") {
                value = 80
                min = 0
                max = 100
                step = 5
                style = sliderStyle {
                    background = "textures/ui/slider_bg.png"
                    fill = "textures/ui/slider_fill.png"
                    handle = "textures/ui/slider_thumb.png"
                    handleWidth = 12
                    handleHeight = 20
                }
                flexWeight = 1
                onValueChanged { ctx -> println("Volume changed") }
            }
        }

        // Difficulty dropdown
        panel(id = "difficultyRow") {
            layoutMode = LayoutMode.Left
            anchor { horizontal = 20; height = 40 }

            label(id = "diffLabel") {
                text = "Difficulty"
                style = labelStyle { fontSize = 14f; textColor = "#CCCCCC" }
                anchor { width = 120; height = 40 }
            }
            dropdownBox(id = "diffDropdown") {
                value = "normal"
                showLabel = true
                flexWeight = 1

                dropdownEntry { value = "peaceful"; text = "Peaceful" }
                dropdownEntry { value = "easy"; text = "Easy" }
                dropdownEntry { value = "normal"; text = "Normal" }
                dropdownEntry { value = "hard"; text = "Hard" }

                onValueChanged { ctx -> println("Difficulty changed") }
            }
        }

        // Fullscreen checkbox
        panel(id = "fullscreenRow") {
            layoutMode = LayoutMode.Left
            anchor { horizontal = 20; height = 40 }

            checkBox(id = "fullscreenCheck") {
                value = true
                onValueChanged { ctx -> println("Fullscreen toggled") }
            }
            label {
                text = "Fullscreen"
                style = labelStyle { fontSize = 14f; textColor = "#CCCCCC" }
                padding { left = 10 }
            }
        }

        textButton(id = "applyBtn") {
            text = "Apply"
            anchor { width = 150; height = 40 }
            style = textButtonStyle {
                default {
                    background = "textures/ui/btn_blue.png"
                    labelStyle = labelStyle { fontSize = 16f; textColor = "#FFFFFF"; horizontalAlignment = LabelAlignment.Center }
                }
                hovered {
                    background = "textures/ui/btn_blue_hover.png"
                    labelStyle = labelStyle { fontSize = 16f; textColor = "#FFFFDD"; horizontalAlignment = LabelAlignment.Center }
                }
            }
            onClick { ctx ->
                ctx.page.update {
                    set("#title.Text", "Settings Applied!")
                }
            }
        }
    }
}

val page = settingsUI.open(playerRef)
```

## Inventory with Tabs

```kotlin
val inventoryUI = ktaleUI("InventoryPage") {
    sceneBlur(id = "blur") { anchor { full = 0 } }

    panel(id = "inventoryPanel") {
        anchor { left = 150; right = 150; top = 60; bottom = 60 }
        background = patchStyle { texturePath = "textures/ui/inventory_bg.png"; border = 8 }

        tabNavigation(id = "invTabs") {
            anchor { full = 0 }
            selectedTab = "items"
            tabs = listOf(
                Tab(id = "items", text = "Items", icon = "textures/ui/tab_items.png"),
                Tab(id = "equipment", text = "Equipment", icon = "textures/ui/tab_equip.png"),
                Tab(id = "crafting", text = "Crafting", icon = "textures/ui/tab_craft.png"),
            )
            style = tabNavigationStyle {
                tabSounds = ButtonSounds(
                    activate = SoundStyle(soundPath = "sounds/ui/tab.ogg"),
                )
            }

            onSelectedTabChanged { ctx -> println("Tab switched") }

            // Items tab
            panel(id = "itemsTab") {
                layoutMode = LayoutMode.Top
                itemGrid(id = "mainInventory") {
                    slotsPerRow = 9
                    displayItemQuantity = true
                    areItemsDraggable = true
                    style = itemGridStyle {
                        slotSize = 48
                        slotSpacing = 4
                        slotBackground = "textures/ui/slot.png"
                    }
                }
            }

            // Equipment tab
            panel(id = "equipmentTab") {
                layoutMode = LayoutMode.CenterMiddle
                itemSlot(id = "helmet") {
                    anchor { top = 10; left = 100; width = 48; height = 48 }
                    showQualityBackground = true
                    showDurabilityBar = true
                }
                itemSlot(id = "chestplate") {
                    anchor { top = 64; left = 100; width = 48; height = 48 }
                    showQualityBackground = true
                    showDurabilityBar = true
                }
                itemSlot(id = "leggings") {
                    anchor { top = 118; left = 100; width = 48; height = 48 }
                    showQualityBackground = true
                    showDurabilityBar = true
                }
                itemSlot(id = "boots") {
                    anchor { top = 172; left = 100; width = 48; height = 48 }
                    showQualityBackground = true
                    showDurabilityBar = true
                }
            }

            // Crafting tab
            panel(id = "craftingTab") {
                layoutMode = LayoutMode.Left
                itemGrid(id = "craftingInput") {
                    slotsPerRow = 3
                    areItemsDraggable = true
                    style = itemGridStyle { slotSize = 48; slotSpacing = 4 }
                }
                label(id = "arrow") {
                    text = ">>>"
                    style = labelStyle { fontSize = 24f; textColor = "#FFFFFF"; horizontalAlignment = LabelAlignment.Center }
                    anchor { width = 60; height = 48 }
                }
                itemSlot(id = "craftingOutput") {
                    anchor { width = 56; height = 56 }
                    showQualityBackground = true
                }
            }
        }
    }
}

val page = inventoryUI.open(playerRef)
```

## HUD Score Display

```kotlin
val scoreHud = ktaleUI("ScoreHUD") {
    panel(id = "scorePanel") {
        anchor { right = 10; top = 10; width = 200; height = 80 }
        background = patchStyle {
            texturePath = "textures/ui/hud_bg.png"
            border = 4
            color = "#00000088"
        }
        layoutMode = LayoutMode.Top
        padding { full = 8 }

        label(id = "teamLabel") {
            text = "Team Alpha"
            style = labelStyle {
                fontSize = 12f
                textColor = "#AAAAAA"
                horizontalAlignment = LabelAlignment.Center
            }
        }
        label(id = "score") {
            text = "0"
            style = labelStyle {
                fontSize = 36f
                textColor = "#FFD700"
                horizontalAlignment = LabelAlignment.Center
                renderBold = true
            }
        }
    }

    progressBar(id = "timeBar") {
        value = 1.0f
        alignment = ProgressBarAlignment.Horizontal
        direction = ProgressBarDirection.Start
        background = "textures/ui/bar_bg.png"
        bar = "textures/ui/bar_fill_blue.png"
        anchor { horizontal = 100; top = 5; height = 8 }
    }
}

val hud = scoreHud.showAsHud(playerRef)

// Update score from server
hud.updateProperty {
    set("#score.Text", "42")
    set("#timeBar.Value", 0.6f)
}
```

## Player Profile with Dynamic Avatar

```kotlin
import org.deichor.ktaleui.asset.DynamicImageManager

val profileUI = ktaleUI("PlayerProfile") {
    sceneBlur(id = "blur") { anchor { full = 0 } }

    panel(id = "card") {
        anchor { left = 250; right = 250; top = 100; bottom = 100 }
        background = patchStyle {
            texturePath = "textures/ui/panel_dark.png"
            border = 6
        }
        layoutMode = LayoutMode.Top

        // Avatar container with border
        group(id = "avatarFrame") {
            anchor { horizontal = 0; height = 120 }
            layoutMode = LayoutMode.CenterMiddle

            dynamicImage(id = "avatar") {
                anchor { width = 96; height = 96 }
                outlineColor = "#4488FF"
                outlineSize = 2f
            }
        }

        label(id = "playerName") {
            text = "Loading..."
            style = labelStyle {
                fontSize = 22f
                textColor = "#FFFFFF"
                horizontalAlignment = LabelAlignment.Center
                renderBold = true
            }
            anchor { horizontal = 0; height = 40 }
        }

        label(id = "playerStatus") {
            text = "Online"
            style = labelStyle {
                fontSize = 14f
                textColor = "#4ade80"
                horizontalAlignment = LabelAlignment.Center
            }
            anchor { horizontal = 0; height = 24 }
        }

        textButton(id = "closeBtn") {
            text = "Close"
            anchor { width = 120; height = 36 }
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

// Open and load avatar dynamically
val page = profileUI.open(playerRef)

page.update {
    set("#playerName.Text", playerRef.username)
}

// Load avatar from remote URL — sends PNG bytes via asset protocol
page.setDynamicImage(
    elementId = "avatar",
    url = "https://hyvatar.io/render/${playerRef.username}?size=128",
    ttlSeconds = 300,
)
```
