package com.mercadolibre.android.andesui.textfield

import android.content.Context
import android.graphics.Typeface
import android.support.constraint.ConstraintLayout
import android.text.Editable
import android.text.InputFilter
import android.text.TextWatcher
import android.util.AttributeSet
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.widget.EditText
import android.widget.TextView
import com.facebook.drawee.view.SimpleDraweeView
import com.mercadolibre.android.andesui.R
import com.mercadolibre.android.andesui.textfield.factory.*
import com.mercadolibre.android.andesui.textfield.state.AndesTextfieldState


class AndesTextfield : ConstraintLayout {

    /**
     * Getter and setter for [label].
     */
    var label: String?
        get() = andesTextfieldAttrs.label
        set(value) {
            andesTextfieldAttrs = andesTextfieldAttrs.copy(label = value)
            setupLabelComponent(createConfig())
        }

    /**
     * Getter and setter for [helper].
     */
    var helper: String?
        get() = andesTextfieldAttrs.helper
        set(value) {
            andesTextfieldAttrs = andesTextfieldAttrs.copy(helper = value)
            setupHelperComponent(createConfig())
        }

    /**
     * Getter and setter for [placeholder].
     */
    var placeholder: String?
        get() = andesTextfieldAttrs.placeholder
        set(value) {
            andesTextfieldAttrs = andesTextfieldAttrs.copy(placeholder = value)
            setupPlaceHolderComponent(createConfig())
        }

    /**
     * Getter and setter for [counter].
     */
    var counter: AndesTextfieldCounter?
        get() = andesTextfieldAttrs.counter
        set(value) {
            andesTextfieldAttrs = andesTextfieldAttrs.copy(counter = value)
            setupCounterComponent(createConfig())
        }

    /**
     * Getter and setter for the state of [EditText].
     */
    var state : AndesTextfieldState
        get() = andesTextfieldAttrs.state
        set(value) {
            andesTextfieldAttrs = andesTextfieldAttrs.copy(state = value)
        }

    private lateinit var andesTextfieldAttrs: AndesTextfieldAttrs
    private lateinit var textfieldContainer: ConstraintLayout
    private lateinit var textContainer: ConstraintLayout
    private lateinit var labelComponent: TextView
    private lateinit var helperComponent: TextView
    private lateinit var counterComponent: TextView
    private lateinit var textComponent: EditText
    private lateinit var iconComponent: SimpleDraweeView



    @Suppress("unused")
    private constructor(context: Context) : super(context) {
        initAttrs(null)
    }

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        initAttrs(attrs)
    }

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs) {
        initAttrs(attrs)
    }

    private fun initAttrs(attrs: AttributeSet?) {
        andesTextfieldAttrs = AndesTextfieldAttrsParser.parse(context, attrs)
        val config = AndesTextfieldConfigurationFactory.create(context, andesTextfieldAttrs)
        setupComponents(config)
    }

    private fun initAttrs(label: String?, helper: String?, placeholder: String?, counter: AndesTextfieldCounter?, state: AndesTextfieldState) {
        andesTextfieldAttrs = AndesTextfieldAttrs(label, helper, placeholder, counter, state)
        val config = AndesTextfieldConfigurationFactory.create(context, andesTextfieldAttrs)
        setupComponents(config)
    }

    private fun setupComponents(config: AndesTextfieldConfiguration){
        initComponents()
        setupViewId()
        setupViewAsClickable()
        setupEnabledView()
        setupBackground(config)
        setupLabelComponent(config)
        setupHelperComponent(config)
        setupCounterComponent(config)
        setupErrorIcon(config)
        setupPlaceHolderComponent(config)
    }

    /**
     * Creates all the views that are part of this texfield.
     * After a view is created then a view id is added to it.
     *
     */
    private fun initComponents() {
        val container = LayoutInflater.from(context).inflate(R.layout.andes_layout_texfield, this, true)

        textfieldContainer = container.findViewById(R.id.andes_textfield_container)
        textContainer = container.findViewById(R.id.andes_textfield_text_container)
        labelComponent = container.findViewById(R.id.andes_texfield_label)
        helperComponent = container.findViewById(R.id.andes_texfield_helper)
        counterComponent = container.findViewById(R.id.andes_texfield_counter)
        iconComponent = container.findViewById(R.id.andes_texfield_icon)
        textComponent = container.findViewById(R.id.andes_textfield_edittext)
    }

    private fun setupViewId() {
        if (id == NO_ID) { //If this view has no id
            id = View.generateViewId()
        }
    }

    private fun setupViewAsClickable() {
        isClickable = true
        isFocusable = true
        textContainer.isClickable = true
        textContainer.isFocusable = true
    }

    private fun setupEnabledView() {
        if(state == AndesTextfieldState.DISABLED){
            isEnabled= false
            textComponent.isEnabled = isEnabled
            textContainer.isEnabled = isEnabled
            textfieldContainer.isEnabled = isEnabled
        }
        else{
            isEnabled = true
            textComponent.isEnabled = isEnabled
            textContainer.isEnabled = isEnabled
            textfieldContainer.isEnabled = isEnabled
        }
    }

    private fun setupErrorIcon(config : AndesTextfieldConfiguration){
        iconComponent.setImageDrawable(config.icon)
        if (config.icon != null) {
            iconComponent.visibility = View.VISIBLE

        } else {
            iconComponent.visibility = View.GONE
        }
    }


    private fun setupBackground(config: AndesTextfieldConfiguration){
        textContainer.background = config.background
    }

    /**
     * Gets data from the config and sets to the Label component.
     *
     */
    private fun setupLabelComponent(config: AndesTextfieldConfiguration) {
        if (config.labelText == null || config.labelText.isEmpty()) {
            labelComponent.visibility = View.GONE
        } else {
            labelComponent.visibility = View.VISIBLE
            labelComponent.text = config.labelText
            labelComponent.setTextSize(TypedValue.COMPLEX_UNIT_PX, config.labelSize)
            labelComponent.setTextColor(config.labelColor.colorInt(context))
            labelComponent.typeface = config.typeface
        }
    }

    /**
     * Gets data from the config and sets to the Helper component.
     *
     */
    private fun setupHelperComponent(config: AndesTextfieldConfiguration) {
        if (config.helperText == null || config.helperText.isEmpty()) {
            helperComponent.visibility = View.GONE
        } else {
            helperComponent.visibility = View.VISIBLE
            helperComponent.text = config.helperText
            helperComponent.setTextSize(TypedValue.COMPLEX_UNIT_PX, config.helperSize)
            helperComponent.setTextColor(config.helperColor.colorInt(context))
            helperComponent.typeface = config.helperTypeface
        }
    }

    /**
     * Gets data from the config and sets to the Counter component.
     *
     */
    private fun setupCounterComponent(config: AndesTextfieldConfiguration) {
        if (config.counterMaxLength!! <= config.counterMinLength!!) {
            counterComponent.visibility = View.GONE
        } else {
            counterComponent.visibility = View.VISIBLE
            counterComponent.setTextSize(TypedValue.COMPLEX_UNIT_PX, config.counterSize)
            counterComponent.setTextColor(config.counterColor.colorInt(context))
            counterComponent.text = resources.getString(R.string.andes_textfield_counter_text, config.counterMinLength,config.counterMaxLength)
            counterComponent.typeface = config.typeface
            textComponent.filters = arrayOf<InputFilter>(InputFilter.LengthFilter(config.counterMaxLength!!))
        }

        textComponent.addTextChangedListener(object: TextWatcher {
            override fun afterTextChanged(s: Editable?) {
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                counterComponent.text = resources.getString(R.string.andes_textfield_counter_text, count, config.counterMaxLength)
            }
        })
    }

    /**
     * Gets data from the config and sets to the Place Holder component.
     *
     */
    private fun setupPlaceHolderComponent(config: AndesTextfieldConfiguration) {
        if (config.placeHolderText != null) {
            textComponent.hint = config.placeHolderText
            textComponent.setHintTextColor(config.placeHolderColor)
            textComponent.typeface = config.typeface
        }
    }

    private fun createConfig() = AndesTextfieldConfigurationFactory.create(context, andesTextfieldAttrs)
}