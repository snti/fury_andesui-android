package com.mercadolibre.android.andesui.textfield

import android.content.Context
import android.support.constraint.ConstraintLayout
import android.util.AttributeSet
import android.view.LayoutInflater
import com.mercadolibre.android.andesui.R

class AndesTextfield : ConstraintLayout {

    @Suppress("unused")
    private constructor(context: Context) : super(context) {
        throw IllegalStateException("Constructor without parameters in Andes Tex is not allowed. You must provide some attributes.")
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
        initComponents()
    }

    /**
     * Creates all the views that are part of this texfield.
     * After a view is created then a view id is added to it.
     *
     */
    private fun initComponents() {
        val container = LayoutInflater.from(context).inflate(R.layout.andes_layout_texfield, this, true)

    }

}