package org.deichor.ktaleui.property

data class ItemGridStyle(
    var slotSize: Int? = null,
    var slotIconSize: Int? = null,
    var slotSpacing: Int? = null,
    var durabilityBarBackground: Any? = null,
    var durabilityBar: String? = null,
    var durabilityBarAnchor: Anchor? = null,
    var durabilityBarColorStart: String? = null,
    var durabilityBarColorEnd: String? = null,
    var cursedIconPatch: Any? = null,
    var cursedIconAnchor: Anchor? = null,
    var slotBackground: Any? = null,
    var quantityPopupSlotOverlay: Any? = null,
    var brokenSlotBackgroundOverlay: Any? = null,
    var brokenSlotIconOverlay: Any? = null,
    var defaultItemIcon: Any? = null,
    var itemStackActivateSound: SoundStyle? = null,
    var itemStackHoveredSound: SoundStyle? = null,
)
