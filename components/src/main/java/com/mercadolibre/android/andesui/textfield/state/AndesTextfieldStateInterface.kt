package com.mercadolibre.android.andesui.textfield.state

import android.content.Context
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.graphics.drawable.GradientDrawable
import android.graphics.drawable.StateListDrawable
import android.support.v4.content.ContextCompat
import com.mercadolibre.android.andesui.R
import com.mercadolibre.android.andesui.color.AndesColor
import com.mercadolibre.android.andesui.color.toAndesColor
import com.mercadolibre.android.andesui.icons.OfflineIconProvider
import com.mercadolibre.android.andesui.utils.buildColoredCircularShapeWithIconDrawable

/**
 * Defines all style related properties that an [AndesTexfield] needs to be drawn properly.
 * Those properties change depending on the style of the message.
 *
 */
internal sealed class AndesTextfieldStateInterface {
    abstract fun primaryColor() : AndesColor
    abstract fun backgroundColor(context: Context): Drawable
    abstract fun icon(context: Context): Drawable?
    abstract fun hintColor() : Int
    fun helperColor() : AndesColor = primaryColor()
    fun labelColor() : AndesColor = primaryColor()
    fun counterColor() : AndesColor = primaryColor()
}

internal object AndesEnabledTexfieldState : AndesTextfieldStateInterface() {
    override fun primaryColor(): AndesColor = R.color.andes_gray_450.toAndesColor()
    override fun hintColor(): Int = R.color.andes_gray_450

    override fun backgroundColor(context: Context): Drawable {
        return StateListDrawable().apply {
            addState(intArrayOf(android.R.attr.state_focused), createGradientDrawable(context, context.resources.getDimension(R.dimen.andes_textfield_focused_stroke).toInt(), ContextCompat.getColor(context, R.color.andes_accent_color_500), null))
            addState(intArrayOf(android.R.attr.state_enabled), createGradientDrawable(context, context.resources.getDimension(R.dimen.andes_textfield_simple_stroke).toInt(), ContextCompat.getColor(context, R.color.andes_gray_200), null))
            addState(intArrayOf(-android.R.attr.state_enabled), createGradientDrawableWithDash(context, context.resources.getDimension(R.dimen.andes_textfield_simple_stroke).toInt(), ContextCompat.getColor(context, R.color.andes_gray_200), context.resources.getDimension(R.dimen.andes_textfield_dash)))
        }
    }

    override fun icon(context: Context): Drawable? = null
}


internal object AndesErrorTexfieldState : AndesTextfieldStateInterface() {
    override fun primaryColor(): AndesColor = R.color.andes_red_500.toAndesColor()
    override fun hintColor(): Int = R.color.andes_gray_450

    override fun backgroundColor(context: Context): Drawable {
        return StateListDrawable().apply {
            addState(intArrayOf(android.R.attr.state_focused), createGradientDrawable(context, context.resources.getDimension(R.dimen.andes_textfield_focused_stroke).toInt(), ContextCompat.getColor(context, R.color.andes_red_500), ContextCompat.getColor(context, R.color.andes_red_50)))
            addState(intArrayOf(android.R.attr.state_enabled), createGradientDrawable(context, context.resources.getDimension(R.dimen.andes_textfield_simple_stroke).toInt(), ContextCompat.getColor(context, R.color.andes_red_500), ContextCompat.getColor(context, R.color.andes_red_50)))
        }
    }

    override fun icon(context: Context): Drawable? {
        return buildColoredCircularShapeWithIconDrawable(
                OfflineIconProvider(context).loadIcon("andes_ui_feedback_warning_16") as BitmapDrawable,
                context,
                R.color.andes_white.toAndesColor(),
                ContextCompat.getColor(context, R.color.andes_red_500),
                context.resources.getDimension(R.dimen.andes_textfield_icon_diameter).toInt())
    }
}


internal object AndesDisabledTexfieldState : AndesTextfieldStateInterface() {
    override fun primaryColor(): AndesColor = R.color.andes_gray_200.toAndesColor()
    override fun hintColor(): Int = R.color.andes_gray_200

    override fun backgroundColor(context: Context): Drawable {
        return createGradientDrawableWithDash(context, context.resources.getDimension(R.dimen.andes_textfield_simple_stroke).toInt(), ContextCompat.getColor(context, R.color.andes_gray_200), context.resources.getDimension(R.dimen.andes_textfield_dash))
    }

    override fun icon(context: Context): Drawable? = null
}


private fun createGradientDrawable(context: Context, stroke: Int, strokeColor: Int, backgrondColor : Int?): Drawable {
    val drawable = GradientDrawable()
    drawable.cornerRadius = context.resources.getDimension(R.dimen.andes_button_border_radius_large)
    drawable.setStroke(stroke, strokeColor)
    if (backgrondColor != null){
        drawable.setColor(backgrondColor)
    }
    return drawable
}

private fun createGradientDrawableWithDash(context: Context, stroke: Int, color: Int, dash: Float): Drawable {
    val drawable = GradientDrawable()
    drawable.cornerRadius = context.resources.getDimension(R.dimen.andes_button_border_radius_large)
    drawable.setStroke(stroke, ContextCompat.getColor(context, R.color.andes_gray_250), dash, dash)
    drawable.setColor(ContextCompat.getColor(context, R.color.andes_gray_040))
    return drawable
}
