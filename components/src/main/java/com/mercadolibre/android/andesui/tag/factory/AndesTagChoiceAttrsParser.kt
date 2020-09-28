package com.mercadolibre.android.andesui.tag.factory

import android.content.Context
import android.content.res.TypedArray
import android.util.AttributeSet
import com.mercadolibre.android.andesui.R
import com.mercadolibre.android.andesui.tag.choice.state.AndesTagChoiceState
import com.mercadolibre.android.andesui.tag.choice.mode.AndesTagChoiceMode
import com.mercadolibre.android.andesui.tag.leftcontent.AndesTagLeftContent
import com.mercadolibre.android.andesui.tag.leftcontent.LeftContent
import com.mercadolibre.android.andesui.tag.rightcontent.AndesTagRightContent
import com.mercadolibre.android.andesui.tag.size.AndesTagSize

/**
 * The data class that contains the public components of the tag.
 */
internal data class AndesTagChoiceAttrs(
        val andesSimpleTagText: String?,
        val andesTagChoiceMode: AndesTagChoiceMode,
        val andesTagSize: AndesTagSize,
        val andesTagChoiceState: AndesTagChoiceState,
        val leftContentData: LeftContent? = null,
        val leftContent: AndesTagLeftContent? = null

) {
    var rightContent: AndesTagRightContent? = null

    init {
        when(andesTagChoiceMode) {
            AndesTagChoiceMode.SIMPLE -> {
                if (andesTagChoiceState == AndesTagChoiceState.SELECTED) {
                    rightContent = AndesTagRightContent.CHECK
                }
            }
            AndesTagChoiceMode.DROPDOWN -> {
                rightContent = AndesTagRightContent.DROPDOWN
            }
        }
    }
}

/**
 * This object parse the attribute set and return an instance of AndesSimpleTagAttrs to be used by AndesSimpleTag
 */
internal object AndesTagChoiceAttrsParser {

    private const val ANDES_CHOICE_TAG_TYPE_SIMPLE = "5000"
    private const val ANDES_CHOICE_TAG_TYPE_DROPDOWN = "5001"

    private const val ANDES_CHOICE_TAG_STATE_IDLE = "6000"
    private const val ANDES_CHOICE_TAG_STATE_SELECTED = "6001"

    private const val ANDES_TAG_SIZE_LARGE = "7000"
    private const val ANDES_TAG_SIZE_SMALL = "7001"

    fun parse(context: Context, attr: AttributeSet?): AndesTagChoiceAttrs {
        val typedArray = context.obtainStyledAttributes(attr, R.styleable.AndesTagChoice)

        val type = parseType(typedArray)
        val size = parseSize(typedArray)
        val state = parseState(typedArray)

        return AndesTagChoiceAttrs(
                andesSimpleTagText = typedArray.getString(R.styleable.AndesTagChoice_tagChoiceText),
                andesTagChoiceMode = type,
                andesTagSize = size,
                andesTagChoiceState = state
        ).also { typedArray.recycle() }
    }

    private fun parseState(typedArray: TypedArray): AndesTagChoiceState {
        return when (typedArray.getString(R.styleable.AndesTagChoice_tagChoiceState)) {
            ANDES_CHOICE_TAG_STATE_IDLE -> AndesTagChoiceState.IDLE
            ANDES_CHOICE_TAG_STATE_SELECTED -> AndesTagChoiceState.SELECTED
            else -> AndesTagChoiceState.IDLE
        }
    }

    private fun parseType(typedArray: TypedArray): AndesTagChoiceMode {
        return when (typedArray.getString(R.styleable.AndesTagChoice_tagChoiceType)) {
            ANDES_CHOICE_TAG_TYPE_SIMPLE -> AndesTagChoiceMode.SIMPLE
            ANDES_CHOICE_TAG_TYPE_DROPDOWN -> AndesTagChoiceMode.DROPDOWN
            else -> AndesTagChoiceMode.SIMPLE
        }
    }

    private fun parseSize(typedArray: TypedArray): AndesTagSize {
        return when (typedArray.getString(R.styleable.AndesTagSimple_tagSimpleSize)) {
            ANDES_TAG_SIZE_LARGE -> AndesTagSize.LARGE
            ANDES_TAG_SIZE_SMALL -> AndesTagSize.SMALL
            else -> AndesTagSize.LARGE
        }
    }
}
