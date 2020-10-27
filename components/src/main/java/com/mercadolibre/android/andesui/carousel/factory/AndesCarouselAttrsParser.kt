package com.mercadolibre.android.andesui.carousel.factory

import com.mercadolibre.android.andesui.R
import android.content.Context
import android.util.AttributeSet
import com.mercadolibre.android.andesui.carousel.margin.AndesCarouselMargin

internal data class AndesCarouselAttrs(
    val andesCarouselCenter: Boolean,
    val andesCarouselMargin: AndesCarouselMargin
)

/**
 * This object parse the attribute set and return an instance of AndesCarouselAttrs
 * to be used by AndesCarousel
 */
internal object AndesCarouselAttrParser {

    private const val ANDES_CAROUSEL_PADDING_NONE = "100"
    private const val ANDES_CAROUSEL_PADDING_SMALL = "101"
    private const val ANDES_CAROUSEL_PADDING_MEDIUM = "102"
    private const val ANDES_CAROUSEL_PADDING_LARGE = "103"

    fun parse(context: Context, attr: AttributeSet?): AndesCarouselAttrs {

        val typedArray = context.obtainStyledAttributes(attr, R.styleable.AndesCarousel)

        val margin = when (typedArray.getString(R.styleable.AndesCarousel_andesCarouselMargin)) {
            ANDES_CAROUSEL_PADDING_NONE -> AndesCarouselMargin.NONE
            ANDES_CAROUSEL_PADDING_SMALL -> AndesCarouselMargin.SMALL
            ANDES_CAROUSEL_PADDING_MEDIUM -> AndesCarouselMargin.MEDIUM
            ANDES_CAROUSEL_PADDING_LARGE -> AndesCarouselMargin.LARGE
            else -> AndesCarouselMargin.SMALL
        }

        val center = typedArray.getBoolean(R.styleable.AndesCarousel_andesCarouselCenter, false)

        return AndesCarouselAttrs(
                andesCarouselCenter = center,
                andesCarouselMargin = margin
        ).also { typedArray.recycle() }
    }
}
