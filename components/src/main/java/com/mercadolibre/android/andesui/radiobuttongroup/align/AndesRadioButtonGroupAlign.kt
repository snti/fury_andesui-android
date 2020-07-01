package com.mercadolibre.android.andesui.radiobuttongroup.align

enum class AndesRadioButtonGroupAlign {
    LEFT,
    RIGHT;

    companion object {
        fun fromString(value: String): AndesRadioButtonGroupAlign = valueOf(value.toUpperCase())
    }

    internal val type get() = getAndesRadiobuttonGroupAlign()

    private fun getAndesRadiobuttonGroupAlign(): AndesRadiobuttonGroupAlignInterface {
        return when (this) {
            LEFT -> AndesRadiobuttonGroupLeft
            RIGHT -> AndesRadiobuttonGroupRight
        }
    }
}
