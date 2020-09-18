package com.mercadolibre.android.andesui.bottomsheet.factory

import android.graphics.Color
import android.os.Build
import com.mercadolibre.android.andesui.BuildConfig
import com.mercadolibre.android.andesui.bottomsheet.state.AndesBottomSheetState
import org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(constants = BuildConfig::class, sdk = [Build.VERSION_CODES.LOLLIPOP] )
class AndesBottomSheetConfigurationFactoryTest {

    private val configFactory = Mockito.spy(AndesBottomSheetConfigurationFactory)
    private lateinit var attrs: AndesBottomSheetAttrs

    @Test
    fun `attr create factory test`() {
        val peekHeight = 100
        val cornerRadius = 25
        attrs = AndesBottomSheetAttrs(
                andesBottomSheetPeekHeight = peekHeight,
                andesBottomSheetCornerRadius = cornerRadius,
                andesBottomSheetBackgroundColor = Color.BLACK,
                andesBottomSheetState = AndesBottomSheetState.EXPANDED
        )

        val config = configFactory.create(attrs)

        assertEquals(config.cornerRadius, cornerRadius)
        assertEquals(config.peekHeight, peekHeight)
        assertEquals(config.backgroundColor, Color.BLACK)
        assertEquals(config.state, AndesBottomSheetState.EXPANDED)
    }
}