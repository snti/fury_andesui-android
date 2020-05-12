package com.mercadolibre.android.andesui.tag

import android.content.Context
import android.graphics.drawable.GradientDrawable
import android.support.constraint.ConstraintLayout
import android.support.constraint.ConstraintSet
import android.util.AttributeSet
import android.util.Log
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import com.mercadolibre.android.andesui.R
import com.mercadolibre.android.andesui.tag.factory.simple.AndesSimpleTagConfigurationFactory
import com.mercadolibre.android.andesui.tag.factory.simple.AndesTagSimpleAttrs
import com.mercadolibre.android.andesui.tag.factory.simple.AndesTagSimpleAttrsParser
import com.mercadolibre.android.andesui.tag.factory.simple.AndesTagSimpleConfiguration
import com.mercadolibre.android.andesui.tag.leftcontent.*
import com.mercadolibre.android.andesui.tag.rightcontent.AndesTagRightContent
import com.mercadolibre.android.andesui.tag.rightcontent.RightContent
import com.mercadolibre.android.andesui.tag.size.AndesTagSize
import com.mercadolibre.android.andesui.tag.type.AndesTagType
import kotlinx.android.synthetic.main.andes_layout_simple_tag.view.*

class AndesTagSimple : ConstraintLayout {

    /**
     * Getter and setter for [type].
     */
    var type: AndesTagType
        get() = andesTagAttrs.andesTagType
        set(value) {
            andesTagAttrs = andesTagAttrs.copy(andesTagType = value)
            setupBackgroundComponents(createConfig())
        }

    /**
     * Getter and setter for [size].
     */
    var size: AndesTagSize
        get() = andesTagAttrs.andesTagSize
        set(value) {
            andesTagAttrs = andesTagAttrs.copy(andesTagSize = value)
            setupBackgroundComponents(createConfig())
            setupTitleComponent(createConfig())
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

    /**
     * Getter and setter for [leftContent].
     */
    var leftContent: LeftContent?
        get() = andesTagAttrs.leftContent
        set(value) {
            if (size == AndesTagSize.SMALL) {
                andesTagAttrs = andesTagAttrs.copy(leftContent = null)
                Log.e("TAG", "leftContent can only be used with tag large")
            } else {
                andesTagAttrs = andesTagAttrs.copy(leftContent = value)
            }
            val config = createConfig()
            setupLeftContent(config)
            setupTitleComponent(config)
        }

    /**
     * Getter and setter for [leftContent].
     */
    var rightContent: RightContent?
        get() = andesTagAttrs.rightContent
        set(value) {
            andesTagAttrs = andesTagAttrs.copy(rightContent = value)
            val config = createConfig()
            setupRightContent(config)
            setupTitleComponent(config)
        }

    private lateinit var andesTagAttrs: AndesTagSimpleAttrs
    private lateinit var containerTag: ConstraintLayout

    @Suppress("unused")
    private constructor(context: Context) : super(context) {
        throw IllegalStateException("Constructor without parameters in Andes Badge is not allowed. You must provide some attributes.")
    }

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        initAttrs(attrs)
    }

    @Suppress("unused")
    constructor(
        context: Context,
        type: AndesTagType = TYPE_DEFAULT,
        size: AndesTagSize = SIZE_DEFAULT,
        text: String? = TEXT_DEFAULT
    ) : super(context) {
        initAttrs(type, size, text)
    }

    /**
     * Sets the proper [config] for this message based on the [attrs] received via XML.
     * @param attrs attributes from the XML.
     */
    private fun initAttrs(attrs: AttributeSet?) {
        andesTagAttrs = AndesTagSimpleAttrsParser.parse(context, attrs)
        val config = AndesSimpleTagConfigurationFactory.create(andesTagAttrs)
        setupComponents(config)
    }

    private fun initAttrs(type: AndesTagType, size: AndesTagSize, text: String?) {
        andesTagAttrs = AndesTagSimpleAttrs(type, size, text)
        val config = AndesSimpleTagConfigurationFactory.create(andesTagAttrs)
        setupComponents(config)
    }

    /**
     * Responsible for setting up all properties of each component that is part of this badge.
     * Is like a choreographer ;)
     */
    private fun setupComponents(config: AndesTagSimpleConfiguration) {
        initComponents()
        setupViewId()

        setupBackgroundComponents(config)
        setupTitleComponent(config)
        setupLeftContent(config)
        setupRightContent(config)
    }

    /**
     * Creates all the views that are part of this badge.
     * After a view is created then a view id is added to it.
     */
    private fun initComponents() {
        val container = LayoutInflater.from(context).inflate(R.layout.andes_layout_simple_tag, this)
        containerTag = container.findViewById(R.id.andes_tag_container)
    }

    /**
     * Sets a view id to this badge.
     */
    private fun setupViewId() {
        if (id == NO_ID) { // If this view has no id
            id = View.generateViewId()
        }
    }

    private fun setupBackgroundComponents(config: AndesTagSimpleConfiguration) {
        val shape = GradientDrawable()
        shape.cornerRadius = size.size.border(context)

        if (config.backgroundColor != null) {
            shape.setColor(config.backgroundColor.colorInt(context))
        }

        val borderSize = resources.getDimension(R.dimen.andes_tag_border)
        shape.setStroke(borderSize.toInt(), config.borderColor.colorInt(context))

        background = shape

        containerTag.minHeight = size.size.height(context).toInt()
        containerTag.maxHeight = size.size.height(context).toInt()
        containerTag.minWidth = size.size.height(context).toInt()
    }

    /**
     * Gets data from the config and sets to the title component of this tag.
     */
    private fun setupTitleComponent(config: AndesTagSimpleConfiguration) {
        if (config.text == null || config.text.isEmpty()) {
            containerTag.visibility = View.GONE
        } else {
            containerTag.visibility = View.VISIBLE

            simple_tag_text.text = config.text
            simple_tag_text.setTextSize(TypedValue.COMPLEX_UNIT_PX, size.size.textSize(context))
            simple_tag_text.setTextColor(config.textColor.colorInt(context))

            val constraintSet = ConstraintSet()
            constraintSet.clone(containerTag)
            if (leftContent == null) {
                constraintSet.setMargin(R.id.simple_tag_text, ConstraintSet.START, size.size.leftMargin(context))
            } else {
                constraintSet.setMargin(R.id.simple_tag_text, ConstraintSet.START, config.leftContent!!.getRightMargin())
            }
            if (rightContent == null || rightContent!!.getType() == AndesTagRightContent.NONE) {
                constraintSet.setMargin(R.id.simple_tag_text, ConstraintSet.END, size.size.rightMargin(context))
            } else {
                constraintSet.setMargin(R.id.simple_tag_text, ConstraintSet.END, config.rightContent!!.getLeftMargin())
            }
            constraintSet.applyTo(containerTag)
        }
    }

    private fun setupLeftContent(config: AndesTagSimpleConfiguration) {
        if (config.leftContent != null && config.leftContent.getType() != AndesTagLeftContent.NONE) {
            left_content.addView(config.leftContent.getView(context))
            left_content.visibility = View.VISIBLE
        } else {
            left_content.visibility = View.GONE
        }
    }

    private fun setupRightContent(config: AndesTagSimpleConfiguration) {
        if (config.rightContent != null && config.rightContent.getType() != AndesTagRightContent.NONE) {
            right_content.addView(
                    config.rightContent.getView(
                            context,
                            config.borderColor,
                            OnClickListener {
                                containerTag.visibility = View.GONE
                            }
                    )
            )
            right_content.visibility = View.VISIBLE
        } else {
            right_content.visibility = View.GONE
        }
    }

    private fun createConfig() = AndesSimpleTagConfigurationFactory.create(andesTagAttrs)

    companion object {
        private val TYPE_DEFAULT = AndesTagType.DEFAULT
        private val SIZE_DEFAULT = AndesTagSize.LARGE
        private val TEXT_DEFAULT = null
    }
}
