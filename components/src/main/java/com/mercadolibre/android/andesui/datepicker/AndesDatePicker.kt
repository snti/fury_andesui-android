package com.mercadolibre.android.andesui.datepicker

import android.content.Context
import android.support.constraint.ConstraintLayout
import android.util.AttributeSet
import android.view.LayoutInflater
import com.mercadolibre.android.andesui.R
import com.mercadolibre.android.andesui.button.AndesButton
import com.mercadolibre.android.andesui.datepicker.factory.AndesDatePickerAttrs
import com.mercadolibre.android.andesui.datepicker.factory.AndesDatePickerAttrsParser
import com.mercadolibre.android.andesui.datepicker.factory.AndesDatePickerConfiguration
import com.mercadolibre.android.andesui.datepicker.factory.AndesDatePickerConfigurationFactory
import kotlinx.android.synthetic.main.andes_layout_datepicker.view.*
import java.util.*

class AndesDatePicker : ConstraintLayout {
    private lateinit var containerDatepicker: ConstraintLayout
    private lateinit var andesDatePickerAttrs: AndesDatePickerAttrs

    var label: String? = ""
        get() = andesDatePickerAttrs.label


    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        initAttrs(attrs)
    }

    private fun initAttrs(attrs: AttributeSet?) {
        andesDatePickerAttrs = AndesDatePickerAttrsParser.parse(context, attrs)
        val config = AndesDatePickerConfigurationFactory.create(context, andesDatePickerAttrs)
        setupComponents(config)
    }

    private fun setupComponents(config: AndesDatePickerConfiguration) {
        initComponents()
    }

    private fun initComponents() {
        val container = LayoutInflater.from(context).inflate(R.layout.andes_layout_datepicker, this, true)
        containerDatepicker = container.findViewById(R.id.andes_datepicker_container)
        onCheckedChangeListener(andesBtnSelectDate)
    }

    private fun onCheckedChangeListener(andesBtnSelectDate: AndesButton) {
        val calendar = Calendar.getInstance()

        calendarView.setOnDateChangeListener { view, year, month, dayOfMonth ->
            calendar.set(year,month,dayOfMonth)
        }
        andesBtnSelectDate.setOnClickListener {
            listener?.onDateApply(calendar)

        }

    }
    interface ApplyDatePickerClickListener {
        fun onDateApply(date: Calendar)
    }
    var listener: ApplyDatePickerClickListener? = null
    fun setDateListener (listener: ApplyDatePickerClickListener){
        if (listener != null ){
            this.listener = listener
        }
    }
}
