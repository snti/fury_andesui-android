package com.mercadolibre.android.andesui.coachmark.presenter

import android.graphics.Rect
import com.mercadolibre.android.andesui.coachmark.model.AndesWalkthroughCoachmarkStep
import com.mercadolibre.android.andesui.coachmark.model.WalkthroughMessagePosition
import com.mercadolibre.android.andesui.coachmark.utils.ViewUtils

internal class CoachmarkPresenter(private val view: CoachmarkViewInterface) {

    /**
     * Verifica si la vista a resaltar esta visible o no, para realzar el scroll
     *
     * @param stepReferenced es el step a resaltar
     * @param heightScreen es el alto de la pantalla
     * @param stepReferenceHitRect informacion sobre la vista a resaltar
     * @param stepReferenceGlobalRect informacion sobre la vista a resaltar
     * @param bodyGlobalRect informacion el overlay
     * @param tooltipHeight el alto del tooltip
     * @param tooltipPosition la posicion del tooltip
     */
    fun resolveScrollMode(
        stepReferenced: AndesWalkthroughCoachmarkStep,
        heightScreen: Int,
        stepReferenceHitRect: Rect,
        stepReferenceGlobalRect: Rect,
        bodyGlobalRect: Rect,
        tooltipHeight: Int,
        tooltipPosition: WalkthroughMessagePosition
    ) {

        stepReferenced.highlightedView?.let {
            val isBodyMoreBottom = bodyGlobalRect.bottom < stepReferenceGlobalRect.bottom
            if (isBodyMoreBottom || !it.getLocalVisibleRect(bodyGlobalRect) || bodyGlobalRect.height() < it.height) {
                resolvePartialOrNotViewedReferenceView(stepReferenced, heightScreen, stepReferenceHitRect, tooltipHeight, tooltipPosition)
            } else {
                resolveCompleteReferenceView(stepReferenced, heightScreen, stepReferenceGlobalRect, tooltipHeight, tooltipPosition)
            }
        }
    }

    /**
     * Casos en el que el stepReference no este visible o este parcialmente visible
     */
    private fun resolvePartialOrNotViewedReferenceView(
        stepReferenced: AndesWalkthroughCoachmarkStep,
        heightScreen: Int,
        targetRect: Rect,
        tooltipHeight: Int,
        tooltipPosition: WalkthroughMessagePosition
    ) {

        var scrollToY = 0
        var paddingScrollView = ViewUtils.dpToPx(SCROLL_VIEW_PADDING)

        when {
            isAbove(tooltipPosition) -> {
                scrollToY = targetRect.top - ViewUtils.dpToPx(ViewUtils.TOOLBAR_SIZE) - tooltipHeight
                paddingScrollView += view.getFooterHeigh()
                view.setScrollViewPaddings(0, 0, 0, paddingScrollView)
            }
            isBelow(tooltipPosition) -> {
                scrollToY = targetRect.bottom -
                    ViewUtils.dpToPx(ViewUtils.TOOLBAR_SIZE) -
                    heightScreen +
                    tooltipHeight +
                    view.getFooterHeigh()
            }
        }
        view.animateScroll(false, scrollToY, stepReferenced)
    }

    /**
     * Casos en el que esta visible el elemento
     */
    private fun resolveCompleteReferenceView(
        stepReferenced: AndesWalkthroughCoachmarkStep,
        heightScreen: Int,
        targetRect: Rect,
        tooltipHeight: Int,
        tooltipPosition: WalkthroughMessagePosition
    ) {

        var scrollToY = targetRect.top - ViewUtils.dpToPx(ViewUtils.TOOLBAR_SIZE)
        var paddingScrollView = ViewUtils.dpToPx(SCROLL_VIEW_PADDING)
        val spaceBottomReferenceView = heightScreen + ViewUtils.dpToPx(ViewUtils.TOOLBAR_SIZE) - targetRect.bottom

        when {
            // Cuando el tooltip no tiene lugar para ser posicionado arriba
            isAbove(tooltipPosition) && targetRect.top - ViewUtils.dpToPx(ViewUtils.TOOLBAR_SIZE) < tooltipHeight -> {
                val spaceTopReferenceView = targetRect.top - ViewUtils.dpToPx(ViewUtils.TOOLBAR_SIZE)
                scrollToY -= tooltipHeight
                paddingScrollView += tooltipHeight - spaceTopReferenceView
                view.setScrollViewPaddings(0, paddingScrollView, 0, 0)
                view.animateScroll(false, scrollToY, stepReferenced)
                return

            }
            // Cuando el tooltip no tiene lugar para ser posicionado debajo
            isBelow(tooltipPosition) && spaceBottomReferenceView < tooltipHeight -> {
                scrollToY += tooltipHeight - spaceBottomReferenceView
                view.setScrollViewPaddings(0, 0, 0, view.getFooterHeigh())
                view.animateScroll(false, scrollToY, stepReferenced)
                return
            }
        }
        view.animateScroll(true, scrollToY, stepReferenced)
    }

    fun addRect(stepReferenced: AndesWalkthroughCoachmarkStep) {
        if (stepReferenced.style == CIRCLE) {
            view.addCircleRect(stepReferenced)
        } else {
            view.addRoundRect(stepReferenced)
        }
    }

    /**
     * Coloca al tooltip en la posicion de la vista a referenciar (arriba o debajo)
     */
    fun relocateTooltip(
        stepReferenced: AndesWalkthroughCoachmarkStep,
        tooltipHeight: Int,
        tooltipPosition: WalkthroughMessagePosition
    ) {

        val padding = ViewUtils.dpToPx(TOOLTIP_MARGIN)

        val rect = Rect()
        stepReferenced.highlightedView?.getGlobalVisibleRect(rect)
        val height = rect.height()

        val y = rect.top
        // setY
        if (isBelow(tooltipPosition)) {
            view.setWalkthroughMessageViewY((y + height + padding).toFloat())
        } else if (isAbove(tooltipPosition)) {
            view.setWalkthroughMessageViewY((y - tooltipHeight - padding).toFloat())
        }
    }

    /**
     * Luego de cambiar de vista a referenciar se limpian los valores
     */
    fun restorePreviousValues() {

        view.cleanCoachmarkOverlayView()
        view.setWalkthroughMessageViewY(0F)

        // Clean scrollview
        view.setScrollViewPaddings(0, 0, 0, 0)
    }

    /**
     * retorna true si el tooltip debe ser posicionado arriba de la vista a referenciar
     */
    private fun isAbove(tooltipPosition: WalkthroughMessagePosition?): Boolean {
        return WalkthroughMessagePosition.ABOVE == tooltipPosition
    }

    /**
     * retorna true si el tooltip debe ser posicionado abajo de la vista a referenciar
     */
    private fun isBelow(tooltipPosition: WalkthroughMessagePosition?): Boolean {
        return WalkthroughMessagePosition.BELOW == tooltipPosition
    }

    companion object {
        const val ANIMATION_TOOLTIP_DURARION = 500L
        const val ANIMATION_OVERLAY_DURATION = 400L
        const val ANIMATION_SCROLL_DURATION = 1000L
        const val TOOLTIP_MARGIN = 24
        const val SCROLL_VIEW_PADDING = 16
        const val CIRCLE = "circle"
    }
}