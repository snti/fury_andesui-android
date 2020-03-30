package com.mercadolibre.android.andesui.textfield.type

import com.mercadolibre.android.andesui.textfield.content.AndesIconTextfieldContent
import com.mercadolibre.android.andesui.textfield.content.AndesPrefixTextfieldContent
import com.mercadolibre.android.andesui.textfield.content.AndesTextfieldContentInterface

/**
 * Utility class that does two things: Defines the possible states an [AndesTextfield] can take because it's an enum, as you can see.
 * But as a bonus it gives you the proper implementation so you don't have to make any mapping.
 *
 * You ask me with, let's say 'ENABLED', and then I'll give you a proper implementation of that content.
 *
 * @property content Possible contents that an [AndesTextfield] may take.
 */

enum class AndesTextfieldType {
    TEXT,
    NUMBER,
    PASSWORD,
    EMAIL;

    internal val textType get() = getAndesTextType()

    private fun getAndesTextType(): String {
        return when (this) {
            TEXT -> "text"
            NUMBER -> "number"
            PASSWORD -> "textPassword"
            EMAIL -> "textEmailAddress"
        }
    }
}