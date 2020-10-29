package com.mercadolibre.android.andesui.datepicker.factory

import android.content.Context

internal data class AndesDatePickerConfiguration(
        val labelText: String? = null,
        val minDate: String? = null,
        val maxDate: String? = null

)

internal object AndesDatePickerConfigurationFactory {

    fun create(context: Context, andesDatePickerAttrs: AndesDatePickerAttrs): AndesDatePickerConfiguration {
        return with(andesDatePickerAttrs) {

            AndesDatePickerConfiguration(
                    labelText = label,
                    minDate = minDate,
                    maxDate = maxDate
            )
        }
    }
}