package com.mercadolibre.android.andesui.carousel.padding

import android.content.Context
import com.mercadolibre.android.andesui.R

/**
 * Defines all types related properties that an [AndesCarousel] needs to be drawn properly.
 * Those properties change depending on the style of the andesCarousel.
 */
internal interface AndesCarouselPaddingInterface {
    /**
     * Return dimen of padding to separate items in Carousel
     */
    fun getPadding(context: Context): Int
}

internal object AndesCarouselPaddingNone : AndesCarouselPaddingInterface {
    override fun getPadding(context: Context) = context.resources.getDimension(R.dimen.andes_carousel_padding_none).toInt()
}

internal object AndesCarouselPaddingSmall : AndesCarouselPaddingInterface {
    override fun getPadding(context: Context) = context.resources.getDimension(R.dimen.andes_carousel_padding_small).toInt()
}

internal object AndesCarouselPaddingMedium : AndesCarouselPaddingInterface {
    override fun getPadding(context: Context) = context.resources.getDimension(R.dimen.andes_carousel_padding_medium).toInt()
}

internal object AndesCarouselPaddingLarge : AndesCarouselPaddingInterface {
    override fun getPadding(context: Context) = context.resources.getDimension(R.dimen.andes_carousel_padding_large).toInt()
}

