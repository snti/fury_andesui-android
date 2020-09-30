package com.mercadolibre.android.andesui.bottomsheet.state

import android.support.design.widget.BottomSheetBehavior

internal interface AndesBottomSheetStateInterface {
    fun getState(): Int
}

internal object AndesBottomSheetStateExpanded : AndesBottomSheetStateInterface {
    override fun getState(): Int = BottomSheetBehavior.STATE_EXPANDED
}

internal object AndesBottomSheetStateCollapsed: AndesBottomSheetStateInterface{
    override  fun getState(): Int = BottomSheetBehavior.STATE_COLLAPSED
}