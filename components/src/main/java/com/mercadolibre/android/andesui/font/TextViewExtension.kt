package com.mercadolibre.android.andesui.font

import android.graphics.Paint
import android.widget.TextView

fun TextView.setTypeface(font: Font) {
    val typefaceSetter = CalligraphyTypefaceSetter()
    typefaceSetter.setTypeface(this, font)
}

fun TextView.setTypeface(paint: Paint, font: Font) {
    val typefaceSetter = CalligraphyTypefaceSetter()
    typefaceSetter.setTypeface(context, paint, font)
}