package com.mercadolibre.android.andesui.list.row.size

import android.content.Context
import com.mercadolibre.android.andesui.R

/**
 * Defines all size related properties that an [AndesListViewItem] needs to be drawn properly.
 * Those properties change depending on the size of the tag.
 */
internal interface AndesListViewItemSizeInterface {
    fun lisViewItemSize(context: Context): Float
}

internal class AndesListViewItemSmallSize : AndesListViewItemSizeInterface {
    override fun lisViewItemSize(context: Context): Float = context.resources.getDimension(R.dimen.andes_list_view_item_size_small)
}

internal class AndesListViewItemMediumSize : AndesListViewItemSizeInterface {
    override fun lisViewItemSize(context: Context): Float = context.resources.getDimension(R.dimen.andes_list_view_item_size_medium)
}

internal class AndesListViewItemLargeSize : AndesListViewItemSizeInterface {
    override fun lisViewItemSize(context: Context): Float = context.resources.getDimension(R.dimen.andes_list_view_item_size_large)
}