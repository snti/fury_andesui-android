package com.mercadolibre.android.andesui.carousel.margin

import android.content.Context
import com.mercadolibre.android.andesui.R

/**
 * Defines all types related properties that an [AndesCarousel] needs to be drawn properly.
 * Those properties change depending on the style of the andesCarousel.
 */
internal interface AndesCarouselMarginInterface {
    /**
     * Return dimen of margin to separate items in Carousel
     */
    fun getMargin(context: Context): Int
}

internal object AndesCarouselMarginNone : AndesCarouselMarginInterface {
    override fun getMargin(context: Context) = context.resources.getDimension(R.dimen.andes_carousel_padding_none).toInt()
}

internal object AndesCarouselMarginSmall : AndesCarouselMarginInterface {
    override fun getMargin(context: Context) = context.resources.getDimension(R.dimen.andes_carousel_padding_small).toInt()
}

internal object AndesCarouselMarginMedium : AndesCarouselMarginInterface {
    override fun getMargin(context: Context) = context.resources.getDimension(R.dimen.andes_carousel_padding_medium).toInt()
}

internal object AndesCarouselMarginLarge : AndesCarouselMarginInterface {
    override fun getMargin(context: Context) = context.resources.getDimension(R.dimen.andes_carousel_padding_large).toInt()
}
