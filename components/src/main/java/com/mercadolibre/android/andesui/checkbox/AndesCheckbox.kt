package com.mercadolibre.android.andesui.checkbox

import android.content.Context
import android.support.annotation.Nullable
import android.support.constraint.ConstraintLayout
import android.util.AttributeSet
import android.widget.CompoundButton.OnCheckedChangeListener
import com.mercadolibre.android.andesui.checkbox.factory.AndesCheckboxAttrParser
import com.mercadolibre.android.andesui.checkbox.factory.AndesCheckboxAttrs
import com.mercadolibre.android.andesui.checkbox.factory.AndesCheckboxConfiguration
import com.mercadolibre.android.andesui.checkbox.factory.AndesCheckboxConfigurationFactory
import com.mercadolibre.android.andesui.checkbox.factory.align.AndesCheckboxAlign
import com.mercadolibre.android.andesui.checkbox.status.AndesCheckboxStatus
import com.mercadolibre.android.andesui.checkbox.type.AndesCheckboxType


class AndesCheckbox : ConstraintLayout {


    /**
     * Getter and setter for [align].
     */
    var align: AndesCheckboxAlign?
        get() = andesCheckboxAttrs.andesCheckboxAlign
        set(value) {
            andesCheckboxAttrs = andesCheckboxAttrs.copy(andesCheckboxAlign = value)
        }

    var status: AndesCheckboxStatus?
        get() = andesCheckboxAttrs.andesCheckboxStatus
        set(value) {
            andesCheckboxAttrs = andesCheckboxAttrs.copy(andesCheckboxStatus = value)
        }

    private lateinit var andesCheckboxAttrs: AndesCheckboxAttrs


    constructor(context: Context, attrs: AttributeSet?, privateListener: OnCheckedChangeListener?) : super(context, attrs) {
        initAttrs(attrs)
    }

    constructor(
            context: Context,
            text: String,
            align: AndesCheckboxAlign? = ANDES_ALIGN_DEFAULT_VALUE,
            status: AndesCheckboxStatus? = ANDES_STATUS_DEFAULT_VALUE,
            type: AndesCheckboxType? = ANDES_TYPE_DEFAULT_VALUE) : super(context) {
        initAttrs(text, align, status, type)
    }

    /**
     * This is the listener set to the super class which is going to be evoke each
     * time the check state has changed.
     */
    private var privateListener: OnCheckedChangeListener? = null

    @Suppress("unused")
    fun setOnCheckedChangeListener(@Nullable listener: OnCheckedChangeListener) {

        // we never truly set the listener to the client implementation, instead we only hold
        // a reference to it and evoke it when needed.
        if (this.privateListener != listener) {
            this.privateListener = listener
        }
    }


    private fun onCheckedChangeListener() = OnCheckedChangeListener { buttonView, isChecked ->
        when (status) {
            AndesCheckboxStatus.CHECKED ->
                status = AndesCheckboxStatus.UNKNOWN
            AndesCheckboxStatus.UNCHECKED ->
                status = AndesCheckboxStatus.CHECKED
            AndesCheckboxStatus.UNKNOWN ->
                status = AndesCheckboxStatus.UNCHECKED
        }
        privateListener?.onCheckedChanged(buttonView, isChecked)
    }


    /**
     * Sets the proper [config] for this message based on the [attrs] received via XML.
     *
     * @param attrs attributes from the XML.
     */
    private fun initAttrs(attrs: AttributeSet?) {
        andesCheckboxAttrs = AndesCheckboxAttrParser.parse(context, attrs)
        val config = AndesCheckboxConfigurationFactory.create(andesCheckboxAttrs)
        setupComponents(config)
    }

    private fun initAttrs(
            text: String,
            align: AndesCheckboxAlign?,
            status: AndesCheckboxStatus?,
            type: AndesCheckboxType?
    ) {
        andesCheckboxAttrs = AndesCheckboxAttrs(align, text, status, type)
        val config = AndesCheckboxConfigurationFactory.create(andesCheckboxAttrs)
        setupComponents(config)
    }

    private fun setupComponents(config: AndesCheckboxConfiguration) {

    }

    companion object {
        private val ANDES_ALIGN_DEFAULT_VALUE = AndesCheckboxAlign.LEFT
        private val ANDES_STATUS_DEFAULT_VALUE = AndesCheckboxStatus.UNKNOWN
        private val ANDES_TYPE_DEFAULT_VALUE = AndesCheckboxType.IDLE
    }

}