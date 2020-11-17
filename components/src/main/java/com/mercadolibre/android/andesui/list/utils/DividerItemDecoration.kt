package com.mercadolibre.android.andesui.list.utils

import android.graphics.Canvas
import android.support.v7.widget.RecyclerView

class DividerItemDecoration : RecyclerView.ItemDecoration() {

    override fun onDraw(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        val childCount = parent.childCount

        childCount

        super.onDraw(c, parent, state)

    }
}