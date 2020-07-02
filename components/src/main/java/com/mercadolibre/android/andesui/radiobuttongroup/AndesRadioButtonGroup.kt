package com.mercadolibre.android.andesui.radiobuttongroup

import android.content.Context
import android.support.annotation.Nullable
import android.util.AttributeSet
import android.view.View
import android.widget.LinearLayout
import com.mercadolibre.android.andesui.R
import com.mercadolibre.android.andesui.radiobutton.AndesRadioButton
import com.mercadolibre.android.andesui.radiobutton.align.AndesRadioButtonAlign
import com.mercadolibre.android.andesui.radiobutton.status.AndesRadioButtonStatus
import com.mercadolibre.android.andesui.radiobutton.type.AndesRadioButtonType
import com.mercadolibre.android.andesui.radiobuttongroup.align.AndesRadioButtonGroupAlign
import com.mercadolibre.android.andesui.radiobuttongroup.distribution.AndesRadioButtonGroupDistribution
import com.mercadolibre.android.andesui.radiobuttongroup.factory.AndesRadioButtonGroupConfigurationFactory
import com.mercadolibre.android.andesui.radiobuttongroup.factory.AndesRadioButtonGroupAttrs
import com.mercadolibre.android.andesui.radiobuttongroup.factory.AndesRadioButtonGroupAttrsParser
import com.mercadolibre.android.andesui.radiobuttongroup.factory.AndesRadioButtonGroupConfiguration

class AndesRadioButtonGroup : LinearLayout {

    /**
     * Getter and setter for [align].
     */
    var align: AndesRadioButtonGroupAlign
        get() = andesRadioButtonGroupAttrs.andesRadioButtonGroupAlign
        set(value) {
            andesRadioButtonGroupAttrs = andesRadioButtonGroupAttrs.copy(andesRadioButtonGroupAlign = value)
            setupRadioButtons()
        }

    /**
     * Getter and setter for [distribution].
     */
    var distribution: AndesRadioButtonGroupDistribution
        get() = andesRadioButtonGroupAttrs.andesRadioButtonGroupDistribution
        set(value) {
            andesRadioButtonGroupAttrs = andesRadioButtonGroupAttrs.copy(andesRadioButtonGroupDistribution = value)
            setupOrientation(createConfig())
        }

    /**
     * Getter and setter for [selected].
     */
    var selected: Int?
        get() = andesRadioButtonGroupAttrs.andesRadiobuttonGroupSelected
        set(value) {
            andesRadioButtonGroupAttrs = andesRadioButtonGroupAttrs.copy(andesRadiobuttonGroupSelected = value)
            setupSelected(createConfig())
        }

    /**
     * Getter and setter for [selected].
     */
    var radioButtonGroups: ArrayList<RadioButtonGroupItem>
        get() = andesRadioButtonGroupAttrs.andesRadioButtonGroupRadioButtonGroups
        set(value) {
            andesRadioButtonGroupAttrs = andesRadioButtonGroupAttrs.copy(andesRadioButtonGroupRadioButtonGroups = value)
            setupRadioButtons()
        }

    /**
     * Setter [OnClickListener].
     */
    fun setupCallback(@Nullable listener: OnClickListener) {
        if (this.privateListener != listener) {
            this.privateListener = listener
        }
    }

    private var oldItemSelected: Int? = -1
    private var privateListener: OnClickListener? = null
    private lateinit var andesRadioButtonGroupAttrs: AndesRadioButtonGroupAttrs

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        initAttrs(attrs)
    }

    constructor(
            context: Context,
            align: AndesRadioButtonGroupAlign,
            distribution: AndesRadioButtonGroupDistribution,
            selected: Int?,
            radioButtonGroups: ArrayList<RadioButtonGroupItem>
    ) : super(context) {
        initAttrs(align, distribution, selected, radioButtonGroups)
    }

    /**
     * Sets the proper [config] for this message based on the [attrs] received via XML.
     *
     * @param attrs attributes from the XML.
     */
    private fun initAttrs(attrs: AttributeSet?) {
        andesRadioButtonGroupAttrs = AndesRadioButtonGroupAttrsParser.parse(context, attrs)
        val config = AndesRadioButtonGroupConfigurationFactory.create(andesRadioButtonGroupAttrs)
        setupComponents(config)
    }

    private fun initAttrs(
        align: AndesRadioButtonGroupAlign = ANDES_ALIGN_DEFAULT_VALUE,
        distribution: AndesRadioButtonGroupDistribution = ANDES_DISTRIBUTION_DEFAULT_VALUE,
        selected: Int?,
        radiobuttons: ArrayList<RadioButtonGroupItem> = arrayListOf()
    ) {
        andesRadioButtonGroupAttrs = AndesRadioButtonGroupAttrs(align, distribution, selected, radiobuttons)
        val config = AndesRadioButtonGroupConfigurationFactory.create(andesRadioButtonGroupAttrs)
        setupComponents(config)
    }

    private fun setupComponents(config: AndesRadioButtonGroupConfiguration) {
        initComponents()
        setupViewId()

        setupOrientation(config)
        setupRadioButtons()
    }

    private fun initComponents() {
        orientation = VERTICAL
    }

    /**
     * Sets a view id to this radiobutton.
     */
    private fun setupViewId() {
        if (id == NO_ID) { // If this view has no id
            id = View.generateViewId()
        }
    }

    private fun setupOrientation(config: AndesRadioButtonGroupConfiguration) {
        orientation = when (config.distribution) {
            AndesRadioButtonGroupDistribution.VERTICAL -> VERTICAL
            AndesRadioButtonGroupDistribution.HORIZONTAL -> HORIZONTAL
        }
    }

    private fun setupRadioButtons() {
        removeAllViews()
        this.radioButtonGroups.forEach { item ->
            val selected = if (selected == item.id) {
                AndesRadioButtonStatus.SELECTED
            } else {
                AndesRadioButtonStatus.UNSELECTED
            }

            val andesRadioButton = AndesRadioButton(
                context,
                item.text,
                AndesRadioButtonAlign.fromString(align.toString()),
                selected,
                AndesRadioButtonType.valueOf(item.type.toString())
            )

            if (distribution == AndesRadioButtonGroupDistribution.VERTICAL) {
                andesRadioButton.setPadding(
                        0,
                        context.resources.getDimension(R.dimen.andes_radiobutton_padding_vertical).toInt(),
                        0,
                        context.resources.getDimension(R.dimen.andes_radiobutton_padding_vertical).toInt()
                )
            } else {
                andesRadioButton.setPadding(
                        context.resources.getDimension(R.dimen.andes_radiobutton_padding_vertical).toInt(),
                        0,
                        context.resources.getDimension(R.dimen.andes_radiobutton_padding_vertical).toInt(),
                        0
                )
            }

            andesRadioButton.setupCallback(OnClickListener {
                this.selected = item.id
            })

            addView(andesRadioButton)
        }
    }

    private fun setupSelected(config: AndesRadioButtonGroupConfiguration) {
        config.radioButtonGroups.forEachIndexed { index, item ->
            if (item.id == oldItemSelected) {
                (getChildAt(index) as AndesRadioButton).status = AndesRadioButtonStatus.UNSELECTED
            }
            if (item.id == selected) {
                (getChildAt(index) as AndesRadioButton).status = AndesRadioButtonStatus.SELECTED
            }
        }
        oldItemSelected = selected
    }

    private fun createConfig() = AndesRadioButtonGroupConfigurationFactory.create(andesRadioButtonGroupAttrs)

    companion object {
        private val ANDES_ALIGN_DEFAULT_VALUE = AndesRadioButtonGroupAlign.LEFT
        private val ANDES_DISTRIBUTION_DEFAULT_VALUE = AndesRadioButtonGroupDistribution.VERTICAL
    }

}
