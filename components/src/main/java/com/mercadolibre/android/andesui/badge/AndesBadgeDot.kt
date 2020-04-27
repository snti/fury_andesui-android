package com.mercadolibre.android.andesui.badge

import android.content.Context
import android.support.v7.widget.CardView
import android.util.AttributeSet
import com.mercadolibre.android.andesui.badge.factory.AndesBadgeDotAttrs
import com.mercadolibre.android.andesui.badge.factory.AndesBadgeDotAttrsParser
import com.mercadolibre.android.andesui.badge.factory.AndesBadgeDotConfiguration
import com.mercadolibre.android.andesui.badge.factory.AndesBadgeDotConfigurationFactory
import com.mercadolibre.android.andesui.badge.type.AndesBadgeType

class AndesBadgeDot : CardView {

    /**
     * Getter and setter for [type].
     */
    var type: AndesBadgeType
        get() = andesBadgeAttrs.andesBadgeType
        set(value) {
            andesBadgeAttrs = andesBadgeAttrs.copy(andesBadgeType = value)
//            setupColorComponents(createConfig())
        }

    private lateinit var andesBadgeAttrs: AndesBadgeDotAttrs

    @Suppress("unused")
    private constructor(context: Context) : super(context) {
        throw IllegalStateException("Constructor without parameters in Andes Badge is not allowed. You must provide some attributes.")
    }

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        initAttrs(attrs)
    }

    @Suppress("unused")
    constructor(context: Context, type: AndesBadgeType = STATE_DEFAULT) : super(context) {
        initAttrs(type)
    }

    /**
     * Sets the proper [config] for this message based on the [attrs] received via XML.
     *
     * @param attrs attributes from the XML.
     */
    private fun initAttrs(attrs: AttributeSet?) {
        andesBadgeAttrs = AndesBadgeDotAttrsParser.parse(context, attrs)
        val config = AndesBadgeDotConfigurationFactory.create(context, andesBadgeAttrs)
        setupComponents(config)
    }

    private fun initAttrs(type: AndesBadgeType) {
        andesBadgeAttrs = AndesBadgeDotAttrs(type)
        val config = AndesBadgeDotConfigurationFactory.create(context, andesBadgeAttrs)
        setupComponents(config)
    }

    /**
     * Responsible for setting up all properties of each component that is part of this badge.
     * Is like a choreographer ;)
     */
    private fun setupComponents(config: AndesBadgeDotConfiguration) {
//        initComponents()
//        setupViewId()
//
//        setupColorComponents(config)
    }
//
//    private fun setupColorComponents(config: AndesBadgePillConfiguration) {
//        setupTitleComponent(config)
//        setupBackground(config)
//    }
//
//    /**
//     * Creates all the views that are part of this badge.
//     * After a view is created then a view id is added to it.
//     */
//    private fun initComponents() {
//        val container = LayoutInflater.from(context).inflate(R.layout.andes_layout_badge_pill, this)
//        badgeTitle = container.findViewById(R.id.andes_badge_text)
//    }
//
//    /**
//     * Sets a view id to this badge.
//     */
//    private fun setupViewId() {
//        if (id == NO_ID) { // If this view has no id
//            id = View.generateViewId()
//        }
//    }
//
//    /**
//     * Gets data from the config and sets to the title component of this badge.
//     */
//    private fun setupTitleComponent(config: AndesBadgePillConfiguration) {
//        if (config.text == null || config.text.isEmpty()) {
//            badgeTitle.visibility = View.GONE
//        } else {
//            badgeTitle.visibility = View.VISIBLE
//            badgeTitle.text = config.text.toUpperCase(Locale.getDefault())
//            badgeTitle.setTextSize(TypedValue.COMPLEX_UNIT_PX, config.textSize)
//            badgeTitle.setTextColor(config.textColor.colorInt(context))
//            val params = LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT)
//            params.setMargins(config.textMargin, 0, config.textMargin, 0)
//            params.gravity = Gravity.CENTER
//            badgeTitle.layoutParams = params
//        }
//    }
//
//    private fun setupBackground(config: AndesBadgePillConfiguration) {
//        val shape = GradientDrawable()
//
//        (shape.mutate() as GradientDrawable).cornerRadii =
//                floatArrayOf(config.backgroundRadius[0], config.backgroundRadius[0],
//                        config.backgroundRadius[1], config.backgroundRadius[1],
//                        config.backgroundRadius[2], config.backgroundRadius[2],
//                        config.backgroundRadius[3], config.backgroundRadius[3])
//
//                shape.setColor(config.backgroundColor.colorInt(context))
//
//                background = shape
//                layoutParams = ViewGroup.LayoutParams(LayoutParams.WRAP_CONTENT, config.height.toInt())
//                minimumWidth = config.height.toInt()
//                minimumHeight = config.height.toInt()
//    }

    private fun createConfig() = AndesBadgeDotConfigurationFactory.create(context, andesBadgeAttrs)

    companion object {
        private val STATE_DEFAULT = AndesBadgeType.NEUTRAL
    }

}