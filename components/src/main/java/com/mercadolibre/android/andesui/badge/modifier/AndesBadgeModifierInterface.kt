package com.mercadolibre.android.andesui.badge.modifier

/**
 * Defines all styles related properties that an [AndesBadge] needs to be drawn properly.
 * Those properties change depending on the style of the badge.
 *
 */
internal interface AndesBadgeModifierInterface

/**
 * Implementation of [AndesBadgeModifierInterface] that returns the required data but personalized for the Large Size,
 * according to Andes specifications.
 *
 */
internal class AndesPillBadgeModifier : AndesBadgeModifierInterface
