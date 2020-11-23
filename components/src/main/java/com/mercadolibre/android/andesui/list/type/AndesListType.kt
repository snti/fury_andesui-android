package com.mercadolibre.android.andesui.list.type

/**
 *
 */
enum class AndesListType {
    SIMPLE,
    CHEVRON,
    CHECK_BOX,
    RADIO_BUTTON;

    companion object {
        fun fromString(value: String): AndesListType = valueOf(value.toUpperCase())
    }

    internal val type get() = getAndesListType()

    private fun getAndesListType(): AndesListTypeInterface {
        return when (this) {
            SIMPLE -> AndesListTypeSimple
            CHEVRON -> AndesListTypeChevron
            CHECK_BOX -> AndesListTypeCheckBox
            RADIO_BUTTON -> AndesListTypeRadioButton
        }
    }

}