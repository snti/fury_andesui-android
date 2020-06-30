package com.mercadolibre.android.andesui.radiobuttongroup.factory

import android.content.Context
import android.util.AttributeSet
import com.mercadolibre.android.andesui.R
import com.mercadolibre.android.andesui.radiobuttongroup.RadiobuttonItem
import com.mercadolibre.android.andesui.radiobuttongroup.align.AndesRadiobuttonGroupAlign
import com.mercadolibre.android.andesui.radiobuttongroup.distribution.AndesRadiobuttonGroupDistribution

internal data class AndesRadiobuttonGroupAttrs(
        val andesRadiobuttonGroupAlign: AndesRadiobuttonGroupAlign,
        val andesRadiobuttonGroupDistribution: AndesRadiobuttonGroupDistribution,
        val andesRadiobuttonGroupSelected: Int?,
        val andesRadiobuttonGroupRadioButtons: ArrayList<RadiobuttonItem>
)

/**
 * This object parse the attribute set and return an instance of AndesRadiobuttonAttrs to be used by AndesRadiobutton
 */
internal object AndesRadiobuttonAttrParser {

//    private const val ANDES_RADIOBUTTON_ALIGN_LEFT = "1000"
//    private const val ANDES_RADIOBUTTON_ALIGN_RIGHT = "1001"
//
//    private const val ANDES_RADIOBUTTON_STATUS_SELECTED = "2000"
//    private const val ANDES_RADIOBUTTON_STATUS_UNSELECTED = "2001"
//
//    private const val ANDES_RADIOBUTTON_TYPE_IDLE = "3000"
//    private const val ANDES_RADIOBUTTON_TYPE_DISABLED = "3001"
//    private const val ANDES_RADIOBUTTON_TYPE_ERROR = "3002"
//
//    fun parse(context: Context, attr: AttributeSet?): AndesRadiobuttonAttrs {
//        val typedArray = context.obtainStyledAttributes(attr, R.styleable.AndesRadiobutton)
//
//        val align = when (typedArray.getString(R.styleable.AndesRadiobutton_andesRadiobuttonAlign)) {
//            ANDES_RADIOBUTTON_ALIGN_LEFT -> AndesRadiobuttonAlign.LEFT
//            ANDES_RADIOBUTTON_ALIGN_RIGHT -> AndesRadiobuttonAlign.RIGHT
//            else -> AndesRadiobuttonAlign.LEFT
//        }
//
//        val status = when (typedArray.getString(R.styleable.AndesRadiobutton_andesRadiobuttonStatus)) {
//            ANDES_RADIOBUTTON_STATUS_SELECTED -> AndesRadiobuttonStatus.SELECTED
//            ANDES_RADIOBUTTON_STATUS_UNSELECTED -> AndesRadiobuttonStatus.UNSELECTED
//            else -> AndesRadiobuttonStatus.UNSELECTED
//        }
//
//        val type = when (typedArray.getString(R.styleable.AndesRadiobutton_andesRadiobuttonType)) {
//            ANDES_RADIOBUTTON_TYPE_IDLE -> AndesRadiobuttonType.IDLE
//            ANDES_RADIOBUTTON_TYPE_DISABLED -> AndesRadiobuttonType.DISABLED
//            ANDES_RADIOBUTTON_TYPE_ERROR -> AndesRadiobuttonType.ERROR
//            else -> AndesRadiobuttonType.IDLE
//        }
//
//        val validatedStatus = if (type == AndesRadiobuttonType.ERROR) {
//            AndesRadiobuttonStatus.UNSELECTED
//        } else {
//            status
//        }
//
//        return AndesRadiobuttonAttrs(
//                andesRadiobuttonAlign = align,
//                andesRadiobuttonText = typedArray.getString(R.styleable.AndesRadiobutton_andesRadiobuttonText),
//                andesRadiobuttonStatus = validatedStatus,
//                andesRadiobuttonType = type
//        ).also { typedArray.recycle() }
//    }
}
