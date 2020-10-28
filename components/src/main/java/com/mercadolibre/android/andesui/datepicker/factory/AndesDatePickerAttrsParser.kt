package com.mercadolibre.android.andesui.datepicker.factory

import android.content.Context
import android.util.AttributeSet
import com.mercadolibre.android.andesui.R

internal data class AndesDatePickerAttrs(
        var label: String?
)
internal object AndesDatePickerAttrsParser {

    fun parse(context: Context, attr: AttributeSet?): AndesDatePickerAttrs {

        val typedArray = context.obtainStyledAttributes(attr, R.styleable.AndesDatePicker)

        return AndesDatePickerAttrs(
                label = typedArray.getString(R.styleable.AndesDatePicker_andesDatePickerLabel)
        ).also { typedArray.recycle() }
    }
}