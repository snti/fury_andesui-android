package com.mercadolibre.android.andesui.tag

import android.content.Context
import android.support.constraint.ConstraintLayout
import android.util.AttributeSet
import com.mercadolibre.android.andesui.tag.choice.AndesTagChoiceType
import com.mercadolibre.android.andesui.tag.factory.AndesChoiceTagConfigurationFactory
import com.mercadolibre.android.andesui.tag.factory.AndesTagChoiceAttrs
import com.mercadolibre.android.andesui.tag.factory.AndesTagChoiceAttrsParser
import com.mercadolibre.android.andesui.tag.leftcontent.AndesTagLeftContent
import com.mercadolibre.android.andesui.tag.leftcontent.LeftContent
import com.mercadolibre.android.andesui.tag.size.AndesTagSize
import com.mercadolibre.android.andesui.tag.type.AndesTagType

class AndesTagChoice : ConstraintLayout {
    private lateinit var andesTagAttrs: AndesTagChoiceAttrs
    private lateinit var containerTag: ConstraintLayout

    @Suppress("unused")
    private constructor(context: Context) : super(context) {
        throw IllegalStateException(
                "Constructor without parameters in Andes Badge is not allowed. You must provide some attributes."
        )
    }

    @Suppress("unused")
    constructor(
            context: Context,
            text: String? = ""
    ) : super(context) {
        initAttrs(text)
    }

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        initAttrs(attrs)
    }

    /**
     * Sets the proper [config] for this message based on the [attrs] received via XML.
     * @param attrs attributes from the XML.
     */
    private fun initAttrs(attrs: AttributeSet?) {
        andesTagAttrs = AndesTagChoiceAttrsParser.parse(context, attrs)
        val config = AndesChoiceTagConfigurationFactory.create(andesTagAttrs)
        //setupComponents(config)
    }

    private fun initAttrs(
            text: String?,
            leftContent: AndesTagLeftContent? = null,
            leftContentData: LeftContent? = null
    ) {
        andesTagAttrs = AndesTagChoiceAttrs(text, leftContentData, leftContent)
        val config = AndesChoiceTagConfigurationFactory.create(andesTagAttrs)
        //setupComponents(config)
    }

    /**
     * Getter and setter for [type].
     */
    var type: AndesTagChoiceType
        get() = andesTagAttrs.andesTagType
        set(value) {
            andesTagAttrs = andesTagAttrs.copy(andesTagType = value)
            setupBackgroundComponents(createConfig())
        }

    /**
     * Getter and setter for [size].
     */
    var size: AndesTagSize
        get() = andesTagAttrs.andesTagSize
        set(value) {
            andesTagAttrs = andesTagAttrs.copy(andesTagSize = value)
            setupBackgroundComponents(createConfig())
            setupTitleComponent(createConfig())
        }

    /**
     * Getter and setter for [text].
     */
    var text: String?
        get() = andesTagAttrs.andesSimpleTagText
        set(value) {
            andesTagAttrs = andesTagAttrs.copy(andesSimpleTagText = value)
            setupTitleComponent(createConfig())
        }
}