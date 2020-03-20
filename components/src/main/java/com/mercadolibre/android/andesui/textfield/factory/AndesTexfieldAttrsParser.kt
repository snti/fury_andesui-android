package com.mercadolibre.android.andesui.textfield.factory


import android.content.Context
import android.util.AttributeSet
import com.mercadolibre.android.andesui.R
import com.mercadolibre.android.andesui.textfield.AndesTextfield
import com.mercadolibre.android.andesui.textfield.state.AndesTextfieldState

/**
 * The data class that contains the public components of the textfield.
 */
internal data class AndesTextfieldAttrs(val label: String?,
                                        val helper: String?,
                                        val placeholder: String?,
                                        val counter: AndesTextfieldCounter?,
                                        val state: AndesTextfieldState)

/**
 * This object parse the attribute set and return an instance of AndesMessageAttrs to be used by AndesMessage
 */
internal object AndesTextfieldAttrsParser {

    private const val ANDES_TEXTFIELD_STATE_ENABLED = "20"
    private const val ANDES_TEXTFIELD_STATE_ERROR = "21"
    private const val ANDES_TEXTFIELD_STATE_DISABLED = "22"
    private const val ANDES_TEXTFIELD_STATE_READONLY = "23"


    fun parse(context: Context, attr: AttributeSet?): AndesTextfieldAttrs {
        val typedArray = context.obtainStyledAttributes(attr, R.styleable.AndesTextfield)

        val state = when (typedArray.getString(R.styleable.AndesTextfield_andesTextfieldState)) {
            ANDES_TEXTFIELD_STATE_ENABLED -> AndesTextfieldState.ENABLED
            ANDES_TEXTFIELD_STATE_ERROR -> AndesTextfieldState.ERROR
            ANDES_TEXTFIELD_STATE_DISABLED -> AndesTextfieldState.DISABLED
            ANDES_TEXTFIELD_STATE_READONLY -> AndesTextfieldState.READONLY
            else -> AndesTextfieldState.ENABLED
        }


        val counterMinValue = typedArray.getInt(R.styleable.AndesTextfield_andesTexfieldCounterMinValue, 0)

        val counterMaxValue = typedArray.getInt(R.styleable.AndesTextfield_andesTexfieldCounterMaxValue, 0)

        return AndesTextfieldAttrs(
                label = typedArray.getString(R.styleable.AndesTextfield_andesTextfieldLabel),
                helper = typedArray.getString(R.styleable.AndesTextfield_andesTextfieldHelper),
                placeholder = typedArray.getString(R.styleable.AndesTextfield_andesTextfieldHint),
                counter = AndesTextfieldCounter(counterMinValue, counterMaxValue),
                state = state
        ).also { typedArray.recycle() }
    }
}