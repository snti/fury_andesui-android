package com.mercadolibre.android.andesui.radiobutton.status

enum class AndesRadioButtonStatus {
    SELECTED,
    UNSELECTED;

    companion object {
        fun fromString(value: String): AndesRadioButtonStatus = valueOf(value.toUpperCase())
    }

    internal val status get() = getAndesRadiobuttonStatus()

    private fun getAndesRadiobuttonStatus(): AndesRadiobuttonStatusInterface {
        return when (this) {
            SELECTED -> AndesRadiobuttonStatusSelected
            UNSELECTED -> AndesRadiobuttonStatusUnselected
        }
    }
}
