package com.mercadolibre.android.andesui.checkbox.status

import android.content.Context
import android.graphics.drawable.Drawable
import com.mercadolibre.android.andesui.icons.IconProvider

internal interface AndesCheckboxStatusInterface {
    /**
     * Returns a [Drawable] that contains the checkbox icon.
     *
     * @return a [Drawable] that contains the checkbox icon.
     */
    fun configIcon(context: Context): Drawable?
}

internal object AndesCheckboxStatusChecked : AndesCheckboxStatusInterface {
    override fun configIcon(context: Context): Drawable? {
        return IconProvider(context).loadIcon("andes_ui_feedback_success_16")
    }
}

internal object AndesCheckboxStatusUnchecked : AndesCheckboxStatusInterface {
    override fun configIcon(context: Context): Drawable? {
        return null
    }
}

internal object AndesCheckboxStatusUnknown : AndesCheckboxStatusInterface {
    override fun configIcon(context: Context): Drawable? {
        return IconProvider(context).loadIcon("andes_ui_restar_16")
    }
}