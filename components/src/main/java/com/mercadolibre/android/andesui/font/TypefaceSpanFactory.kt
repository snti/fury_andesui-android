package com.mercadolibre.android.andesui.font

import android.graphics.Typeface
import android.os.Build
import android.text.style.TypefaceSpan

/**
 * This class wrap the TypefaceSpan creation to split according to the Android API version
 */
class TypefaceSpanFactory {
    fun create(typeface: Typeface): TypefaceSpan {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            TypefaceSpan(typeface)
        } else TypefaceSpanCompat(typeface)
    }
}