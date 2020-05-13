package com.mercadolibre.android.andesui.typeface

import android.content.Context
import android.graphics.Typeface
import com.mercadolibre.android.andesui.font.Font
import com.mercadolibre.android.andesui.font.TypefaceHelper

/**
 * Extension function that retrieves a desired typeface if it founds it.
 * Otherwise, it returns the (provided or not) default typeface.
 *
 * @param desiredTypeface the int res id that identifies typeface we want to retrieve
 * @param defaultTypeface the typeface we'd like to get if the desired is not available. By default is the system's default
 * @return the found typeface
 */
fun Context.getFontOrDefault(font: Font, defaultTypeface: Typeface = Typeface.DEFAULT): Typeface {

    val test = TypefaceHelper.getFontTypeface(this, font)

    return TypefaceHelper.getFontTypeface(this, font) ?: defaultTypeface
}