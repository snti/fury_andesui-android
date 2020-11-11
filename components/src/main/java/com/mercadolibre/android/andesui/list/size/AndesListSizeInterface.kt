package com.mercadolibre.android.andesui.list.size

import android.content.Context
import com.mercadolibre.android.andesui.R

/**
 * Defines all types related properties that an [AndesList] needs to be drawn properly.
 * Those properties change depending on the style of the andesList.
 */
internal interface AndesListSizeInterface {
    //TODO AndesList: Replace this with your requirement
    fun textSize(context: Context): Int
}

internal object AndesListSizeValue1 : AndesListSizeInterface {
    //TODO AndesList: Replace this with your requirement
    override fun textSize(context: Context): Int = context.resources.getDimension(R.dimen.andes_list_text_small).toInt()
}

internal object AndesListSizeValue2 : AndesListSizeInterface {
    //TODO AndesList: Replace this with your requirement
    override fun textSize(context: Context): Int = context.resources.getDimension(R.dimen.andes_list_text_large).toInt()
}