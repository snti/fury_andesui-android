package com.mercadolibre.android.andesui.textfield

import android.os.Build
import com.facebook.common.logging.FLog
import com.facebook.drawee.backends.pipeline.Fresco
import com.facebook.imagepipeline.core.ImagePipelineConfig
import com.facebook.imagepipeline.listener.RequestListener
import com.facebook.imagepipeline.listener.RequestLoggingListener
import com.facebook.soloader.SoLoader
import com.mercadolibre.android.andesui.R
import com.mercadolibre.android.andesui.color.toAndesColor
import com.mercadolibre.android.andesui.textfield.state.*
import junit.framework.Assert.assertEquals
import junit.framework.Assert.assertNull
import org.junit.Before
import org.junit.BeforeClass
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.RuntimeEnvironment
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [Build.VERSION_CODES.LOLLIPOP])
class AndesTextfieldStateInterfaceTest {
    private var context = RuntimeEnvironment.application
    private lateinit var stateInterface: AndesTextfieldStateInterface

    companion object {
        @JvmStatic
        @BeforeClass
        fun beforeClass() {
            SoLoader.setInTestMode()
        }
    }

    @Before
    fun setUp() {
        val requestListeners = setOf<RequestListener>(RequestLoggingListener())
        val config = ImagePipelineConfig.newBuilder(context)
                // other setters
                .setRequestListeners(requestListeners)
                .build()
        Fresco.initialize(context, config)
        FLog.setMinimumLoggingLevel(FLog.VERBOSE)
    }

    @Test
    fun `enabled state`() {
        stateInterface = AndesIdleTextfieldState
        assertEquals(R.color.andes_gray_800.toAndesColor(), stateInterface.labelColor())
        assertEquals(R.color.andes_gray_200.toAndesColor(), stateInterface.placeholderColor())
        assertEquals(R.color.andes_gray_450.toAndesColor(), stateInterface.helpersColor())
        assertNull(stateInterface.icon(context))
    }

    @Test
    fun `error state`() {
        stateInterface = AndesErrorTextfieldState
        assertEquals(R.color.andes_red_500.toAndesColor(), stateInterface.labelColor())
        assertEquals(R.color.andes_gray_200.toAndesColor(), stateInterface.placeholderColor())
        assertEquals(R.color.andes_red_500.toAndesColor(), stateInterface.helpersColor())
    }

    @Test
    fun `disabled state`() {
        stateInterface = AndesDisabledTextfieldState
        assertEquals(R.color.andes_gray_250.toAndesColor(), stateInterface.labelColor())
        assertEquals(R.color.andes_gray_250.toAndesColor(), stateInterface.placeholderColor())
        assertEquals(R.color.andes_gray_250.toAndesColor(), stateInterface.helpersColor())
        assertNull(stateInterface.icon(context))
    }

    @Test
    fun `read only state`() {
        stateInterface = AndesReadonlyTextfieldState
        assertEquals(R.color.andes_gray_450.toAndesColor(), stateInterface.labelColor())
        assertEquals(R.color.andes_gray_800.toAndesColor(), stateInterface.placeholderColor())
        assertEquals(R.color.andes_gray_250.toAndesColor(), stateInterface.helpersColor())
        assertNull(stateInterface.icon(context))
    }
}
