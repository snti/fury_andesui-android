package com.mercadolibre.android.andesui.badge.factory

import android.content.Context
import com.mercadolibre.android.andesui.badge.border.AndesBadgeBorder
import com.mercadolibre.android.andesui.badge.border.AndesBadgeBorderInterface
import com.mercadolibre.android.andesui.badge.hierarchy.AndesBadgeHierarchyInterface
import com.mercadolibre.android.andesui.badge.size.AndesBadgeSizeInterface
import com.mercadolibre.android.andesui.badge.type.AndesBadgeTypeInterface
import com.mercadolibre.android.andesui.color.AndesColor

internal data class AndesBadgeConfiguration(
        val backgroundColor: AndesColor,
        val backgroundRadius: FloatArray,
        val textColor: AndesColor,
        val text: String? = null,
        val textSize: Float,
        val textMargin: Int,
        val height: Float
)

internal object AndesBadgeConfigurationFactory {

    fun create(context: Context, andesMessageAttrs: AndesBadgeAttrs): AndesBadgeConfiguration {
        return with(andesMessageAttrs) {
            AndesBadgeConfiguration(
                    backgroundColor = resolveBackgroundColor(andesBadgeHierarchy.hierarchy, andesBadgeType.type),
                    backgroundRadius = resolveBackgroundRadius(andesBadgeSize.size, andesBadgeBorder.border, context),
                    textColor = resolveTextColor(andesBadgeHierarchy.hierarchy, andesBadgeType.type),
                    text = andesBadgeText,
                    textSize = resolveTextSize(andesBadgeSize.size, context),
                    textMargin = resolveTextMargin(andesBadgeSize.size, context),
                    height = resolveHeight(andesBadgeSize.size, context)
            )
        }
    }

    private fun resolveBackgroundColor(hierarchy: AndesBadgeHierarchyInterface, type: AndesBadgeTypeInterface) = hierarchy.backgroundColor(type)
    private fun resolveBackgroundRadius(size: AndesBadgeSizeInterface, border: AndesBadgeBorderInterface, context: Context) =
             floatArrayOf(border.upStartCornerRadius(size, context), border.upEndCornerRadius(size, context),
                     border.bottomEndCornerRadius(size, context), border.bottomStartCornerRadius(size, context))
    private fun resolveTextColor(hierarchy: AndesBadgeHierarchyInterface, type: AndesBadgeTypeInterface) = hierarchy.textColor(type)
    private fun resolveTextSize(size: AndesBadgeSizeInterface, context: Context) = size.textSize(context)
    private fun resolveTextMargin(size: AndesBadgeSizeInterface, context: Context) = size.textMargin(context)
    private fun resolveHeight(size: AndesBadgeSizeInterface, context: Context) = size.height(context)

}
