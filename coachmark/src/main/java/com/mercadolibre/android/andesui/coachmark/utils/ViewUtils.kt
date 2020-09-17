package com.mercadolibre.android.andesui.coachmark.utils

import android.content.res.Resources

internal object ViewUtils {

    fun dpToPx(dp: Float): Float {
        return (dp * Resources.getSystem().displayMetrics.density)
    }

    const val ANIMATION_TOOLTIP_DURARION = 500L
    const val ANIMATION_OVERLAY_DURATION = 400L
    const val ANIMATION_SCROLL_DURATION = 1000L
}
