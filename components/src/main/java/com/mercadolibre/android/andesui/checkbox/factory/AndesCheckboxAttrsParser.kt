package com.mercadolibre.android.andesui.checkbox.factory

import android.content.Context
import android.util.AttributeSet
import com.mercadolibre.android.andesui.R
import com.mercadolibre.android.andesui.checkbox.status.AndesCheckboxStatus
import com.mercadolibre.android.andesui.checkbox.factory.align.AndesCheckboxAlign
import com.mercadolibre.android.andesui.checkbox.type.AndesCheckboxType

internal data class AndesCheckboxAttrs(
        val andesCheckboxAlign: AndesCheckboxAlign?,
        val andesCheckboxText: String?,
        val andesCheckboxStatus: AndesCheckboxStatus?,
        val andesCheckboxType: AndesCheckboxType?


)

/**
 * This object parse the attribute set and return an instance of AndesSimpleTagAttrs to be used by AndesCheckbox
 */
internal object AndesCheckboxAttrParser {

    private const val ANDES_CHECKBOX_ALIGN_LEFT = "8"
    private const val ANDES_CHECKBOX_ALIGN_RIGHT = "9"

    private const val ANDES_CHECKBOX_STATUS_CHECKED = "1999"
    private const val ANDES_CHECKBOX_STATUS_UNCHECKED = "2000"
    private const val ANDES_CHECKBOX_STATUS_UNKNOWN = "2001"

    private const val ANDES_CHECKBOX_TYPE_IDLE = "8888"
    private const val ANDES_CHECKBOX_TYPE_DISABLED = "8889"
    private const val ANDES_CHECKBOX_TYPE_ERROR = "8890"

    fun parse(context: Context, attr: AttributeSet?): AndesCheckboxAttrs {
        val typedArray = context.obtainStyledAttributes(attr, R.styleable.AndesCheckbox)

        val align = when (typedArray.getString(R.styleable.AndesCheckbox_andesCheckboxAlign)) {
            ANDES_CHECKBOX_ALIGN_LEFT -> AndesCheckboxAlign.LEFT
            ANDES_CHECKBOX_ALIGN_RIGHT -> AndesCheckboxAlign.RIGHT
            else -> AndesCheckboxAlign.LEFT
        }

        val status = when (typedArray.getString(R.styleable.AndesCheckbox_andesCheckboxStatus)) {
            ANDES_CHECKBOX_STATUS_CHECKED -> AndesCheckboxStatus.CHECKED
            ANDES_CHECKBOX_STATUS_UNCHECKED -> AndesCheckboxStatus.UNCHECKED
            ANDES_CHECKBOX_STATUS_UNKNOWN -> AndesCheckboxStatus.UNKNOWN
            else -> AndesCheckboxStatus.UNKNOWN
        }

        val type = when (typedArray.getString(R.styleable.AndesCheckbox_andesCheckboxType)) {
            ANDES_CHECKBOX_TYPE_IDLE -> AndesCheckboxType.IDLE
            ANDES_CHECKBOX_TYPE_DISABLED -> AndesCheckboxType.DISABLED
            ANDES_CHECKBOX_TYPE_ERROR -> AndesCheckboxType.ERROR
            else -> AndesCheckboxType.IDLE
        }

        return AndesCheckboxAttrs(
                andesCheckboxAlign = align,
                andesCheckboxText = typedArray.getString(R.styleable.AndesCheckbox_andesCheckboxText),
                andesCheckboxStatus = status,
                andesCheckboxType = type
        ).also { typedArray.recycle() }
    }
}