package com.mercadolibre.android.andesui.carousel.utils

import android.view.View

interface AndesCarouselDelegate {
    fun bind(view: View, position: Int)
    fun onClickItem (position: Int)
    fun getDataSetSize(): Int
    fun getLayoutItem(): Int
}
