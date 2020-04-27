package com.mercadolibre.android.andesui.badge.factory

import android.content.Context
import com.mercadolibre.android.andesui.badge.hierarchy.AndesBadgeHierarchyInterface
import com.mercadolibre.android.andesui.badge.type.AndesBadgeTypeInterface
import com.mercadolibre.android.andesui.color.AndesColor

internal data class AndesBadgeDotConfiguration (
    val backgroundColor: AndesColor
)

internal object AndesBadgeDotConfigurationFactory {

    fun create(context: Context, andesMessageAttrs: AndesBadgeDotAttrs): AndesBadgeDotConfiguration {
        return with(andesMessageAttrs) {
            AndesBadgeDotConfiguration(
                backgroundColor = resolveBackgroundColor(andesBadgePillHierarchy.hierarchy, andesBadgeType.type)
            )
        }
    }

    private fun resolveBackgroundColor(hierarchy: AndesBadgeHierarchyInterface, type: AndesBadgeTypeInterface) = hierarchy.backgroundColor(type)

}
