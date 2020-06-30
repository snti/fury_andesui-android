package com.mercadolibre.android.andesui.radiobuttongroup

import android.content.Context
import android.support.annotation.Nullable
import android.support.constraint.ConstraintLayout
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import com.mercadolibre.android.andesui.R
import com.mercadolibre.android.andesui.radiobuttongroup.align.AndesRadiobuttonGroupAlign
import com.mercadolibre.android.andesui.radiobuttongroup.distribution.AndesRadiobuttonGroupDistribution
import com.mercadolibre.android.andesui.radiobuttongroup.factory.AndesRadiobuttonGroupAttrs
import kotlinx.android.synthetic.main.andes_layout_radiobutton.view.*

class AndesRadiobuttonGroup : ConstraintLayout {

    /**
     * Getter and setter for [align].
     */
    var align: AndesRadiobuttonGroupAlign
        get() = andesRadiobuttonGroupAttrs.andesRadiobuttonGroupAlign
        set(value) {
            andesRadiobuttonGroupAttrs = andesRadiobuttonGroupAttrs.copy(andesRadiobuttonGroupAlign = value)
//            setupAlignComponent(createConfig())
        }

    /**
     * Getter and setter for [distribution].
     */
    var distribution: AndesRadiobuttonGroupDistribution
        get() = andesRadiobuttonGroupAttrs.andesRadiobuttonGroupDistribution
        set(value) {
            andesRadiobuttonGroupAttrs = andesRadiobuttonGroupAttrs.copy(andesRadiobuttonGroupDistribution = value)
//            setupBackgroundComponent(createConfig())
        }

    /**
     * Getter and setter for [selected].
     */
    var selected: Int?
        get() = andesRadiobuttonGroupAttrs.andesRadiobuttonGroupSelected
        set(value) {
            andesRadiobuttonGroupAttrs = andesRadiobuttonGroupAttrs.copy(andesRadiobuttonGroupSelected = value)
//            setupBackgroundComponent(createConfig())
        }

    /**
     * Getter and setter for [selected].
     */
    var radiobuttons: ArrayList<RadiobuttonItem>
        get() = andesRadiobuttonGroupAttrs.andesRadiobuttonGroupRadioButtons
        set(value) {
            andesRadiobuttonGroupAttrs = andesRadiobuttonGroupAttrs.copy(andesRadiobuttonGroupRadioButtons = value)
//            setupBackgroundComponent(createConfig())
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
    private lateinit var andesRadiobuttonGroupAttrs: AndesRadiobuttonGroupAttrs

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
//        initAttrs(attrs)
    }

    constructor(
        context: Context,
        align: AndesRadiobuttonGroupAlign,
        distribution: AndesRadiobuttonGroupDistribution,
        selected: Int?,
        radiobuttons: ArrayList<RadiobuttonItem>
    ) : super(context) {
//        initAttrs(align, distribution, selected, radiobuttons)
    }
//
//    *
//     * Sets the proper [config] for this message based on the [attrs] received via XML.
//     *
//     * @param attrs attributes from the XML.
//
//    private fun initAttrs(attrs: AttributeSet?) {
//        andesRadiobuttonGroupAttrs = AndesRadiobuttonAttrParser.parse(context, attrs)
//        val config = AndesRadiobuttonConfigurationFactory.create(andesRadiobuttonAttrs)
//        setupComponents(config)
//    }
//
//    private fun initAttrs(
//        align: AndesRadiobuttonGroupAlign = ANDES_ALIGN_DEFAULT_VALUE,
//        distribution: AndesRadiobuttonGroupDistribution = ANDES_DISTRIBUTION_DEFAULT_VALUE,
//        selected: Int?,
//        radiobuttons: ArrayList<RadiobuttonItem> = arrayListOf()
//    ) {
//        andesRadiobuttonGroupAttrs = AndesRadiobuttonAttrs(align, text, status, type)
//        val config = AndesRadiobuttonConfigurationFactory.create(andesRadiobuttonAttrs)
//        setupComponents(config)
//    }
//
//    private fun setupComponents(config: AndesRadiobuttonConfiguration) {
//        initComponents()
//        setupViewId()
//
//    }
//
//    *
//     * Creates all the views that are part of this checkbox.
//     * After a view is created then a view id is added to it.
//
//    private fun initComponents() {
//        val container = LayoutInflater.from(context).inflate(R.layout.andes_layout_radiobutton, this)
//        containerRadiobutton = container.findViewById(R.id.andes_radiobutton_container)
//        onCheckedChangeListener(leftRadiobutton)
//        onCheckedChangeListener(rightRadiobutton)
//    }
//
//    *
//     * Sets a view id to this radiobutton.
//
//    private fun setupViewId() {
//        if (id == NO_ID) { // If this view has no id
//            id = View.generateViewId()
//        }
//    }
//
//    private fun createConfig() = AndesRadiobuttonConfigurationFactory.create(andesRadiobuttonAttrs)

    companion object {
        private val ANDES_ALIGN_DEFAULT_VALUE = AndesRadiobuttonGroupAlign.LEFT
        private val ANDES_DISTRIBUTION_DEFAULT_VALUE = AndesRadiobuttonGroupDistribution.VERTICAL
    }
}
