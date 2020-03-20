package com.mercadolibre.android.andesui.textfield.content

import android.content.Context
import android.graphics.drawable.BitmapDrawable
import android.view.View
import android.widget.TextView
import com.facebook.drawee.view.SimpleDraweeView
import com.mercadolibre.android.andesui.R
import com.mercadolibre.android.andesui.button.AndesButton
import com.mercadolibre.android.andesui.button.hierarchy.AndesButtonHierarchy
import com.mercadolibre.android.andesui.button.size.AndesButtonSize
import com.mercadolibre.android.andesui.color.toAndesColor
import com.mercadolibre.android.andesui.color.toColor
import com.mercadolibre.android.andesui.icons.OfflineIconProvider
import com.mercadolibre.android.andesui.typeface.getFontOrDefault
import com.mercadolibre.android.andesui.utils.buildColoredBitmapDrawable

/**
 * Defines all style related properties that an [AndesTexfield] needs to be drawn properly.
 * Those properties change depending on the style of the message.
 *
 */
internal sealed class AndesTextfieldContentInterface {
    abstract fun component(context: Context): View
    abstract fun leftMargin(context: Context) : Int
    abstract fun rightMargin(context: Context) : Int
}

internal object AndesSuffixTextfieldContent : AndesTextfieldContentInterface() {
    override fun leftMargin(context: Context): Int = context.resources.getDimension(R.dimen.andes_textfield_suffix_left_margin).toInt()

    override fun rightMargin(context: Context): Int = context.resources.getDimension(R.dimen.andes_textfield_suffix_right_margin).toInt()

    override fun component(context: Context): TextView {
        val suffix  = TextView(context)
        suffix.setTextColor(R.color.andes_gray_450.toColor(context))
        suffix.text = context.getString(R.string.andes_suffix_hint)
        suffix.typeface = context.getFontOrDefault(R.font.andes_font_regular)
        return suffix
    }
}


internal object AndesPrefixTextfieldContent : AndesTextfieldContentInterface() {
    override fun leftMargin(context: Context): Int = context.resources.getDimension(R.dimen.andes_textfield_prefix_left_margin).toInt()

    override fun rightMargin(context: Context): Int = context.resources.getDimension(R.dimen.andes_textfield_prefix_right_margin).toInt()

    override fun component(context: Context): TextView {
        val prefix  = TextView(context)
        prefix.setTextColor(R.color.andes_gray_450.toColor(context))
        prefix.text = context.getString(R.string.andes_prefix_hint)
        prefix.typeface = context.getFontOrDefault(R.font.andes_font_regular)
        return prefix
    }
}

internal object AndesIconTextfieldContent : AndesTextfieldContentInterface() {
    override fun leftMargin(context: Context): Int = context.resources.getDimension(R.dimen.andes_textfield_icon_left_margin).toInt()

    override fun rightMargin(context: Context): Int = context.resources.getDimension(R.dimen.andes_textfield_icon_right_margin).toInt()

    override fun component(context: Context): SimpleDraweeView {
        val icon = SimpleDraweeView(context)
        icon.setImageDrawable(buildColoredBitmapDrawable(
                OfflineIconProvider(context).loadIcon("andes_ui_placeholder_imagen_24") as BitmapDrawable,
                context,
                R.color.andes_gray_800.toAndesColor()))
        return icon
    }
}

internal object AndesTooltipTextfieldContent : AndesTextfieldContentInterface() {
    override fun leftMargin(context: Context): Int = context.resources.getDimension(R.dimen.andes_textfield_tooltip_left_margin).toInt()

    override fun rightMargin(context: Context): Int = context.resources.getDimension(R.dimen.andes_textfield_tooltip_right_margin).toInt()

    override fun component(context: Context): View {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}

internal object AndesValidatedTextfieldContent : AndesTextfieldContentInterface() {
    override fun leftMargin(context: Context): Int = context.resources.getDimension(R.dimen.andes_textfield_validated_left_margin).toInt()

    override fun rightMargin(context: Context): Int = context.resources.getDimension(R.dimen.andes_textfield_validated_right_margin).toInt()

    override fun component(context: Context): SimpleDraweeView {
        val validated =  SimpleDraweeView(context)
        validated.setImageDrawable(buildColoredBitmapDrawable(
                OfflineIconProvider(context).loadIcon("andes_ui_feedback_success_24") as BitmapDrawable,
                context,
                R.color.andes_green_500.toAndesColor())
        )
        return validated
    }
}

internal object AndesClearTextfieldContent : AndesTextfieldContentInterface() {
    override fun leftMargin(context: Context): Int = context.resources.getDimension(R.dimen.andes_textfield_clear_left_margin).toInt()

    override fun rightMargin(context: Context): Int = context.resources.getDimension(R.dimen.andes_textfield_clear_right_margin).toInt()

    override fun component(context: Context): SimpleDraweeView {
        val validated =  SimpleDraweeView(context)
        validated.setImageDrawable(buildColoredBitmapDrawable(
                OfflineIconProvider(context).loadIcon("andes_ui_close_24") as BitmapDrawable,
                context,
                R.color.andes_gray_450.toAndesColor())
        )
        return validated    }
}

internal object AndesActionTextfieldContent : AndesTextfieldContentInterface() {
    override fun leftMargin(context: Context): Int = context.resources.getDimension(R.dimen.andes_textfield_action_left_margin).toInt()

    override fun rightMargin(context: Context): Int = context.resources.getDimension(R.dimen.andes_textfield_action_right_margin).toInt()

    override fun component(context: Context): AndesButton {
        return AndesButton(context, AndesButtonSize.MEDIUM, AndesButtonHierarchy.TRANSPARENT)
    }
}

internal object AndesIndeterminateTextfieldContent : AndesTextfieldContentInterface() {
    override fun leftMargin(context: Context): Int = context.resources.getDimension(R.dimen.andes_textfield_indeterminate_left_margin).toInt()

    override fun rightMargin(context: Context): Int = context.resources.getDimension(R.dimen.andes_textfield_indeterminate_right_margin).toInt()

    override fun component(context: Context): View {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}

internal object AndesCheckboxTextfieldContent : AndesTextfieldContentInterface() {
    override fun leftMargin(context: Context): Int = 0

    override fun rightMargin(context: Context): Int = 0

    override fun component(context: Context): View {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}


