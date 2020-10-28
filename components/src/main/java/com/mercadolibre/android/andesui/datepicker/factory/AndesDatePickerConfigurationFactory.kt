package com.mercadolibre.android.andesui.datepicker.factory

import android.content.Context
import com.mercadolibre.android.andesui.textfield.factory.AndesTextareaAttrs

internal data class AndesDatePickerConfiguration(
        val labelText: String? = null
)

internal object AndesDatePickerConfigurationFactory {

    fun create(context: Context, andesDatePickerAttrs: AndesDatePickerAttrs): AndesDatePickerConfiguration {
        return with(andesDatePickerAttrs) {

            AndesDatePickerConfiguration(
                    labelText = label
            )
        }
    }

    fun create(context: Context, andesTextareaAttrs: AndesTextareaAttrs): AndesDatePickerConfiguration {
        return with(andesTextareaAttrs) {

            AndesDatePickerConfiguration(
                    labelText = label
            )
        }
    }
}