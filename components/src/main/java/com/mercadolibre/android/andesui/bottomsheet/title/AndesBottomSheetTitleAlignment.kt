package com.mercadolibre.android.andesui.bottomsheet.title

enum class AndesBottomSheetTitleAlignment {
    LEFT_ALIGN,
    CENTERED;

    companion object {
        fun fromString(value: String): AndesBottomSheetTitleAlignment = valueOf(value.toUpperCase())
    }
}
