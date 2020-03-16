package com.mercadolibre.android.andesui.textfield

import android.content.Context
import android.support.constraint.ConstraintLayout
import android.util.AttributeSet
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
            //TODO set up the component
            setupLabelComponent(createConfig())
        }

    /**
     * Getter and setter for [helper].
     */
    var helper: String?
        get() = andesTextfieldAttrs.helper
        set(value) {
            andesTextfieldAttrs = andesTextfieldAttrs.copy(helper = value)
            //TODO set up the component
        }

    /**
     * Getter and setter for [placeholder].
     */
    var placeholder: String?
        get() = andesTextfieldAttrs.placeholder
        set(value) {
            andesTextfieldAttrs = andesTextfieldAttrs.copy(placeholder = value)
            //TODO set up the component
        }

    /**
     * Getter and setter for [counter].
     */
    var counter: AndesTextfieldCounter?
        get() = andesTextfieldAttrs.counter
        set(value) {
            andesTextfieldAttrs = andesTextfieldAttrs.copy(counter = value)
            //TODO set up the component
        }

    /**
     * Getter and setter for the state of [EditText].
     */
    var state : AndesTextfieldState
        get() = andesTextfieldAttrs.state
        set(value) {
            andesTextfieldAttrs = andesTextfieldAttrs.copy(state = value)
            //TODO set up the component
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
        setupErrorIcon(config)
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
        textComponent = container.findViewById(R.id.andes_textfield_edittext)
        iconComponent = container.findViewById(R.id.andes_texfield_icon)
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
     * Gets data from the config and sets to the text component of this button.
     *
     */
    private fun setupLabelComponent(config: AndesTextfieldConfiguration) {
        if (config.titleText == null || config.titleText.isEmpty()) {
            titleComponent.visibility = View.GONE
        } else {
            titleComponent.visibility = View.VISIBLE
            titleComponent.text = config.titleText
            titleComponent.setTextSize(TypedValue.COMPLEX_UNIT_PX, config.titleSize)
            titleComponent.setTextColor(config.textColor.colorInt(context))
            titleComponent.typeface = config.titleTypeface
        }
    }

    private fun createConfig() = AndesTextfieldConfigurationFactory.create(context, andesTextfieldAttrs)
}