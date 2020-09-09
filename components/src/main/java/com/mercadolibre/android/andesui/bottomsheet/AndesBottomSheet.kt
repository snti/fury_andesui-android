package com.mercadolibre.android.andesui.bottomsheet

import android.content.Context
import android.os.Bundle
import android.support.design.widget.BottomSheetBehavior
import android.support.design.widget.BottomSheetBehavior.BottomSheetCallback
import android.support.design.widget.CoordinatorLayout
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.util.AttributeSet
import android.view.View
import android.widget.FrameLayout
import com.mercadolibre.android.andesui.R

class AndesBottomSheet : CoordinatorLayout {
    private var bottomSheetBehavior: BottomSheetBehavior<FrameLayout>? = null
//    private var presenter = BottomSheetComponentPresenter()
    private var peekHeight = 0

    private var listener: BottomSheetListener? = null

    constructor(context: Context) : super(context) {
        initView()
    }
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        initView()
    }
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        initView()
    }

    private fun initView() {
        View.inflate(context, R.layout.andes_layout_bottom_sheet, this)
        val bottomSheetView = findViewById<FrameLayout>(R.id.bottom_sheet_frame_layout)
        bottomSheetBehavior = BottomSheetBehavior.from(bottomSheetView)
        bottomSheetBehavior?.setBottomSheetCallback(bottomSheetBehaviorCallback)
//        bottomSheetBehavior?.peekHeight = DEFAULT_PEAK_HEIGHT
    }

    /**
     * Sets the desired fragment inside of the bottomSheet
     *
     * @param supportFragmentManager the supportFragmentManager
     * @param fragment the fragment to be shown, should be
     * @param bundle the info bundle for the fragment
     */
    fun <T> setFragment(supportFragmentManager: FragmentManager, fragment: T, bundle: Bundle? = null)
            where T : Fragment {
        fragment.arguments = bundle
        supportFragmentManager
                .beginTransaction()
                .replace(R.id.bottom_sheet_frame_layout, fragment)
                .commit()
        // TODO: review this, should we pass supportFragmentManager as param?
    }

    /**
     * Sets the peekHeight of the BottomSheet. This is how much of the view is seen when collpased in px.
     *
     * @param peekHeight visible portion on collapsed in px
     */
    fun setPeekHeight(peekHeight: Int) {
        this.peekHeight = peekHeight
        updateBottomSheetPeekHeight()
    }

    /**
     * Set listener to the onCollpase and onExpand events.
     *
     * @param listener the events listener
     */
    fun setBottomSheetListener(listener: BottomSheetListener) {
//        presenter.onSetListener(listener)
        this.listener = listener
    }

    /**
     * Fully Expands the BottomSheet
     */
    fun expand() {
        bottomSheetBehavior?.state = BottomSheetBehavior.STATE_EXPANDED
    }

    /**
     * Collapses the BottomSheet
     */
    fun collapse() {
        bottomSheetBehavior?.state = BottomSheetBehavior.STATE_COLLAPSED
    }

    // todo: add javadoc
    fun getState(): Int? {
        return bottomSheetBehavior?.state
    }

    private fun updateBottomSheetPeekHeight() {
        if (bottomSheetBehavior != null) {
            bottomSheetBehavior!!.peekHeight = peekHeight ?: return
        }
    }

    private val bottomSheetBehaviorCallback: BottomSheetCallback = object : BottomSheetCallback() {
        override fun onStateChanged(bottomSheet: View, newState: Int) {
            when (newState) {
                BottomSheetBehavior.STATE_HIDDEN -> {
                    // not used
                }
                BottomSheetBehavior.STATE_EXPANDED -> {
                    listener?.onExpanded()
                }
                BottomSheetBehavior.STATE_COLLAPSED -> {
                    listener?.onCollapsed()
                }
                BottomSheetBehavior.STATE_DRAGGING -> {
                    // not used
                }
                BottomSheetBehavior.STATE_SETTLING -> {
                    // not used
                }
                BottomSheetBehavior.STATE_HALF_EXPANDED -> {
                    // not used
                }
            }
        }

        override fun onSlide(bottomSheet: View, slideOffset: Float) {
            // not used
        }
    }

}