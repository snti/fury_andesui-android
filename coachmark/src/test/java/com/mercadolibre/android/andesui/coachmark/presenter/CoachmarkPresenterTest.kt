package com.mercadolibre.android.andesui.coachmark.presenter

import android.graphics.Rect
import android.os.Build
import android.support.v4.widget.NestedScrollView
import android.view.View
import com.mercadolibre.android.andesui.BuildConfig
import com.mercadolibre.android.andesui.coachmark.model.AndesWalkthroughCoachmark
import com.mercadolibre.android.andesui.coachmark.model.AndesWalkthroughCoachmarkStep
import com.mercadolibre.android.andesui.coachmark.model.WalkthroughMessagePosition
import com.nhaarman.mockitokotlin2.verifyNoMoreInteractions
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(constants = BuildConfig::class, sdk = [Build.VERSION_CODES.LOLLIPOP])
class CoachmarkPresenterTest {

    @Mock
    private val view = Mockito.mock(CoachmarkViewInterface::class.java)
    @Mock
    private val viewReference = Mockito.mock(View::class.java)
    @Mock
    private val scrollView = Mockito.mock(NestedScrollView::class.java)

    lateinit var coachmarkModel: AndesWalkthroughCoachmark

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)

        val stepsNewCoachmark = ArrayList<AndesWalkthroughCoachmarkStep>()
        stepsNewCoachmark.add(AndesWalkthroughCoachmarkStep("Primer titulo", "Resaltamos el primer texto", "Siguiente", viewReference, "rectangle"))
        stepsNewCoachmark.add(AndesWalkthroughCoachmarkStep("Segundo titulo", "Probando el circulo magico con flecha abajo a la izquierda Probando el circulo magico con flecha abajo a la izquierda Probando el circulo magico con flecha abajo a la izquierda", "Siguiente", viewReference, "circle"))
        stepsNewCoachmark.add(AndesWalkthroughCoachmarkStep("Tercer titulo ", "Resaltamos el primer texto", "Siguiente", viewReference, "rectangle"))
        stepsNewCoachmark.add(AndesWalkthroughCoachmarkStep("Cuarto titulo ", "Probando el circulo magico con flecha abajo a la derecha", "Siguiente", viewReference, "circle"))
        stepsNewCoachmark.add(AndesWalkthroughCoachmarkStep("Quinto titulo ", "Resaltamos el texto largo Resaltamos el texto largo Resaltamos el texto largo Resaltamos el texto largo Resaltamos el texto largo Resaltamos el texto largo Resaltamos el texto largo Resaltamos el texto largo Resaltamos el texto largo Resaltamos el texto largo Resaltamos el texto largo", "Siguiente", viewReference, "rectangle"))
        stepsNewCoachmark.add(AndesWalkthroughCoachmarkStep("Sexto titulo ", "Si vemos esto es porque scrolleo al fin y estamos al final del coachmark ;)", "Siguiente", viewReference, "rectangle"))
        stepsNewCoachmark.add(AndesWalkthroughCoachmarkStep("Septimo titulo ", "Probando el circulo magico con flecha arriba a la izquierda", "Siguiente", viewReference, "circle"))
        stepsNewCoachmark.add(AndesWalkthroughCoachmarkStep("Octavo titulo ", "Probando el circulo magico con flecha arriba a la derecha Probando el circulo magico con flecha arriba a la derecha Probando el circulo magico con flecha arriba a la derecha Probando el circulo magico con flecha arriba a la derecha", "Siguiente", viewReference, "circle"))
        stepsNewCoachmark.add(AndesWalkthroughCoachmarkStep("Noveno titulo ", "Volvimos abajo", "Siguiente", viewReference, "rectangle"))
        stepsNewCoachmark.add(AndesWalkthroughCoachmarkStep("Decimo titulo ", "Probando scroll hacia arriba", "Siguiente", viewReference, "rectangle"))
        stepsNewCoachmark.add(AndesWalkthroughCoachmarkStep("Undecimo titulo ", "Esto sigue en prueba y esta bueno que funcione bien", "Siguiente", viewReference, "rectangle"))

        coachmarkModel = AndesWalkthroughCoachmark(stepsNewCoachmark, scrollView) {
            println("Entro al despues de cerrar")
        }
    }

    @Test
    fun `resolveScrollMode - Draw bottom WalkthroughMessage with space for draw WalkthroughMessage stephighlight full viewed`() {

        val stepReferenceHitRect = Rect(48, 48, 418, 121)
        val stepReferenceGlobalRect = Rect(48, 288, 418, 361)
        val bodyGlobalRect = Rect(0, 240, 1080, 1632)
        val tooltipHeigh = 404


        Mockito.`when`(viewReference.height).thenReturn(73)
        Mockito.`when`(viewReference.getLocalVisibleRect(bodyGlobalRect)).thenReturn(true)

        val presenter = CoachmarkPresenter(view)
        presenter.resolveScrollMode(
            coachmarkModel.steps[0],
            HEIGHT_SCREEN,
            stepReferenceHitRect,
            stepReferenceGlobalRect,
            bodyGlobalRect,
            tooltipHeigh,
            WalkthroughMessagePosition.BELOW
        )

        verify(view).animateScroll(true, 224, coachmarkModel.steps[0])
        verifyNoMoreInteractions(view)
    }

    @Test
    fun `resolveScrollMode - Draw Above WalkthroughMessage with stephighlight not viewed`() {

        val stepReferenceHitRect = Rect(48, 1316, 408, 1676)
        val stepReferenceGlobalRect = Rect(48, 1556, 408, 1916)
        val bodyGlobalRect = Rect(0, 240, 1080, 1632)
        val tooltipHeigh = 680


        Mockito.`when`(viewReference.height).thenReturn(360)
        Mockito.`when`(viewReference.getLocalVisibleRect(bodyGlobalRect)).thenReturn(true)

        val presenter = CoachmarkPresenter(view)
        presenter.resolveScrollMode(
            coachmarkModel.steps[1],
            HEIGHT_SCREEN,
            stepReferenceHitRect,
            stepReferenceGlobalRect,
            bodyGlobalRect,
            tooltipHeigh,
            WalkthroughMessagePosition.ABOVE
        )

        verify(view).getFooterHeigh()
        verify(view).setScrollViewPaddings(0, 0, 0, 16)
        verify(view).animateScroll(false, 572, coachmarkModel.steps[1])
        verifyNoMoreInteractions(view)
    }

    companion object {
        const val HEIGHT_SCREEN = 1392
    }
}