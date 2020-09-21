package com.mercadolibre.android.andesui.utils

import android.content.Context
import kotlin.math.roundToInt

fun Int.pxToDp(context: Context): Int {
    val density = context.resources.displayMetrics.density
    return (this * density).roundToInt()
}
