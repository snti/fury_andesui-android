package com.mercadolibre.android.andesui.checkbox.status

enum class AndesCheckboxStatus {
    UNKNOWN,
    CHECKED,
    UNCHECKED;

    companion object {
        fun fromString(value: String): AndesCheckboxStatus = valueOf(value.toUpperCase())
    }

    internal val type get() = getAndesCheckboxHierarchy()

    private fun getAndesCheckboxHierarchy(): AndesCheckboxStatusInterface {
        return when (this) {
            CHECKED -> AndesCheckboxStatusChecked
            UNCHECKED -> AndesCheckboxStatusUnchecked
            UNKNOWN -> AndesCheckboxStatusUnknown
        }
    }
}