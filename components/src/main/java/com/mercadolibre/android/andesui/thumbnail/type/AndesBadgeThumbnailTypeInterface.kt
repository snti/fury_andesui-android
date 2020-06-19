package com.mercadolibre.android.andesui.thumbnail.type

import com.mercadolibre.android.andesui.R
import com.mercadolibre.android.andesui.color.AndesColor
import com.mercadolibre.android.andesui.color.toAndesColor

/**
 * Defines all types related properties that an [AndesThumbnail] needs to be drawn properly.
 * Those properties change depending on the style of the badge.
 *
 */
internal interface AndesThumbnailTypeInterface {

    /**
     * Returns a [AndesColor] that contains the primary color data for the badge.
     *
     * @return a [AndesColor] that contains the primary color data for the badge.
     */
    fun primaryColor(): AndesColor
}

internal class AndesIconThumbnailType : AndesThumbnailTypeInterface {
    override fun primaryColor() = R.color.andes_gray_450_solid.toAndesColor()
}
