package com.mercadolibre.android.andesui.tag.factory

import com.mercadolibre.android.andesui.color.AndesColor
import com.mercadolibre.android.andesui.tag.choice.AndesTagChoiceStateInterface
import com.mercadolibre.android.andesui.tag.leftcontent.AndesTagLeftContent
import com.mercadolibre.android.andesui.tag.leftcontent.LeftContent
import com.mercadolibre.android.andesui.tag.rightcontent.AndesTagRightContent
import com.mercadolibre.android.andesui.tag.rightcontent.RightContent

internal data class AndesTagChoiceConfiguration(
        val text: String? = null,
        val backgroundColor: AndesColor,
        val borderColor: AndesColor,
        val textColor: AndesColor,
        val rightContentColor: AndesColor,
        val leftContentData: LeftContent? = null,
        val leftContent: AndesTagLeftContent? = null,
        val rightContentData: RightContent? = null,
        val rightContent: AndesTagRightContent? = null
)

internal object AndesChoiceTagConfigurationFactory {

    fun create(andesTagSimpleAttrs: AndesTagChoiceAttrs): AndesTagChoiceConfiguration {
        return with(andesTagSimpleAttrs) {
            AndesTagChoiceConfiguration(
                    text = andesSimpleTagText,
                    backgroundColor = resolveBackgroundColor(andesTagChoiceState.state),
                    borderColor = resolveBorderColor(andesTagChoiceState.state),
                    textColor = resolveTextColor(andesTagChoiceState.state),
                    rightContentColor = resolveRightContentColor(andesTagChoiceState.state),
                    leftContentData = andesTagSimpleAttrs.leftContentData,
                    leftContent = andesTagSimpleAttrs.leftContent,
                    rightContentData = andesTagSimpleAttrs.rightContentData,
                    rightContent = andesTagSimpleAttrs.rightContent
            )
        }
    }

    private fun resolveBackgroundColor(state: AndesTagChoiceStateInterface) = state.backgroundColor()
    private fun resolveBorderColor(state: AndesTagChoiceStateInterface) = state.borderColor()
    private fun resolveTextColor(state: AndesTagChoiceStateInterface) = state.textColor()
    private fun resolveRightContentColor(state: AndesTagChoiceStateInterface) = state.rightContentColor()
}
