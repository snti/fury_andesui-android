package com.mercadolibre.android.andesui.coachmark.view

import android.content.Context
import android.graphics.*
import android.support.v4.content.ContextCompat
import android.util.AttributeSet
import android.view.View
import com.mercadolibre.android.andesui.R
import com.mercadolibre.android.andesui.coachmark.utils.ViewUtils

internal class CoachmarkOverlay @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    private val paintFill: Paint = Paint()
    private val paintStroke: Paint = Paint()
    private var rectF = mutableListOf<RectF>()
    private var radius: Int = ViewUtils.dpToPx(RADIUS)
    private val padding = ViewUtils.dpToPx(PADDING)
    private var isCircle: Boolean = false
    private var x: Int = 0
    private var y: Int = 0

    init {
        isDrawingCacheEnabled = true
        setLayerType(LAYER_TYPE_SOFTWARE, null)
        paintFill.isAntiAlias = true
        paintFill.color = Color.TRANSPARENT
        paintFill.xfermode = PorterDuffXfermode(PorterDuff.Mode.CLEAR)
        paintStroke.strokeWidth = ViewUtils.dpToPx(PADDING_BORDER).toFloat()
        paintStroke.style = Paint.Style.STROKE
        paintStroke.color = ContextCompat.getColor(context, R.color.andes_accent_color)
    }

    fun addRect(x: Int, y: Int, width: Int, height: Int, isCircle: Boolean, radius: Int = ViewUtils.dpToPx(RADIUS)) {

        this.radius = radius
        this.isCircle = isCircle
        this.x = x
        this.y = y

        val r = x + width
        val b = y + height

        rectF.add(RectF(
            (x - padding).toFloat(),
            (y - padding).toFloat(),
            (r + padding).toFloat(),
            (b + padding).toFloat()
        ))
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        if (isCircle) {
            canvas.drawCircle(x.toFloat(), y.toFloat(), radius.toFloat(), paintFill)
            canvas.drawCircle(x.toFloat(), y.toFloat(), radius.toFloat(), paintStroke)
        } else {
            for (rect: RectF in rectF) {
                canvas.drawRoundRect(rect, radius.toFloat(), radius.toFloat(), paintFill)
                canvas.drawRoundRect(rect, radius.toFloat(), radius.toFloat(), paintStroke)
            }
        }
    }

    fun clear() {
        rectF.clear()
        isCircle = false
    }

    companion object {
        const val PADDING = 12
        const val PADDING_BORDER = 3
        const val RADIUS = 8
    }
}
