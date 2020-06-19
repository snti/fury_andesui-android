package com.mercadolibre.android.andesui.thumbnail.factory

import android.content.Context
import android.util.AttributeSet
import com.mercadolibre.android.andesui.R
import com.mercadolibre.android.andesui.color.AndesColor
import com.mercadolibre.android.andesui.color.toAndesColor
import com.mercadolibre.android.andesui.thumbnail.hierarchy.AndesThumbnailHierarchy
import com.mercadolibre.android.andesui.thumbnail.size.AndesThumbnailSize
import com.mercadolibre.android.andesui.thumbnail.state.AndesThumbnailState
import com.mercadolibre.android.andesui.thumbnail.type.AndesThumbnailType

/**
 * The data class that contains the public components of the badge.
 */
internal data class AndesThumbnailAttrs(
    val andesThumbnailAccentColor: AndesColor,
    val andesThumbnailFallbackImage: String,
    val andesThumbnailHierarchy: AndesThumbnailHierarchy,
    val andesThumbnailImage: Int,
    val andesThumbnailType: AndesThumbnailType,
    val andesThumbnailSize: AndesThumbnailSize,
    val andesThumbnailState: AndesThumbnailState
)

/**
 * This object parse the attribute set and return an instance of AndesBadgeAttrs to be used by AndesBadge
 */
internal object AndesThumbnailAttrsParser {

    private const val ANDES_THUMBNAIL_HIERARCHY_DEFAULT = "1000"
    private const val ANDES_THUMBNAIL_HIERARCHY_LOUD = "1001"
    private const val ANDES_THUMBNAIL_HIERARCHY_QUIET = "1002"

    private const val ANDES_THUMBNAIL_TYPE_ICON = "2000"
    private const val ANDES_THUMBNAIL_TYPE_IMAGE_CIRCLE = "2001"
    private const val ANDES_THUMBNAIL_TYPE_IMAGE_SQUARE = "2002"

    private const val ANDES_THUMBNAIL_SIZE_24 = "3000"
    private const val ANDES_THUMBNAIL_SIZE_32 = "3001"
    private const val ANDES_THUMBNAIL_SIZE_40 = "3002"
    private const val ANDES_THUMBNAIL_SIZE_48 = "3003"
    private const val ANDES_THUMBNAIL_SIZE_56 = "3004"
    private const val ANDES_THUMBNAIL_SIZE_64 = "3005"
    private const val ANDES_THUMBNAIL_SIZE_72 = "3006"
    private const val ANDES_THUMBNAIL_SIZE_80 = "3007"

    private const val ANDES_THUMBNAIL_STATE_DISABLED = "4000"
    private const val ANDES_THUMBNAIL_STATE_ENABLED = "4001"

    fun parse(context: Context, attr: AttributeSet?): AndesThumbnailAttrs {
        val typedArray = context.obtainStyledAttributes(attr, R.styleable.AndesThumbnail)

        val hierarchy = when (typedArray.getString(R.styleable.AndesThumbnail_andesThumbnailHierarchy)) {
            ANDES_THUMBNAIL_HIERARCHY_DEFAULT -> AndesThumbnailHierarchy.DEFAULT
            ANDES_THUMBNAIL_HIERARCHY_LOUD -> AndesThumbnailHierarchy.LOUD
            ANDES_THUMBNAIL_HIERARCHY_QUIET -> AndesThumbnailHierarchy.QUIET
            else -> AndesThumbnailHierarchy.DEFAULT
        }

        val type = when (typedArray.getString(R.styleable.AndesThumbnail_andesThumbnailType)) {
            ANDES_THUMBNAIL_TYPE_ICON -> AndesThumbnailType.ICON
            else -> AndesThumbnailType.ICON
        }

        val size = when (typedArray.getString(R.styleable.AndesThumbnail_andesThumbnailSize)) {
            ANDES_THUMBNAIL_SIZE_24 -> AndesThumbnailSize.SIZE_24
            ANDES_THUMBNAIL_SIZE_32 -> AndesThumbnailSize.SIZE_32
            ANDES_THUMBNAIL_SIZE_40 -> AndesThumbnailSize.SIZE_40
            ANDES_THUMBNAIL_SIZE_48 -> AndesThumbnailSize.SIZE_48
            ANDES_THUMBNAIL_SIZE_56 -> AndesThumbnailSize.SIZE_56
            ANDES_THUMBNAIL_SIZE_64 -> AndesThumbnailSize.SIZE_64
            ANDES_THUMBNAIL_SIZE_72 -> AndesThumbnailSize.SIZE_72
            ANDES_THUMBNAIL_SIZE_80 -> AndesThumbnailSize.SIZE_80
            else -> AndesThumbnailSize.SIZE_48
        }

        val state = when (typedArray.getString(R.styleable.AndesThumbnail_andesThumbnailState)) {
            ANDES_THUMBNAIL_STATE_DISABLED -> AndesThumbnailState.DISABLED
            ANDES_THUMBNAIL_STATE_ENABLED -> AndesThumbnailState.ENABLED
            else -> AndesThumbnailState.ENABLED
        }

        return AndesThumbnailAttrs(
                andesThumbnailHierarchy = hierarchy,
                andesThumbnailType = type,
                andesThumbnailSize = size,
                andesThumbnailState = state,
                andesThumbnailAccentColor = R.styleable.AndesThumbnail_andesThumbnailAccentColor.toAndesColor(),
                andesThumbnailFallbackImage
                = typedArray.getString(R.styleable.AndesThumbnail_andesThumbnailFallbackImage),
                andesThumbnailImage = R.styleable.AndesThumbnail_andesThumbnailImage
        ).also { typedArray.recycle() }
    }
}
