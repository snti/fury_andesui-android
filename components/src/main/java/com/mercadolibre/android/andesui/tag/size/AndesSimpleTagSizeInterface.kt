package com.mercadolibre.android.andesui.tag.size

import android.content.Context
import com.mercadolibre.android.andesui.R

/**
 * Defines all size related properties that an [AndesTag] needs to be drawn properly.
 * Those properties change depending on the size of the tag.
 */
internal interface AndesSimpleTagSizeInterface {
    /**
     * Returns a [Float] representing the text size to be used.
     *
     * @param context needed for accessing some resources.
     * @return a [Float] representing the text size to be used.
     */
    fun textSize(context: Context): Float

    /**
     * Returns a [Float] representing the height size to be used.
     *
     * @param context needed for accessing some resources.
     * @return a [Float] representing the height size to be used.
     */
    fun height(context: Context): Float

    /**
     * Returns an [Int] representing the margin to be used.
     *
     * @param context needed for accessing some resources.
     * @return an [Int] representing the margin to be used.
     */
    fun textLeftMargin(context: Context, hasLeftContent: Boolean): Int

    /**
     * Returns an [Int] representing the margin to be used.
     *
     * @param context needed for accessing some resources.
     * @return an [Int] representing the margin to be used.
     */
    fun textRightMargin(context: Context, hasRightContent: Boolean): Int

    /**
     * Returns a [Float] representing the corner radius to be used.
     *
     * @param context needed for accessing some resources.
     * @return a [Float] representing the corner radius to be used.
     */
    fun borderRadius(context: Context): Float
}

/**
 * Implementation of [AndesSimpleTagSizeInterface] that returns the required data but personalized for the Large Size,
 * according to Andes specifications.
 *
 */
internal class AndesLargeSimpleTagSize : AndesSimpleTagSizeInterface {
    override fun textSize(context: Context) = context.resources.getDimension(R.dimen.andes_tag_large_text_size)
    override fun height(context: Context) = context.resources.getDimension(R.dimen.andes_tag_large_height)

    override fun textLeftMargin(context: Context, hasLeftContent: Boolean): Int {
        return if (hasLeftContent) {
            context.resources.getDimension(R.dimen.andes_tag_small_text_margin).toInt()
        } else {
            context.resources.getDimension(R.dimen.andes_tag_large_text_margin).toInt()
        }
    }

    override fun textRightMargin(context: Context, hasRightContent: Boolean): Int {
        return if (hasRightContent) {
            context.resources.getDimension(R.dimen.andes_tag_small_text_margin).toInt()
        } else {
            context.resources.getDimension(R.dimen.andes_tag_large_text_margin).toInt()
        }
    }

    override fun borderRadius(context: Context) = context.resources.getDimension(R.dimen.andes_tag_large_corner_radius)
}

/**
 * Implementation of [AndesSimpleTagSizeInterface] that returns the required data but personalized for the Small Size,
 * according to Andes specifications.
 */
internal class AndesSmallSimpleTagSize : AndesSimpleTagSizeInterface {
    override fun textSize(context: Context) = context.resources.getDimension(R.dimen.andes_tag_small_text_size)
    override fun height(context: Context) = context.resources.getDimension(R.dimen.andes_tag_small_height)
    override fun textLeftMargin(context: Context, hasLeftContent: Boolean) = context.resources.getDimension(R.dimen.andes_tag_small_text_margin).toInt()
    override fun textRightMargin(context: Context, hasRightContent: Boolean) = context.resources.getDimension(R.dimen.andes_tag_small_text_margin).toInt()
    override fun borderRadius(context: Context) = context.resources.getDimension(R.dimen.andes_tag_small_corner_radius)
}
