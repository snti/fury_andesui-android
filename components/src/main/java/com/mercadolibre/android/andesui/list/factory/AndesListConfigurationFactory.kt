package com.mercadolibre.android.andesui.list.factory

import com.mercadolibre.android.andesui.list.row.size.AndesListViewItemSize

internal data class AndesListConfiguration(
        val size: AndesListViewItemSize
)

internal object AndesListConfigurationFactory {

    fun create(andesListAttrs: AndesListAttrs): AndesListConfiguration {
        return with(andesListAttrs) {
            AndesListConfiguration(
                    size = andesListAttrs.andesListViewItemSize
            )
        }
    }

}