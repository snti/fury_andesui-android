package com.mercadolibre.android.andesui.checkbox.factory.align

import com.mercadolibre.android.andesui.checkbox.align.AndesCheckboxAlignInterface
import com.mercadolibre.android.andesui.checkbox.align.AndesCheckboxLeft
import com.mercadolibre.android.andesui.checkbox.align.AndesCheckboxRight

enum class AndesCheckboxAlign {
    LEFT,
    RIGHT;

    companion object {
        fun fromString(value: String): AndesCheckboxAlign = AndesCheckboxAlign.valueOf(value.toUpperCase())
    }

    internal val type get() = getAndesCheckboxHierarchy()

    private fun getAndesCheckboxHierarchy(): AndesCheckboxAlignInterface {
        return when (this) {
            LEFT -> AndesCheckboxLeft
            RIGHT -> AndesCheckboxRight
        }
    }
}