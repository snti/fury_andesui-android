package com.mercadolibre.android.andesui.radiobutton.align

enum class AndesRadiobuttonAlign {
    LEFT,
    RIGHT;

    companion object {
        fun fromString(value: String): AndesRadiobuttonAlign = valueOf(value.toUpperCase())
    }

    internal val type get() = getAndesRadiobuttonAlign()

    private fun getAndesRadiobuttonAlign(): AndesRadiobuttonAlignInterface {
        return when (this) {
            LEFT -> AndesRadiobuttonLeft
            RIGHT -> AndesRadiobuttonRight
        }
    }
}
