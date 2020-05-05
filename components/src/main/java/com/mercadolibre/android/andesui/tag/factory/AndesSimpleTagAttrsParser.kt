package com.mercadolibre.android.andesui.tag.factory

import android.content.Context
import android.util.AttributeSet
import com.mercadolibre.android.andesui.R
import com.mercadolibre.android.andesui.tag.size.AndesSimpleTagSize
import com.mercadolibre.android.andesui.tag.type.AndesSimpleTagType

/**
 * The data class that contains the public components of the tag.
 */
internal data class AndesSimpleTagAttrs (
        val andesSimpleTagType: AndesSimpleTagType,
        val andesSimpleTagSize: AndesSimpleTagSize,
        val andesSimpleTagText: String?,
        val isDismissable: Boolean = false
)

/**
 * This object parse the attribute set and return an instance of AndesSimpleTagAttrs to be used by AndesSimpleTag
 */
internal object AndesSimpleTagAttrsParser {

    private const val ANDES_SIMPLE_TAG_TYPE_NEUTRAL = "2000"
    private const val ANDES_SIMPLE_TAG_TYPE_HIGHLIGHT = "2001"
    private const val ANDES_SIMPLE_TAG_TYPE_SUCCESS = "2002"
    private const val ANDES_SIMPLE_TAG_TYPE_WARNING = "2003"
    private const val ANDES_SIMPLE_TAG_TYPE_ERROR = "2004"

    private const val ANDES_SIMPLE_TAG_SIZE_LARGE = "3000"
    private const val ANDES_SIMPLE_TAG_SIZE_SMALL = "3001"

    private const val ANDES_SIMPLE_TAG_NOT_DISMISSABLE = "4000"
    private const val ANDES_SIMPLE_TAG_DISMISSABLE = "4001"

    fun parse(context: Context, attr: AttributeSet?): AndesSimpleTagAttrs {
        val typedArray = context.obtainStyledAttributes(attr, R.styleable.AndesSimpleTag)

        val type = when (typedArray.getString(R.styleable.AndesSimpleTag_andesSimpleTagType)) {
            ANDES_SIMPLE_TAG_TYPE_NEUTRAL -> AndesSimpleTagType.NEUTRAL
            ANDES_SIMPLE_TAG_TYPE_HIGHLIGHT -> AndesSimpleTagType.HIGHLIGHT
            ANDES_SIMPLE_TAG_TYPE_SUCCESS -> AndesSimpleTagType.SUCCESS
            ANDES_SIMPLE_TAG_TYPE_WARNING -> AndesSimpleTagType.WARNING
            ANDES_SIMPLE_TAG_TYPE_ERROR -> AndesSimpleTagType.ERROR
            else -> AndesSimpleTagType.DEFAULT
        }

        val size = when (typedArray.getString(R.styleable.AndesSimpleTag_andesSimpleTagSize)) {
            ANDES_SIMPLE_TAG_SIZE_LARGE -> AndesSimpleTagSize.LARGE
            ANDES_SIMPLE_TAG_SIZE_SMALL -> AndesSimpleTagSize.SMALL
            else -> AndesSimpleTagSize.SMALL
        }

        val dismissable = when (typedArray.getString(R.styleable.AndesSimpleTag_andesSimpleTagIsDismissable)) {
            ANDES_SIMPLE_TAG_NOT_DISMISSABLE -> false
            ANDES_SIMPLE_TAG_DISMISSABLE -> true
            else -> false
        }

        return AndesSimpleTagAttrs(
                andesSimpleTagType = type,
                andesSimpleTagSize = size,
                andesSimpleTagText = typedArray.getString(R.styleable.AndesSimpleTag_andesSimpleTagText),
                isDismissable = dismissable
        ).also { typedArray.recycle() }
    }

}