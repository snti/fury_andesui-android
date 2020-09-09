package com.mercadolibre.android.andesui.carousel

import com.mercadolibre.android.andesui.carousel.factory.AndesCarouselAttrs
import com.mercadolibre.android.andesui.carousel.factory.AndesCarouselConfigurationFactory
import org.mockito.Mockito
import org.robolectric.RuntimeEnvironment

class AndesCarouselTest {

    private var context = RuntimeEnvironment.application
    private val configFactory = Mockito.spy(AndesCarouselConfigurationFactory)
    private lateinit var attrs: AndesCarouselAttrs

}