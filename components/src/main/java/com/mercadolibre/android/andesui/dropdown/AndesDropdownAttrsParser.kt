package com.mercadolibre.android.andesui.dropdown

import android.content.Context
import android.util.AttributeSet
import com.mercadolibre.android.andesui.R
import com.mercadolibre.android.andesui.textfield.state.AndesTextfieldState

internal data class AndesDropdownAttrs(val label: String?,
                                        val helper: String?,
                                        val placeholder: String?,
                                        val state: AndesTextfieldState)

/**
 * This object parse the attribute set and return an instance of AndesMessageAttrs to be used by AndesMessage
 */
internal object AndesDropdownAttrsParser {



    private const val ANDES_TEXTFIELD_STATE_ENABLED = "20"
    private const val ANDES_TEXTFIELD_STATE_ERROR = "21"
    private const val ANDES_TEXTFIELD_STATE_DISABLED = "22"
    private const val ANDES_TEXTFIELD_STATE_READONLY = "23"


    fun parse(context: Context, attr: AttributeSet?): AndesDropdownAttrs {
        val typedArray = context.obtainStyledAttributes(attr, R.styleable.AndesDropdown)

        val state = when (typedArray.getString(R.styleable.AndesDropdown_andesDropdownState)) {
            ANDES_TEXTFIELD_STATE_ENABLED -> AndesTextfieldState.ENABLED
            ANDES_TEXTFIELD_STATE_ERROR -> AndesTextfieldState.ERROR
            ANDES_TEXTFIELD_STATE_DISABLED -> AndesTextfieldState.DISABLED
            ANDES_TEXTFIELD_STATE_READONLY -> AndesTextfieldState.READONLY
            else -> AndesTextfieldState.ENABLED
        }


        return AndesDropdownAttrs(
                label = typedArray.getString(R.styleable.AndesDropdown_andesDropdownLabel),
                helper = typedArray.getString(R.styleable.AndesDropdown_andesDropdownHelper),
                placeholder = typedArray.getString(R.styleable.AndesDropdown_andesDropdownState),
                state = state
        ).also { typedArray.recycle() }
    }
}