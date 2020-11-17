package com.mercadolibre.android.andesui.list.type

/**
 *
 */
enum class AndesListType {
    SIMPLE,
    CHEVRON;

    companion object {
        fun fromString(value: String): AndesListType = valueOf(value.toUpperCase())
    }

    internal val type get() = getAndesListType()

    private fun getAndesListType(): AndesListTypeInterface {
        return when (this) {
            SIMPLE -> AndesListTypeSimple
            CHEVRON -> AndesListTypeChevron
        }
    }

}