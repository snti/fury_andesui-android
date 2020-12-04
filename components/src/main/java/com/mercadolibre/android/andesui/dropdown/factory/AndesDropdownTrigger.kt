package com.mercadolibre.android.andesui.dropdown.factory

import android.content.Context

internal interface AndesDropdownTrigger {
    fun getType(context: Context): AndesDropdownTriggerType
}

internal object AndesDropdownTriggerTypeFromDropdown : AndesDropdownTrigger {
    override fun getType(context: Context) = AndesDropdownTriggerType.FORMDROPDOWN
}

internal object AndesDropdownTriggerTypeStandalone : AndesDropdownTrigger {
    override fun getType(context: Context) = AndesDropdownTriggerType.STANDALONE
}