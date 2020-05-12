package com.mercadolibre.android.andesui.tag.rightcontent

import android.content.Context
import android.graphics.drawable.BitmapDrawable
import android.view.View
import android.widget.ImageView
import com.mercadolibre.android.andesui.R
import com.mercadolibre.android.andesui.color.AndesColor
import com.mercadolibre.android.andesui.icons.IconProvider
import com.mercadolibre.android.andesui.utils.buildColoredAndesBitmapDrawable

class RightContentDismiss(context: Context, private val onClickListener: View.OnClickListener? = null) : RightContent(context) {

    override fun getType(): AndesTagRightContent {
        return AndesTagRightContent.DISMISS
    }

    override fun getLeftMargin(): Int {
        return context.resources.getDimension(R.dimen.andes_tag_medium_margin).toInt()
    }

    override fun getRightMargin(): Int {
        return context.resources.getDimension(R.dimen.andes_tag_medium_margin).toInt()
    }

    override fun getView(context: Context, color: AndesColor, callback: View.OnClickListener): View {
        val bitmapDrawable = buildColoredAndesBitmapDrawable(
                image = IconProvider(context).loadIcon("andes_ui_close_16") as BitmapDrawable,
                context = context,
                color = color
        )
        val imageView = ImageView(context)
        imageView.setImageDrawable(bitmapDrawable)
        imageView.setOnClickListener {
            callback.onClick(it)
            onClickListener?.onClick(it)
        }
        return imageView
    }
}
