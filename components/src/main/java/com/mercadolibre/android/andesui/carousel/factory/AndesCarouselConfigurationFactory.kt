package com.mercadolibre.android.andesui.carousel.factory

import android.content.Context
import com.mercadolibre.android.andesui.carousel.padding.AndesCarouselPaddingInterface

internal data class AndesCarouselConfiguration(val padding: Int, val layout: Int, val center: Boolean)

internal object AndesCarouselConfigurationFactory {

    fun create(context: Context, andesCarouselAttrs: AndesCarouselAttrs): AndesCarouselConfiguration {
        val padding = andesCarouselAttrs.andesCarouselPadding.padding
        return AndesCarouselConfiguration(
            padding = resolveItemPadding(padding, context),
            layout = andesCarouselAttrs.andesCarouselItemLayout,
            center = andesCarouselAttrs.andesCarouselCenter
        )
    }

    private fun resolveItemPadding(padding: AndesCarouselPaddingInterface, context: Context) = padding.getPadding(context)
}
