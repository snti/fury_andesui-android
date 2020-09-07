package com.mercadolibre.android.andesui.carousel.util

import android.view.View

interface ViewHolderListener {
    fun bind(view: View, model: Any)
}