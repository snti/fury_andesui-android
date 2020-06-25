package com.mercadolibre.android.andesui.radiobutton.factory

import com.mercadolibre.android.andesui.radiobutton.align.AndesRadiobuttonAlign
import com.mercadolibre.android.andesui.radiobutton.status.AndesRadiobuttonStatus
import com.mercadolibre.android.andesui.radiobutton.type.AndesRadiobuttonType

internal data class AndesRadiobuttonConfiguration(
    val text: String?,
    val textSize: Int,
    val align: AndesRadiobuttonAlign,
    val status: AndesRadiobuttonStatus,
    val type: AndesRadiobuttonType
)

internal object AndesRadiobuttonConfigurationFactory {

    fun create(andesRadiobuttonAttrs: AndesRadiobuttonAttrs): AndesRadiobuttonConfiguration {
        return with(andesRadiobuttonAttrs) {
            val validatedStatus = if (andesRadiobuttonAttrs.andesRadiobuttonType == AndesRadiobuttonType.ERROR) {
                AndesRadiobuttonStatus.UNSELECTED
            } else {
                andesRadiobuttonAttrs.andesRadiobuttonStatus
            }
            AndesRadiobuttonConfiguration(
                    text = andesRadiobuttonText,
                    textSize = resolveTextSize(),
                    align = andesRadiobuttonAttrs.andesRadiobuttonAlign,
                    status = validatedStatus,
                    type = andesRadiobuttonAttrs.andesRadiobuttonType
            )
        }
    }

    private fun resolveTextSize() = 16
}
