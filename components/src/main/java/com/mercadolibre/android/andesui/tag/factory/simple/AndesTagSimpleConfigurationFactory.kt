package com.mercadolibre.android.andesui.tag.factory.simple

import com.mercadolibre.android.andesui.color.AndesColor
import com.mercadolibre.android.andesui.tag.leftcontent.AndesTagLeftContent
import com.mercadolibre.android.andesui.tag.leftcontent.LeftContent
import com.mercadolibre.android.andesui.tag.rightcontent.AndesTagRightContent
import com.mercadolibre.android.andesui.tag.rightcontent.RightContent
import com.mercadolibre.android.andesui.tag.type.AndesSimpleTagTypeInterface

internal data class AndesTagSimpleConfiguration(
    val text: String? = null,
    val borderColor: AndesColor,
    val textColor: AndesColor,
    val backgroundColor: AndesColor? = null,
    val leftContentData: LeftContent? = null,
    val leftContent: AndesTagLeftContent? = null,
    val rightContentData: RightContent? = null,
    val rightContent: AndesTagRightContent? = null
)

internal object AndesSimpleTagConfigurationFactory {

    fun create(andesTagSimpleAttrs: AndesTagSimpleAttrs): AndesTagSimpleConfiguration {
        return with(andesTagSimpleAttrs) {
            AndesTagSimpleConfiguration(
                    text = andesSimpleTagText,
                    backgroundColor = resolveBackgroundColor(andesTagType.type),
                    borderColor = resolveBorderColor(andesTagType.type),
                    textColor = resolveTextColor(andesTagType.type),
                    leftContentData = andesTagSimpleAttrs.leftContentData,
                    leftContent = andesTagSimpleAttrs.leftContent,
                    rightContentData = andesTagSimpleAttrs.rightContentData,
                    rightContent = andesTagSimpleAttrs.rightContent
            )
        }
    }

    private fun resolveBackgroundColor(type: AndesSimpleTagTypeInterface) = type.secondaryColor()
    private fun resolveBorderColor(type: AndesSimpleTagTypeInterface) = type.primaryColor()
    private fun resolveTextColor(type: AndesSimpleTagTypeInterface) = type.primaryColor()
}
