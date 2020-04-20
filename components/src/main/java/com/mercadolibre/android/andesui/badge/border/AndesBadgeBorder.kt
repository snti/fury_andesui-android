package com.mercadolibre.android.andesui.badge.border

import com.mercadolibre.android.andesui.badge.AndesBadge

/**
 * Utility class that does two things: Defines the possible sizes an [AndesBadge] can take because it's an enum, as you can see.
 * But as a bonus it gives you the proper implementation so you don't have to make any mapping.
 *
 * You ask me with, let's say 'STANDARD', and then I'll give you a proper implementation of that border.
 *
 * @property border Possible borders that an [AndesBadge] may take.
 */
enum class AndesBadgeBorder {
    CORNER,
    ROUNDED,
    STANDARD;

    internal val border get() = getAndesBadgeBorder()

    private fun getAndesBadgeBorder(): AndesBadgeBorderInterface {
        return when (this) {
            CORNER -> AndesCornerBadgeBorder()
            ROUNDED -> AndesRoundedBadgeBorder()
            STANDARD -> AndesStandardBadgeBorder()
        }
    }
}