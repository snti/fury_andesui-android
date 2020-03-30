package com.mercadolibre.android.andesui.dropdown

import android.content.Context
import android.graphics.drawable.BitmapDrawable
import android.support.constraint.ConstraintLayout
import android.util.AttributeSet
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.widget.EditText
import android.widget.TextView
import com.facebook.drawee.view.SimpleDraweeView
import com.mercadolibre.android.andesui.R
import com.mercadolibre.android.andesui.color.toAndesColor
import com.mercadolibre.android.andesui.icons.OfflineIconProvider
import com.mercadolibre.android.andesui.textfield.factory.AndesTextfieldConfiguration
import com.mercadolibre.android.andesui.textfield.factory.AndesTextfieldConfigurationFactory
import com.mercadolibre.android.andesui.textfield.state.AndesTextfieldState
import com.mercadolibre.android.andesui.utils.buildColoredBitmapDrawable

class AndesDropdown : ConstraintLayout {

    /**
     * Getter and setter for [label].
     */
    var label: String?
        get() = andesDropdownAttrs.label
        set(value) {
            andesDropdownAttrs = andesDropdownAttrs.copy(label = value)
            setupLabelComponent(createConfig())
        }

    /**
     * Getter and setter for [helper].
     */
    var helper: String?
        get() = andesDropdownAttrs.helper
        set(value) {
            andesDropdownAttrs = andesDropdownAttrs.copy(helper = value)
            setupHelperComponent(createConfig())
        }

    /**
     * Getter and setter for [placeholder].
     */
    var placeholder: String?
        get() = andesDropdownAttrs.placeholder
        set(value) {
            andesDropdownAttrs = andesDropdownAttrs.copy(placeholder = value)
            setupPlaceHolderComponent(createConfig())
        }

    /**
     * Getter and setter for the state of [EditText].
     */
    var state: AndesTextfieldState
        get() = andesDropdownAttrs.state
        set(value) {
            andesDropdownAttrs = andesDropdownAttrs.copy(state = value)
            setupColorComponents(createConfig())
        }


    private lateinit var andesDropdownAttrs: AndesDropdownAttrs
    private lateinit var textfieldContainer: ConstraintLayout
    private lateinit var textContainer: ConstraintLayout
    private lateinit var labelComponent: TextView
    private lateinit var helperComponent: TextView
    private lateinit var textComponent: TextView
    private lateinit var iconComponent: SimpleDraweeView
    private lateinit var rightComponent: SimpleDraweeView

    @Suppress("unused")
    constructor(context: Context) : super(context) {
        initAttrs(LABEL_DEFAULT, HELPER_DEFAULT, PLACEHOLDER_DEFAULT, STATE_DEFAULT)
    }

    constructor(context: Context,
                label: String? = LABEL_DEFAULT,
                helper: String? = HELPER_DEFAULT,
                placeholder: String? = PLACEHOLDER_DEFAULT,
                state: AndesTextfieldState = STATE_DEFAULT)
            : super(context) {
        initAttrs(label, helper, placeholder, state)
    }

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        initAttrs(attrs)
    }

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs) {
        initAttrs(attrs)
    }

    private fun initAttrs(attrs: AttributeSet?) {
        andesDropdownAttrs = AndesDropdownAttrsParser.parse(context, attrs)
        val config = AndesTextfieldConfigurationFactory.create(context, andesDropdownAttrs)
        setupComponents(config)
    }

    private fun initAttrs(label: String?, helper: String?, placeholder: String?, state: AndesTextfieldState) {
        andesDropdownAttrs = AndesDropdownAttrs(label, helper, placeholder, state)
        val config = AndesTextfieldConfigurationFactory.create(context, andesDropdownAttrs)
        setupComponents(config)
    }

    private fun setupComponents(config: AndesTextfieldConfiguration) {
        initComponents()
        setupViewId()
        setupViewAsClickable()
        setupEnabledView()
        setupLabelComponent(config)
        setupHelperComponent(config)
        setupPlaceHolderComponent(config)
        setupRightComponent()
        setupColorComponents(config)
    }

    /**
     * Creates all the views that are part of this textfield.
     * After a view is created then a view id is added to it.
     *
     */
    private fun initComponents() {
        val container = LayoutInflater.from(context).inflate(R.layout.andes_layout_dropdown, this, true)

        textfieldContainer = container.findViewById(R.id.andes_dropdown_container)
        textContainer = container.findViewById(R.id.andes_dropdown_text_container)
        labelComponent = container.findViewById(R.id.andes_dropdown_label)
        helperComponent = container.findViewById(R.id.andes_dropdown_helper)
        iconComponent = container.findViewById(R.id.andes_dropdown_icon)
        textComponent = container.findViewById(R.id.andes_dropdown_textview)
        rightComponent = container.findViewById(R.id.andes_dropdown_chevron)
    }

    private fun setupViewId() {
        if (id == NO_ID) { //If this view has no id
            id = View.generateViewId()
        }
    }

    private fun setupViewAsClickable() {
        isFocusable = true
        textContainer.isClickable = true
        textContainer.isFocusable = true
    }

    private fun setupEnabledView() {
        if(state == AndesTextfieldState.DISABLED || state == AndesTextfieldState.READONLY){
            isEnabled = false
            textComponent.isEnabled = isEnabled
            textContainer.isEnabled = isEnabled
            textfieldContainer.isEnabled = isEnabled
        } else {
            isEnabled = true
            textComponent.isEnabled = isEnabled
            textContainer.isEnabled = isEnabled
            textfieldContainer.isEnabled = isEnabled
        }
    }

    /**
     * Gets data from the config to sets the color of the components regarding to the state.
     *
     */
    private fun setupColorComponents(config: AndesTextfieldConfiguration) {
        textContainer.background = config.background

        iconComponent.setImageDrawable(config.icon)
        if (config.icon != null) {
            iconComponent.visibility = View.VISIBLE

        } else {
            iconComponent.visibility = View.GONE
        }

        helperComponent.setTextColor(config.helperColor.colorInt(context))
        helperComponent.typeface = config.helperTypeface

        labelComponent.setTextColor(config.labelColor.colorInt(context))
        labelComponent.typeface = config.typeface

        textComponent.setHintTextColor(config.placeHolderColor.colorInt(context))
        textComponent.typeface = config.typeface
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
        }
    }

    /**
     * Gets data from the config and sets to the Helper component.
     *
     */
    private fun setupHelperComponent(config: AndesTextfieldConfiguration) {
        if (config.helperText != null) {
            helperComponent.visibility = View.VISIBLE
            helperComponent.text = config.helperText
            helperComponent.setTextSize(TypedValue.COMPLEX_UNIT_PX, config.helperSize)
        } else {
            helperComponent.visibility = View.GONE
        }
    }


    /**
     * Gets data from the config and sets to the Place Holder component.
     *
     */
    private fun setupPlaceHolderComponent(config: AndesTextfieldConfiguration) {
        if (config.placeHolderText != null) {
            textComponent.visibility = View.VISIBLE
            textComponent.text = config.placeHolderText
            textComponent.typeface = config.typeface
        }
    }

    private fun setupRightComponent() {
        rightComponent.setImageDrawable(buildColoredBitmapDrawable(
                OfflineIconProvider(context).loadIcon("andes_ui_chevron_down_20") as BitmapDrawable,
                context,
                R.color.andes_accent_color_500.toAndesColor())
        )
    }



    private fun createConfig() = AndesTextfieldConfigurationFactory.create(context, andesDropdownAttrs)

    /**
     * Default values for AndesTextfield basic properties
     */
    companion object {
        private val LABEL_DEFAULT = null
        private val HELPER_DEFAULT = null
        private val PLACEHOLDER_DEFAULT = null
        private val STATE_DEFAULT = AndesTextfieldState.ENABLED


    }

}