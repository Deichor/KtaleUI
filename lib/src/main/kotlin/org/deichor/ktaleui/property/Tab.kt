package org.deichor.ktaleui.property

data class Tab(
    var id: String? = null,
    var icon: Any? = null,
    var iconSelected: Any? = null,
    var iconAnchor: Anchor? = null,
    var text: String? = null,
    var tooltipText: String? = null,
)
