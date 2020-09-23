package com.mercadolibre.android.andesui.tag.factory

import com.mercadolibre.android.andesui.color.AndesColor
import com.mercadolibre.android.andesui.tag.leftcontent.AndesTagLeftContent
import com.mercadolibre.android.andesui.tag.leftcontent.LeftContent
import com.mercadolibre.android.andesui.tag.rightcontent.AndesTagRightContent
import com.mercadolibre.android.andesui.tag.rightcontent.RightContent

internal data class AndesTagChoiceConfiguration(
        val text: String? = null,
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
                    leftContentData = andesTagSimpleAttrs.leftContentData,
                    leftContent = andesTagSimpleAttrs.leftContent,
                    rightContentData = andesTagSimpleAttrs.rightContentData,
                    rightContent = andesTagSimpleAttrs.rightContent
            )
        }
    }
}
