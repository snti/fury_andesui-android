package com.mercadolibre.android.andesui.tag

import android.content.Context
import android.graphics.drawable.GradientDrawable
import android.support.constraint.ConstraintLayout
import android.support.constraint.ConstraintSet
import android.util.AttributeSet
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import com.mercadolibre.android.andesui.R
import com.mercadolibre.android.andesui.tag.choice.AndesTagChoiceCallback
import com.mercadolibre.android.andesui.tag.choice.AndesTagChoiceState
import com.mercadolibre.android.andesui.tag.choice.AndesTagChoiceType
import com.mercadolibre.android.andesui.tag.factory.AndesChoiceTagConfigurationFactory
import com.mercadolibre.android.andesui.tag.factory.AndesTagChoiceAttrs
import com.mercadolibre.android.andesui.tag.factory.AndesTagChoiceAttrsParser
import com.mercadolibre.android.andesui.tag.factory.AndesTagChoiceConfiguration
import com.mercadolibre.android.andesui.tag.leftcontent.AndesTagLeftContent
import com.mercadolibre.android.andesui.tag.leftcontent.LeftContent
import com.mercadolibre.android.andesui.tag.rightcontent.AndesTagRightContent
import com.mercadolibre.android.andesui.tag.size.AndesLargeTagSize
import com.mercadolibre.android.andesui.tag.size.AndesTagSize
import com.mercadolibre.android.andesui.tag.size.AndesTagSizeInterface
import com.mercadolibre.android.andesui.typeface.getFontOrDefault
import kotlinx.android.synthetic.main.andes_layout_simple_tag.view.*

class AndesTagChoice : ConstraintLayout {

    private lateinit var andesTagAttrs: AndesTagChoiceAttrs
    private lateinit var containerTag: ConstraintLayout
    private var size: AndesTagSizeInterface = AndesLargeTagSize()

    /**
     * Getter and setter for [state].
     */
    var state: AndesTagChoiceState
        get() = andesTagAttrs.andesTagChoiceState
        set(value) {
            andesTagAttrs = andesTagAttrs.copy(andesTagChoiceState = value)
            setupBackgroundComponents(createConfig())
        }

    /**
     * Getter and setter for [text].
     */
    var text: String?
        get() = andesTagAttrs.andesSimpleTagText
        set(value) {
            andesTagAttrs = andesTagAttrs.copy(andesSimpleTagText = value)
            setupTitleComponent(createConfig())
        }

    private var callback: AndesTagChoiceCallback? = null

    /**
     * Getter and setter for [leftContent].
     */
    var leftContent: LeftContent?
        get() = andesTagAttrs.leftContentData
        set(value) {
            val andesTagLeftContent = when {
                value?.dot != null -> AndesTagLeftContent.DOT
                value?.icon != null -> AndesTagLeftContent.ICON
                value?.image != null -> AndesTagLeftContent.IMAGE
                else -> AndesTagLeftContent.NONE
            }
            andesTagAttrs = andesTagAttrs.copy(leftContentData = value)
            andesTagAttrs = andesTagAttrs.copy(leftContent = andesTagLeftContent)

            val config = createConfig()
            setupLeftContent(config)
            setupTitleComponent(config)
        }

    @Suppress("unused")
    private constructor(context: Context) : super(context) {
        throw IllegalStateException(
                "Constructor without parameters in Andes Badge is not allowed. You must provide some attributes."
        )
    }

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        initAttrs(attrs)
    }

    @Suppress("unused")
    constructor(
            context: Context,
            type: AndesTagChoiceType = TYPE_DEFAULT,
            state: AndesTagChoiceState = STATE_DEFAULT,
            text: String? = TEXT_DEFAULT
    ) : super(context) {
        initAttrs(type, state, text)
    }

    /**
     * Sets the proper [config] for this message based on the [attrs] received via XML.
     * @param attrs attributes from the XML.
     */
    private fun initAttrs(attrs: AttributeSet?) {
        andesTagAttrs = AndesTagChoiceAttrsParser.parse(context, attrs)
        val config = AndesChoiceTagConfigurationFactory.create(andesTagAttrs)
        setupComponents(config)
    }

    private fun initAttrs(
            type: AndesTagChoiceType,
            state: AndesTagChoiceState,
            text: String?,
            leftContent: AndesTagLeftContent? = null,
            leftContentData: LeftContent? = null
    ) {
        andesTagAttrs = AndesTagChoiceAttrs(text, type, state, leftContentData, leftContent)
        val config = AndesChoiceTagConfigurationFactory.create(andesTagAttrs)
        setupComponents(config)
    }

    /**
     * Responsible for setting up all properties of each component that is part of this badge.
     * Is like a choreographer ;)
     */
    private fun setupComponents(config: AndesTagChoiceConfiguration) {
        initComponents()

        if (id == NO_ID) { // If this view has no id
            id = View.generateViewId()
        }

        setupBackgroundComponents(config)
        setupTitleComponent(config)
        setupLeftContent(config)
        setupRightContent(config)

        containerTag.setOnClickListener {
            onTagClick()
        }
    }

    private fun onTagClick() {
        val result = callback?.shouldSelectTag(this) ?: true
        if (result) {
            state = if (state == AndesTagChoiceState.SELECTED) {
                AndesTagChoiceState.IDLE
            } else {
                AndesTagChoiceState.SELECTED
            }
        }

        andesTagAttrs = andesTagAttrs.copy(andesTagChoiceState = state)
        setupComponents(createConfig())
    }

    /**
     * Creates all the views that are part of this badge.
     * After a view is created then a view id is added to it.
     */
    private fun initComponents() {
        val container = LayoutInflater.from(context).inflate(R.layout.andes_layout_simple_tag, this)
        containerTag = container.findViewById(R.id.andes_tag_container)
    }

    private fun setupBackgroundComponents(config: AndesTagChoiceConfiguration) {
        val shape = GradientDrawable()
        shape.cornerRadius = size.border(context)
        shape.setColor(config.backgroundColor.colorInt(context))
        val borderSize = resources.getDimension(R.dimen.andes_tag_border)
        shape.setStroke(borderSize.toInt(), config.borderColor.colorInt(context))
        background = shape

        containerTag.minHeight = size.height(context).toInt()
        containerTag.maxHeight = size.height(context).toInt()
        containerTag.minWidth = size.height(context).toInt()
    }

    /**
     * Gets data from the config and sets to the title component of this tag.
     */
    private fun setupTitleComponent(config: AndesTagChoiceConfiguration) {
        if (config.text == null || config.text.isEmpty()) {
            containerTag.visibility = View.GONE
        } else {
            containerTag.visibility = View.VISIBLE

            simpleTagText.text = config.text
            simpleTagText.typeface = context.getFontOrDefault(R.font.andes_font_regular)
            simpleTagText.setTextSize(TypedValue.COMPLEX_UNIT_PX, size.textSize(context))
            simpleTagText.setTextColor(config.textColor.colorInt(context))

            // TODO revisar margenes
            val constraintSet = ConstraintSet()
            constraintSet.clone(containerTag)
            if (leftContent == null) {
                constraintSet.setMargin(
                        R.id.simpleTagText,
                        ConstraintSet.START,
                        size.leftMargin(context)
                )
            } else if (config.leftContent != null) {
                constraintSet.setMargin(
                        R.id.simpleTagText,
                        ConstraintSet.START,
                        config.leftContent.content.rightMargin(context)
                )
            }
            if (config.rightContent == null || config.rightContent == AndesTagRightContent.NONE) {
                constraintSet.setMargin(R.id.simpleTagText, ConstraintSet.END, size.rightMargin(context))
            } else {
                constraintSet.setMargin(
                        R.id.simpleTagText,
                        ConstraintSet.END,
                        config.rightContent.content.leftMargin(context, AndesTagSize.LARGE)
                )
            }
            constraintSet.applyTo(containerTag)
        }
    }

    private fun setupLeftContent(config: AndesTagChoiceConfiguration) {
        // TODO revisar colores. Si no mandan background o iconColor
        // TODO revisar margenes
        val leftContent = findViewById<FrameLayout>(R.id.leftContent)
        if (config.leftContent != null && config.leftContentData != null && config.leftContent != AndesTagLeftContent.NONE) {
            leftContent.removeAllViews()
            leftContent.addView(config.leftContent.content.view(context, config.leftContentData))
            leftContent.visibility = View.VISIBLE
        } else {
            leftContent.visibility = View.GONE
        }
    }

    private fun setupRightContent(config: AndesTagChoiceConfiguration) {
        val rightContent = findViewById<FrameLayout>(R.id.rightContent)
        if (config.rightContent != null && config.rightContent != AndesTagRightContent.NONE) {
            rightContent.removeAllViews()
            // TODO revisar colores
            rightContent.addView(config.rightContent.content.view(
                    context,
                    config.rightContentColor,
                    null,
                    null
            ))

            val params = rightContent.layoutParams as MarginLayoutParams
            params.marginEnd = config.rightContent.content.rightMargin(context, AndesTagSize.LARGE)
            rightContent.layoutParams = params

            rightContent.visibility = View.VISIBLE
        } else {
            rightContent.visibility = View.GONE
        }
    }

    private fun createConfig() = AndesChoiceTagConfigurationFactory.create(andesTagAttrs)

    companion object {
        private val TYPE_DEFAULT = AndesTagChoiceType.SIMPLE
        private val STATE_DEFAULT = AndesTagChoiceState.IDLE
        private val TEXT_DEFAULT = null
    }
}