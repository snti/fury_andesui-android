package com.mercadolibre.android.andesui.list.size

import com.mercadolibre.android.andesui.list.row.size.AndesListViewItemLargeSize
import com.mercadolibre.android.andesui.list.row.size.AndesListViewItemMediumSize
import com.mercadolibre.android.andesui.list.row.size.AndesListViewItemSizeInterface
import com.mercadolibre.android.andesui.list.row.size.AndesListViewItemSmallSize

/**
 * Utility class that does two things: Defines the possible styles an [AndesList] can take
 * because it's an enum, as you can see.
 * But as a bonus it gives you the proper implementation so you don't have to make any mapping.
 *
 * @property type Possible styles that an [AndesList] may take.
 */
enum class AndesListSize {
    SMALL,
    MEDIUM,
    LARGE;

    companion object {
        fun fromString(value: String): AndesListSize = valueOf(value.toUpperCase())
    }

    internal val size get() = getAndesListSize()

    private fun getAndesListSize(): AndesListViewItemSizeInterface {
        return when (this) {
            SMALL -> AndesListViewItemSmallSize()
            MEDIUM -> AndesListViewItemMediumSize()
            LARGE -> AndesListViewItemLargeSize()
        }
    }

}