package com.mercadolibre.android.andesui.tag.type

import com.mercadolibre.android.andesui.R
import com.mercadolibre.android.andesui.color.AndesColor
import com.mercadolibre.android.andesui.color.toAndesColor

/**
 * Defines all types related properties that an [AndesTag] needs to be drawn properly.
 * Those properties change depending on the style of the tag.
 */
internal interface AndesSimpleTagTypeInterface {

    /**
     * Returns a [AndesColor] that contains the primary color data for the tag.
     *
     * @return a [AndesColor] that contains the primary color data for the tag.
     */
    fun primaryColor(): AndesColor

    /**
     * Returns a [AndesColor] that contains the secondary color data for the tag.
     *
     * @return a [AndesColor] that contains the secondary color data for the tag.
     */
    fun secondaryColor(): AndesColor
}

internal class AndesNeutralTagType : AndesSimpleTagTypeInterface {
    override fun primaryColor() = R.color.andes_gray_450_solid.toAndesColor()
    override fun secondaryColor() = R.color.andes_gray_070_solid.toAndesColor()
}

internal class AndesHighlightTagType : AndesSimpleTagTypeInterface {
    override fun primaryColor() = R.color.andes_accent_color_500.toAndesColor()
    override fun secondaryColor() = R.color.andes_accent_color_100.toAndesColor()
}

internal class AndesSuccessTagType : AndesSimpleTagTypeInterface {
    override fun primaryColor() = R.color.andes_green_500.toAndesColor()
    override fun secondaryColor() = R.color.andes_green_100.toAndesColor()
}

internal class AndesWarningTagType : AndesSimpleTagTypeInterface {
    override fun primaryColor() = R.color.andes_orange_500.toAndesColor()
    override fun secondaryColor() = R.color.andes_orange_100.toAndesColor()
}

internal class AndesErrorTagType : AndesSimpleTagTypeInterface {
    override fun primaryColor() = R.color.andes_red_500.toAndesColor()
    override fun secondaryColor() = R.color.andes_red_100.toAndesColor()
}

internal class AndesDefaultTagType : AndesSimpleTagTypeInterface {
    override fun primaryColor() = R.color.andes_gray_450_solid.toAndesColor()
    override fun secondaryColor() = R.color.andes_transparent.toAndesColor()
}
