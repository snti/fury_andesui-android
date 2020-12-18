package com.mercadolibre.android.andesui.dropdown.utils

import android.content.Context
import android.content.DialogInterface
import android.graphics.drawable.GradientDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.LinearLayout
import androidx.core.content.res.ResourcesCompat
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.mercadolibre.android.andesui.R
import com.mercadolibre.android.andesui.list.AndesList
import com.mercadolibre.android.andesui.list.utils.AndesListDelegate
import kotlinx.android.synthetic.main.andes_layout_test_bs.*

class DropdownBottomSheetDialog : BottomSheetDialogFragment() {
    internal var mDelegate: AndesListDelegate? = null
    private lateinit var dragIndicator: View
    private lateinit var containerView: LinearLayout
    private lateinit var andesList: AndesList

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.andes_layout_test_bs, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpViews(view)
    }

    private fun setUpViews(view: View) {
        dragIndicator = view.findViewById(R.id.andes_bottom_sheet_drag_indicator)
        containerView = view.findViewById(R.id.andes_bottom_sheet_container)
        andesList = view.findViewById(R.id.andesListDropdown)

        if (mDelegate != null) {
            andesList.delegate = mDelegate!!
        } else {
            throw RuntimeException("$context must implement AndesListDelegate")
        }



        resolveDragIndicator()
//        resolveBottomSheetBackground()
    }

    override fun onDismiss(dialog: DialogInterface?) {
        super.onDismiss(dialog)

    }

    override fun onDetach() {
        super.onDetach()
        mDelegate = null
    }
//
//    override fun onAttach(context: Context) {
//        super.onAttach(context)
//        if (context is AndesListDelegate) {
//            mDelegate = context
//        } else {
//            throw RuntimeException("$context must implement AndesListDelegate")
//        }
//    }

    companion object {
        @JvmStatic
        fun newInstance(bundle: Bundle): DropdownBottomSheetDialog {
            val fragment = DropdownBottomSheetDialog()
            fragment.arguments = bundle
            return fragment
        }
    }

    private fun resolveDragIndicator() {
        context?.let { safeContext ->

            val cornerRadius = safeContext.resources.getDimension(R.dimen.andes_bottom_sheet_drag_indicator_corner_radius)
            val shape = GradientDrawable()
            shape.shape = GradientDrawable.RECTANGLE
            shape.setColor(ResourcesCompat.getColor(resources, R.color.andes_gray_250, safeContext.theme))
            shape.cornerRadius = cornerRadius

            dragIndicator.background = shape
        }
    }

//    private fun resolveBottomSheetBackground() {
//        context?.let { safeContext ->
//
//            val cornerRadius = safeContext.resources.getDimension(R.dimen.andes_bottom_sheet_default_radius)
//            val shape = GradientDrawable()
//            shape.shape = GradientDrawable.RECTANGLE
//            shape.cornerRadii = floatArrayOf(cornerRadius, cornerRadius, cornerRadius, cornerRadius, 0f, 0f, 0f, 0f)
//            shape.setColor(ResourcesCompat.getColor(resources, R.color.andes_white, safeContext.theme))
//
//            containerView.background = shape
//        }
//    }


}