package com.mercadolibre.android.andesui.radiobutton

import android.content.Context
import android.graphics.drawable.GradientDrawable
import android.support.annotation.Nullable
import android.support.constraint.ConstraintLayout
import android.util.AttributeSet
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import com.mercadolibre.android.andesui.R
import com.mercadolibre.android.andesui.radiobutton.align.AndesRadiobuttonAlign
import com.mercadolibre.android.andesui.radiobutton.factory.AndesRadiobuttonAttrParser
import com.mercadolibre.android.andesui.radiobutton.factory.AndesRadiobuttonAttrs
import com.mercadolibre.android.andesui.radiobutton.factory.AndesRadiobuttonConfiguration
import com.mercadolibre.android.andesui.radiobutton.factory.AndesRadiobuttonConfigurationFactory
import com.mercadolibre.android.andesui.radiobutton.status.AndesRadiobuttonStatus
import com.mercadolibre.android.andesui.radiobutton.type.AndesRadiobuttonType
import com.mercadolibre.android.andesui.typeface.getFontOrDefault
import kotlinx.android.synthetic.main.andes_layout_radiobutton.view.*

class AndesRadiobutton : ConstraintLayout {

    /**
     * Getter and setter for [text].
     */
    var text: String?
        get() = andesRadiobuttonAttrs.andesRadiobuttonText
        set(value) {
            andesRadiobuttonAttrs = andesRadiobuttonAttrs.copy(andesRadiobuttonText = value)
            setupTitleComponent(createConfig())
        }

    /**
     * Getter and setter for [align].
     */
    var align: AndesRadiobuttonAlign
        get() = andesRadiobuttonAttrs.andesRadiobuttonAlign
        set(value) {
            andesRadiobuttonAttrs = andesRadiobuttonAttrs.copy(andesRadiobuttonAlign = value)
            setupAlignComponent(createConfig())
        }

    /**
     * Getter and setter for [AndesRadiobuttonStatus].
     */
    var status: AndesRadiobuttonStatus
        get() = andesRadiobuttonAttrs.andesRadiobuttonStatus
        set(value) {
            andesRadiobuttonAttrs = andesRadiobuttonAttrs.copy(andesRadiobuttonStatus = value)
            setupBackgroundComponent(createConfig())
        }

    /**
     * Getter and setter for [type].
     */
    var type: AndesRadiobuttonType
        get() = andesRadiobuttonAttrs.andesRadiobuttonType
        set(value) {
            andesRadiobuttonAttrs = andesRadiobuttonAttrs.copy(andesRadiobuttonType = value)
            setupBackgroundComponent(createConfig())
        }

    /**
     * Setter [OnClickListener].
     */
    fun setupCallback(@Nullable listener: OnClickListener) {
        if (this.privateListener != listener) {
            this.privateListener = listener
        }
    }

    private var privateListener: OnClickListener? = null
    private lateinit var andesRadiobuttonAttrs: AndesRadiobuttonAttrs
    private lateinit var containerRadiobutton: ConstraintLayout

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        initAttrs(attrs)
    }

    constructor(
        context: Context,
        text: String,
        align: AndesRadiobuttonAlign = ANDES_ALIGN_DEFAULT_VALUE,
        status: AndesRadiobuttonStatus = ANDES_STATUS_DEFAULT_VALUE,
        type: AndesRadiobuttonType = ANDES_TYPE_DEFAULT_VALUE
    ) : super(context) {
        initAttrs(text, align, status, type)
    }

    /**
     * Sets the proper [config] for this message based on the [attrs] received via XML.
     *
     * @param attrs attributes from the XML.
     */
    private fun initAttrs(attrs: AttributeSet?) {
        andesRadiobuttonAttrs = AndesRadiobuttonAttrParser.parse(context, attrs)
        val config = AndesRadiobuttonConfigurationFactory.create(andesRadiobuttonAttrs)
        setupComponents(config)
    }

    private fun initAttrs(
        text: String,
        align: AndesRadiobuttonAlign = ANDES_ALIGN_DEFAULT_VALUE,
        status: AndesRadiobuttonStatus = ANDES_STATUS_DEFAULT_VALUE,
        type: AndesRadiobuttonType = ANDES_TYPE_DEFAULT_VALUE
    ) {
        andesRadiobuttonAttrs = AndesRadiobuttonAttrs(align, text, status, type)
        val config = AndesRadiobuttonConfigurationFactory.create(andesRadiobuttonAttrs)
        setupComponents(config)
    }

    private fun setupComponents(config: AndesRadiobuttonConfiguration) {
        initComponents()
        setupViewId()

        setupTitleComponent(config)
        setupAlignComponent(config)
        setupBackgroundComponent(config)
    }

    /**
     * Creates all the views that are part of this checkbox.
     * After a view is created then a view id is added to it.
     */
    private fun initComponents() {
        val container = LayoutInflater.from(context).inflate(R.layout.andes_layout_radiobutton, this)
        containerRadiobutton = container.findViewById(R.id.andes_radiobutton_container)
        onCheckedChangeListener(leftRadiobutton)
        onCheckedChangeListener(rightRadiobutton)
    }

    private fun onCheckedChangeListener(checkbox: FrameLayout) {
        checkbox.setOnClickListener {
            when (type) {
                AndesRadiobuttonType.ERROR -> {
                    type = AndesRadiobuttonType.IDLE
                    status = AndesRadiobuttonStatus.SELECTED
                    privateListener?.onClick(this)
                    setupBackgroundComponent(createConfig())
                }
                AndesRadiobuttonType.IDLE -> {
                    status = when (status) {
                        AndesRadiobuttonStatus.SELECTED -> AndesRadiobuttonStatus.UNSELECTED
                        AndesRadiobuttonStatus.UNSELECTED -> AndesRadiobuttonStatus.SELECTED
                    }
                    privateListener?.onClick(this)
                    setupBackgroundComponent(createConfig())
                }
            }
        }
    }

    /**
     * Sets a view id to this radiobutton.
     */
    private fun setupViewId() {
        if (id == NO_ID) { // If this view has no id
            id = View.generateViewId()
        }
    }

    /**
     * Gets data from the config and sets to the title component of this radiobutton.
     */
    private fun setupTitleComponent(config: AndesRadiobuttonConfiguration) {
        checkboxText.text = config.text
        checkboxText.typeface = context.getFontOrDefault(R.font.andes_font_regular)
        checkboxText.setTextSize(TypedValue.COMPLEX_UNIT_PX, context.resources.getDimension(R.dimen.andes_radiobutton_text_size))
        checkboxText.setTextColor(config.type.type.textColor(context).colorInt(context))
    }

    /**
     * Gets data from the config and sets to the align component of this radiobutton.
     */
    private fun setupAlignComponent(config: AndesRadiobuttonConfiguration) {
        when (config.align) {
            AndesRadiobuttonAlign.LEFT -> {
                leftRadiobutton.visibility = View.VISIBLE
                rightRadiobutton.visibility = View.GONE
            }
            AndesRadiobuttonAlign.RIGHT -> {
                leftRadiobutton.visibility = View.GONE
                rightRadiobutton.visibility = View.VISIBLE
            }
        }
    }

    /**
     * Draw radiobutton.
     */
    private fun setupBackgroundComponent(config: AndesRadiobuttonConfiguration) {
        // Border
        val shapeBorder = GradientDrawable()
        shapeBorder.cornerRadius = resources.getDimension(R.dimen.andes_radiobutton_radius)
        shapeBorder.setStroke(
                resources.getDimension(R.dimen.andes_radiobutton_stroke_width).toInt(),
                config.type.type.borderColor(context, config.status).colorInt(context)
        )
        leftRadiobuttonBorder.background = shapeBorder
        rightRadiobuttonBorder.background = shapeBorder

        // Background
        val shapeBackground = config.status.status.icon(
                context,
                config.type.type.backgroundColor(context, config.status)
        )
        leftRadiobuttonIcon.background = shapeBackground
        rightRadiobuttonIcon.background = shapeBackground
    }

    private fun createConfig() = AndesRadiobuttonConfigurationFactory.create(andesRadiobuttonAttrs)

    companion object {
        private val ANDES_ALIGN_DEFAULT_VALUE = AndesRadiobuttonAlign.LEFT
        private val ANDES_STATUS_DEFAULT_VALUE = AndesRadiobuttonStatus.UNSELECTED
        private val ANDES_TYPE_DEFAULT_VALUE = AndesRadiobuttonType.IDLE
    }
}
