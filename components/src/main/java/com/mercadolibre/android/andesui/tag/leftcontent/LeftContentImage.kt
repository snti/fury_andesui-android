package com.mercadolibre.android.andesui.tag.leftcontent

import android.content.Context
import android.graphics.Bitmap
import android.view.View
import android.widget.ImageView
import com.mercadolibre.android.andesui.R
import com.mercadolibre.android.andesui.utils.buildCircleBitmap

class LeftContentImage(
        context: Context,
        var avatar: Bitmap
): LeftContent(context) {

     init {
          // Define size
          size = context.resources.getDimension(R.dimen.andes_tag_icon_size).toInt()
          border = context.resources.getDimension(R.dimen.andes_tag_icon_radius)
          // Create avatar
          val icon = buildCircleBitmap(avatar, size, size)
          if (icon != null) {
               avatar = icon
          }
     }

     override fun getType(): AndesTagLeftContent  {
          return AndesTagLeftContent.IMAGE
     }

     override fun getLeftMargin(): Int {
          return context.resources.getDimension(R.dimen.andes_tag_small_margin).toInt()
     }

     override fun getRightMargin(): Int {
          return context.resources.getDimension(R.dimen.andes_tag_medium_margin).toInt()
     }

     override fun getView(context: Context): View {
          val imageView = ImageView(context)
          imageView.setImageBitmap(avatar)
          return imageView
     }

}