package com.mercadolibre.android.andesui.badge

import android.os.Build
import com.mercadolibre.android.andesui.BuildConfig
import com.mercadolibre.android.andesui.R
import com.mercadolibre.android.andesui.badge.border.AndesBadgeBorder
import com.mercadolibre.android.andesui.badge.factory.AndesBadgeAttrs
import com.mercadolibre.android.andesui.badge.factory.AndesBadgeConfigurationFactory
import com.mercadolibre.android.andesui.badge.hierarchy.AndesBadgeHierarchy
import com.mercadolibre.android.andesui.badge.modifier.AndesBadgeModifier
import com.mercadolibre.android.andesui.badge.size.AndesBadgeSize
import com.mercadolibre.android.andesui.badge.type.AndesBadgeType
import com.mercadolibre.android.andesui.color.toAndesColor
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito.spy
import org.robolectric.RobolectricTestRunner
import org.robolectric.RuntimeEnvironment
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(constants = BuildConfig::class, sdk = [Build.VERSION_CODES.LOLLIPOP])
class AndesBadgeConfigurationLoudTest {

    private var context = RuntimeEnvironment.application

    private val configFactory = spy(AndesBadgeConfigurationFactory)
    private lateinit var attrs: AndesBadgeAttrs

    @Test
    fun `Pill, Loud, Neutral, Standard, Small background color`() {
        attrs = AndesBadgeAttrs(AndesBadgeModifier.PILL, AndesBadgeHierarchy.LOUD, AndesBadgeType.NEUTRAL,
                AndesBadgeBorder.STANDARD, AndesBadgeSize.SMALL, "Title")
        val config = configFactory.create(context, attrs)
        assertEquals(R.color.andes_gray_450_solid.toAndesColor(), config.backgroundColor)
        assertEquals(SMALL_HEIGHT, config.height)
    }

    @Test
    fun `Pill, Loud, Highlight, Standard, Small background color`() {
        attrs = AndesBadgeAttrs(AndesBadgeModifier.PILL, AndesBadgeHierarchy.LOUD, AndesBadgeType.HIGHLIGHT,
                AndesBadgeBorder.STANDARD, AndesBadgeSize.SMALL, "Title")
        val config = configFactory.create(context, attrs)
        assertEquals(R.color.andes_accent_color_500.toAndesColor(), config.backgroundColor)
        assertEquals(SMALL_HEIGHT, config.height)
    }

    @Test
    fun `Pill, Loud, Success, Standard, Small background color`() {
        attrs = AndesBadgeAttrs(AndesBadgeModifier.PILL, AndesBadgeHierarchy.LOUD, AndesBadgeType.SUCCESS,
                AndesBadgeBorder.STANDARD, AndesBadgeSize.SMALL, "Title")
        val config = configFactory.create(context, attrs)
        assertEquals(R.color.andes_green_500.toAndesColor(), config.backgroundColor)
        assertEquals(SMALL_HEIGHT, config.height)
    }

    @Test
    fun `Pill, Loud, Error, Standard, Small background color`() {
        attrs = AndesBadgeAttrs(AndesBadgeModifier.PILL, AndesBadgeHierarchy.LOUD, AndesBadgeType.ERROR,
                AndesBadgeBorder.STANDARD, AndesBadgeSize.SMALL, "Title")
        val config = configFactory.create(context, attrs)
        assertEquals(R.color.andes_red_500.toAndesColor(), config.backgroundColor)
        assertEquals(SMALL_HEIGHT, config.height)
    }

    @Test
    fun `Pill, Loud, Warning, Standard, Small background color`() {
        attrs = AndesBadgeAttrs(AndesBadgeModifier.PILL, AndesBadgeHierarchy.LOUD, AndesBadgeType.WARNING,
                AndesBadgeBorder.STANDARD, AndesBadgeSize.SMALL, "Title")
        val config = configFactory.create(context, attrs)
        assertEquals(R.color.andes_orange_500.toAndesColor(), config.backgroundColor)
        assertEquals(SMALL_HEIGHT, config.height)
    }

    @Test
    fun `Pill, Loud, Neutral, Standard, Large background color`() {
        attrs = AndesBadgeAttrs(AndesBadgeModifier.PILL, AndesBadgeHierarchy.LOUD, AndesBadgeType.NEUTRAL,
                AndesBadgeBorder.STANDARD, AndesBadgeSize.LARGE, "Title")
        val config = configFactory.create(context, attrs)
        assertEquals(R.color.andes_gray_450_solid.toAndesColor(), config.backgroundColor)
        assertEquals(LARGE_HEIGHT, config.height)
    }

    @Test
    fun `Pill, Loud, Highlight, Standard, Large background color`() {
        attrs = AndesBadgeAttrs(AndesBadgeModifier.PILL, AndesBadgeHierarchy.LOUD, AndesBadgeType.HIGHLIGHT,
                AndesBadgeBorder.STANDARD, AndesBadgeSize.LARGE, "Title")
        val config = configFactory.create(context, attrs)
        assertEquals(R.color.andes_accent_color_500.toAndesColor(), config.backgroundColor)
        assertEquals(LARGE_HEIGHT, config.height)
    }

    @Test
    fun `Pill, Loud, Success, Standard, Large background color`() {
        attrs = AndesBadgeAttrs(AndesBadgeModifier.PILL, AndesBadgeHierarchy.LOUD, AndesBadgeType.SUCCESS,
                AndesBadgeBorder.STANDARD, AndesBadgeSize.LARGE, "Title")
        val config = configFactory.create(context, attrs)
        assertEquals(R.color.andes_green_500.toAndesColor(), config.backgroundColor)
        assertEquals(LARGE_HEIGHT, config.height)
    }

    @Test
    fun `Pill, Loud, Error, Standard, Large background color`() {
        attrs = AndesBadgeAttrs(AndesBadgeModifier.PILL, AndesBadgeHierarchy.LOUD, AndesBadgeType.ERROR,
                AndesBadgeBorder.STANDARD, AndesBadgeSize.LARGE, "Title")
        val config = configFactory.create(context, attrs)
        assertEquals(R.color.andes_red_500.toAndesColor(), config.backgroundColor)
        assertEquals(LARGE_HEIGHT, config.height)
    }

    @Test
    fun `Pill, Loud, Warning, Standard, Large background color`() {
        attrs = AndesBadgeAttrs(AndesBadgeModifier.PILL, AndesBadgeHierarchy.LOUD, AndesBadgeType.WARNING,
                AndesBadgeBorder.STANDARD, AndesBadgeSize.LARGE, "Title")
        val config = configFactory.create(context, attrs)
        assertEquals(R.color.andes_orange_500.toAndesColor(), config.backgroundColor)
        assertEquals(LARGE_HEIGHT, config.height)
    }

    companion object {
        private const val SMALL_HEIGHT = 16F
        private const val LARGE_HEIGHT = 24F
    }
}
