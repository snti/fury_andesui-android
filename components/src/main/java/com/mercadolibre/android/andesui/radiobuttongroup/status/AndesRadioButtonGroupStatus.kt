package com.mercadolibre.android.andesui.radiobuttongroup.status

enum class AndesRadioButtonGroupStatus {
    SELECTED,
    UNSELECTED;

    companion object {
        fun fromString(value: String): AndesRadioButtonGroupStatus = valueOf(value.toUpperCase())
    }

    internal val status get() = getAndesRadioButtonGroupStatus()

    private fun getAndesRadioButtonGroupStatus(): AndesRadioButtonGroupStatusInterface {
        return when (this) {
            SELECTED -> AndesRadioButtonGroupStatusSelected
            UNSELECTED -> AndesRadioButtonGroupStatusUnselected
        }
    }
}
