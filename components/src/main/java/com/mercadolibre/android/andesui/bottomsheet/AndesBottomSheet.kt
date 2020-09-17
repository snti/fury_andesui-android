package com.mercadolibre.android.andesui.bottomsheet

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.os.Bundle
import android.support.constraint.ConstraintLayout
import android.support.design.widget.BottomSheetBehavior
import android.support.design.widget.BottomSheetBehavior.BottomSheetCallback
import android.support.design.widget.CoordinatorLayout
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.util.AttributeSet
import android.view.View
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import android.widget.FrameLayout
import com.mercadolibre.android.andesui.bottomsheet.factory.AndesBottomSheetAttrs
import com.mercadolibre.android.andesui.bottomsheet.factory.AndesBottomSheetAttrsParser
import com.mercadolibre.android.andesui.bottomsheet.factory.AndesBottomSheetConfiguration
import com.mercadolibre.android.andesui.bottomsheet.factory.AndesBottomSheetConfigurationFactory
import com.mercadolibre.android.andesui.bottomsheet.state.AndesBottomSheetState

class AndesBottomSheet : CoordinatorLayout {

    /**
     * Getter and Setter fo [peekHeight] of the BottomSheet.
     * This is how much of the view is seen when collapsed in px.
     */
    var peekHeight: Int
        get() = andesBottomSheetAttrs.andesBottomSheetPeekHeight
        set(value) {
            andesBottomSheetAttrs = andesBottomSheetAttrs.copy(andesBottomSheetPeekHeight = value)
            createConfig().also {
                updatePeekHeight()
            }
        }

    /**
     * Getter and Setter for [cornerRadius] of the bottomSheet
     */
    var cornerRadius: Int
        get() = andesBottomSheetAttrs.andesBottomSheetCornerRadius
        set(value) {
            andesBottomSheetAttrs = andesBottomSheetAttrs.copy(andesBottomSheetCornerRadius = value)
            createConfig().also {
                resolveBottomSheetBackground(it)
            }
        }

    /**
     * Getter and Setter for [state]
     */
    var state: AndesBottomSheetState
        get() = andesBottomSheetAttrs.andesBottomSheetState
        set(value) {
            andesBottomSheetAttrs = andesBottomSheetAttrs.copy(andesBottomSheetState = value)
            createConfig().also {
                resolveBottomSheetState(it)
            }
        }

    private lateinit var andesBottomSheetAttrs: AndesBottomSheetAttrs
    private lateinit var frameView: FrameLayout
    private lateinit var bottomSheetBehavior: BottomSheetBehavior<FrameLayout>
    private var listener: BottomSheetListener? = null

    constructor(context: Context) : super(context) {
        initAttrs(DEFAULT_PEEK_HEIGHT, DEFAULT_CORNER_RADIUS, DEFAULT_BACKGROUND_COLOR, DEFAULT_BOTTOM_SHEET_STATE)
    }

    constructor(
        context: Context,
        peekHeight: Int,
        cornerRadius: Int,
        backgroundColor: Int,
        bottomSheetState: AndesBottomSheetState
    ) : super(context) {
        initAttrs(peekHeight, cornerRadius, backgroundColor, bottomSheetState)
    }

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        initAttrs(attrs)
    }

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : this(context, attrs)

    private fun initAttrs(attrs: AttributeSet?) {
        andesBottomSheetAttrs = AndesBottomSheetAttrsParser.parse(context, attrs)
        setupComponents(createConfig())
    }

    private fun initAttrs(
        peekHeight: Int,
        cornerRadius: Int,
        backgroundColor: Int,
        bottomSheetState: AndesBottomSheetState
    ) {
        andesBottomSheetAttrs = AndesBottomSheetAttrs(peekHeight, cornerRadius, backgroundColor, bottomSheetState)
        setupComponents(createConfig())
    }

    private fun createConfig() = AndesBottomSheetConfigurationFactory.create(andesBottomSheetAttrs)

    private fun setupComponents(config: AndesBottomSheetConfiguration) {

        initComponents()

        setupViewId()

        addView(frameView)

        resolveBottomSheetParams()
        resolveBottomSheetBackground(config)
        initBottomSheetBehavior()
        resolveBottomSheetState(config)

        updatePeekHeight()
    }

    /**
     * Creates all the views which are part of this bottomSheet and sets them an Id.
     */
    private fun initComponents() {
        frameView = FrameLayout(context)
        frameView.id = View.generateViewId()
    }

    /**
     * Sets a view id to this bottomSheet.
     *
     */
    private fun setupViewId() {
        if (id == ConstraintLayout.NO_ID) { // If this view has no id
            id = View.generateViewId()
        }
    }

    private fun resolveBottomSheetParams() {
        val params = frameView.layoutParams as LayoutParams
        params.behavior = BottomSheetBehavior<FrameLayout>()
        params.height = WRAP_CONTENT
        params.width = MATCH_PARENT
    }

    private fun resolveBottomSheetBackground(config: AndesBottomSheetConfiguration) {
        val cornerRadius = config.cornerRadius.toFloat()
        val shape = GradientDrawable()
        shape.shape = GradientDrawable.RECTANGLE
        shape.cornerRadii = floatArrayOf(cornerRadius, cornerRadius, cornerRadius, cornerRadius, 0f, 0f, 0f, 0f)
        shape.setColor(config.backgroundColor)

        frameView.background = shape
    }

    private fun initBottomSheetBehavior() {
        bottomSheetBehavior = BottomSheetBehavior.from(frameView)
        bottomSheetBehavior.setBottomSheetCallback(bottomSheetBehaviorCallback)
        bottomSheetBehavior.onLayoutChild(this, frameView, View.LAYOUT_DIRECTION_LTR)
    }

    private fun resolveBottomSheetState(config: AndesBottomSheetConfiguration) {
        when (config.state) {
            AndesBottomSheetState.EXPANDED -> bottomSheetBehavior.state = BottomSheetBehavior.STATE_EXPANDED
            AndesBottomSheetState.COLLAPSED -> bottomSheetBehavior.state = BottomSheetBehavior.STATE_COLLAPSED
        }
    }

    private fun updatePeekHeight() {
        bottomSheetBehavior.peekHeight = peekHeight
    }

    /**
     * Sets the background color of the bottomSheet
     */
    override fun setBackgroundColor(color: Int) {
        andesBottomSheetAttrs = andesBottomSheetAttrs.copy(andesBottomSheetBackgroundColor = color)
        createConfig().also {
            resolveBottomSheetBackground(it)
        }
    }

    /**
     * Sets the desired fragment inside of the bottomSheet
     *
     * @param supportFragmentManager the supportFragmentManager
     * @param fragment the fragment to be shown, should be
     * @param bundle the info bundle for the fragment
     */
    fun <T> setFragment(fragmentManager: FragmentManager, fragment: T, bundle: Bundle? = null)
            where T : Fragment {
        if (bundle != null) {
            fragment.arguments = bundle
        }
        fragmentManager
                .beginTransaction()
                .replace(frameView.id, fragment)
                .commit()
    }

    /**
     * Sets a view inside the bottomSheet
     */
    fun setView(view: View) {
        frameView.addView(view)
    }

    /**
     * Removes all views from FrameLayout inside of the bottomSheet
     */
    fun removeViews() {
        if (frameView.childCount > 0) {
            frameView.removeAllViews()
        }
    }

    /**
     * Fully expands this bottomSheet
     */
    fun expand() {
        state = AndesBottomSheetState.EXPANDED
    }

    /**
     * Collapses this bottomSheet
     */
    fun collapse() {
        state = AndesBottomSheetState.COLLAPSED
    }

    /**
     * Set listener to the onCollpase and onExpand events.
     *
     * @param listener the events listener
     */
    fun setBottomSheetListener(listener: BottomSheetListener) {
        this.listener = listener
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

    companion object {
        private const val DEFAULT_PEEK_HEIGHT = 0
        private const val DEFAULT_CORNER_RADIUS = 0
        private const val DEFAULT_BACKGROUND_COLOR = Color.TRANSPARENT
        private val DEFAULT_BOTTOM_SHEET_STATE = AndesBottomSheetState.COLLAPSED
    }
}
