package com.mercadolibre.android.andesui.radiobutton.status

enum class AndesRadiobuttonStatus {
    SELECTED,
    UNSELECTED;

    companion object {
        fun fromString(value: String): AndesRadiobuttonStatus = valueOf(value.toUpperCase())
    }

    internal val status get() = getAndesRadiobuttonStatus()

    private fun getAndesRadiobuttonStatus(): AndesRadiobuttonStatusInterface {
        return when (this) {
            SELECTED -> AndesRadiobuttonStatusSelected
            UNSELECTED -> AndesRadiobuttonStatusUnselected
        }
    }
}
