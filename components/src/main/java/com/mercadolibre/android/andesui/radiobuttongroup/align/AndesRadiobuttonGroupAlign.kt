package com.mercadolibre.android.andesui.radiobuttongroup.align

enum class AndesRadiobuttonGroupAlign {
    LEFT,
    RIGHT;

    companion object {
        fun fromString(value: String): AndesRadiobuttonGroupAlign = valueOf(value.toUpperCase())
    }

    internal val type get() = getAndesRadiobuttonGroupAlign()

    private fun getAndesRadiobuttonGroupAlign(): AndesRadiobuttonGroupAlignInterface {
        return when (this) {
            LEFT -> AndesRadiobuttonGroupLeft
            RIGHT -> AndesRadiobuttonGroupRight
        }
    }
}
