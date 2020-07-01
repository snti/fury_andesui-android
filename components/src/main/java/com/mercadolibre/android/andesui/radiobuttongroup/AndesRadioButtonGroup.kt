package com.mercadolibre.android.andesui.radiobuttongroup

import android.content.Context
import android.support.annotation.Nullable
import android.support.constraint.ConstraintLayout
import android.util.AttributeSet
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

class AndesRadioButtonGroup : ConstraintLayout {

    /**
     * Getter and setter for [align].
     */
    var align: AndesRadioButtonGroupAlign
        get() = andesRadioButtonGroupAttrs.andesRadioButtonGroupAlign
        set(value) {
            andesRadioButtonGroupAttrs = andesRadioButtonGroupAttrs.copy(andesRadioButtonGroupAlign = value)
//            setupAlignComponent(createConfig())
        }

    /**
     * Getter and setter for [distribution].
     */
    var distribution: AndesRadioButtonGroupDistribution
        get() = andesRadioButtonGroupAttrs.andesRadioButtonGroupDistribution
        set(value) {
            andesRadioButtonGroupAttrs = andesRadioButtonGroupAttrs.copy(andesRadioButtonGroupDistribution = value)
//            setupBackgroundComponent(createConfig())
        }

    /**
     * Getter and setter for [selected].
     */
    var selected: Int?
        get() = andesRadioButtonGroupAttrs.andesRadiobuttonGroupSelected
        set(value) {
            andesRadioButtonGroupAttrs = andesRadioButtonGroupAttrs.copy(andesRadiobuttonGroupSelected = value)
//            setupBackgroundComponent(createConfig())
        }

    /**
     * Getter and setter for [selected].
     */
    var radioButtonGroups: ArrayList<RadioButtonGroupItem>
        get() = andesRadioButtonGroupAttrs.andesRadioButtonGroupRadioButtonGroups
        set(value) {
            andesRadioButtonGroupAttrs = andesRadioButtonGroupAttrs.copy(andesRadioButtonGroupRadioButtonGroups = value)
            setupComponents(createConfig())
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
//        setupViewId()
    }

    private fun initComponents() {
        val components = arrayListOf<AndesRadioButton>()
        this.radioButtonGroups.forEach { item ->
            components.add(
                    AndesRadioButton(
                            context,
                            item.text,
                            AndesRadioButtonAlign.fromString(align.toString()),
                            AndesRadioButtonStatus.UNSELECTED,
                            AndesRadioButtonType.valueOf(item.type.toString())
                    )
            )
        }

        val asd = "SAPE"

//        val container = LayoutInflater.from(context).inflate(R.layout.andes_layout_radiobutton, this)
//        containerRadiobutton = container.findViewById(R.id.andes_radiobutton_container)
//        onCheckedChangeListener(leftRadiobutton)
//        onCheckedChangeListener(rightRadiobutton)
    }
//
//    *
//     * Sets a view id to this radiobutton.
//
//    private fun setupViewId() {
//        if (id == NO_ID) { // If this view has no id
//            id = View.generateViewId()
//        }
//    }

    private fun createConfig() = AndesRadioButtonGroupConfigurationFactory.create(andesRadioButtonGroupAttrs)

    companion object {
        private val ANDES_ALIGN_DEFAULT_VALUE = AndesRadioButtonGroupAlign.LEFT
        private val ANDES_DISTRIBUTION_DEFAULT_VALUE = AndesRadioButtonGroupDistribution.VERTICAL
    }
}
