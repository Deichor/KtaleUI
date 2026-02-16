package org.deichor.ktaleui.property

data class PatchStyle(
    var area: Padding? = null,
    var color: String? = null,
    var anchor: Anchor? = null,
    var horizontalBorder: Int? = null,
    var verticalBorder: Int? = null,
    var texturePath: String? = null,
    var border: Int? = null,
)
