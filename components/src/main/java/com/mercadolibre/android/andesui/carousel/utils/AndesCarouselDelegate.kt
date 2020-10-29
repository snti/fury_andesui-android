package com.mercadolibre.android.andesui.carousel.utils

import android.view.View
import com.mercadolibre.android.andesui.carousel.AndesCarousel

/**
 * AndesCarouselDelegate: interface to define the delegate of AndesCarousel
 */
interface AndesCarouselDelegate {

    /**
     * Bind view that will executes in adapter of carousel
     */
    fun bind(andesCarouselView: AndesCarousel, view: View, position: Int)

    /**
     * Listener that will executes when user clicks the item
     */
    fun onClickItem (andesCarouselView: AndesCarousel, position: Int)

    /**
     * Amount of items in carousel
     */
    fun getDataSetSize(andesCarouselView: AndesCarousel): Int

    /**
     * View that will use to render item of carousel
     */
    fun getLayoutItem(andesCarouselView: AndesCarousel): Int
}
