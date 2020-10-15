package com.mercadolibre.android.andesui.carousel.utils

import android.graphics.Rect
import android.support.v7.widget.RecyclerView
import android.view.View

/**
 * AndesCarouselPaddingItemDecoration: override the itemDecoration to recyclerview which uses in AndesCarousel
 */
class AndesCarouselPaddingItemDecoration(private val padding: Int) : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {

        val lastPosition = parent.adapter?.let {
            it.itemCount - 1
        } ?: FIRST_POSITION

        with(outRect) {
            when (parent.getChildAdapterPosition(view)) {
                FIRST_POSITION -> right = padding
                lastPosition -> left = padding
                else -> {
                    left = padding
                    right = padding
                }
            }
        }
    }

    companion object {
        private const val FIRST_POSITION = 0
    }
}
