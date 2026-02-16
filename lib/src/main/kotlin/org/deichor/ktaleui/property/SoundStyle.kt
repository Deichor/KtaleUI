package org.deichor.ktaleui.property

data class SoundStyle(
    var soundPath: String? = null,
    var volume: Float? = null,
    var minPitch: Float? = null,
    var maxPitch: Float? = null,
    var stopExistingPlayback: Boolean? = null,
)
