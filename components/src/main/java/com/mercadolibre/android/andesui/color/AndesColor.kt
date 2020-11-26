package com.mercadolibre.android.andesui.color

import android.content.Context
import android.graphics.Color
import androidx.annotation.ColorInt
import androidx.annotation.ColorRes
import androidx.core.graphics.ColorUtils

data class AndesColor(
    @ColorRes val colorRes: Int,
    var alpha: Float = 1f
) {
    @ColorInt
    fun colorInt(context: Context): Int =
            if (alpha == 1f) {
                colorRes.toColor(context)
            } else {
                ColorUtils.blendARGB(colorRes.toColor(context), Color.BLACK, alpha)
            }

    @ColorInt
    fun colorIntToAlpha(context: Context): Int =
        ColorUtils.setAlphaComponent(colorRes.toColor(context), convertAlphaToInt(alpha))

    private fun convertAlphaToInt(alpha: Float): Int = (255 * alpha).toInt()
}
