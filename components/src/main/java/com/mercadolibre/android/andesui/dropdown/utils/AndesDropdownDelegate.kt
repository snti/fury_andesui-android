package com.mercadolibre.android.andesui.dropdown.utils

import com.mercadolibre.android.andesui.dropdown.AndesDropDownForm

interface AndesDropdownDelegate {
    fun onItemSelected(andesDropDownForm: AndesDropDownForm, position: Int)
}