package com.mercadolibre.android.andesui.list.factory

import android.content.Context
import android.graphics.Typeface
import android.support.v4.content.ContextCompat
import com.mercadolibre.android.andesui.R
import com.mercadolibre.android.andesui.list.size.AndesListViewItemSize
import com.mercadolibre.android.andesui.list.size.AndesListViewItemSizeInterface
import com.mercadolibre.android.andesui.typeface.getFontOrDefault

internal data class AndesListViewItemConfiguration(
        val paddingLeft: Int,
        val paddingRight: Int,
        val paddingTop: Int,
        val paddingBottom: Int,
        val titleFontSize: Float,
        val subTitleFontSize: Float,
        val titleTypeface: Typeface,
        val subTitleTypeface: Typeface,
        val titleColor: Int,
        val subTitleColor: Int,
        val height: Float,
        val titleMaxLines: Int,
        val spaceTitleSubtitle: Int
)

internal object AndesListViewItemConfigurationFactory {

    fun create(context: Context, andesListViewItemSize: AndesListViewItemSize, hasSubTitle: Boolean): AndesListViewItemConfiguration {
        val size = andesListViewItemSize.size

        return AndesListViewItemConfiguration(
                paddingLeft = resolvePaddingLeft(context, size),
                paddingRight = resolvePaddingRight(context, size),
                paddingTop = resolvePaddingTop(context, size),
                paddingBottom = resolvePaddingBottom(context, size),
                subTitleFontSize = resolveSubTitleFontSize(context, size),
                titleFontSize = resolveTitleFontSize(context, size),
                height = resolveHeight(context, size),
                titleTypeface = resolveTitleTypeFace(context),
                titleColor = resolveTitleColor(context),
                subTitleColor = resolveSubTitleColor(context),
                subTitleTypeface = resolveSubTitleTypeFace(context),
                titleMaxLines = resolveTitleMaxLines(context, size),
                spaceTitleSubtitle = resolveTitleSubtitleSpace(context, size)
        )
    }

    private fun resolveTitleColor(context: Context) = ContextCompat.getColor(context, R.color.andes_gray_800)

    private fun resolveSubTitleColor(context: Context) = ContextCompat.getColor(context, R.color.andes_gray_450)

    private fun resolvePaddingLeft(context: Context, size: AndesListViewItemSizeInterface) = size.paddingLeft(context)

    private fun resolvePaddingRight(context: Context, size: AndesListViewItemSizeInterface) = size.paddingRight(context)

    private fun resolvePaddingTop(context: Context, size: AndesListViewItemSizeInterface) = size.paddingTop(context)

    private fun resolvePaddingBottom(context: Context, size: AndesListViewItemSizeInterface) = size.paddingBottom(context)

    private fun resolveSubTitleFontSize(context: Context, size: AndesListViewItemSizeInterface) = size.subTitleFontSize(context)

    private fun resolveTitleFontSize(context: Context, size: AndesListViewItemSizeInterface) = size.titleFontSize(context)

    /**
     *
     */
    private fun resolveHeight(context: Context, size: AndesListViewItemSizeInterface) = size.height(context)

    private fun resolveTitleMaxLines(context: Context, size: AndesListViewItemSizeInterface) = size.titleMaxLines(context)

    private fun resolveTitleTypeFace(context: Context) = context.getFontOrDefault(R.font.andes_font_semibold)

    private fun resolveSubTitleTypeFace(context: Context) = context.getFontOrDefault(R.font.andes_font_semibold)

    private fun resolveTitleSubtitleSpace(context: Context, size: AndesListViewItemSizeInterface) = size.spaceBetweenTitleAndSubtitle(context)
}