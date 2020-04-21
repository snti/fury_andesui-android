package com.mercadolibre.android.andesui.font

import android.graphics.Paint
import android.widget.TextView

fun TextView.setTypeface(font: Font) {
    TypefaceHelper.setTypeface(this, font)
}

fun TextView.setTypeface(paint: Paint, font: Font) {
    TypefaceHelper.setTypeface(context, paint, font)
}