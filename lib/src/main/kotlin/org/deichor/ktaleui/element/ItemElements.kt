package org.deichor.ktaleui.element

import org.deichor.ktaleui.enums.ItemGridInfoDisplayMode
import org.deichor.ktaleui.property.*

// ItemGrid
class ItemGridElement : LeafElement("ItemGrid") {
    var scrollbarStyle: ScrollbarStyle? = null
    var itemStacks: List<ClientItemStack>? = null
    var slotsPerRow: Int? = null
    var showScrollbar: Boolean? = null
    var style: ItemGridStyle? = null
    var renderItemQualityBackground: Boolean? = null
    var infoDisplay: ItemGridInfoDisplayMode? = null
    var adjacentInfoPaneGridWidth: Int? = null
    var areItemsDraggable: Boolean? = null
    var inventorySectionId: Int? = null
    var slots: List<ItemGridSlot>? = null
    var allowMaxStackDraggableItems: Boolean? = null
    var displayItemQuantity: Boolean? = null

    fun onSlotDoubleClicking(handler: EventHandler) { eventHandlers["SlotDoubleClicking"] = handler }
    fun onDragCancelled(handler: EventHandler) { eventHandlers["DragCancelled"] = handler }
    fun onSlotMouseEntered(handler: EventHandler) { eventHandlers["SlotMouseEntered"] = handler }
    fun onSlotMouseExited(handler: EventHandler) { eventHandlers["SlotMouseExited"] = handler }

    override fun collectProperties(): Map<String, Any?> {
        return super.collectProperties() + buildMap {
            scrollbarStyle?.let { put("ScrollbarStyle", it) }
            itemStacks?.let { put("ItemStacks", it) }
            slotsPerRow?.let { put("SlotsPerRow", it) }
            showScrollbar?.let { put("ShowScrollbar", it) }
            style?.let { put("Style", it) }
            renderItemQualityBackground?.let { put("RenderItemQualityBackground", it) }
            infoDisplay?.let { put("InfoDisplay", it) }
            adjacentInfoPaneGridWidth?.let { put("AdjacentInfoPaneGridWidth", it) }
            areItemsDraggable?.let { put("AreItemsDraggable", it) }
            inventorySectionId?.let { put("InventorySectionId", it) }
            slots?.let { put("Slots", it) }
            allowMaxStackDraggableItems?.let { put("AllowMaxStackDraggableItems", it) }
            displayItemQuantity?.let { put("DisplayItemQuantity", it) }
        }
    }
}

// ItemSlot
class ItemSlotElement : LeafElement("ItemSlot") {
    var itemId: String? = null
    var quantity: Int? = null
    var showQuantity: Boolean? = null
    var showQualityBackground: Boolean? = null
    var showDurabilityBar: Boolean? = null

    override fun collectProperties(): Map<String, Any?> {
        return super.collectProperties() + buildMap {
            itemId?.let { put("ItemId", it) }
            quantity?.let { put("Quantity", it) }
            showQuantity?.let { put("ShowQuantity", it) }
            showQualityBackground?.let { put("ShowQualityBackground", it) }
            showDurabilityBar?.let { put("ShowDurabilityBar", it) }
        }
    }
}

// ItemIcon
class ItemIconElement : LeafElement("ItemIcon") {
    var itemId: String? = null
    var showItemTooltip: Boolean? = null

    override fun collectProperties(): Map<String, Any?> {
        return super.collectProperties() + buildMap {
            itemId?.let { put("ItemId", it) }
            showItemTooltip?.let { put("ShowItemTooltip", it) }
        }
    }
}

// BlockSelector
class BlockSelectorElement : LeafElement("BlockSelector") {
    var capacity: Int? = null
    var value: String? = null
    var style: BlockSelectorStyle? = null

    fun onValueChanged(handler: EventHandler) { eventHandlers["ValueChanged"] = handler }

    override fun collectProperties(): Map<String, Any?> {
        return super.collectProperties() + buildMap {
            capacity?.let { put("Capacity", it) }
            value?.let { put("Value", it) }
            style?.let { put("Style", it) }
        }
    }
}
