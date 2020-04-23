package com.mercadolibre.android.andesui.badge

import android.content.Context
import android.graphics.drawable.GradientDrawable
import android.support.v7.widget.CardView
import android.util.AttributeSet
import android.util.TypedValue
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.mercadolibre.android.andesui.R
import com.mercadolibre.android.andesui.badge.border.AndesBadgeBorder
import com.mercadolibre.android.andesui.badge.factory.AndesBadgeAttrs
import com.mercadolibre.android.andesui.badge.factory.AndesBadgeAttrsParser
import com.mercadolibre.android.andesui.badge.factory.AndesBadgeConfiguration
import com.mercadolibre.android.andesui.badge.factory.AndesBadgeConfigurationFactory
import com.mercadolibre.android.andesui.badge.hierarchy.AndesBadgeHierarchy
import com.mercadolibre.android.andesui.badge.modifier.AndesBadgeModifier
import com.mercadolibre.android.andesui.badge.size.AndesBadgeSize
import com.mercadolibre.android.andesui.badge.type.AndesBadgeType
import java.util.*

class AndesBadge : CardView {

    /**
     * Getter and setter for [modifier].
     */
    var modifier: AndesBadgeModifier
        get() = andesBadgeAttrs.andesBadgeModifier
        set(value) {
            andesBadgeAttrs = andesBadgeAttrs.copy(andesBadgeModifier = value)
            setupColorComponents(createConfig())
        }

    /**
     * Getter and setter for [hierarchy].
     */
    var hierarchy: AndesBadgeHierarchy
        get() = andesBadgeAttrs.andesBadgeHierarchy
        set(value) {
            andesBadgeAttrs = andesBadgeAttrs.copy(andesBadgeHierarchy = value)
            setupColorComponents(createConfig())
        }

    /**
     * Getter and setter for [type].
     */
    var type: AndesBadgeType
        get() = andesBadgeAttrs.andesBadgeType
        set(value) {
            andesBadgeAttrs = andesBadgeAttrs.copy(andesBadgeType = value)
            setupColorComponents(createConfig())
        }

    /**
     * Getter and setter for [border].
     */
    var border: AndesBadgeBorder
        get() = andesBadgeAttrs.andesBadgeBorder
        set(value) {
            andesBadgeAttrs = andesBadgeAttrs.copy(andesBadgeBorder = value)
            setupColorComponents(createConfig())
        }

    /**
     * Getter and setter for [size].
     */
    var size: AndesBadgeSize
        get() = andesBadgeAttrs.andesBadgeSize
        set(value) {
            andesBadgeAttrs = andesBadgeAttrs.copy(andesBadgeSize = value)
            setupColorComponents(createConfig())
        }

    /**
     * Getter and setter for [text].
     */
    var text: String?
        get() = andesBadgeAttrs.andesBadgeText
        set(value) {
            andesBadgeAttrs = andesBadgeAttrs.copy(andesBadgeText = value)
            setupTitleComponent(createConfig())
        }

    private lateinit var badgeTitle: TextView
    private lateinit var andesBadgeAttrs: AndesBadgeAttrs

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
        modifier: AndesBadgeModifier = MODIFIER_DEFAULT,
        hierarchy: AndesBadgeHierarchy = HIERARCHY_DEFAULT,
        type: AndesBadgeType = STATE_DEFAULT,
        border: AndesBadgeBorder = BORDER_DEFAULT,
        size: AndesBadgeSize = SIZE_DEFAULT,
        text: String? = TEXT_DEFAULT
    ) : super(context) {
        initAttrs(modifier, hierarchy, type, border, size, text)
    }

    /**
     * Sets the proper [config] for this message based on the [attrs] received via XML.
     *
     * @param attrs attributes from the XML.
     */
    private fun initAttrs(attrs: AttributeSet?) {
        andesBadgeAttrs = AndesBadgeAttrsParser.parse(context, attrs)
        val config = AndesBadgeConfigurationFactory.create(context, andesBadgeAttrs)
        setupComponents(config)
    }

    private fun initAttrs(
        modifier: AndesBadgeModifier,
        hierarchy: AndesBadgeHierarchy,
        type: AndesBadgeType,
        border: AndesBadgeBorder,
        size: AndesBadgeSize,
        title: String?
    ) {
        andesBadgeAttrs = AndesBadgeAttrs(modifier, hierarchy, type, border, size, title)
        val config = AndesBadgeConfigurationFactory.create(context, andesBadgeAttrs)
        setupComponents(config)
    }

    /**
     * Responsible for setting up all properties of each component that is part of this badge.
     * Is like a choreographer ;)
     *
     */
    private fun setupComponents(config: AndesBadgeConfiguration) {
        cardElevation = CARD_ELEVATION

        initComponents()
        setupViewId()

        setupColorComponents(config)
    }

    private fun setupColorComponents(config: AndesBadgeConfiguration) {
        setupTitleComponent(config)
        setupBackground(config)
    }

    /**
     * Creates all the views that are part of this badge.
     * After a view is created then a view id is added to it.
     *
     */
    private fun initComponents() {
        val container = LayoutInflater.from(context).inflate(R.layout.andes_layout_badge, this)
        badgeTitle = container.findViewById(R.id.andes_badge_text)
    }

    /**
     * Sets a view id to this badge.
     *
     */
    private fun setupViewId() {
        if (id == NO_ID) { // If this view has no id
            id = View.generateViewId()
        }
    }

    /**
     * Gets data from the config and sets to the title component of this badge.
     *
     */
    private fun setupTitleComponent(config: AndesBadgeConfiguration) {
        if (config.text == null || config.text.isEmpty()) {
            badgeTitle.visibility = View.GONE
        } else {
            badgeTitle.visibility = View.VISIBLE
            badgeTitle.text = config.text.toUpperCase(Locale.getDefault())
            badgeTitle.setTextSize(TypedValue.COMPLEX_UNIT_PX, config.textSize)
            badgeTitle.setTextColor(config.textColor.colorInt(context))
            val params = LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT)
            params.setMargins(config.textMargin, 0, config.textMargin, 0)
            params.gravity = Gravity.CENTER
            badgeTitle.layoutParams = params
        }
    }

    private fun setupBackground(config: AndesBadgeConfiguration) {
        val shape = GradientDrawable()

        (shape.mutate() as GradientDrawable).cornerRadii =
                floatArrayOf(config.backgroundRadius[0], config.backgroundRadius[0],
                        config.backgroundRadius[1], config.backgroundRadius[1],
                        config.backgroundRadius[2], config.backgroundRadius[2],
                        config.backgroundRadius[3], config.backgroundRadius[3])

                shape.setColor(config.backgroundColor.colorInt(context))

                background = shape
                layoutParams = ViewGroup.LayoutParams(LayoutParams.WRAP_CONTENT, config.height.toInt())
                minimumWidth = config.height.toInt()
                minimumHeight = config.height.toInt()
    }

    private fun createConfig() = AndesBadgeConfigurationFactory.create(context, andesBadgeAttrs)

    companion object {
        private const val CARD_ELEVATION = 0F
        private val BORDER_DEFAULT = AndesBadgeBorder.ROUNDED
        private val HIERARCHY_DEFAULT = AndesBadgeHierarchy.LOUD
        private val MODIFIER_DEFAULT = AndesBadgeModifier.PILL
        private val SIZE_DEFAULT = AndesBadgeSize.SMALL
        private val STATE_DEFAULT = AndesBadgeType.NEUTRAL
        private val TEXT_DEFAULT = null
    }
}
