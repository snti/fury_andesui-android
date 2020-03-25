package com.mercadolibre.android.andesui.textfield.content

/**
 * Utility class that does two things: Defines the possible states an [AndesTextfield] can take because it's an enum, as you can see.
 * But as a bonus it gives you the proper implementation so you don't have to make any mapping.
 *
 * You ask me with, let's say 'ENABLED', and then I'll give you a proper implementation of that content.
 *
 * @property content Possible contents that an [AndesTextfield] may take.
 */

enum class AndesTextfieldLeftContent {
    PREFIX,
    ICON;

    internal val leftContent get() = getAndesTexfieldLeftContent()

    private fun getAndesTexfieldLeftContent(): AndesTextfieldContentInterface {
        return when (this) {
            PREFIX -> AndesPrefixTextfieldContent
            ICON -> AndesIconTextfieldContent
        }
    }
}

enum class AndesTextfieldRightContent {
    SUFFIX,
    ICON,
    TOOLTIP,
    VALIDATED,
    CLEAR,
    ACTION,
    INDETERMINATE,
    CHECKBOX;

    internal val rightContent get() = getAndesTexfieldRightContent()

    private fun getAndesTexfieldRightContent(): AndesTextfieldContentInterface {
        return when (this) {
            SUFFIX -> AndesSuffixTextfieldContent
            ICON -> AndesIconTextfieldContent
            TOOLTIP -> AndesTooltipTextfieldContent
            VALIDATED -> AndesValidatedTextfieldContent
            CLEAR -> AndesClearTextfieldContent
            ACTION -> AndesActionTextfieldContent
            INDETERMINATE -> AndesIndeterminateTextfieldContent
            CHECKBOX -> AndesCheckboxTextfieldContent
        }
    }
}

