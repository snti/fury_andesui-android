package com.mercadolibre.android.andesui.dropdown

import android.os.Build
import com.mercadolibre.android.andesui.dropdown.size.AndesDropdownSize
import com.mercadolibre.android.andesui.dropdown.type.AndesDropdownMenuType
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.RuntimeEnvironment
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [Build.VERSION_CODES.LOLLIPOP])
class AndesDropdownTest {
    private var context = RuntimeEnvironment.application

    companion object {
        private const val SIZE = 10
    }

    @Test
    fun `test delegate`() {

    }


    @Test
    fun `test dropdown standalone with different sizes`() {
        var dropdown = AndesDropDownStandalone(context, AndesDropdownMenuType.BOTTOMSHEET, AndesDropdownSize.SMALL, "test")
        Assert.assertEquals(dropdown.size, AndesDropdownSize.SMALL)

        dropdown = AndesDropDownStandalone(context, AndesDropdownMenuType.BOTTOMSHEET, AndesDropdownSize.MEDIUM, "test")
        Assert.assertEquals(dropdown.size, AndesDropdownSize.MEDIUM)

        dropdown = AndesDropDownStandalone(context, AndesDropdownMenuType.BOTTOMSHEET, AndesDropdownSize.LARGE, "test")
        Assert.assertEquals(dropdown.size, AndesDropdownSize.LARGE)
    }

    @Test
    fun `test dropdown form`() {
        var label = "label"
        var helper = "helper"
        var placeHolder = "placeHolder"

        val dropdown = AndesDropDownForm(context, AndesDropdownMenuType.BOTTOMSHEET, label, helper, placeHolder)
        Assert.assertEquals(dropdown.label, label)
        Assert.assertEquals(dropdown.helper, helper)
        Assert.assertEquals(dropdown.placeholder, placeHolder)

        label = "label2"
        helper = "helper2"
        placeHolder = "placeHolder2"

        Assert.assertEquals(dropdown.label, label)
        Assert.assertEquals(dropdown.helper, helper)
        Assert.assertEquals(dropdown.placeholder, placeHolder)
    }

}