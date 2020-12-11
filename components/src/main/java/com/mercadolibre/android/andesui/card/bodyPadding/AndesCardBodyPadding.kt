package com.mercadolibre.android.andesui.card.bodyPadding

import com.mercadolibre.android.andesui.card.padding.AndesCardPaddingXLarge
import com.mercadolibre.android.andesui.card.padding.AndesCardPaddingInterface
import com.mercadolibre.android.andesui.card.padding.AndesCardPaddingLarge
import com.mercadolibre.android.andesui.card.padding.AndesCardPaddingMedium
import com.mercadolibre.android.andesui.card.padding.AndesCardPaddingNone
import com.mercadolibre.android.andesui.card.padding.AndesCardPaddingSmall

enum class AndesCardBodyPadding {
    NONE,
    SMALL,
    MEDIUM,
    LARGE,
    XLARGE;

    companion object {
        fun fromString(value: String): AndesCardBodyPadding = valueOf(value.toUpperCase())
    }

    internal val bodyPadding get() = getAndesCardBodyPadding()
    internal val padding get() = getAndesCardPadding()

    private fun getAndesCardBodyPadding(): AndesCardBodyPaddingInterface {
        return when (this) {
            NONE -> AndesCardBodyPaddingNone
            SMALL -> AndesCardBodyPaddingSmall
            MEDIUM -> AndesCardBodyPaddingMedium
            LARGE -> AndesCardBodyPaddingLarge
            XLARGE -> AndesCardBodyPaddingXLarge
        }
    }

    private fun getAndesCardPadding(): AndesCardPaddingInterface {
        return when(this) {
            NONE -> AndesCardPaddingNone
            SMALL -> AndesCardPaddingSmall
            MEDIUM -> AndesCardPaddingMedium
            LARGE -> AndesCardPaddingLarge
            XLARGE -> AndesCardPaddingXLarge
        }
    }
}