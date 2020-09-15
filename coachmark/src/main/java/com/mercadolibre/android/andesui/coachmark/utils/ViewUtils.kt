package com.mercadolibre.android.andesui.coachmark.utils

import android.content.res.Resources
import android.view.View
import android.view.ViewTreeObserver

internal object ViewUtils {

    fun pxToDp(px: Int): Int {
        return (px / Resources.getSystem().displayMetrics.density).toInt()
    }

    fun dpToPx(dp: Int): Int {
        return (dp * Resources.getSystem().displayMetrics.density).toInt()
    }

    fun dpToPx(dp: Float): Float {
        return (dp * Resources.getSystem().displayMetrics.density)
    }

    fun doAfterLayout(view: View, listen: () -> Unit) {
        val listener = object : ViewTreeObserver.OnGlobalLayoutListener {
            override fun onGlobalLayout() {
                val viewTreeObserver = view.viewTreeObserver
                viewTreeObserver.removeOnGlobalLayoutListener(this)
                listen.invoke()
            }
        }
        view.viewTreeObserver.addOnGlobalLayoutListener(listener)
    }

    const val TOOLBAR_STATUS_BAR_SIZE = 80
    const val TOOLBAR_SIZE = 64
    const val STATUS_BAR_SIZE = 26
}
