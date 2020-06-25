package com.mercadolibre.android.andesui.radiobutton.type

import android.content.Context
import com.mercadolibre.android.andesui.R
import com.mercadolibre.android.andesui.radiobutton.status.AndesRadiobuttonStatus
import com.mercadolibre.android.andesui.color.AndesColor
import com.mercadolibre.android.andesui.color.toAndesColor

internal interface AndesRadiobuttonTypeInterface {
    fun borderColor(context: Context, status: AndesRadiobuttonStatus): AndesColor
    fun iconColor(context: Context, status: AndesRadiobuttonStatus): AndesColor
    fun backgroundColor(context: Context, status: AndesRadiobuttonStatus): AndesColor
    fun textColor(context: Context): AndesColor
}

internal object AndesRadiobuttonTypeIdle : AndesRadiobuttonTypeInterface {
    override fun borderColor(context: Context, status: AndesRadiobuttonStatus): AndesColor {
        return if (status == AndesRadiobuttonStatus.UNSELECTED) {
            R.color.andes_gray_250_solid.toAndesColor()
        } else {
            R.color.andes_blue_ml_500.toAndesColor()
        }
    }
    override fun iconColor(context: Context, status: AndesRadiobuttonStatus) = R.color.andes_white.toAndesColor()
    override fun backgroundColor(context: Context, status: AndesRadiobuttonStatus): AndesColor {
        return if (status == AndesRadiobuttonStatus.UNSELECTED) {
            R.color.andes_white.toAndesColor()
        } else {
            R.color.andes_blue_ml_500.toAndesColor()
        }
    }
    override fun textColor(context: Context) = R.color.andes_gray_800_solid.toAndesColor()
}

internal object AndesRadiobuttonTypeDisabled : AndesRadiobuttonTypeInterface {
    override fun borderColor(context: Context, status: AndesRadiobuttonStatus) = R.color.andes_gray_100_solid.toAndesColor()
    override fun iconColor(context: Context, status: AndesRadiobuttonStatus) = R.color.andes_gray_250_solid.toAndesColor()
    override fun backgroundColor(context: Context, status: AndesRadiobuttonStatus): AndesColor {
        return if (status == AndesRadiobuttonStatus.UNSELECTED) {
            R.color.andes_white.toAndesColor()
        } else {
            R.color.andes_gray_100_solid.toAndesColor()
        }
    }
    override fun textColor(context: Context) = R.color.andes_gray_250_solid.toAndesColor()
}

internal object AndesRadiobuttonTypeError : AndesRadiobuttonTypeInterface {
    override fun borderColor(context: Context, status: AndesRadiobuttonStatus) = R.color.andes_red_500.toAndesColor()
    override fun iconColor(context: Context, status: AndesRadiobuttonStatus) = R.color.andes_white.toAndesColor()
    override fun backgroundColor(context: Context, status: AndesRadiobuttonStatus) = R.color.andes_white.toAndesColor()
    override fun textColor(context: Context) = R.color.andes_gray_800_solid.toAndesColor()
}
