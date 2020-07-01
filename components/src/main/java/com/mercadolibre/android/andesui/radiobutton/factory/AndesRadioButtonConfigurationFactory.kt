package com.mercadolibre.android.andesui.radiobutton.factory

import com.mercadolibre.android.andesui.radiobutton.align.AndesRadioButtonAlign
import com.mercadolibre.android.andesui.radiobutton.status.AndesRadioButtonStatus
import com.mercadolibre.android.andesui.radiobutton.type.AndesRadioButtonType

internal data class AndesRadioButtonConfiguration(
    val text: String?,
    val textSize: Int,
    val align: AndesRadioButtonAlign,
    val status: AndesRadioButtonStatus,
    val type: AndesRadioButtonType
)

internal object AndesRadioButtonConfigurationFactory {

    fun create(andesRadiobuttonAttrs: AndesRadioButtonAttrs): AndesRadioButtonConfiguration {
        return with(andesRadiobuttonAttrs) {
            val validatedStatus = if (andesRadiobuttonAttrs.andesRadioButtonType == AndesRadioButtonType.ERROR) {
                AndesRadioButtonStatus.UNSELECTED
            } else {
                andesRadiobuttonAttrs.andesRadioButtonStatus
            }
            AndesRadioButtonConfiguration(
                    text = andesRadiobuttonText,
                    textSize = resolveTextSize(),
                    align = andesRadiobuttonAttrs.andesRadioButtonAlign,
                    status = validatedStatus,
                    type = andesRadiobuttonAttrs.andesRadioButtonType
            )
        }
    }

    private fun resolveTextSize() = 16
}
