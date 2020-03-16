package com.mercadolibre.android.andesui.textfield.factory

import android.content.Context
import android.graphics.Typeface
import android.graphics.drawable.Drawable
import com.mercadolibre.android.andesui.R
import com.mercadolibre.android.andesui.color.AndesColor
import com.mercadolibre.android.andesui.textfield.state.AndesTextfieldStateInterface
import com.mercadolibre.android.andesui.typeface.getFontOrDefault

internal data class AndesTextfieldConfiguration(
        val background: Drawable?,
        val helperTextColor : AndesColor,
        val helperText: String?,
        //val labelTextColor : ColorStateList,
        //val counterTextColor : ColorStateList,
        val typeface: Typeface,
        val icon: Drawable?

        )

internal object AndesTextfieldConfigurationFactory {

    fun create(context: Context, andesTextfieldAttrs: AndesTextfieldAttrs): AndesTextfieldConfiguration {
        val state = andesTextfieldAttrs.state.state

        return with(andesTextfieldAttrs) {

            AndesTextfieldConfiguration(
                    background = resolveBackground(context, state),
                    helperTextColor = resolveHelperTextColor(context),
                    //labelTextColor = resolveLabelTextColor(context),
                    //counterTextColor = resolveCounterTextColor(context),
                    typeface = resolveTypeface(context),
                    icon = resolveIcon(context, state)

            )
        }
    }

    private fun resolveBackground(context: Context, state: AndesTextfieldStateInterface) : Drawable? = state.backgroundColor(context)
    private fun resolveHelperTextColor(context: Context) = context.getColor()
    //private fun resolveLabelTextColor(context: Context) : ColorStateList
    //private fun resolveCounterTextColor(context: Context) : ColorStateList
    private fun resolveTypeface(context: Context) =  context.getFontOrDefault(R.font.andes_font_regular)
    private fun resolveIcon(context: Context, state: AndesTextfieldStateInterface) : Drawable? = state.icon(context)


}

