package com.mercadolibre.android.andesui.tag

import android.content.Context
import android.graphics.drawable.GradientDrawable
import android.support.constraint.ConstraintLayout
import android.support.constraint.ConstraintSet
import android.util.AttributeSet
import android.util.TypedValue
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import android.widget.TextView
import com.facebook.drawee.view.SimpleDraweeView
import com.mercadolibre.android.andesui.R
import com.mercadolibre.android.andesui.icons.IconProvider
import com.mercadolibre.android.andesui.tag.factory.AndesSimpleTagAttrs
import com.mercadolibre.android.andesui.tag.factory.AndesSimpleTagAttrsParser
import com.mercadolibre.android.andesui.tag.factory.AndesSimpleTagConfiguration
import com.mercadolibre.android.andesui.tag.factory.AndesSimpleTagConfigurationFactory
import com.mercadolibre.android.andesui.tag.size.AndesSimpleTagSize
import com.mercadolibre.android.andesui.tag.type.AndesSimpleTagType

class AndesSimpleTag : ConstraintLayout {

    /**
     * Getter and setter for [type].
     */
    var type: AndesSimpleTagType
        get() = andesTagAttrs.andesSimpleTagType
        set(value) {
            andesTagAttrs = andesTagAttrs.copy(andesSimpleTagType = value)
            setupColorComponents(createConfig())
        }


    /**
     * Getter and setter for [size].
     */
    var size: AndesSimpleTagSize
        get() = andesTagAttrs.andesSimpleTagSize
        set(value) {
            andesTagAttrs = andesTagAttrs.copy(andesSimpleTagSize = value)
            setupColorComponents(createConfig())
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
     * Getter and setter for [isDismissable].
     */
    var isDismissable: Boolean
        get() = andesTagAttrs.isDismissable
        set(value) {
            andesTagAttrs = andesTagAttrs.copy(isDismissable = value)
            setupDismissable(createConfig())
        }

    private lateinit var andesTagAttrs: AndesSimpleTagAttrs
    private lateinit var containerTag: ConstraintLayout
    private lateinit var tagTitle: TextView
    private lateinit var leftContent: FrameLayout
    private lateinit var rightContent: SimpleDraweeView

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
            type: AndesSimpleTagType = TYPE_DEFAULT,
            size: AndesSimpleTagSize = SIZE_DEFAULT,
            text: String? = TEXT_DEFAULT,
            dismissable: Boolean = IS_DISMISSABLE
    ) : super(context) {
        initAttrs(type, size, text, dismissable)
    }

    /**
     * Sets the proper [config] for this message based on the [attrs] received via XML.
     *
     * @param attrs attributes from the XML.
     */
    private fun initAttrs(attrs: AttributeSet?) {
        andesTagAttrs = AndesSimpleTagAttrsParser.parse(context, attrs)
        val config = AndesSimpleTagConfigurationFactory.create(context, andesTagAttrs)
        setupComponents(config)
    }

    private fun initAttrs(
            type: AndesSimpleTagType,
            size: AndesSimpleTagSize,
            title: String?,
            dismissable: Boolean
    ) {
        andesTagAttrs = AndesSimpleTagAttrs(type, size, title, dismissable)
        val config = AndesSimpleTagConfigurationFactory.create(context, andesTagAttrs)
        setupComponents(config)
    }

    /**
     * Responsible for setting up all properties of each component that is part of this badge.
     * Is like a choreographer ;)
     */
    private fun setupComponents(config: AndesSimpleTagConfiguration) {
        initComponents()
        setupViewId()

        setupColorComponents(config)
        setupDismissable(config)
    }

    private fun setupColorComponents(config: AndesSimpleTagConfiguration) {
        setupTitleComponent(config)
        setupBackground(config)
    }

    private fun setupDismissable(config: AndesSimpleTagConfiguration) {
        if (config.isDismissable) {
            rightContent.setImageDrawable(config.dismissableIcon)
            rightContent.visibility = View.VISIBLE
            rightContent.setOnClickListener {
                visibility = View.GONE
            }
        } else {
            rightContent.visibility = View.GONE
        }
    }

    /**
     * Creates all the views that are part of this badge.
     * After a view is created then a view id is added to it.
     */
    private fun initComponents() {
        val container = LayoutInflater.from(context).inflate(R.layout.andes_layout_simple_tag, this)
        containerTag = container.findViewById(R.id.andes_tag_container)
        tagTitle = container.findViewById(R.id.simple_tag_text)
        leftContent = container.findViewById(R.id.left_content)
        rightContent = container.findViewById(R.id.right_content)
    }

    /**
     * Sets a view id to this badge.
     */
    private fun setupViewId() {
        if (id == NO_ID) { // If this view has no id
            id = View.generateViewId()
        }
    }

    /**
     * Gets data from the config and sets to the title component of this tag.
     */
    private fun setupTitleComponent(config: AndesSimpleTagConfiguration) {
        if (config.text == null || config.text.isEmpty()) {
            visibility = View.GONE
        } else {
            visibility = View.VISIBLE

            tagTitle.text = config.text.toUpperCase()
            tagTitle.setTextSize(TypedValue.COMPLEX_UNIT_PX, config.textSize)
            tagTitle.setTextColor(config.textColor.colorInt(context))

            val constraintSet = ConstraintSet()
            constraintSet.clone(containerTag)
            constraintSet.setMargin(R.id.simple_tag_text, ConstraintSet.START, config.textLeftMargin)
            constraintSet.setMargin(R.id.simple_tag_text, ConstraintSet.END, config.textRightMargin)
            constraintSet.applyTo(containerTag)
        }
    }

    private fun setupBackground(config: AndesSimpleTagConfiguration) {
        val shape = GradientDrawable()
        shape.cornerRadius = config.radius

        if (config.backgroundColor != null) {
            shape.setColor(config.backgroundColor.colorInt(context))
        }

        val borderSize = resources.getDimension(R.dimen.andes_tag_border)
        shape.setStroke(borderSize.toInt(), config.borderColor.colorInt(context))

        background = shape

        minHeight = config.height.toInt()
        minWidth = config.height.toInt()
    }

    private fun createConfig() = AndesSimpleTagConfigurationFactory.create(context, andesTagAttrs)

    companion object {
        private val SIZE_DEFAULT = AndesSimpleTagSize.SMALL
        private val TYPE_DEFAULT = AndesSimpleTagType.DEFAULT
        private val TEXT_DEFAULT = null
        private val IS_DISMISSABLE = false
    }
}
