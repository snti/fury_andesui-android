package com.mercadolibre.android.andesui.radiobutton.type

enum class AndesRadioButtonType {
    IDLE,
    DISABLED,
    ERROR;

    companion object {
        fun fromString(value: String): AndesRadioButtonType = valueOf(value.toUpperCase())
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
