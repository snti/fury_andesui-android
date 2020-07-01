package com.mercadolibre.android.andesui.radiobuttongroup.type

enum class AndesRadioButtonGroupType {
    IDLE,
    DISABLED,
    ERROR;

    companion object {
        fun fromString(value: String): AndesRadioButtonGroupType = valueOf(value.toUpperCase())
    }

    internal val type get() = getAndesRadioButtonGroupType()

    private fun getAndesRadioButtonGroupType(): AndesRadioButtonGroupTypeInterface {
        return when (this) {
            IDLE -> AndesRadioButtonGroupTypeIdle
            DISABLED -> AndesRadioButtonGroupTypeDisabled
            ERROR -> AndesRadioButtonGroupTypeError
        }
    }
}
