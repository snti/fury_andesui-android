package com.mercadolibre.android.andesui.carousel.factory

import com.mercadolibre.android.andesui.R
import android.content.Context
import android.util.AttributeSet
import com.mercadolibre.android.andesui.carousel.padding.AndesCarouselPadding

internal data class AndesCarouselAttrs(
        val andesCarouselCenter: Boolean,
        val andesCarouselItemLayout: Int?,
        val andesCarouselPadding: AndesCarouselPadding
    )

/**
 * This object parse the attribute set and return an instance of AndesCarouselAttrs
 * to be used by AndesCarousel
 */
internal object AndesCarouselAttrParser {

    private const val ANDES_CAROUSEL_PADDING_SMALL = "101"
    private const val ANDES_CAROUSEL_PADDING_MEDIUM = "101"
    private const val ANDES_CAROUSEL_PADDING_LARGE = "101"

    fun parse(context: Context, attr: AttributeSet?): AndesCarouselAttrs {

        val typedArray = context.obtainStyledAttributes(attr, R.styleable.AndesCarousel)

        val padding = when (typedArray.getString(R.styleable.AndesCarousel_andesCarouselPadding)) {
            ANDES_CAROUSEL_PADDING_SMALL -> AndesCarouselPadding.SMALL
            ANDES_CAROUSEL_PADDING_MEDIUM -> AndesCarouselPadding.MEDIUM
            ANDES_CAROUSEL_PADDING_LARGE -> AndesCarouselPadding.LARGE
            else -> AndesCarouselPadding.NONE
        }

        val layout = typedArray.getInteger(R.styleable.AndesCarousel_andesCarouselItemLayout,0)

        val center = typedArray.getBoolean(R.styleable.AndesCarousel_andesCarouselCenter, false)

        return AndesCarouselAttrs(
                andesCarouselCenter = center,
                andesCarouselPadding = padding,
                andesCarouselItemLayout = layout).also { typedArray.recycle() }
    }

}