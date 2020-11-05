package com.mercadolibre.android.andesui.datepicker.factory

internal data class AndesDatePickerConfiguration(
        val text: String?,
        val minDate: String?,
        val maxDate: String?,
        val btnVisibility : Boolean?
)

internal object AndesDatePickerConfigurationFactory {

    fun create(andesDatePickerAttrs: AndesDatePickerAttrs): AndesDatePickerConfiguration {
        return with(andesDatePickerAttrs) {
            AndesDatePickerConfiguration(
                    text = andesDatePickerAttrs.andesDatePickerText,
                    minDate = andesDatePickerAttrs.andesDatePickerMinDate,
                    maxDate = andesDatePickerAttrs.andesDatePickerMaxDate,
                    btnVisibility = andesDatePickerAttrs.andesBtnVisibility
            )
        }
    }

}
