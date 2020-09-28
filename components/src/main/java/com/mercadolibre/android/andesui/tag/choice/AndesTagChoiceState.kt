package com.mercadolibre.android.andesui.tag.choice

import com.mercadolibre.android.andesui.tag.type.AndesTagType

enum class AndesTagChoiceState {
    IDLE,
    SELECTED;

    companion object {
        fun fromString(value: String): AndesTagType = AndesTagType.valueOf(value.toUpperCase())
    }

    internal val state get() = getAndesTagHierarchy()

    private fun getAndesTagHierarchy(): AndesTagChoiceStateInterface {
        return when (this) {
            IDLE -> AndesChoiceIdleState()
            SELECTED -> AndesChoiceSelectedState()
        }
    }
}
