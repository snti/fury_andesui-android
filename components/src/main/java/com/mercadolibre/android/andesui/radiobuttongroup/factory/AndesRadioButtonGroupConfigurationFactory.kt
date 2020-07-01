package com.mercadolibre.android.andesui.radiobuttongroup.factory

import com.mercadolibre.android.andesui.radiobuttongroup.RadioButtonGroupItem
import com.mercadolibre.android.andesui.radiobuttongroup.align.AndesRadioButtonGroupAlign
import com.mercadolibre.android.andesui.radiobuttongroup.distribution.AndesRadioButtonGroupDistribution

internal data class AndesRadioButtonGroupConfiguration(
    val align: AndesRadioButtonGroupAlign,
    val distribution: AndesRadioButtonGroupDistribution,
    val selected: Int?,
    val radioButtonGroups: ArrayList<RadioButtonGroupItem>
)

internal object AndesRadioButtonGroupConfigurationFactory {

    fun create(andesRadiobuttonAttrs: AndesRadioButtonGroupAttrs): AndesRadioButtonGroupConfiguration {
        return with(andesRadiobuttonAttrs) {
            AndesRadioButtonGroupConfiguration(
                    align = andesRadiobuttonAttrs.andesRadioButtonGroupAlign,
                    distribution = andesRadiobuttonAttrs.andesRadioButtonGroupDistribution,
                    selected = andesRadiobuttonAttrs.andesRadiobuttonGroupSelected,
                    radioButtonGroups = andesRadiobuttonAttrs.andesRadioButtonGroupRadioButtonGroups
            )
        }
    }

}
