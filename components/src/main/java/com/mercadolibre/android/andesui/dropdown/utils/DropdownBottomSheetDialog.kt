package com.mercadolibre.android.andesui.dropdown.utils

import android.content.Context
import android.graphics.drawable.GradientDrawable
import android.os.Bundle
import android.view.View
import android.widget.FrameLayout
import androidx.core.content.res.ResourcesCompat
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.mercadolibre.android.andesui.R
import com.mercadolibre.android.andesui.utils.ScreenUtils

class DropdownBottomSheetDialog(
        context: Context,
        theme: Int
) : BottomSheetDialog(context, theme) {
    private var containerView: FrameLayout? = null
    private var dragIndicator: View? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        containerView = findViewById(R.id.andes_bottom_sheet_container)
        dragIndicator = findViewById(R.id.andes_bottom_sheet_drag_indicator)

        initBottomSheetBehavior()
        resolveDragIndicator()
        resolveBottomSheetBackground()
    }

    private fun resolveDragIndicator() {
        val cornerRadius = context.resources.getDimension(R.dimen.andes_bottom_sheet_drag_indicator_corner_radius)
        val shape = GradientDrawable()
        shape.shape = GradientDrawable.RECTANGLE
        shape.setColor(ResourcesCompat.getColor(context.resources, R.color.andes_gray_250, context.theme))
        shape.cornerRadius = cornerRadius

        dragIndicator?.background = shape
    }

    private fun resolveBottomSheetBackground() {
        val cornerRadius = context.resources.getDimension(R.dimen.andes_bottom_sheet_default_radius)
        val shape = GradientDrawable()
        shape.shape = GradientDrawable.RECTANGLE
        shape.cornerRadii = floatArrayOf(cornerRadius, cornerRadius, cornerRadius, cornerRadius, 0f, 0f, 0f, 0f)
        shape.setColor(ResourcesCompat.getColor(context.resources, R.color.andes_white, context.theme))

        containerView?.background = shape
    }

    private fun initBottomSheetBehavior() {
        val bottomSheet = findViewById<FrameLayout>(com.google.android.material.R.id.design_bottom_sheet)
        val behavior: BottomSheetBehavior<*> = BottomSheetBehavior.from(bottomSheet)
        behavior.peekHeight = ScreenUtils.getScreenHeight() / 2 // TODO mejorar
    }

}