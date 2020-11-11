package com.mercadolibre.android.andesui.list.row.size

/**
 * Utility class that does two things: Defines the possible styles an [AndesListViewItem] can take
 * because it's an enum, as you can see.
 * But as a bonus it gives you the proper implementation so you don't have to make any mapping.
 *
 * @property size Possible styles that an [AndesListViewItem] may take.
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