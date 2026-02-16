package org.deichor.ktaleui.property

data class ClientItemStack(
    var id: String? = null,
    var quantity: Int? = null,
    var durability: Double? = null,
    var maxDurability: Double? = null,
    var overrideDroppedItemAnimation: Boolean? = null,
)
