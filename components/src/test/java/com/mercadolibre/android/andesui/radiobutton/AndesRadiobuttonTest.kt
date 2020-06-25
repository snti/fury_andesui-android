package com.mercadolibre.android.andesui.radiobutton

import android.os.Build
import com.mercadolibre.android.andesui.BuildConfig
import com.mercadolibre.android.andesui.R
import com.mercadolibre.android.andesui.color.toAndesColor
import com.mercadolibre.android.andesui.radiobutton.align.AndesRadiobuttonAlign
import com.mercadolibre.android.andesui.radiobutton.factory.AndesRadiobuttonAttrs
import com.mercadolibre.android.andesui.radiobutton.factory.AndesRadiobuttonConfigurationFactory
import com.mercadolibre.android.andesui.radiobutton.status.AndesRadiobuttonStatus
import com.mercadolibre.android.andesui.radiobutton.type.AndesRadiobuttonType
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito.spy
import org.robolectric.RobolectricTestRunner
import org.robolectric.RuntimeEnvironment
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(constants = BuildConfig::class, sdk = [Build.VERSION_CODES.LOLLIPOP])
class AndesRadiobuttonTest {

    private var context = RuntimeEnvironment.application
    private val configFactory = spy(AndesRadiobuttonConfigurationFactory)
    private lateinit var attrs: AndesRadiobuttonAttrs

    @Test
    fun `Radiobutton, Idle, Unselected, Border`() {
        attrs = AndesRadiobuttonAttrs(
                andesRadiobuttonAlign = AndesRadiobuttonAlign.LEFT,
                andesRadiobuttonStatus = AndesRadiobuttonStatus.UNSELECTED,
                andesRadiobuttonType = AndesRadiobuttonType.IDLE,
                andesRadiobuttonText = "Andes checkbox"
        )
        val config = configFactory.create(attrs)
        assertEquals(
                config.type.type.borderColor(context, config.status),
                R.color.andes_gray_250_solid.toAndesColor()
        )
    }

    @Test
    fun `Radiobutton, Idle, Unselected, Background`() {
        attrs = AndesRadiobuttonAttrs(
                andesRadiobuttonAlign = AndesRadiobuttonAlign.LEFT,
                andesRadiobuttonStatus = AndesRadiobuttonStatus.UNSELECTED,
                andesRadiobuttonType = AndesRadiobuttonType.IDLE,
                andesRadiobuttonText = "Andes checkbox"
        )
        val config = configFactory.create(attrs)
        assertEquals(
                config.type.type.backgroundColor(context, config.status),
                R.color.andes_white.toAndesColor()
        )
    }

    @Test
    fun `Radiobutton, Idle, Unselected, Icon color`() {
        attrs = AndesRadiobuttonAttrs(
                andesRadiobuttonAlign = AndesRadiobuttonAlign.LEFT,
                andesRadiobuttonStatus = AndesRadiobuttonStatus.UNSELECTED,
                andesRadiobuttonType = AndesRadiobuttonType.IDLE,
                andesRadiobuttonText = "Andes checkbox"
        )
        val config = configFactory.create(attrs)
        assertEquals(
                config.type.type.iconColor(context, config.status),
                R.color.andes_white.toAndesColor()
        )
    }

    @Test
    fun `Radiobutton, Idle, Selected, Border`() {
        attrs = AndesRadiobuttonAttrs(
                andesRadiobuttonAlign = AndesRadiobuttonAlign.LEFT,
                andesRadiobuttonStatus = AndesRadiobuttonStatus.SELECTED,
                andesRadiobuttonType = AndesRadiobuttonType.IDLE,
                andesRadiobuttonText = "Andes checkbox"
        )
        val config = configFactory.create(attrs)
        assertEquals(
                config.type.type.borderColor(context, config.status),
                R.color.andes_blue_ml_500.toAndesColor()
        )
    }

    @Test
    fun `Radiobutton, Idle, Selected, Background`() {
        attrs = AndesRadiobuttonAttrs(
                andesRadiobuttonAlign = AndesRadiobuttonAlign.LEFT,
                andesRadiobuttonStatus = AndesRadiobuttonStatus.SELECTED,
                andesRadiobuttonType = AndesRadiobuttonType.IDLE,
                andesRadiobuttonText = "Andes checkbox"
        )
        val config = configFactory.create(attrs)
        assertEquals(
                config.type.type.backgroundColor(context, config.status),
                R.color.andes_blue_ml_500.toAndesColor()
        )
    }

    @Test
    fun `Radiobutton, Idle, Selected, Icon color`() {
        attrs = AndesRadiobuttonAttrs(
                andesRadiobuttonAlign = AndesRadiobuttonAlign.LEFT,
                andesRadiobuttonStatus = AndesRadiobuttonStatus.SELECTED,
                andesRadiobuttonType = AndesRadiobuttonType.IDLE,
                andesRadiobuttonText = "Andes checkbox"
        )
        val config = configFactory.create(attrs)
        assertEquals(
                config.type.type.iconColor(context, config.status),
                R.color.andes_white.toAndesColor()
        )
    }

    @Test
    fun `Radiobutton, Disabled, Unselected, Border`() {
        attrs = AndesRadiobuttonAttrs(
                andesRadiobuttonAlign = AndesRadiobuttonAlign.LEFT,
                andesRadiobuttonStatus = AndesRadiobuttonStatus.UNSELECTED,
                andesRadiobuttonType = AndesRadiobuttonType.DISABLED,
                andesRadiobuttonText = "Andes checkbox"
        )
        val config = configFactory.create(attrs)
        assertEquals(
                config.type.type.borderColor(context, config.status),
                R.color.andes_gray_100_solid.toAndesColor()
        )
    }

    @Test
    fun `Radiobutton, Disabled, Unselected, Background`() {
        attrs = AndesRadiobuttonAttrs(
                andesRadiobuttonAlign = AndesRadiobuttonAlign.LEFT,
                andesRadiobuttonStatus = AndesRadiobuttonStatus.UNSELECTED,
                andesRadiobuttonType = AndesRadiobuttonType.DISABLED,
                andesRadiobuttonText = "Andes checkbox"
        )
        val config = configFactory.create(attrs)
        assertEquals(
                config.type.type.backgroundColor(context, config.status),
                R.color.andes_white.toAndesColor()
        )
    }

    @Test
    fun `Radiobutton, Disabled, Unselected, Icon color`() {
        attrs = AndesRadiobuttonAttrs(
                andesRadiobuttonAlign = AndesRadiobuttonAlign.LEFT,
                andesRadiobuttonStatus = AndesRadiobuttonStatus.UNSELECTED,
                andesRadiobuttonType = AndesRadiobuttonType.DISABLED,
                andesRadiobuttonText = "Andes checkbox"
        )
        val config = configFactory.create(attrs)
        assertEquals(
                config.type.type.iconColor(context, config.status),
                R.color.andes_gray_250_solid.toAndesColor()
        )
    }

    @Test
    fun `Radiobutton, Disabled, Selected, Border`() {
        attrs = AndesRadiobuttonAttrs(
                andesRadiobuttonAlign = AndesRadiobuttonAlign.LEFT,
                andesRadiobuttonStatus = AndesRadiobuttonStatus.SELECTED,
                andesRadiobuttonType = AndesRadiobuttonType.DISABLED,
                andesRadiobuttonText = "Andes checkbox"
        )
        val config = configFactory.create(attrs)
        assertEquals(
                config.type.type.borderColor(context, config.status),
                R.color.andes_gray_100_solid.toAndesColor()
        )
    }

    @Test
    fun `Radiobutton, Disabled, Selected, Background`() {
        attrs = AndesRadiobuttonAttrs(
                andesRadiobuttonAlign = AndesRadiobuttonAlign.LEFT,
                andesRadiobuttonStatus = AndesRadiobuttonStatus.SELECTED,
                andesRadiobuttonType = AndesRadiobuttonType.DISABLED,
                andesRadiobuttonText = "Andes checkbox"
        )
        val config = configFactory.create(attrs)
        assertEquals(
                config.type.type.backgroundColor(context, config.status),
                R.color.andes_gray_100_solid.toAndesColor()
        )
    }

    @Test
    fun `Radiobutton, Disabled, Selected, Icon color`() {
        attrs = AndesRadiobuttonAttrs(
                andesRadiobuttonAlign = AndesRadiobuttonAlign.LEFT,
                andesRadiobuttonStatus = AndesRadiobuttonStatus.SELECTED,
                andesRadiobuttonType = AndesRadiobuttonType.DISABLED,
                andesRadiobuttonText = "Andes checkbox"
        )
        val config = configFactory.create(attrs)
        assertEquals(
                config.type.type.iconColor(context, config.status),
                R.color.andes_gray_250_solid.toAndesColor()
        )
    }

    @Test
    fun `Radiobutton, Error, Unselected, Border`() {
        attrs = AndesRadiobuttonAttrs(
                andesRadiobuttonAlign = AndesRadiobuttonAlign.LEFT,
                andesRadiobuttonStatus = AndesRadiobuttonStatus.UNSELECTED,
                andesRadiobuttonType = AndesRadiobuttonType.ERROR,
                andesRadiobuttonText = "Andes checkbox"
        )
        val config = configFactory.create(attrs)
        assertEquals(
                config.type.type.borderColor(context, config.status),
                R.color.andes_red_500.toAndesColor()
        )
    }

    @Test
    fun `Radiobutton, Error, Unselected, Background`() {
        attrs = AndesRadiobuttonAttrs(
                andesRadiobuttonAlign = AndesRadiobuttonAlign.LEFT,
                andesRadiobuttonStatus = AndesRadiobuttonStatus.UNSELECTED,
                andesRadiobuttonType = AndesRadiobuttonType.ERROR,
                andesRadiobuttonText = "Andes checkbox"
        )
        val config = configFactory.create(attrs)
        assertEquals(
                config.type.type.backgroundColor(context, config.status),
                R.color.andes_white.toAndesColor()
        )
    }

    @Test
    fun `Radiobutton, Error, Unselected, Icon color`() {
        attrs = AndesRadiobuttonAttrs(
                andesRadiobuttonAlign = AndesRadiobuttonAlign.LEFT,
                andesRadiobuttonStatus = AndesRadiobuttonStatus.UNSELECTED,
                andesRadiobuttonType = AndesRadiobuttonType.ERROR,
                andesRadiobuttonText = "Andes checkbox"
        )
        val config = configFactory.create(attrs)
        assertEquals(
                config.type.type.iconColor(context, config.status),
                R.color.andes_white.toAndesColor()
        )
    }

    @Test
    fun `Radiobutton, Error, Selected, Border`() {
        attrs = AndesRadiobuttonAttrs(
                andesRadiobuttonAlign = AndesRadiobuttonAlign.LEFT,
                andesRadiobuttonStatus = AndesRadiobuttonStatus.SELECTED,
                andesRadiobuttonType = AndesRadiobuttonType.ERROR,
                andesRadiobuttonText = "Andes checkbox"
        )
        val config = configFactory.create(attrs)
        assertEquals(
                config.type.type.borderColor(context, config.status),
                R.color.andes_red_500.toAndesColor()
        )
    }

    @Test
    fun `Radiobutton, Error, Selected, Background`() {
        attrs = AndesRadiobuttonAttrs(
                andesRadiobuttonAlign = AndesRadiobuttonAlign.LEFT,
                andesRadiobuttonStatus = AndesRadiobuttonStatus.SELECTED,
                andesRadiobuttonType = AndesRadiobuttonType.ERROR,
                andesRadiobuttonText = "Andes checkbox"
        )
        val config = configFactory.create(attrs)
        assertEquals(
                config.type.type.backgroundColor(context, config.status),
                R.color.andes_white.toAndesColor()
        )
    }

    @Test
    fun `Radiobutton, Error, Selected, Icon color`() {
        attrs = AndesRadiobuttonAttrs(
                andesRadiobuttonAlign = AndesRadiobuttonAlign.LEFT,
                andesRadiobuttonStatus = AndesRadiobuttonStatus.SELECTED,
                andesRadiobuttonType = AndesRadiobuttonType.ERROR,
                andesRadiobuttonText = "Andes checkbox"
        )
        val config = configFactory.create(attrs)
        assertEquals(
                config.type.type.iconColor(context, config.status),
                R.color.andes_white.toAndesColor()
        )
    }
}
