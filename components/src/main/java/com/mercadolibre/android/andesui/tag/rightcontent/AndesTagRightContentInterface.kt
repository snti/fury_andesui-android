package com.mercadolibre.android.andesui.tag.rightcontent

import android.content.Context
import android.graphics.drawable.BitmapDrawable
import android.view.View
import android.widget.ImageView
import com.mercadolibre.android.andesui.R
import com.mercadolibre.android.andesui.color.AndesColor
import com.mercadolibre.android.andesui.icons.IconProvider
import com.mercadolibre.android.andesui.tag.size.AndesTagSize
import com.mercadolibre.android.andesui.utils.buildColoredAndesBitmapDrawable

internal sealed class AndesTagRightContentInterface {
    abstract fun leftMargin(context: Context, size: AndesTagSize): Int
    abstract fun rightMargin(context: Context, size: AndesTagSize): Int
    abstract fun size(context: Context): Int
    abstract fun border(context: Context): Float
    abstract fun view(context: Context, color: AndesColor, rightContent: RightContent, callback: View.OnClickListener): View
}

internal object AndesTagRightContentNone : AndesTagRightContentInterface() {
    override fun leftMargin(context: Context, size: AndesTagSize): Int = 0
    override fun rightMargin(context: Context, size: AndesTagSize): Int = 0
    override fun size(context: Context): Int = 0
    override fun border(context: Context): Float = 0f
    override fun view(context: Context, color: AndesColor, rightContent: RightContent, callback: View.OnClickListener): View = View(context)
}

internal object AndesTagRightContentDismiss : AndesTagRightContentInterface() {
    override fun leftMargin(context: Context, size: AndesTagSize): Int {
        return if (size == AndesTagSize.SMALL) {
            context.resources.getDimension(R.dimen.andes_tag_small_margin).toInt()
        } else {
            context.resources.getDimension(R.dimen.andes_tag_medium_margin).toInt()
        }
    }
    override fun rightMargin(context: Context, size: AndesTagSize): Int {
        return if (size == AndesTagSize.SMALL) {
            context.resources.getDimension(R.dimen.andes_tag_small_margin).toInt()
        } else {
            context.resources.getDimension(R.dimen.andes_tag_medium_margin).toInt()
        }
    }
    override fun size(context: Context): Int = context.resources.getDimension(R.dimen.andes_tag_icon_size).toInt()
    override fun border(context: Context): Float = context.resources.getDimension(R.dimen.andes_tag_icon_radius)
    override fun view(context: Context, color: AndesColor, rightContent: RightContent, callback: View.OnClickListener): View {
        val bitmapDrawable = buildColoredAndesBitmapDrawable(
                image = IconProvider(context).loadIcon("andes_ui_close_16") as BitmapDrawable,
                context = context,
                color = color
        )
        val imageView = ImageView(context)
        imageView.setImageDrawable(bitmapDrawable)
        imageView.setOnClickListener {
            callback.onClick(it)
        }
        return imageView
    }
}
