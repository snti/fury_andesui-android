package com.mercadolibre.android.andesui.radiobutton.type

enum class AndesRadiobuttonType {
    IDLE,
    DISABLED,
    ERROR;

    companion object {
        fun fromString(value: String): AndesRadiobuttonType = valueOf(value.toUpperCase())
    }

    internal val type get() = getAndesRadiobuttonType()

    private fun getAndesRadiobuttonType(): AndesRadiobuttonTypeInterface {
        return when (this) {
            IDLE -> AndesRadiobuttonTypeIdle
            DISABLED -> AndesRadiobuttonTypeDisabled
            ERROR -> AndesRadiobuttonTypeError
        }
    }
}
