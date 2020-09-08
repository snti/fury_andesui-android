package com.mercadolibre.android.andesui.carousel.padding

/**
 * Utility class that does two things: Defines the possible styles an [AndesCarousel] can take
 * because it's an enum, as you can see.
 * But as a bonus it gives you the proper implementation so you don't have to make any mapping.
 *
 * @property padding Possible styles that an [AndesCarousel] may take.
 */
enum class AndesCarouselPadding {
    NONE,
    SMALL,
    MEDIUM,
    LARGE;

    companion object {
        fun fromString(value: String): AndesCarouselPadding = valueOf(value.toUpperCase())
    }

    internal val padding get() = getAndesCarouselPadding()

    private fun getAndesCarouselPadding(): AndesCarouselPaddingInterface {
        return when (this) {
            NONE -> AndesCarouselPaddingNone
            SMALL -> AndesCarouselPaddingSmall
            MEDIUM -> AndesCarouselPaddingMedium
            LARGE -> AndesCarouselPaddingLarge
        }
    }
}
