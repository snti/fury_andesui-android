package com.mercadolibre.android.andesui.bottomsheet.factory

import com.mercadolibre.android.andesui.bottomsheet.state.AndesBottomSheetState

internal data class AndesBottomSheetConfiguration(
    val peekHeight: Int,
    val cornerRadius: Int,
    val backgroundColor: Int,
    val state: AndesBottomSheetState
)

internal object AndesBottomSheetConfigurationFactory {

    fun create(andesBottomSheetAttrs: AndesBottomSheetAttrs): AndesBottomSheetConfiguration {
        return AndesBottomSheetConfiguration(
                peekHeight = andesBottomSheetAttrs.andesBottomSheetPeekHeight,
                cornerRadius = andesBottomSheetAttrs.andesBottomSheetCornerRadius,
                backgroundColor = andesBottomSheetAttrs.andesBottomSheetBackgroundColor,
                state = andesBottomSheetAttrs.andesBottomSheetState
        )
    }
}
