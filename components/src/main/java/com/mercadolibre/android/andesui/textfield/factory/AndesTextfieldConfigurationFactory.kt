package com.mercadolibre.android.andesui.textfield.factory

import android.content.Context
import android.graphics.Typeface
import android.graphics.drawable.Drawable
import android.view.View
import com.mercadolibre.android.andesui.R
import com.mercadolibre.android.andesui.textfield.content.AndesTextfieldContentInterface
import com.mercadolibre.android.andesui.textfield.state.AndesTextfieldStateInterface
import com.mercadolibre.android.andesui.typeface.getFontOrDefault

internal data class AndesTextfieldConfiguration(
        val background: Drawable?,
       // val helperTextColor : ColorStateList,
        //val labelTextColor : ColorStateList,
        //val counterTextColor : ColorStateList,
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
                    background = resolveBackground(context, state.state),
                    //helperTextColor = resolveHelperTextColor(context),
                    //labelTextColor = resolveLabelTextColor(context),
                    //counterTextColor = resolveCounterTextColor(context),
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
   // private fun resolveHelperTextColor(context: Context) : ColorStateList
    //private fun resolveLabelTextColor(context: Context) : ColorStateList
    //private fun resolveCounterTextColor(context: Context) : ColorStateList
    private fun resolveTypeface(context: Context) =  context.getFontOrDefault(R.font.andes_font_regular)
    private fun resolveIcon(context: Context, state: AndesTextfieldStateInterface) : Drawable? = state.icon(context)
    private fun resolveLeftComponent(context: Context, leftContent: AndesTextfieldContentInterface?) : View? = leftContent?.component(context)
    private fun resolveRightComponent(context: Context, rightContent: AndesTextfieldContentInterface?) : View? = rightContent?.component(context)
    private fun resolveLeftComponentLeftMargin(context: Context, leftContent: AndesTextfieldContentInterface?) : Int? = leftContent?.leftMargin(context)
    private fun resolveLeftComponentRightMargin(context: Context, leftContent: AndesTextfieldContentInterface?) : Int? = leftContent?.rightMargin(context)
    private fun resolveRightComponentLeftMargin(context: Context, rightContent: AndesTextfieldContentInterface?) : Int? = rightContent?.leftMargin(context)
    private fun resolveRightComponentRightMargin(context: Context, rightContent: AndesTextfieldContentInterface?) : Int? = rightContent?.rightMargin(context)
}

