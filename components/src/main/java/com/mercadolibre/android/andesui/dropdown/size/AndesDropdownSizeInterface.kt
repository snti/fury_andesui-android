package com.mercadolibre.android.andesui.dropdown.size

import android.content.Context
import com.mercadolibre.android.andesui.R

/**
 * Defines all size related properties that an [AndesDropdown] needs to be drawn properly.
 * Those properties change depending on the size of the dropdown.
 */
internal interface AndesDropdownSizeInterface {
    fun titleFontSize(context: Context): Float
    fun chevronSize(context: Context): Int
}

internal class AndesDropdownSmallSize : AndesDropdownSizeInterface {
    override fun titleFontSize(context: Context): Float = context.resources.getDimension(R.dimen.andes_dropdown_title_font_size_small)
    override fun chevronSize(context: Context): Int {
        TODO("Not yet implemented")
    }

}

internal class AndesDropdownMediumSize : AndesDropdownSizeInterface {
    override fun titleFontSize(context: Context): Float = context.resources.getDimension(R.dimen.andes_dropdown_title_font_size_medium)

    override fun chevronSize(context: Context): Int {
        TODO("Not yet implemented")
    }

}

internal class AndesDropdownLargeSize : AndesDropdownSizeInterface {
    override fun titleFontSize(context: Context): Float = context.resources.getDimension(R.dimen.andes_dropdown_title_font_size_large)


    override fun chevronSize(context: Context): Int {
        TODO("Not yet implemented")
    }

}