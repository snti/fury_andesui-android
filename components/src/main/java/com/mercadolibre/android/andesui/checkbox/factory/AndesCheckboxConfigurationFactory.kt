package com.mercadolibre.android.andesui.checkbox.factory

import com.mercadolibre.android.andesui.checkbox.factory.align.AndesCheckboxAlign
import com.mercadolibre.android.andesui.checkbox.status.AndesCheckboxStatus


internal data class AndesCheckboxConfiguration(
        val text: String? = null,
        val textSize: Int,
        val align: AndesCheckboxAlign?,
        val status: AndesCheckboxStatus?
)

internal object AndesCheckboxConfigurationFactory {

    fun create(andesCheckboxAttrs: AndesCheckboxAttrs): AndesCheckboxConfiguration {
        return with(andesCheckboxAttrs) {
            AndesCheckboxConfiguration(
                    text = andesCheckboxText,
                    textSize = resolveTextSize(),
                    align = andesCheckboxAttrs.andesCheckboxAlign,
                    status = andesCheckboxAttrs.andesCheckboxStatus
            )
        }
    }

    private fun resolveTextSize() = 16
}
