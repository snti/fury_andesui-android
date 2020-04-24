package com.mercadolibre.android.andesui.font

import android.graphics.Paint
import android.widget.TextView

/**
 * Sets the typeface to the given view
 * @param <T>   A generic for the textview
 * @param font  The [Font] the text should have
 */
fun TextView.setTypeface(font: Font) {
    TypefaceHelper.setTypeface(this, font)
}

/**
 * Sets the typeface to the given [Paint]
 * @param paint   The paint to which apply the font
 * @param font    The [Font] the text should have
 */
fun TextView.setTypeface(paint: Paint, font: Font) {
    TypefaceHelper.setTypeface(context, paint, font)
}
