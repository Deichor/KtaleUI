package org.deichor.ktaleui.ref

// ItemGrid
class ItemGridRef @PublishedApi internal constructor(id: String) : ElementRef(id) {
    val scrollbarStyle get() = UISelector("#$id.ScrollbarStyle")
    val itemStacks get() = UISelector("#$id.ItemStacks")
    val slotsPerRow get() = UISelector("#$id.SlotsPerRow")
    val showScrollbar get() = UISelector("#$id.ShowScrollbar")
    val style get() = UISelector("#$id.Style")
    val renderItemQualityBackground get() = UISelector("#$id.RenderItemQualityBackground")
    val infoDisplay get() = UISelector("#$id.InfoDisplay")
    val adjacentInfoPaneGridWidth get() = UISelector("#$id.AdjacentInfoPaneGridWidth")
    val areItemsDraggable get() = UISelector("#$id.AreItemsDraggable")
    val inventorySectionId get() = UISelector("#$id.InventorySectionId")
    val slots get() = UISelector("#$id.Slots")
    val allowMaxStackDraggableItems get() = UISelector("#$id.AllowMaxStackDraggableItems")
    val displayItemQuantity get() = UISelector("#$id.DisplayItemQuantity")
}

// ItemSlot
class ItemSlotRef @PublishedApi internal constructor(id: String) : ElementRef(id) {
    val itemId get() = UISelector("#$id.ItemId")
    val quantity get() = UISelector("#$id.Quantity")
    val showQuantity get() = UISelector("#$id.ShowQuantity")
    val showQualityBackground get() = UISelector("#$id.ShowQualityBackground")
    val showDurabilityBar get() = UISelector("#$id.ShowDurabilityBar")
}

// ItemIcon
class ItemIconRef @PublishedApi internal constructor(id: String) : ElementRef(id) {
    val itemId get() = UISelector("#$id.ItemId")
    val showItemTooltip get() = UISelector("#$id.ShowItemTooltip")
}

// BlockSelector
class BlockSelectorRef @PublishedApi internal constructor(id: String) : ElementRef(id) {
    val capacity get() = UISelector("#$id.Capacity")
    val value get() = UISelector("#$id.Value")
    val style get() = UISelector("#$id.Style")
}
