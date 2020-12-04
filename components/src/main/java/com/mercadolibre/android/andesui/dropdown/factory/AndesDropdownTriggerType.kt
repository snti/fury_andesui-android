package com.mercadolibre.android.andesui.dropdown.factory

enum class AndesDropdownTriggerType {
    FORMDROPDOWN,
    STANDALONE;

    companion object {
        fun fromString(value: String): AndesDropdownTriggerType = valueOf(value.toUpperCase())
    }

    internal val type get() = getAndesDropdownTriggerType()

    private fun getAndesDropdownTriggerType(): AndesDropdownTrigger {
        return when (this) {
            FORMDROPDOWN -> AndesDropdownTriggerTypeFromDropdown
            STANDALONE -> AndesDropdownTriggerTypeStandalone
        }
    }
}