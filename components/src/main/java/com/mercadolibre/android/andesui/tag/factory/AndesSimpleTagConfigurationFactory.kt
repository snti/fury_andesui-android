package com.mercadolibre.android.andesui.tag.factory

import android.content.Context
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import com.mercadolibre.android.andesui.color.AndesColor
import com.mercadolibre.android.andesui.icons.IconProvider
import com.mercadolibre.android.andesui.tag.size.AndesSimpleTagSize
import com.mercadolibre.android.andesui.tag.size.AndesSimpleTagSizeInterface
import com.mercadolibre.android.andesui.tag.type.AndesSimpleTagTypeInterface
import com.mercadolibre.android.andesui.utils.buildColoredBitmapDrawable

internal data class AndesSimpleTagConfiguration(
    val text: String? = null,
    val textSize: Float,
    val textLeftMargin: Int,
    val textRightMargin: Int,
    val height: Float,
    val radius: Float,
    val isDismissable: Boolean,
    val dismissableIcon: Drawable?,
    val backgroundColor: AndesColor? = null,
    val borderColor: AndesColor,
    val textColor: AndesColor
)

internal object AndesSimpleTagConfigurationFactory {

    fun create(context: Context, andesSimpleTagAttrs: AndesSimpleTagAttrs): AndesSimpleTagConfiguration {
        return with(andesSimpleTagAttrs) {
            AndesSimpleTagConfiguration(
                    text =  andesSimpleTagText,
                    textSize = resolveTextSize(andesSimpleTagSize.size, context),
                    textLeftMargin = resolveLeftTextMargin(andesSimpleTagSize.size, false, context),
                    textRightMargin = resolveRightTextMargin(andesSimpleTagSize.size, andesSimpleTagSize == AndesSimpleTagSize.LARGE && isDismissable, context),
                    height = resolveHeight(andesSimpleTagSize.size, context),
                    radius = resolveRadius(andesSimpleTagSize.size, context),
                    isDismissable = andesSimpleTagSize == AndesSimpleTagSize.LARGE && isDismissable,
                    dismissableIcon = resolveDismissableIcon(andesSimpleTagType.type, context),
                    backgroundColor = resolveBackgroundColor(andesSimpleTagType.type),
                    borderColor = resolveBorderColor(andesSimpleTagType.type),
                    textColor = resolveTextColor(andesSimpleTagType.type)
            )
        }
    }

    private fun resolveTextSize(size: AndesSimpleTagSizeInterface, context: Context) = size.textSize(context)
    private fun resolveLeftTextMargin(size: AndesSimpleTagSizeInterface, hasLeftContent: Boolean, context: Context) = size.textLeftMargin(context, hasLeftContent)
    private fun resolveRightTextMargin(size: AndesSimpleTagSizeInterface, hasRightContent: Boolean, context: Context) = size.textRightMargin(context, hasRightContent)
    private fun resolveHeight(size: AndesSimpleTagSizeInterface, context: Context) = size.height(context)
    private fun resolveRadius(size: AndesSimpleTagSizeInterface, context: Context) = size.borderRadius(context)
    private fun resolveBackgroundColor(type: AndesSimpleTagTypeInterface) =  type.secondaryColor()
    private fun resolveBorderColor(type: AndesSimpleTagTypeInterface) = type.primaryColor()
    private fun resolveTextColor(type: AndesSimpleTagTypeInterface) = type.primaryColor()
    private fun resolveDismissableIcon(type: AndesSimpleTagTypeInterface, context: Context): BitmapDrawable {
        return buildColoredBitmapDrawable(
                IconProvider(context).loadIcon("andes_ui_close_20") as BitmapDrawable,
                context,
                color = type.primaryColor()
        )
    }

}