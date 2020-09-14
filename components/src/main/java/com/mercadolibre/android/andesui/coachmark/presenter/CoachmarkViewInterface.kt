package com.mercadolibre.android.andesui.coachmark.presenter

import com.mercadolibre.android.andesui.coachmark.model.AndesWalkthroughCoachmarkStep

internal interface CoachmarkViewInterface {

    fun cleanCoachmarkOverlayView()

    fun setWalkthroughMessageViewY(positionY: Float)

    fun setScrollViewPaddings(left: Int, top: Int, right: Int, bottom: Int)

    fun addTarget(stepReferenced: AndesWalkthroughCoachmarkStep)

    fun scrollTo(scrollToY: Int)

    fun animateScroll(isVisible: Boolean, scrollToY: Int, stepReferenced: AndesWalkthroughCoachmarkStep)

    fun getFooterHeigh(): Int

    fun addRoundRect(stepReferenced: AndesWalkthroughCoachmarkStep)

    fun addCircleRect(stepReferenced: AndesWalkthroughCoachmarkStep)
}