package com.mercadolibre.android.andesui.badge.modifier

import com.mercadolibre.android.andesui.badge.AndesBadge

/**
 * Utility class that does two things: Defines the possible styles an [AndesBadge] can take because it's an enum, as you can see.
 * But as a bonus it gives you the proper implementation so you don't have to make any mapping.
 *
 * You ask me with, let's say 'SMALL', and then I'll give you a proper implementation of that size.
 *
 * @property modifier Possible styles that an [AndesBadge] may take.
 */
enum class AndesBadgeModifier {
    PILL;

    companion object {
        fun fromString(value: String): AndesBadgeModifier = valueOf(value.toUpperCase())
    }

    internal val modifier get() = getAndesBadgeModifier()

    private fun getAndesBadgeModifier(): AndesBadgeModifierInterface {
        return when (this) {
            PILL -> AndesPillBadgeModifier()
        }
    }
}
