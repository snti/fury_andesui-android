package com.mercadolibre.android.andesui.coachmark.view.walkthroughmessage

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.RectF
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
    private var radius = ViewUtils.dpToPx(RADIUS_ARROW)
    private var startTooltipAngle = ANGLE_90
    private var startTargetAngle = ANGLE_270
    private var sweepAngle = ANGLE_90

    // About line between arcs
    private val linePosition = mutableListOf<Float>() // [0] xStart - yStart - xEnd - yEnd

    // Vars about head of arrow
    private var startHeadArrowX: Float = START_POINT
    private var startY: Float = START_POINT
    private var endLeftX: Float = START_POINT
    private var endLeftY: Float = START_POINT
    private var endRightX: Float = START_POINT
    private var endRightY: Float = START_POINT
    private val lengthHeadArrow = ViewUtils.dpToPx(ARROW_LENGHT)
    private var lengthRadioHeadArrow = ViewUtils.dpToPx(ARROW_LENGHT / NUMBER_TWO)

    init {
        mPaint.style = Paint.Style.STROKE       // set to STOKE
        mPaint.strokeCap = Paint.Cap.ROUND      // set the paint cap to round too
        mPaint.strokeWidth = ViewUtils.dpToPx(STROKE)
        mPaint.isAntiAlias = true
        mPaint.color = ContextCompat.getColor(context, R.color.andes_white)
        rectTooltipF = RectF()
        rectTargetF = RectF()
    }

    fun addRect(xTooltip: Int, yTooltip: Int, xTarget: Int, yTarget: Int) {

        // flecha derecha abajo
        if (xTooltip < xTarget && yTooltip < yTarget) {
            setBottomRightArrow(xTooltip, yTooltip, xTarget)

            // flecha izquierda abajo
        } else if (xTarget < xTooltip && yTooltip < yTarget) {
            setBottomLeftArrow(xTooltip, yTooltip, xTarget)

            // flecha derecha arriba
        } else if (xTooltip < xTarget && yTarget < yTooltip) {
            setTopRightArrow(xTooltip, yTooltip, xTarget)

        // flecha izquierda arriba
        } else {
            setTopLeftArrow(xTooltip, yTooltip, xTarget)
        }
    }

    fun setBottomRightArrow(xTooltip: Int, yTooltip: Int, xTarget: Int) {

        startTooltipAngle = ANGLE_90
        startTargetAngle = ANGLE_270

        rectTooltipF = RectF(
            xTooltip.toFloat(),
            yTooltip.toFloat() - radius,
            xTooltip.toFloat() + (NUMBER_TWO * radius),
            yTooltip.toFloat() + radius)

        rectTargetF = RectF(
            xTarget.toFloat() - (NUMBER_TWO * radius),
            yTooltip.toFloat() + radius,
            xTarget.toFloat(),
            yTooltip.toFloat() + (NUMBER_THREE * radius))

        linePosition.add(xTooltip.toFloat() + radius)
        linePosition.add(yTooltip.toFloat() + radius)
        linePosition.add(xTarget.toFloat() - radius)
        linePosition.add(yTooltip.toFloat() + radius)

        startHeadArrowX = xTarget.toFloat()
        startY = yTooltip.toFloat() + (NUMBER_TWO * radius)
        endLeftX = xTarget.toFloat() - lengthRadioHeadArrow - ViewUtils.dpToPx(SMALL_X_NEW_POSITION)
        endLeftY = yTooltip.toFloat() + (NUMBER_TWO * radius) - lengthHeadArrow + ViewUtils.dpToPx(LARGE_Y_NEW_POSITION)
        endRightX = xTarget.toFloat() + lengthRadioHeadArrow - ViewUtils.dpToPx(LARGE_X_NEW_POSITION)
        endRightY = yTooltip.toFloat() + (NUMBER_TWO * radius) - lengthHeadArrow - ViewUtils.dpToPx(SMALL_Y_NEW_POSITION)
    }

    fun setBottomLeftArrow(xTooltip: Int, yTooltip: Int, xTarget: Int) {

        startTooltipAngle = ANGLE_0
        startTargetAngle = ANGLE_180

        rectTooltipF = RectF(
            xTooltip.toFloat() - (NUMBER_TWO * radius),
            yTooltip.toFloat() - radius,
            xTooltip.toFloat(),
            yTooltip.toFloat() + radius)

        rectTargetF = RectF(
            xTarget.toFloat(),
            yTooltip.toFloat() + radius,
            xTarget + (NUMBER_TWO * radius),
            yTooltip.toFloat() + (NUMBER_THREE * radius))

        linePosition.add(xTarget.toFloat() + radius)
        linePosition.add(yTooltip.toFloat() + radius)
        linePosition.add(xTooltip.toFloat() - radius)
        linePosition.add(yTooltip.toFloat() + radius)

        startHeadArrowX = xTarget.toFloat()
        startY = yTooltip.toFloat() + (NUMBER_TWO * radius)
        endLeftX = xTarget.toFloat() - lengthRadioHeadArrow + ViewUtils.dpToPx(LARGE_X_NEW_POSITION)
        endLeftY = yTooltip.toFloat() + (NUMBER_TWO * radius) - lengthHeadArrow - ViewUtils.dpToPx(SMALL_Y_NEW_POSITION)
        endRightX = xTarget.toFloat() + lengthRadioHeadArrow + ViewUtils.dpToPx(SMALL_X_NEW_POSITION)
        endRightY = yTooltip.toFloat() + (NUMBER_TWO * radius) - lengthHeadArrow + ViewUtils.dpToPx(LARGE_Y_NEW_POSITION)
    }

    fun setTopRightArrow(xTooltip: Int, yTooltip: Int, xTarget: Int) {

        startTooltipAngle = ANGLE_180
        startTargetAngle = ANGLE_0

        rectTooltipF = RectF(
            xTooltip.toFloat(),
            yTooltip.toFloat() - radius,
            xTooltip.toFloat() + (NUMBER_TWO * radius),
            yTooltip.toFloat() + radius)

        rectTargetF = RectF(
            xTarget.toFloat() - (NUMBER_TWO * radius),
            yTooltip.toFloat() - (NUMBER_THREE * radius),
            xTarget.toFloat(),
            yTooltip.toFloat() - radius)

        linePosition.add(xTooltip.toFloat() + radius)
        linePosition.add(yTooltip.toFloat() - radius)
        linePosition.add(xTarget.toFloat() - radius)
        linePosition.add(yTooltip.toFloat() - radius)

        startHeadArrowX = xTarget.toFloat()
        startY = yTooltip.toFloat() - (NUMBER_TWO * radius)
        endLeftX = xTarget.toFloat() - lengthRadioHeadArrow - ViewUtils.dpToPx(SMALL_X_NEW_POSITION)
        endLeftY = yTooltip.toFloat() - (NUMBER_TWO * radius) + lengthHeadArrow - ViewUtils.dpToPx(LARGE_Y_NEW_POSITION)
        endRightX = xTarget.toFloat() + lengthRadioHeadArrow - ViewUtils.dpToPx(LARGE_X_NEW_POSITION)
        endRightY = yTooltip.toFloat() - (NUMBER_TWO * radius) + lengthHeadArrow + ViewUtils.dpToPx(SMALL_Y_NEW_POSITION)
    }

    fun setTopLeftArrow(xTooltip: Int, yTooltip: Int, xTarget: Int) {

        startTooltipAngle = ANGLE_270
        startTargetAngle = ANGLE_90

        rectTooltipF = RectF(
            xTooltip.toFloat() - (NUMBER_TWO * radius),
            yTooltip.toFloat() - radius,
            xTooltip.toFloat(),
            yTooltip.toFloat() + radius)

        rectTargetF = RectF(
            xTarget.toFloat(),
            yTooltip.toFloat() - (NUMBER_THREE * radius),
            xTarget.toFloat() + (NUMBER_TWO * radius),
            yTooltip.toFloat() - radius)

        linePosition.add(xTarget.toFloat() + radius)
        linePosition.add(yTooltip.toFloat() - radius)
        linePosition.add(xTooltip.toFloat() - radius)
        linePosition.add(yTooltip.toFloat() - radius)

        startHeadArrowX = xTarget.toFloat()
        startY = yTooltip.toFloat() - (NUMBER_TWO * radius)
        endLeftX = xTarget.toFloat() - lengthRadioHeadArrow + ViewUtils.dpToPx(LARGE_X_NEW_POSITION)
        endLeftY = yTooltip.toFloat() - (NUMBER_TWO * radius) + lengthHeadArrow + ViewUtils.dpToPx(SMALL_Y_NEW_POSITION)
        endRightX = xTarget.toFloat() + lengthRadioHeadArrow + ViewUtils.dpToPx(SMALL_X_NEW_POSITION)
        endRightY = yTooltip.toFloat() - (NUMBER_TWO * radius) + lengthHeadArrow - ViewUtils.dpToPx(LARGE_Y_NEW_POSITION)
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        if (linePosition.isNotEmpty()) {

            // arc start on tooltip
            canvas.drawArc(rectTooltipF, startTooltipAngle, sweepAngle, false, mPaint)

            // arc start on highlighted view
            canvas.drawArc(rectTargetF, startTargetAngle, sweepAngle, false, mPaint)

            // line between arcs
            canvas.drawLine(linePosition[0], linePosition[NUMBER_ONE], linePosition[NUMBER_TWO], linePosition[NUMBER_THREE], mPaint)

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

    companion object {
        const val NUMBER_ONE = 1
        const val NUMBER_TWO = 2
        const val NUMBER_THREE = 3
        const val ARROW_LENGHT = 10
        const val START_POINT = 0F
        const val RADIUS_ARROW = 24F
        const val ANGLE_0 = 0F
        const val ANGLE_90 = 90F
        const val ANGLE_180 = 180F
        const val ANGLE_270 = 270F
        const val STROKE = 1F

        const val LARGE_X_NEW_POSITION = 3.26F
        const val SMALL_X_NEW_POSITION = 2.66F
        const val LARGE_Y_NEW_POSITION = 2.23F
        const val SMALL_Y_NEW_POSITION = 1.21F
    }
}
