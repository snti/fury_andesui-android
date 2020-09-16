package com.mercadolibre.android.andesui.carousel.utils

import android.view.View
import com.mercadolibre.android.andesui.carousel.AndesCarousel

interface AndesCarouselDelegate {

    fun bind(andesCarouselView: AndesCarousel, view: View, position: Int)

    fun onClickItem (andesCarouselView: AndesCarousel, position: Int)

    fun getDataSetSize(andesCarouselView: AndesCarousel): Int

    fun getLayoutItem(andesCarouselView: AndesCarousel): Int
}
