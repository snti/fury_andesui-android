package com.mercadolibre.android.andesui.radiobuttongroup.factory

import android.content.Context
import android.util.AttributeSet
import com.mercadolibre.android.andesui.R
import com.mercadolibre.android.andesui.radiobuttongroup.RadioButtonGroupItem
import com.mercadolibre.android.andesui.radiobuttongroup.align.AndesRadioButtonGroupAlign
import com.mercadolibre.android.andesui.radiobuttongroup.distribution.AndesRadioButtonGroupDistribution

internal data class AndesRadioButtonGroupAttrs(
        val andesRadioButtonGroupAlign: AndesRadioButtonGroupAlign,
        val andesRadioButtonGroupDistribution: AndesRadioButtonGroupDistribution,
        val andesRadiobuttonGroupSelected: Int?,
        val andesRadioButtonGroupRadioButtonGroups: ArrayList<RadioButtonGroupItem>
)

/**
 * This object parse the attribute set and return an instance of AndesRadioButtonGroupAttrs
 * to be used by AndesRadioButtonGroup
 */
internal object AndesRadioButtonGroupAttrsParser {

    private const val ANDES_RADIOBUTTON_ALIGN_LEFT = "1000"
    private const val ANDES_RADIOBUTTON_ALIGN_RIGHT = "1001"

    private const val ANDES_RADIOBUTTON_DISTRIBUTION_VERTICAL = "2000"
    private const val ANDES_RADIOBUTTON_DISTRIBUTION_HORIZONTAL = "2001"

    fun parse(context: Context, attr: AttributeSet?): AndesRadioButtonGroupAttrs {
        val typedArray = context.obtainStyledAttributes(attr, R.styleable.AndesRadioButtonGroup)

        val align = when (typedArray.getString(R.styleable.AndesRadioButtonGroup_andesRadioButtonGroupAlign)) {
            ANDES_RADIOBUTTON_ALIGN_LEFT -> AndesRadioButtonGroupAlign.LEFT
            ANDES_RADIOBUTTON_ALIGN_RIGHT -> AndesRadioButtonGroupAlign.RIGHT
            else -> AndesRadioButtonGroupAlign.LEFT
        }

        val distribution = when (typedArray.getString(R.styleable.AndesRadioButtonGroup_andesRadioButtonGroupDistribution)) {
            ANDES_RADIOBUTTON_DISTRIBUTION_VERTICAL -> AndesRadioButtonGroupDistribution.VERTICAL
            ANDES_RADIOBUTTON_DISTRIBUTION_HORIZONTAL -> AndesRadioButtonGroupDistribution.HORIZONTAL
            else -> AndesRadioButtonGroupDistribution.VERTICAL
        }

        return AndesRadioButtonGroupAttrs(
                andesRadioButtonGroupAlign = align,
                andesRadioButtonGroupDistribution = distribution,
                andesRadiobuttonGroupSelected = null,
                andesRadioButtonGroupRadioButtonGroups = arrayListOf()
        ).also { typedArray.recycle() }
    }

}
