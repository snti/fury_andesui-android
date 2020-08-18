package com.mercadolibre.android.andesui.textfield

import android.os.Build
import com.facebook.soloader.SoLoader
import com.mercadolibre.android.andesui.BuildConfig
import com.mercadolibre.android.andesui.textfield.maskTextField.TextFieldMaskWatcher
import org.junit.Assert
import org.junit.BeforeClass
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(constants = BuildConfig::class, sdk = [Build.VERSION_CODES.LOLLIPOP])
class TextFieldMaskWatcherTest {

    companion object {
        @JvmStatic
        @BeforeClass
        fun beforeClass() {
            SoLoader.setInTestMode()
        }
    }

    @Test
    fun `clean mask`() {
        val maskWatcher = TextFieldMaskWatcher("##/-##-,##", null)
        Assert.assertEquals(maskWatcher.cleanMask("44--55--66"), "445566")
    }

    @Test
    fun `max size text without mask`() {
        val maskWatcher = TextFieldMaskWatcher("##--##-,##", null)
        Assert.assertEquals(maskWatcher.getMaxLength(), 6)
    }
}
