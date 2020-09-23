package com.mercadolibre.android.andesui.bottomsheet.factory

import com.mercadolibre.android.andesui.bottomsheet.state.AndesBottomSheetState
import com.mercadolibre.android.andesui.bottomsheet.title.AndesBottomSheetTitleAlignment

internal data class AndesBottomSheetConfiguration(
    val peekHeight: Int,
    val cornerRadius: Int,
    val backgroundColor: Int,
    val state: AndesBottomSheetState,
    val titleText: String?,
    val titleAlignment: AndesBottomSheetTitleAlignment,
    val buttonText: String?,
    val isBackgroundDimEnabled: Boolean
)

internal object AndesBottomSheetConfigurationFactory {

    fun create(andesBottomSheetAttrs: AndesBottomSheetAttrs): AndesBottomSheetConfiguration {
        return AndesBottomSheetConfiguration(
                peekHeight = andesBottomSheetAttrs.andesBottomSheetPeekHeight,
                cornerRadius = andesBottomSheetAttrs.andesBottomSheetCornerRadius,
                backgroundColor = andesBottomSheetAttrs.andesBottomSheetBackgroundColor,
                state = andesBottomSheetAttrs.andesBottomSheetState,
                titleText = andesBottomSheetAttrs.andesBottomSheetTitleText,
                titleAlignment = andesBottomSheetAttrs.andesBottomSheetTitleAlignment,
                buttonText = andesBottomSheetAttrs.andesBottomSheetButtonText,
                isBackgroundDimEnabled = andesBottomSheetAttrs.andesBottomSheetBackgroundDim
        )
    }
}
