package com.mercadolibre.android.andesui.list.size

/**
*
 */
enum class AndesListViewItemSize {
    SMALL,
    MEDIUM,
    LARGE;

    companion object {
        fun fromString(value: String): AndesListViewItemSize = valueOf(value.toUpperCase())
    }

    internal val size get() = getAndesListRowSize()

    private fun getAndesListRowSize(): AndesListViewItemSizeInterface {
        return when (this) {
            SMALL -> AndesListViewItemSmallSize()
            MEDIUM -> AndesListViewItemMediumSize()
            LARGE -> AndesListViewItemLargeSize()
        }
    }

}