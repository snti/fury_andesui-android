package com.mercadolibre.android.andesui.textfield

import android.content.Context
import android.graphics.drawable.BitmapDrawable
import android.support.constraint.ConstraintLayout
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.EditText
import android.widget.FrameLayout
import android.widget.TextView
import com.facebook.drawee.view.SimpleDraweeView
import com.mercadolibre.android.andesui.R
import com.mercadolibre.android.andesui.button.AndesButton
import com.mercadolibre.android.andesui.color.toAndesColor
import com.mercadolibre.android.andesui.icons.OfflineIconProvider
import com.mercadolibre.android.andesui.textfield.content.AndesTextfieldLeftContent
import com.mercadolibre.android.andesui.textfield.content.AndesTextfieldRightContent
import com.mercadolibre.android.andesui.textfield.factory.*
import com.mercadolibre.android.andesui.textfield.state.AndesTextfieldState
import com.mercadolibre.android.andesui.utils.buildColoredBitmapDrawable


class AndesTextfield : ConstraintLayout {

    var label: String?
        get() = andesTextfieldAttrs.label
        set(value) {
            andesTextfieldAttrs = andesTextfieldAttrs.copy(label = value)
            //TODO set up the component
        }

    var helper: String?
        get() = andesTextfieldAttrs.helper
        set(value) {
            andesTextfieldAttrs = andesTextfieldAttrs.copy(helper = value)
            //TODO set up the component
        }

    var placeholder: String?
        get() = andesTextfieldAttrs.placeholder
        set(value) {
            andesTextfieldAttrs = andesTextfieldAttrs.copy(placeholder = value)
            //TODO set up the component
        }

    var counter: AndesTextfieldCounter?
        get() = andesTextfieldAttrs.counter
        set(value) {
            andesTextfieldAttrs = andesTextfieldAttrs.copy(counter = value)
            //TODO set up the component
        }

    var state: AndesTextfieldState
        get() = andesTextfieldAttrs.state
        set(value) {
            andesTextfieldAttrs = andesTextfieldAttrs.copy(state = value)
            //TODO set up the component
        }

    var leftContent: AndesTextfieldLeftContent?
        get() = andesTextfieldAttrs.leftContent
        set(value) {
            andesTextfieldAttrs = andesTextfieldAttrs.copy(leftContent = value)
            setupLeftComponent(createConfig())
        }

    var rightContent: AndesTextfieldRightContent?
        get() = andesTextfieldAttrs.rightContent
        set(value) {
            andesTextfieldAttrs = andesTextfieldAttrs.copy(rightContent = value)
            setupRightComponent(createConfig())
        }

    private lateinit var andesTextfieldAttrs: AndesTextfieldAttrs
    private lateinit var textfieldContainer: ConstraintLayout
    private lateinit var textContainer: ConstraintLayout
    private lateinit var labelComponent: TextView
    private lateinit var helperComponent: TextView
    private lateinit var counterComponent: TextView
    private lateinit var textComponent: EditText
    private lateinit var iconComponent: SimpleDraweeView
    private lateinit var leftComponent: FrameLayout
    private lateinit var rightComponent: FrameLayout


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

    private fun initAttrs(label: String?, helper: String?, placeholder: String?, counter: AndesTextfieldCounter?, state: AndesTextfieldState, leftContent: AndesTextfieldLeftContent?, rightContent: AndesTextfieldRightContent?) {
        andesTextfieldAttrs = AndesTextfieldAttrs(label, helper, placeholder, counter, state, leftContent, rightContent)
        val config = AndesTextfieldConfigurationFactory.create(context, andesTextfieldAttrs)
        setupComponents(config)
    }

    private fun setupComponents(config: AndesTextfieldConfiguration) {
        initComponents()
        setupViewId()
        setupViewAsClickable()
        setupEnabledView()
        setupBackground(config)
        setupErrorIcon(config)
        setupLeftComponent(config)
        setupRightComponent(config)
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
        leftComponent = container.findViewById(R.id.andes_textfield_left_component)
        rightComponent = container.findViewById(R.id.andes_textfield_right_component)

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
        if (state == AndesTextfieldState.DISABLED) {
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

    private fun setupErrorIcon(config: AndesTextfieldConfiguration) {
        iconComponent.setImageDrawable(config.icon)
        if (config.icon != null) {
            iconComponent.visibility = View.VISIBLE

        } else {
            iconComponent.visibility = View.GONE
        }
    }


    private fun setupBackground(config: AndesTextfieldConfiguration) {
        textContainer.background = config.background
    }

    private fun setupLeftComponent(config: AndesTextfieldConfiguration) {
        if (config.leftComponent != null) {
            leftComponent.removeAllViews()
            leftComponent.addView(config.leftComponent)

            val params = leftComponent.layoutParams as ConstraintLayout.LayoutParams
            params.marginStart = config.leftComponentLeftMargin!!
            params.marginEnd = config.leftComponentRightMargin!!
            leftComponent.layoutParams = params

            leftComponent.visibility = View.VISIBLE
        } else {
            leftComponent.visibility = View.GONE
        }
    }

    private fun setupRightComponent(config: AndesTextfieldConfiguration) {
        if (config.rightComponent != null) {
            rightComponent.removeAllViews()
            rightComponent.addView(config.rightComponent)
            setupClear()

            val params = rightComponent.layoutParams as ConstraintLayout.LayoutParams
            params.marginStart = config.rightComponentLeftMargin!!
            params.marginEnd = config.rightComponentRightMargin!!
            rightComponent.layoutParams = params

            rightComponent.visibility = View.VISIBLE

        } else {
            rightComponent.visibility = View.GONE
        }
    }

    private fun setupClear() {
        if (rightContent == AndesTextfieldRightContent.CLEAR) {
            val clear: SimpleDraweeView = rightComponent.getChildAt(0) as SimpleDraweeView
            clear.setOnClickListener { textComponent.text.clear() }
        }
    }

    fun setupAction(text: String, onClickListener: OnClickListener) {
        rightContent = AndesTextfieldRightContent.ACTION
        val action: AndesButton = rightComponent.getChildAt(0) as AndesButton
        action.text = text
        action.setOnClickListener(onClickListener)

    }

    fun setupRightIcon(iconPath: String) {
        rightContent = AndesTextfieldRightContent.ICON
        val rightIcon: SimpleDraweeView = rightComponent.getChildAt(0) as SimpleDraweeView
        rightIcon.setImageDrawable(buildColoredBitmapDrawable(
                OfflineIconProvider(context).loadIcon(iconPath) as BitmapDrawable,
                context,
                R.color.andes_gray_800.toAndesColor())
        )
    }

    fun setupLeftIcon(iconPath: String) {
        leftContent = AndesTextfieldLeftContent.ICON
        val leftIcon: SimpleDraweeView = leftComponent.getChildAt(0) as SimpleDraweeView
        leftIcon.setImageDrawable(buildColoredBitmapDrawable(
                OfflineIconProvider(context).loadIcon(iconPath) as BitmapDrawable,
                context,
                R.color.andes_gray_450.toAndesColor())
        )

    }

    fun setupPrefix(text: String) {
        leftContent = AndesTextfieldLeftContent.PREFIX
        val prefix: TextView = leftComponent.getChildAt(0) as TextView
        prefix.text = text
    }


    fun setupSuffix(text: String) {
        rightContent = AndesTextfieldRightContent.SUFFIX
            val suffix: TextView = leftComponent.getChildAt(0) as TextView
            suffix.text = text
    }

    private fun createConfig() = AndesTextfieldConfigurationFactory.create(context, andesTextfieldAttrs)
}