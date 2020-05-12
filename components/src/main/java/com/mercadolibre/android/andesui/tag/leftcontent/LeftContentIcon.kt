package com.mercadolibre.android.andesui.tag.leftcontent

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.graphics.drawable.GradientDrawable
import android.view.View
import android.widget.ImageView
import com.mercadolibre.android.andesui.R
import com.mercadolibre.android.andesui.icons.IconProvider
import com.mercadolibre.android.andesui.utils.buildColoredBitmapDrawable
import com.mercadolibre.android.andesui.utils.validateColor

class LeftContentIcon(
    context: Context,
    val backgroundColor: String,
    val path: String? = null,
    val icon: Drawable? = null,
    var iconColor: String? = null
) : LeftContent(context) {

     init {
          // Valid color
          if (!iconColor.isNullOrEmpty() && !validateColor(iconColor!!)) {
               throw IllegalStateException("Invalid color, it is necessary to pass it in hexadecimal.")
          }
          // Valid if i have path or drawable
          if (path.isNullOrEmpty() && icon == null) {
               throw IllegalStateException("A drawable or IconProvider path is required.")
          }
          // Define size
          size = context.resources.getDimension(R.dimen.andes_tag_icon_size).toInt()
          border = context.resources.getDimension(R.dimen.andes_tag_icon_radius)
     }

     override fun getType(): AndesTagLeftContent {
          return AndesTagLeftContent.ICON
     }

     override fun getLeftMargin(): Int {
          return context.resources.getDimension(R.dimen.andes_tag_small_margin).toInt()
     }

     override fun getRightMargin(): Int {
          return context.resources.getDimension(R.dimen.andes_tag_medium_margin).toInt()
     }

     override fun getView(context: Context): View {
          val imageView = ImageView(context)
          val shape = GradientDrawable()
          shape.cornerRadius = border
          shape.setColor(Color.parseColor(backgroundColor))
          imageView.background = shape

          val bitmapDrawable = if (!path.isNullOrEmpty()) {
               IconProvider(context).loadIcon(path) as BitmapDrawable
          } else {
               val padding = context.resources.getDimension(R.dimen.andes_tag_small_margin).toInt()
               imageView.setPadding(padding, padding, padding, padding)
               icon as BitmapDrawable
          }

          if (iconColor.isNullOrEmpty()) {
               iconColor = "#000000"
          }
          val icon = buildColoredBitmapDrawable(
               bitmapDrawable,
               context,
               dstWidth = context.resources.getDimension(R.dimen.andes_tag_icon_size).toInt(),
               dstHeight = context.resources.getDimension(R.dimen.andes_tag_icon_size).toInt(),
               color = Color.parseColor(iconColor)
          )

          imageView.setImageDrawable(icon)
          return imageView
     }
}
