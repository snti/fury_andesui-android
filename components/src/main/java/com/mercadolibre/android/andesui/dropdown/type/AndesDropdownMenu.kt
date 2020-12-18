package com.mercadolibre.android.andesui.dropdown.type

import android.content.Context

internal interface AndesDropdownMenu {
    fun getType(context: Context): AndesDropdownMenuType
}

internal object AndesDropdownMenuTypeBottomSheet : AndesDropdownMenu {
    override fun getType(context: Context) = AndesDropdownMenuType.BOTTOMSHEET
}

internal object AndesDropdownMenuTypeFloatingMenu : AndesDropdownMenu {
    override fun getType(context: Context) = AndesDropdownMenuType.FLOATINGMENU
}
