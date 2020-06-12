package com.mercadolibre.android.andesui.checkbox.type

import android.content.Context
import com.mercadolibre.android.andesui.color.AndesColor

internal interface AndesCheckboxTypeInterface {
    fun borderColor(context: Context): AndesColor
    fun backgroundColor(context: Context): AndesColor
    fun textColor(context: Context): AndesColor

}

internal object AndesCheckboxTypeIdle : AndesCheckboxTypeInterface {
    override fun borderColor(context: Context): AndesColor {
        TODO("Not yet implemented")
    }

    override fun backgroundColor(context: Context): AndesColor {
        TODO("Not yet implemented")
    }

    override fun textColor(context: Context): AndesColor {
        TODO("Not yet implemented")
    }
// background color ?, border color, text color
}

internal object AndesCheckboxTypeDisabled : AndesCheckboxTypeInterface {
    override fun borderColor(context: Context): AndesColor {
        TODO("Not yet implemented")
    }

    override fun backgroundColor(context: Context): AndesColor {
        TODO("Not yet implemented")
    }

    override fun textColor(context: Context): AndesColor {
        TODO("Not yet implemented")
    }
// background color ?, border color, text color
}

internal object AndesCheckboxTypeError : AndesCheckboxTypeInterface {
    override fun borderColor(context: Context): AndesColor {
        TODO("Not yet implemented")
    }

    override fun backgroundColor(context: Context): AndesColor {
        TODO("Not yet implemented")
    }

    override fun textColor(context: Context): AndesColor {
        TODO("Not yet implemented")
    }
// background color ?, border color, text color
}