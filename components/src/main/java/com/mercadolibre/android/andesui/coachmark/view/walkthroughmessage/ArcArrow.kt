package com.mercadolibre.android.andesui.coachmark.view.walkthroughmessage

import android.content.Context
import android.graphics.*
import android.support.v4.content.ContextCompat
import android.util.AttributeSet
import android.view.View
import com.mercadolibre.android.andesui.R
import com.mercadolibre.android.andesui.coachmark.utils.ViewUtils

class ArcArrow @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    private val mPaint: Paint = Paint()

    //About arcs
    private var rectTooltipF: RectF
    private var rectTargetF: RectF
    private var radius = ViewUtils.dpToPx(24).toFloat()
    private var startTooltipAngle = 90F
    private var startTargetAngle = 270F
    private var sweepAngle = 90F

    // About line between arcs
    private val linePosition = mutableListOf<Float>() // [0] xStart - yStart - xEnd - yEnd

    // Vars about head of arrow
    private var startHeadArrowX: Float = 0F
    private var startY: Float = 0F
    private var endLeftX: Float = 0F
    private var endLeftY: Float = 0F
    private var endRightX: Float = 0F
    private var endRightY: Float = 0F
    private val lengthHeadArrow = ViewUtils.dpToPx(10)
    private var lengthRadioHeadArrow = ViewUtils.dpToPx(5)

    init {
        mPaint.style = Paint.Style.STROKE       // set to STOKE
        mPaint.strokeCap = Paint.Cap.ROUND      // set the paint cap to round too
        mPaint.strokeWidth = ViewUtils.dpToPx(1).toFloat()
        mPaint.isAntiAlias = true
        mPaint.color = ContextCompat.getColor(context, R.color.andes_white)
        rectTooltipF = RectF()
        rectTargetF = RectF()
    }

    fun addRect(xTooltip: Int, yTooltip: Int, xTarget: Int, yTarget: Int) {

        // flecha derecha abajo
        if (xTooltip < xTarget && yTooltip < yTarget) {
            startTooltipAngle = 90F
            startTargetAngle = 270F

            rectTooltipF = RectF(
                xTooltip.toFloat(),
                yTooltip.toFloat() - radius,
                xTooltip.toFloat() + (2 * radius),
                yTooltip.toFloat() + radius)

            rectTargetF = RectF(
                xTarget.toFloat() - (2 * radius),
                yTooltip.toFloat() + radius,
                xTarget.toFloat(),
                yTooltip.toFloat() + (3 * radius))

            linePosition.add(xTooltip.toFloat() + radius)
            linePosition.add(yTooltip.toFloat() + radius)
            linePosition.add(xTarget.toFloat() - radius)
            linePosition.add(yTooltip.toFloat() + radius)

            startHeadArrowX = xTarget.toFloat()
            startY = yTooltip.toFloat() + (2 * radius)
            endLeftX = xTarget.toFloat() - lengthRadioHeadArrow - ViewUtils.dpToPx(2.66F)
            endLeftY = yTooltip.toFloat() + (2 * radius) - lengthHeadArrow + ViewUtils.dpToPx(2.23F)
            endRightX = xTarget.toFloat() + lengthRadioHeadArrow - ViewUtils.dpToPx(3.26F)
            endRightY = yTooltip.toFloat() + (2 * radius) - lengthHeadArrow - ViewUtils.dpToPx(1.21F)

        // flecha izquierda abajo
        } else if (xTarget < xTooltip && yTooltip < yTarget) {
            startTooltipAngle = 0F
            startTargetAngle = 180F

            rectTooltipF = RectF(
                xTooltip.toFloat() - (2 * radius),
                yTooltip.toFloat() - radius,
                xTooltip.toFloat(),
                yTooltip.toFloat() + radius)

            rectTargetF = RectF(
                xTarget.toFloat(),
                yTooltip.toFloat() + radius,
                xTarget + (2 * radius),
                yTooltip.toFloat() + (3 * radius))

            linePosition.add(xTarget.toFloat() + radius)
            linePosition.add(yTooltip.toFloat() + radius)
            linePosition.add(xTooltip.toFloat() - radius)
            linePosition.add(yTooltip.toFloat() + radius)

            startHeadArrowX = xTarget.toFloat()
            startY = yTooltip.toFloat() + (2 * radius)
            endLeftX = xTarget.toFloat() - lengthRadioHeadArrow + ViewUtils.dpToPx(3.26F)
            endLeftY = yTooltip.toFloat() + (2 * radius) - lengthHeadArrow - ViewUtils.dpToPx(1.21F)
            endRightX = xTarget.toFloat() + lengthRadioHeadArrow + ViewUtils.dpToPx(2.66F)
            endRightY = yTooltip.toFloat() + (2 * radius) - lengthHeadArrow + ViewUtils.dpToPx(2.23F)

        // flecha derecha arriba
        } else if (xTooltip < xTarget && yTarget < yTooltip) {
            startTooltipAngle = 180F
            startTargetAngle = 0F

            rectTooltipF = RectF(
                xTooltip.toFloat(),
                yTooltip.toFloat() - radius,
                xTooltip.toFloat() + (2 * radius),
                yTooltip.toFloat() + radius)

            rectTargetF = RectF(
                xTarget.toFloat() - (2 * radius),
                yTooltip.toFloat() - (3 * radius),
                xTarget.toFloat(),
                yTooltip.toFloat() - radius)

            linePosition.add(xTooltip.toFloat() + radius)
            linePosition.add(yTooltip.toFloat() - radius)
            linePosition.add(xTarget.toFloat() - radius)
            linePosition.add(yTooltip.toFloat() - radius)

            startHeadArrowX = xTarget.toFloat()
            startY = yTooltip.toFloat() - (2 * radius)
            endLeftX = xTarget.toFloat() - lengthRadioHeadArrow - ViewUtils.dpToPx(2.66F)
            endLeftY = yTooltip.toFloat() - (2 * radius) + lengthHeadArrow - ViewUtils.dpToPx(2.23F)
            endRightX = xTarget.toFloat() + lengthRadioHeadArrow - ViewUtils.dpToPx(3.26F)
            endRightY = yTooltip.toFloat() - (2 * radius) + lengthHeadArrow + ViewUtils.dpToPx(1.21F)

        // flecha izquierda arriba
        } else {
            startTooltipAngle = 270F
            startTargetAngle = 90F

            rectTooltipF = RectF(
                xTooltip.toFloat() - (2 * radius),
                yTooltip.toFloat() - radius,
                xTooltip.toFloat(),
                yTooltip.toFloat() + radius)

            rectTargetF = RectF(
                xTarget.toFloat(),
                yTooltip.toFloat() - (3 * radius),
                xTarget.toFloat() + (2 * radius),
                yTooltip.toFloat() - radius)

            linePosition.add(xTarget.toFloat() + radius)
            linePosition.add(yTooltip.toFloat() - radius)
            linePosition.add(xTooltip.toFloat() - radius)
            linePosition.add(yTooltip.toFloat() - radius)

            startHeadArrowX = xTarget.toFloat()
            startY = yTooltip.toFloat() - (2 * radius)
            endLeftX = xTarget.toFloat() - lengthRadioHeadArrow + ViewUtils.dpToPx(3.26F)
            endLeftY = yTooltip.toFloat() - (2 * radius) + lengthHeadArrow + ViewUtils.dpToPx(1.21F)
            endRightX = xTarget.toFloat() + lengthRadioHeadArrow + ViewUtils.dpToPx(2.66F)
            endRightY = yTooltip.toFloat() - (2 * radius) + lengthHeadArrow - ViewUtils.dpToPx(2.23F)
        }
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        if (linePosition.isNotEmpty()) {

            // arc start on tooltip
            canvas.drawArc(rectTooltipF, startTooltipAngle, sweepAngle, false, mPaint)

            // arc start on highlighted view
            canvas.drawArc(rectTargetF, startTargetAngle, sweepAngle, false, mPaint)

            // line between arcs
            canvas.drawLine(linePosition[0], linePosition[1], linePosition[2], linePosition[3], mPaint)

            // head arrow
            canvas.drawLine(startHeadArrowX, startY, endLeftX, endLeftY, mPaint)
            canvas.drawLine(startHeadArrowX, startY, endRightX, endRightY, mPaint)
        }
    }

    fun clear() {
        rectTooltipF = RectF()
        rectTargetF = RectF()
        linePosition.clear()
    }
}