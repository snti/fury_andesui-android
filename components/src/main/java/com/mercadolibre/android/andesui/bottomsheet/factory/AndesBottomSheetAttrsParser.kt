package com.mercadolibre.android.andesui.bottomsheet.factory

import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import com.mercadolibre.android.andesui.R
import com.mercadolibre.android.andesui.bottomsheet.state.AndesBottomSheetState

internal data class AndesBottomSheetAttrs(
    val andesBottomSheetPeekHeight: Int,
    val andesBottomSheetCornerRadius: Int,
    val andesBottomSheetBackgroundColor: Int,
    val andesBottomSheetState: AndesBottomSheetState
)

internal object AndesBottomSheetAttrsParser {

    private const val ANDES_BOTTOM_SHEET_STATE_COLLAPSED = "1000"
    private const val ANDES_BOTTOM_SHEET_STATE_EXPANDED = "1001"

    fun parse(context: Context, attr: AttributeSet?): AndesBottomSheetAttrs {
        val typedArray = context.obtainStyledAttributes(attr, R.styleable.AndesBottomSheet)

        val state = when (typedArray.getString(R.styleable.AndesBottomSheet_andesBottomSheetState)) {
            ANDES_BOTTOM_SHEET_STATE_COLLAPSED -> AndesBottomSheetState.COLLAPSED
            ANDES_BOTTOM_SHEET_STATE_EXPANDED -> AndesBottomSheetState.EXPANDED
            else -> AndesBottomSheetState.COLLAPSED
        }

        return AndesBottomSheetAttrs(
                andesBottomSheetPeekHeight =
                    typedArray.getDimension(R.styleable.AndesBottomSheet_andesBottomSheetPeekHeight, 0f).toInt(),
                andesBottomSheetCornerRadius =
                    typedArray.getDimension(R.styleable.AndesBottomSheet_andesBottomSheetCornerRadius, 0f).toInt(),
                andesBottomSheetBackgroundColor =
                    typedArray
                        .getColor(R.styleable.AndesBottomSheet_andesBottomSheetBackgroundColor, Color.TRANSPARENT),
                andesBottomSheetState = state
        ).also { typedArray.recycle() }
    }
}
