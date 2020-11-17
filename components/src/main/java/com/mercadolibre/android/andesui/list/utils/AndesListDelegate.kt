package com.mercadolibre.android.andesui.list.utils

import android.view.View
import com.mercadolibre.android.andesui.list.AndesListViewItem
import com.mercadolibre.android.andesui.list.AndesListViewItemSimple

/**
 * AndesListDelegate: interface to define the delegate of AndesList
 */
interface AndesListDelegate {
    fun onItemClick (position: Int)

    fun bind(view: View, position: Int) : AndesListViewItem

    fun getDataSetSize(): Int
}