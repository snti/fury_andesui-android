package com.mercadolibre.android.andesui.radiobuttongroup.distribution

enum class AndesRadioButtonGroupDistribution {
    VERTICAL,
    HORIZONTAL;

    companion object {
        fun fromString(value: String): AndesRadioButtonGroupDistribution = valueOf(value.toUpperCase())
    }

    internal val type get() = getAndesRadiobuttonGroupAlign()

    private fun getAndesRadiobuttonGroupAlign(): AndesRadiobuttonGroupDistributionInterface {
        return when (this) {
            VERTICAL -> AndesRadiobuttonGroupVertical
            HORIZONTAL -> AndesRadiobuttonGroupHorizontal
        }
    }
}
