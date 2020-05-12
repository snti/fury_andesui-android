package com.mercadolibre.android.andesui.tag.leftcontent

import android.content.Context
import android.view.View

open class LeftContent(
    var context: Context,
    var size: Int = 0,
    var border: Float = 0f
) {

     open fun getType(): AndesTagLeftContent {
          return AndesTagLeftContent.NONE
     }

     open fun getLeftMargin(): Int {
          return 0
     }

     open fun getRightMargin(): Int {
          return 0
     }

     open fun getView(context: Context): View {
          return View(context)
     }
}
