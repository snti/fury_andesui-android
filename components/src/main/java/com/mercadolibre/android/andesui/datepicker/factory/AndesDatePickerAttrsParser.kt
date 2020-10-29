package com.mercadolibre.android.andesui.datepicker.factory

import android.content.Context
import android.util.AttributeSet
import com.mercadolibre.android.andesui.R

internal data class AndesDatePickerAttrs(
        val label: String?,
        val minDate: String?,
        val maxDate: String?
)
internal object AndesDatePickerAttrsParser {

    fun parse(context: Context, attr: AttributeSet?): AndesDatePickerAttrs {

        val typedArray = context.obtainStyledAttributes(attr, R.styleable.AndesDatePicker)

        return AndesDatePickerAttrs(
                label = typedArray.getString(R.styleable.AndesDatePicker_andesDatePickerLabel),
                minDate = typedArray.getString(R.styleable.AndesDatePicker_andesDatePickerMinDate),
                maxDate = typedArray.getString(R.styleable.AndesDatePicker_andesDatePickerMaxDate)
                ).also { typedArray.recycle() }
    }
}

