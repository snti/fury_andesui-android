package com.mercadolibre.android.andesui.tag.leftcontent

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.util.TypedValue
import android.view.Gravity
import android.view.View
import android.widget.TextView
import com.mercadolibre.android.andesui.R
import com.mercadolibre.android.andesui.utils.validateColor

class LeftContentDot(
    context: Context,
    val backgroundColor: String,
    var text: String? = null,
    val textColor: String? = null
) : LeftContent(context) {

     init {
          // Valid colors
          var isValid = validateColor(backgroundColor)
          if (isValid && !textColor.isNullOrEmpty()) {
               isValid = isValid && validateColor(textColor)
          }
          if (!isValid) {
               throw IllegalStateException("Invalid color, it is necessary to pass it in hexadecimal.")
          }
          // Valid text
          if (!text.isNullOrEmpty() && text!!.length > 2) {
               text = text!!.substring(0, 2)
          }
          // Define size
          size = context.resources.getDimension(R.dimen.andes_tag_icon_size).toInt()
          border = context.resources.getDimension(R.dimen.andes_tag_icon_radius)
     }

     override fun getType(): AndesTagLeftContent {
          return AndesTagLeftContent.DOT
     }

     override fun getLeftMargin(): Int {
          return context.resources.getDimension(R.dimen.andes_tag_small_margin).toInt()
     }

     override fun getRightMargin(): Int {
          return context.resources.getDimension(R.dimen.andes_tag_medium_margin).toInt()
     }

     override fun getView(context: Context): View {
          if (text.isNullOrEmpty()) {
               val view = View(context)
               val shape = GradientDrawable()
               shape.cornerRadius = border
               shape.setColor(Color.parseColor(backgroundColor))
               view.background = shape
               return view
          } else {
               val textView = TextView(context)
               textView.text = text
               textView.gravity = Gravity.CENTER
               val shape = GradientDrawable()
               shape.cornerRadius = border
               textView.setTextSize(TypedValue.COMPLEX_UNIT_PX, context.resources.getDimension(R.dimen.andes_tag_small_text_size))
               if (!textColor.isNullOrEmpty()) {
                    textView.setTextColor(Color.parseColor(textColor))
               }
               shape.setColor(Color.parseColor(backgroundColor))
               textView.background = shape
               return textView
          }
     }
}
