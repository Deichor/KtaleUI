package org.deichor.ktaleui.property

data class TabNavigationStyle(
    var tabStyle: TabStyle? = null,
    var selectedTabStyle: TabStyle? = null,
    var separatorAnchor: Anchor? = null,
    var separatorBackground: Any? = null,
    var tabSounds: ButtonSounds? = null,
)
