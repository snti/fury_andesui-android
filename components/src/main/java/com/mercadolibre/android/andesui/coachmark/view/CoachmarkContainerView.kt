package com.mercadolibre.android.andesui.coachmark.view

import android.content.Context
import android.support.constraint.ConstraintLayout
import android.util.AttributeSet
import com.mercadolibre.android.andesui.R
import kotlinx.android.synthetic.main.andes_walkthrough_container.view.*

class CoachmarkContainerView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    private var listener: CoachmarkContainerListener? = null

    init {
        inflate(context, R.layout.andes_walkthrough_container, this)
    }

    fun setListener(l: CoachmarkContainerListener) {
        listener = l
    }

    fun setData(buttonText: String, position: Int, size: Int) {
        // counterText.typeface = context.getFontOrDefault(R.font.andes_font_regular)
        nextButton.text = buttonText
        nextButton.setOnClickListener { listener?.onClickNextButton(position) }
        closeButton.setOnClickListener { listener?.onClickClose(position) }
        counterText.text = context.resources.getString(R.string.andes_coachmark_header_numeration_of, position + 1, size)
    }

    interface CoachmarkContainerListener {
        fun onClickNextButton(position: Int)
        fun onClickClose(position: Int)
    }
}