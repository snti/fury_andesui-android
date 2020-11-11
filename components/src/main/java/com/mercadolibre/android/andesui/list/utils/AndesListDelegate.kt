package com.mercadolibre.android.andesui.list.utils

import android.view.View
import com.mercadolibre.android.andesui.list.AndesListRow

/**
 * AndesListDelegate: interface to define the delegate of AndesList
 */
interface AndesListDelegate {
    fun onClickItem (position: Int)

    fun bind(view: View, position: Int) : AndesListRow

    fun getDataSetSize(): Int
}