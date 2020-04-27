package com.mercadolibre.android.andesui.badge.factory

import android.content.Context
import android.util.AttributeSet
import com.mercadolibre.android.andesui.R
import com.mercadolibre.android.andesui.badge.border.AndesBadgeBorder
import com.mercadolibre.android.andesui.badge.hierarchy.AndesBadgeHierarchy
import com.mercadolibre.android.andesui.badge.modifier.AndesBadgeModifier
import com.mercadolibre.android.andesui.badge.size.AndesBadgeSize
import com.mercadolibre.android.andesui.badge.type.AndesBadgeType

/**
 * The data class that contains the public components of the badge.
 */
internal data class AndesBadgeAttrs(
    val andesBadgeModifier: AndesBadgeModifier,
    val andesBadgeHierarchy: AndesBadgeHierarchy,
    val andesBadgeType: AndesBadgeType,
    val andesBadgeBorder: AndesBadgeBorder,
    val andesBadgeSize: AndesBadgeSize,
    val andesBadgeText: String?
)

/**
 * This object parse the attribute set and return an instance of AndesBadgeAttrs to be used by AndesBadge
 */
internal object AndesBadgeAttrsParser {

    private const val ANDES_BADGE_HIERARCHY_LOUD = "1000"
    private const val ANDES_BADGE_HIERARCHY_QUIET = "1001"

    private const val ANDES_BADGE_TYPE_NEUTRAL = "2000"
    private const val ANDES_BADGE_TYPE_HIGHLIGHT = "2001"
    private const val ANDES_BADGE_TYPE_SUCCESS = "2002"
    private const val ANDES_BADGE_TYPE_WARNING = "2003"
    private const val ANDES_BADGE_TYPE_ERROR = "2004"

    private const val ANDES_BADGE_SIZE_LARGE = "3000"
    private const val ANDES_BADGE_SIZE_SMALL = "3001"

    private const val ANDES_BADGE_MODIFIER_PILL = "4000"
    private const val ANDES_BADGE_MODIFIER_DOT = "4001"

    private const val ANDES_BADGE_BORDER_CORNER = "5000"
    private const val ANDES_BADGE_BORDER_ROUNDED = "5001"
    private const val ANDES_BADGE_BORDER_STANDARD = "5002"

    fun parse(context: Context, attr: AttributeSet?): AndesBadgeAttrs {
        val typedArray = context.obtainStyledAttributes(attr, R.styleable.AndesBadge)

        val modifier = when (typedArray.getString(R.styleable.AndesBadge_andesBadgeModifier)) {
            ANDES_BADGE_MODIFIER_PILL -> AndesBadgeModifier.PILL
            ANDES_BADGE_MODIFIER_DOT -> AndesBadgeModifier.DOT
            else -> AndesBadgeModifier.PILL
        }

        val hierarchy = when (typedArray.getString(R.styleable.AndesBadge_andesBadgeHierarchy)) {
            ANDES_BADGE_HIERARCHY_LOUD -> AndesBadgeHierarchy.LOUD
            ANDES_BADGE_HIERARCHY_QUIET -> AndesBadgeHierarchy.QUIET
            else -> AndesBadgeHierarchy.LOUD
        }

        val type = when (typedArray.getString(R.styleable.AndesBadge_andesBadgeType)) {
            ANDES_BADGE_TYPE_NEUTRAL -> AndesBadgeType.NEUTRAL
            ANDES_BADGE_TYPE_HIGHLIGHT -> AndesBadgeType.HIGHLIGHT
            ANDES_BADGE_TYPE_SUCCESS -> AndesBadgeType.SUCCESS
            ANDES_BADGE_TYPE_WARNING -> AndesBadgeType.WARNING
            ANDES_BADGE_TYPE_ERROR -> AndesBadgeType.ERROR
            else -> AndesBadgeType.NEUTRAL
        }

        val border = when (typedArray.getString(R.styleable.AndesBadge_andesBadgeBorder)) {
            ANDES_BADGE_BORDER_CORNER -> AndesBadgeBorder.CORNER
            ANDES_BADGE_BORDER_ROUNDED -> AndesBadgeBorder.ROUNDED
            ANDES_BADGE_BORDER_STANDARD -> AndesBadgeBorder.STANDARD
            else -> AndesBadgeBorder.STANDARD
        }

        val size = when (typedArray.getString(R.styleable.AndesBadge_andesBadgeSize)) {
            ANDES_BADGE_SIZE_LARGE -> AndesBadgeSize.LARGE
            ANDES_BADGE_SIZE_SMALL -> AndesBadgeSize.SMALL
            else -> AndesBadgeSize.LARGE
        }

        return AndesBadgeAttrs(
                andesBadgeModifier = modifier,
                andesBadgeHierarchy = hierarchy,
                andesBadgeType = type,
                andesBadgeBorder = border,
                andesBadgeSize = size,
                andesBadgeText = typedArray.getString(R.styleable.AndesBadge_andesBadgeText)
        ).also { typedArray.recycle() }
    }
}
