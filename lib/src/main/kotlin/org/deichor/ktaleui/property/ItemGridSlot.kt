package org.deichor.ktaleui.property

data class ItemGridSlot(
    var itemStack: ClientItemStack? = null,
    var background: Any? = null,
    var extraOverlays: List<PatchStyle>? = null,
    var overlay: Any? = null,
    var icon: Any? = null,
    var isItemIncompatible: Boolean? = null,
    var name: String? = null,
    var description: String? = null,
    var inventorySlotIndex: Int? = null,
    var skipItemQualityBackground: Boolean? = null,
    var isActivatable: Boolean? = null,
    var isItemUncraftable: Boolean? = null,
)
