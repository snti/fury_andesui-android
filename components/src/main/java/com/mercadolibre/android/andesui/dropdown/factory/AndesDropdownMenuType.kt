package com.mercadolibre.android.andesui.dropdown.factory

enum class AndesDropdownMenuType {
    BOTTOMSHEET,
    FLOATINGMENU;

    companion object {
        fun fromString(value: String): AndesDropdownMenuType = AndesDropdownMenuType.valueOf(value.toUpperCase())
    }

    internal val type get() = getAndesDropdownMenuType()

    private fun getAndesDropdownMenuType(): AndesDropdownMenu {
        return when (this) {
            BOTTOMSHEET -> AndesDropdownMenuTypeBottomSheet
            FLOATINGMENU -> AndesDropdownMenuTypeFloatingMenu
        }
    }
}