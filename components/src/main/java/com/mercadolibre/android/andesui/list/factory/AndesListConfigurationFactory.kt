package com.mercadolibre.android.andesui.list.factory

import com.mercadolibre.android.andesui.list.size.AndesListViewItemSize
import com.mercadolibre.android.andesui.list.type.AndesListType

internal data class AndesListConfiguration(
        val size: AndesListViewItemSize,
        val type: AndesListType
)

internal object AndesListConfigurationFactory {

    fun create(andesListAttrs: AndesListAttrs): AndesListConfiguration {
        return with(andesListAttrs) {
            AndesListConfiguration(
                    size = andesListAttrs.andesListItemSize,
                    type = andesListAttrs.andesListType
            )
        }
    }

}