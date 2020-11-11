package com.mercadolibre.android.andesui.list.factory

import android.content.Context
import android.util.AttributeSet
import com.mercadolibre.android.andesui.R
import com.mercadolibre.android.andesui.list.row.size.AndesListViewItemSize

internal data class AndesListAttrs(
        val andesListViewItemSize: AndesListViewItemSize
)

/**
 * This object parse the attribute set and return an instance of AndesListAttrs
 * to be used by AndesList
 */
internal object AndesListAttrParser {

    private const val ANDES_LIST_SIZE_SMALL = "9000"
    private const val ANDES_LIST_SIZE_MEDIUM = "9001"
    private const val ANDES_LIST_SIZE_LARGE = "9002"

    fun parse(context: Context, attr: AttributeSet?): AndesListAttrs {
        val typedArray = context.obtainStyledAttributes(attr, R.styleable.AndesList)

        val size = when (typedArray.getString(R.styleable.AndesList_andesListViewItemSize)) {
            ANDES_LIST_SIZE_SMALL -> AndesListViewItemSize.SMALL
            ANDES_LIST_SIZE_MEDIUM -> AndesListViewItemSize.MEDIUM
            ANDES_LIST_SIZE_LARGE -> AndesListViewItemSize.LARGE
            else -> AndesListViewItemSize.MEDIUM
        }

        return AndesListAttrs(
                andesListViewItemSize = size
        ).also { typedArray.recycle() }
    }

}