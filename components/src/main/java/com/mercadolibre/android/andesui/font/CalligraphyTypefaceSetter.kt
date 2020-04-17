package com.mercadolibre.android.andesui.font

import android.content.Context
import android.graphics.Paint
import android.graphics.Typeface
import android.widget.Switch
import android.widget.TextView
import com.mercadolibre.android.andesui.font.TypefaceHelper.TypefaceSetter
import uk.co.chrisjenx.calligraphy.TypefaceUtils

/**
 * This TypefaceSetter implements Calligraphy library.
 */
class CalligraphyTypefaceSetter : TypefaceSetter {

    override fun <T : TextView?> setTypeface(view: T, font: Font) {
        val typeface = createTypeface(view!!.context, font)
        view.typeface = typeface
        if (view is Switch) {
            (view as Switch).setSwitchTypeface(typeface)
        }
    }

    override fun setTypeface(context: Context, paint: Paint, font: Font) {
        val typeface = createTypeface(context, font)
        paint.typeface = typeface
    }

    override fun getTypeface(context: Context, font: Font): Typeface? {
        return createTypeface(context, font)
    }

    private fun createTypeface(context: Context, font: Font): Typeface? {
        return TypefaceUtils.load(context.assets, font.fontPath)
    }

}