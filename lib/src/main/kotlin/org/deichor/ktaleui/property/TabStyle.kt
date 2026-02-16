package org.deichor.ktaleui.property

data class TabStyle(
    var default: TabStyleState? = null,
    var hovered: TabStyleState? = null,
    var pressed: TabStyleState? = null,
)
