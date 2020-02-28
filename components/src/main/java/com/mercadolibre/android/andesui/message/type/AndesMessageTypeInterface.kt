package com.mercadolibre.android.andesui.message.type

import android.content.Context
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.support.v4.content.ContextCompat
import com.mercadolibre.android.andesui.R
import com.mercadolibre.android.andesui.button.hierarchy.BackgroundColorConfig
import com.mercadolibre.android.andesui.color.AndesColor
import com.mercadolibre.android.andesui.color.toAndesColor
import com.mercadolibre.android.andesui.message.hierarchy.AndesMessageHierarchyInterface
import com.mercadolibre.android.andesui.utils.buildColoredCircularShapeWithIconDrawable


internal sealed class AndesMessageTypeInterface {

    abstract fun primaryColor(): AndesColor

    abstract fun secondaryColor(): AndesColor

    fun pipeColor(): AndesColor = primaryColor()

    abstract fun icon(context: Context, hierarchy: AndesMessageHierarchyInterface): Drawable?

    abstract fun primaryActionColorConfig(): BackgroundColorConfig

    abstract fun secondaryActionColorConfig() : BackgroundColorConfig

}

internal object AndesNeutralMessageType : AndesMessageTypeInterface() {
    override fun primaryColor() = R.color.andesui_accent_color_500.toAndesColor()
    override fun secondaryColor() = R.color.andesui_accent_color_600.toAndesColor()
    override fun icon(context: Context, hierarchy: AndesMessageHierarchyInterface) = buildColoredCircularShapeWithIconDrawable(
            ContextCompat.getDrawable(context, R.drawable.andesui_ui_feedback_info_16) as BitmapDrawable,
            context,
            R.color.andesui_white.toAndesColor(),
            hierarchy.iconBackgroundColor(this)?.colorInt(context),
            context.resources.getDimension(R.dimen.andesui_message_icon_diameter).toInt()
    )

    override fun primaryActionColorConfig(): BackgroundColorConfig {
        return BackgroundColorConfig(
                enabledColor = R.color.andesui_accent_color_600.toAndesColor(),
                pressedColor = R.color.andesui_accent_color_800.toAndesColor(),
                focusedColor = R.color.andesui_accent_color_400.toAndesColor(),
                hoveredColor = R.color.andesui_accent_color_700.toAndesColor(),
                disabledColor = R.color.andesui_gray_100.toAndesColor(),
                otherColor = null)
    }

    override fun secondaryActionColorConfig(): BackgroundColorConfig {
        return BackgroundColorConfig(
                enabledColor = R.color.andesui_accent_color_500.toAndesColor(),
                pressedColor = R.color.andesui_accent_color_700.toAndesColor(),
                focusedColor = R.color.andesui_accent_color_300.toAndesColor(),
                hoveredColor = R.color.andesui_accent_color_600.toAndesColor(),
                disabledColor = R.color.andesui_gray_100.toAndesColor(),
                otherColor = null)
    }
}

internal object AndesSuccessMessageType : AndesMessageTypeInterface() {
    override fun primaryColor() = R.color.andesui_green_500.toAndesColor()
    override fun secondaryColor() = R.color.andesui_green_600.toAndesColor()
    override fun icon(context: Context, hierarchy: AndesMessageHierarchyInterface) = buildColoredCircularShapeWithIconDrawable(
            ContextCompat.getDrawable(context, R.drawable.andesui_ui_feedback_success_16) as BitmapDrawable,
            context,
            R.color.andesui_white.toAndesColor(),
            hierarchy.iconBackgroundColor(this)?.colorInt(context),
            context.resources.getDimension(R.dimen.andesui_message_icon_diameter).toInt()
    )

    override fun primaryActionColorConfig(): BackgroundColorConfig {
        return BackgroundColorConfig(
                enabledColor = R.color.andesui_green_600.toAndesColor(),
                pressedColor = R.color.andesui_green_800.toAndesColor(),
                focusedColor = R.color.andesui_green_400.toAndesColor(),
                hoveredColor = R.color.andesui_green_700.toAndesColor(),
                disabledColor = R.color.andesui_gray_100.toAndesColor(),
                otherColor = null)
    }

    override fun secondaryActionColorConfig(): BackgroundColorConfig {
        return BackgroundColorConfig(
                enabledColor = R.color.andesui_green_500.toAndesColor(),
                pressedColor = R.color.andesui_green_700.toAndesColor(),
                focusedColor = R.color.andesui_green_300.toAndesColor(),
                hoveredColor = R.color.andesui_green_600.toAndesColor(),
                disabledColor = R.color.andesui_gray_100.toAndesColor(),
                otherColor = null)
    }
}

internal object AndesWarningMessageType : AndesMessageTypeInterface() {
    override fun primaryColor() = R.color.andesui_orange_500.toAndesColor()
    override fun secondaryColor() = R.color.andesui_orange_600.toAndesColor()
    override fun icon(context: Context, hierarchy: AndesMessageHierarchyInterface) = buildColoredCircularShapeWithIconDrawable(
            ContextCompat.getDrawable(context, R.drawable.andesui_ui_feedback_warning_16) as BitmapDrawable,
            context,
            R.color.andesui_white.toAndesColor(),
            hierarchy.iconBackgroundColor(this)?.colorInt(context),
            context.resources.getDimension(R.dimen.andesui_message_icon_diameter).toInt()
    )

    override fun primaryActionColorConfig(): BackgroundColorConfig {
        return BackgroundColorConfig(
                enabledColor = R.color.andesui_orange_600.toAndesColor(),
                pressedColor = R.color.andesui_orange_800.toAndesColor(),
                focusedColor = R.color.andesui_orange_400.toAndesColor(),
                hoveredColor = R.color.andesui_orange_700.toAndesColor(),
                disabledColor = R.color.andesui_gray_100.toAndesColor(),
                otherColor = null)
    }

    override fun secondaryActionColorConfig(): BackgroundColorConfig {
        return BackgroundColorConfig(
                enabledColor = R.color.andesui_orange_500.toAndesColor(),
                pressedColor = R.color.andesui_orange_700.toAndesColor(),
                focusedColor = R.color.andesui_orange_300.toAndesColor(),
                hoveredColor = R.color.andesui_orange_600.toAndesColor(),
                disabledColor = R.color.andesui_gray_100.toAndesColor(),
                otherColor = null)
    }
}

internal object AndesErrorMessageType : AndesMessageTypeInterface() {
    override fun primaryColor() = R.color.andesui_red_500.toAndesColor()
    override fun secondaryColor() = R.color.andesui_red_600.toAndesColor()
    override fun icon(context: Context, hierarchy: AndesMessageHierarchyInterface) = buildColoredCircularShapeWithIconDrawable(
            ContextCompat.getDrawable(context, R.drawable.andesui_ui_feedback_warning_16) as BitmapDrawable,
            context,
            R.color.andesui_white.toAndesColor(),
            hierarchy.iconBackgroundColor(this)?.colorInt(context),
            context.resources.getDimension(R.dimen.andesui_message_icon_diameter).toInt()
    )

    override fun primaryActionColorConfig(): BackgroundColorConfig {
        return BackgroundColorConfig(
                enabledColor = R.color.andesui_red_600.toAndesColor(),
                pressedColor = R.color.andesui_red_800.toAndesColor(),
                focusedColor = R.color.andesui_red_400.toAndesColor(),
                hoveredColor = R.color.andesui_red_700.toAndesColor(),
                disabledColor = R.color.andesui_gray_100.toAndesColor(),
                otherColor = null)
    }

    override fun secondaryActionColorConfig(): BackgroundColorConfig {
        return BackgroundColorConfig(
                enabledColor = R.color.andesui_red_500.toAndesColor(),
                pressedColor = R.color.andesui_red_700.toAndesColor(),
                focusedColor = R.color.andesui_red_300.toAndesColor(),
                hoveredColor = R.color.andesui_red_600.toAndesColor(),
                disabledColor = R.color.andesui_gray_100.toAndesColor(),
                otherColor = null)
    }
}