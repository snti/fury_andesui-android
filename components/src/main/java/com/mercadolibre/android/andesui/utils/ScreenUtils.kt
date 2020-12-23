package com.mercadolibre.android.andesui.utils

import android.content.res.Resources

class ScreenUtils {
    companion object {
        internal fun getScreenHeight(): Int {
            return Resources.getSystem().displayMetrics.heightPixels
        }
    }
}