package com.mercadolibre.android.andesui.carousel

import android.os.Build
import android.view.View
import com.mercadolibre.android.andesui.BuildConfig
import com.mercadolibre.android.andesui.R
import com.mercadolibre.android.andesui.carousel.factory.AndesCarouselAttrs
import com.mercadolibre.android.andesui.carousel.factory.AndesCarouselConfigurationFactory
import com.mercadolibre.android.andesui.carousel.padding.AndesCarouselPadding
import com.mercadolibre.android.andesui.carousel.utils.AndesCarouselDelegate
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito.spy
import org.robolectric.RobolectricTestRunner
import org.robolectric.RuntimeEnvironment
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(constants = BuildConfig::class, sdk = [Build.VERSION_CODES.LOLLIPOP])
class AndesCarouselTest {

    private var context = RuntimeEnvironment.application
    private val configFactory = spy(AndesCarouselConfigurationFactory)
    private lateinit var attrs: AndesCarouselAttrs

    @Test
    fun `test padding none` () {
        attrs = AndesCarouselAttrs(
            CENTER,
            AndesCarouselPadding.NONE
        )
        val config = configFactory.create(context, attrs)
        Assert.assertEquals(config.padding, context.resources.getDimension(R.dimen.andes_carousel_padding_none).toInt())
    }

    @Test
    fun `test padding small` () {
        attrs = AndesCarouselAttrs(
                CENTER,
                AndesCarouselPadding.SMALL
        )
        val config = configFactory.create(context, attrs)
        Assert.assertEquals(config.padding, context.resources.getDimension(R.dimen.andes_carousel_padding_small).toInt())
    }

    @Test
    fun `test padding medium` () {
        attrs = AndesCarouselAttrs(
                CENTER,
                AndesCarouselPadding.MEDIUM
        )
        val config = configFactory.create(context, attrs)
        Assert.assertEquals(config.padding, context.resources.getDimension(R.dimen.andes_carousel_padding_medium).toInt())
    }

    @Test
    fun `test padding large` () {
        attrs = AndesCarouselAttrs(
                CENTER,
                AndesCarouselPadding.LARGE
        )
        val config = configFactory.create(context, attrs)
        Assert.assertEquals(config.padding, context.resources.getDimension(R.dimen.andes_carousel_padding_large).toInt())
    }

    @Test
    fun `test center true` () {
        attrs = AndesCarouselAttrs(
                CENTER,
                AndesCarouselPadding.NONE
        )
        val config = configFactory.create(context, attrs)
        Assert.assertEquals(config.center, true)
    }

    @Test
    fun `test center false` () {
        attrs = AndesCarouselAttrs(
                FREE,
                AndesCarouselPadding.NONE
        )
        val config = configFactory.create(context, attrs)
        Assert.assertEquals(config.center, false)
    }

    @Test
    fun `test delegate` () {
        val andesCarouselDelegate = object : AndesCarouselDelegate {
            override fun bind(andesCarouselView: AndesCarousel, view: View, position: Int) {}

            override fun onClickItem(andesCarouselView: AndesCarousel, position: Int) {}

            override fun getDataSetSize(andesCarouselView: AndesCarousel) = SIZE

            override fun getLayoutItem(andesCarouselView: AndesCarousel) = LAYOUT

        }
        val carousel = AndesCarousel(context, true, AndesCarouselPadding.NONE)
        carousel.delegate = andesCarouselDelegate
        Assert.assertNotNull(carousel.delegate)

        Assert.assertEquals(SIZE, andesCarouselDelegate.getDataSetSize(carousel))
        Assert.assertEquals(LAYOUT, andesCarouselDelegate.getLayoutItem(carousel))
    }

    @Test
    fun `test carousel with different padding` () {
        var carousel = AndesCarousel(context, true, AndesCarouselPadding.NONE)
        Assert.assertEquals(carousel.padding, AndesCarouselPadding.NONE)

        carousel = AndesCarousel(context, true, AndesCarouselPadding.SMALL)
        Assert.assertEquals(carousel.padding, AndesCarouselPadding.SMALL)

        carousel = AndesCarousel(context, true, AndesCarouselPadding.MEDIUM)
        Assert.assertEquals(carousel.padding, AndesCarouselPadding.MEDIUM)

        carousel = AndesCarousel(context, true, AndesCarouselPadding.LARGE)
        Assert.assertEquals(carousel.padding, AndesCarouselPadding.LARGE)
    }

    companion object {
        private const val CENTER = true
        private const val FREE = false
        private const val SIZE = 0
        private const val LAYOUT = 0
    }

}