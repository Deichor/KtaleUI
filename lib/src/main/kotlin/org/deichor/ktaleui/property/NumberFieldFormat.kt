package org.deichor.ktaleui.property

data class NumberFieldFormat(
    var maxDecimalPlaces: Int? = null,
    var step: Double? = null,
    var minValue: Double? = null,
    var maxValue: Double? = null,
    var defaultValue: Double? = null,
    var suffix: String? = null,
)
