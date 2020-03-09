package com.mercadolibre.android.andesui.textfield

import android.content.Context
import android.support.constraint.ConstraintLayout
import android.util.AttributeSet

class AndesTextfield : ConstraintLayout {

    @Suppress("unused")
    private constructor(context: Context) : super(context) {
        throw IllegalStateException("Constructor without parameters in Andes Message is not allowed. You must provide some attributes.")
    }

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        initAttrs(attrs)
    }

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs) {
        initAttrs(attrs)
    }

    private fun initAttrs(attrs: AttributeSet?) {
        /*
        andesTextfiledParser = AndesTextfieldParser.parse(context, attrs)
        val config = AndesTextfieldConfigurationFactory.create(context, andesMessageAttrs)
         */
    }

}