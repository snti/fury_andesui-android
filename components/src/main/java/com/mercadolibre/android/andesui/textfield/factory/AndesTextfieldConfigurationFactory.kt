package com.mercadolibre.android.andesui.textfield.factory

import android.content.Context
import android.graphics.Typeface
import android.graphics.drawable.Drawable
import android.view.View
import com.mercadolibre.android.andesui.R
import com.mercadolibre.android.andesui.color.AndesColor
import com.mercadolibre.android.andesui.textfield.content.AndesTextfieldContentInterface
import com.mercadolibre.android.andesui.textfield.state.AndesTextfieldStateInterface
import com.mercadolibre.android.andesui.typeface.getFontOrDefault

internal data class AndesTextfieldConfiguration(
        val background: Drawable?,
        val helperColor : AndesColor,
        val helperText: String? = null,
        val helperSize: Float,
        val helperTypeface: Typeface,
        val labelColor : AndesColor,
        val labelText: String? = null,
        val labelSize: Float,
        val counterColor : AndesColor,
        val counterSize: Float,
        val counterMinLength: Int?,
        val counterMaxLength: Int?,
        val placeHolderColor : AndesColor,
        val placeHolderText : String? = null,
        val placeHolderSize : Float,
        val typeface: Typeface,
        val icon: Drawable?,
        val leftComponent : View?,
        val rightComponent : View?,
        val leftComponentLeftMargin: Int?,
        val leftComponentRightMargin: Int?,
        val rightComponentLeftMargin : Int?,
        val rightComponentRightMargin: Int?
        )

internal object AndesTextfieldConfigurationFactory {

    fun create(context: Context, andesTextfieldAttrs: AndesTextfieldAttrs): AndesTextfieldConfiguration {
        return with(andesTextfieldAttrs) {

            AndesTextfieldConfiguration(
                    labelColor = resolveLabelTextColor(state.state),
                    labelSize = resolveLabelSize(context),
                    labelText = label,
                    background = resolveBackground(context, state.state),
                    helperColor = resolveHelperTextColor(state.state),
                    helperSize = resolveHelperSize(context),
                    helperText = helper,
                    helperTypeface = resolveHelperTypeface(state.state, context),
                    counterColor = resolveCounterTextColor(state.state),
                    counterMinLength = counter!!.minLength,
                    counterMaxLength = counter!!.maxLength,
                    counterSize = resolveCounterSize(context),
                    placeHolderColor = resolvePlaceHolderColor(state.state, context),
                    placeHolderSize = resolvePlaceHolderSize(context),
                    placeHolderText = placeholder,
                    typeface = resolveTypeface(context),
                    icon = resolveIcon(context, state.state),
                    leftComponent = resolveLeftComponent(context, leftContent?.leftContent),
                    rightComponent = resolveRightComponent(context,rightContent?.rightContent),
                    leftComponentLeftMargin = resolveLeftComponentLeftMargin(context, leftContent?.leftContent),
                    leftComponentRightMargin = resolveLeftComponentRightMargin(context, leftContent?.leftContent),
                    rightComponentLeftMargin = resolveRightComponentLeftMargin(context, rightContent?.rightContent),
                    rightComponentRightMargin = resolveRightComponentRightMargin(context, rightContent?.rightContent)
            )
        }
    }

    private fun resolveBackground(context: Context, state: AndesTextfieldStateInterface) : Drawable? = state.backgroundColor(context)
    private fun resolveHelperTextColor(state: AndesTextfieldStateInterface): AndesColor = state.textColor()
    private fun resolveHelperSize(context: Context) : Float = context.resources.getDimension(R.dimen.andes_textfield_helper_textSize)
    private fun resolveHelperTypeface(state: AndesTextfieldStateInterface, context: Context) = state.typeFace(context)
    private fun resolveLabelTextColor(state: AndesTextfieldStateInterface) : AndesColor = state.textColor()
    private fun resolveLabelSize(context: Context) : Float = context.resources.getDimension(R.dimen.andes_textfield_label_textSize)
    private fun resolveCounterTextColor(state: AndesTextfieldStateInterface) : AndesColor = state.textColor()
    private fun resolveCounterSize(context: Context) : Float = context.resources.getDimension(R.dimen.andes_textfield_counter_textSize)
    private fun resolveTypeface(context: Context) =  context.getFontOrDefault(R.font.andes_font_regular)
    private fun resolveIcon(context: Context, state: AndesTextfieldStateInterface) : Drawable? = state.icon(context)
    private fun resolvePlaceHolderColor(state: AndesTextfieldStateInterface, context: Context) : AndesColor = state.hintColor()
    private fun resolvePlaceHolderSize(context: Context) : Float = context.resources.getDimension(R.dimen.andes_textfield_placeholder_textSize)
    private fun resolveLeftComponent(context: Context, leftContent: AndesTextfieldContentInterface?) : View? = leftContent?.component(context)
    private fun resolveRightComponent(context: Context, rightContent: AndesTextfieldContentInterface?) : View? = rightContent?.component(context)
    private fun resolveLeftComponentLeftMargin(context: Context, leftContent: AndesTextfieldContentInterface?) : Int? = leftContent?.leftMargin(context)
    private fun resolveLeftComponentRightMargin(context: Context, leftContent: AndesTextfieldContentInterface?) : Int? = leftContent?.rightMargin(context)
    private fun resolveRightComponentLeftMargin(context: Context, rightContent: AndesTextfieldContentInterface?) : Int? = rightContent?.leftMargin(context)
    private fun resolveRightComponentRightMargin(context: Context, rightContent: AndesTextfieldContentInterface?) : Int? = rightContent?.rightMargin(context)
}

