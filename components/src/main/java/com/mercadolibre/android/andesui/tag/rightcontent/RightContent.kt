package com.mercadolibre.android.andesui.tag.rightcontent

import android.content.Context
import android.view.View
import com.mercadolibre.android.andesui.R
import com.mercadolibre.android.andesui.color.AndesColor

open class RightContent(var context: Context) {

    open fun getType(): AndesTagRightContent {
        return AndesTagRightContent.NONE
    }

    open fun getLeftMargin(): Int {
        return context.resources.getDimension(R.dimen.andes_tag_medium_margin).toInt()
    }

    open fun getRightMargin(): Int {
        return context.resources.getDimension(R.dimen.andes_tag_medium_margin).toInt()
    }

    open fun getView(context: Context, color: AndesColor, callback: View.OnClickListener): View {
        return View(context)
    }
}
