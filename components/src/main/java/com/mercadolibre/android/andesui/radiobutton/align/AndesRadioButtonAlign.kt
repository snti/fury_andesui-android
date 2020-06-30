package com.mercadolibre.android.andesui.radiobutton.align

enum class AndesRadioButtonAlign {
    LEFT,
    RIGHT;

    companion object {
        fun fromString(value: String): AndesRadioButtonAlign = valueOf(value.toUpperCase())
    }

    internal val type get() = getAndesRadiobuttonAlign()

    private fun getAndesRadiobuttonAlign(): AndesRadiobuttonAlignInterface {
        return when (this) {
            LEFT -> AndesRadiobuttonLeft
            RIGHT -> AndesRadiobuttonRight
        }
    }
}
