package com.mercadolibre.android.andesui.bottomsheet

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.content.Context
import android.graphics.drawable.GradientDrawable
import android.os.Bundle
import android.support.constraint.ConstraintLayout
import android.support.design.widget.BottomSheetBehavior
import android.support.design.widget.CoordinatorLayout
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.content.res.ResourcesCompat
import android.util.AttributeSet
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import android.widget.TextView
import com.mercadolibre.android.andesui.R
import com.mercadolibre.android.andesui.bottomsheet.factory.AndesBottomSheetAttrs
import com.mercadolibre.android.andesui.bottomsheet.factory.AndesBottomSheetAttrsParser
import com.mercadolibre.android.andesui.bottomsheet.factory.AndesBottomSheetConfiguration
import com.mercadolibre.android.andesui.bottomsheet.factory.AndesBottomSheetConfigurationFactory
import com.mercadolibre.android.andesui.bottomsheet.state.AndesBottomSheetState
import com.mercadolibre.android.andesui.bottomsheet.title.AndesBottomSheetTitleAlignment
import com.mercadolibre.android.andesui.typeface.getFontOrDefault
import com.mercadolibre.android.andesui.utils.pxToDp

@Suppress("TooManyFunctions")
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
                updatePeekHeight(it)
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

    /**
     * Getter and Setter for [bottomSheetTitleText], if this value is null or an empty string no title will be shown
     */
    var titleText: String?
        get() = andesBottomSheetAttrs.andesBottomSheetTitleText
        set(value) {
            andesBottomSheetAttrs = andesBottomSheetAttrs.copy(andesBottomSheetTitleText = value)
            createConfig().also {
                resolveTitleViewText(it)
            }
        }

    /**
     * Getter and Setter for [bottomSheetTitleAlignment], alternatives: centered and left_aligned
     */
    var titleAlignment: AndesBottomSheetTitleAlignment
        get() = andesBottomSheetAttrs.andesBottomSheetTitleAlignment
        set(value) {
            andesBottomSheetAttrs = andesBottomSheetAttrs.copy(andesBottomSheetTitleAlignment = value)
            createConfig().also {
                resolveTitleViewAlignment(it)
            }
    }

    /**
     * Getter and setter for [isBackgroundDimEnabled], this determines if a background filter is shown (with animation)
     * when the bottomSheet is expanded
     */
    var isBackgroundDimEnabled: Boolean
        get() = andesBottomSheetAttrs.andesBottomSheetBackgroundDim
        set(value) {
            andesBottomSheetAttrs = andesBottomSheetAttrs.copy(andesBottomSheetBackgroundDim = value)
            createConfig().also{
                resolveBackgroundDim(it)
            }
        }

    private lateinit var andesBottomSheetAttrs: AndesBottomSheetAttrs
    private lateinit var containerView: FrameLayout
    private lateinit var frameView: FrameLayout
    private lateinit var bottomSheetBehavior: BottomSheetBehavior<FrameLayout>
    private lateinit var titleTextView: TextView
    private lateinit var backgroundDimView: View
    private var listener: BottomSheetListener? = null

    constructor(context: Context) : super(context) {
        initAttrs(
                DEFAULT_PEEK_HEIGHT,
                DEFAULT_CORNER_RADIUS.pxToDp(context),
                DEFAULT_BOTTOM_SHEET_STATE,
                DEFAULT_TITLE,
                DEFAULT_TITLE_ALIGNMENT,
                DEFAULT_BACKGROUND_DIM
        )
    }

    @Suppress("LongParameterList")
    constructor(
        context: Context,
        peekHeight: Int,
        cornerRadius: Int,
        state: AndesBottomSheetState,
        title: String,
        titleAlignment: AndesBottomSheetTitleAlignment,
        isBackgroundDimEnabled: Boolean
    ) : super(context) {
        initAttrs(peekHeight, cornerRadius, state, title, titleAlignment, isBackgroundDimEnabled)
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
        bottomSheetState: AndesBottomSheetState,
        titleText: String?,
        titleAlignment: AndesBottomSheetTitleAlignment,
        isBackgroundDimEnabled: Boolean
    ) {
        andesBottomSheetAttrs =
                AndesBottomSheetAttrs(
                        peekHeight,
                        cornerRadius,
                        bottomSheetState,
                        titleText,
                        titleAlignment,
                        isBackgroundDimEnabled
                )

        setupComponents(createConfig())
    }

    private fun createConfig() = AndesBottomSheetConfigurationFactory.create(andesBottomSheetAttrs)

    private fun setupComponents(config: AndesBottomSheetConfiguration) {

        initComponents()

        setupViewId()

        resolveBottomSheetParams()
        resolveBottomSheetBackground(config)
        initBottomSheetBehavior()
        resolveBottomSheetState(config)
        resolveTitleViewText(config)
        resolveTitleViewAlignment(config)

        updatePeekHeight(config)
    }

    /**
     * Creates all the views which are part of this bottomSheet and sets them an Id.
     */
    private fun initComponents() {
        val layout = LayoutInflater.from(context).inflate(R.layout.andes_layout_bottom_sheet, this)
        containerView = layout.findViewById(R.id.andes_bottom_sheet_container)
        frameView = layout.findViewById(R.id.andes_bottom_sheet_frame_view)
        titleTextView = layout.findViewById(R.id.andes_bottom_sheet_title)
        backgroundDimView = layout.findViewById(R.id.andes_bottom_sheet_background_dim)
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
        val params = containerView.layoutParams as LayoutParams
        params.behavior = BottomSheetBehavior<FrameLayout>()
    }

    private fun resolveBottomSheetBackground(config: AndesBottomSheetConfiguration) {
        val cornerRadius = config.cornerRadius.toFloat()
        val shape = GradientDrawable()
        shape.shape = GradientDrawable.RECTANGLE
        shape.cornerRadii = floatArrayOf(cornerRadius, cornerRadius, cornerRadius, cornerRadius, 0f, 0f, 0f, 0f)
        shape.setColor(ResourcesCompat.getColor(resources, R.color.andes_white, context.theme))

        containerView.background = shape
    }

    private fun initBottomSheetBehavior() {
        bottomSheetBehavior = BottomSheetBehavior.from(containerView)
        bottomSheetBehavior.setBottomSheetCallback(bottomSheetBehaviorCallback)
        bottomSheetBehavior.onLayoutChild(this, containerView, View.LAYOUT_DIRECTION_LTR)
    }

    private fun resolveBottomSheetState(config: AndesBottomSheetConfiguration) {
        when (config.state) {
            AndesBottomSheetState.EXPANDED -> bottomSheetBehavior.state = BottomSheetBehavior.STATE_EXPANDED
            AndesBottomSheetState.COLLAPSED -> bottomSheetBehavior.state = BottomSheetBehavior.STATE_COLLAPSED
        }
    }

    private fun resolveTitleViewText(config: AndesBottomSheetConfiguration) {
        if (config.titleText.isNullOrEmpty()) {
            titleTextView.visibility = View.GONE
            return
        }

        titleTextView.visibility = View.VISIBLE
        titleTextView.text = config.titleText
        titleTextView.typeface = context.getFontOrDefault(R.font.andes_font_semibold)
    }

    private fun resolveTitleViewAlignment(config: AndesBottomSheetConfiguration) {
        when (config.titleAlignment) {
            AndesBottomSheetTitleAlignment.CENTERED -> titleTextView.gravity = Gravity.CENTER
            AndesBottomSheetTitleAlignment.LEFT_ALIGN -> titleTextView.gravity = Gravity.START
        }
    }

    private fun updatePeekHeight(config: AndesBottomSheetConfiguration) {
        bottomSheetBehavior.peekHeight = config.peekHeight
    }

    private fun resolveBackgroundDim(config: AndesBottomSheetConfiguration) {
        if (!config.isBackgroundDimEnabled) return
        backgroundDimView.setOnClickListener { collapse() }

        when(state) {
            AndesBottomSheetState.EXPANDED -> showBackgroundDim()
            AndesBottomSheetState.COLLAPSED -> hideBackgroundDim()
        }
    }

    private fun showBackgroundDim() {
        backgroundDimView.visibility = View.VISIBLE
        backgroundDimView.animate()
                ?.alpha(DIM_MAX_ALPHA)
                ?.setDuration(DIM_ANIMATION_TIME.toLong())
                ?.setListener(null)
    }

    private fun hideBackgroundDim() {
        backgroundDimView.animate()
                ?.alpha(DIM_MIN_ALPHA)
                ?.setDuration(DIM_ANIMATION_TIME.toLong())
                ?.setListener(object : AnimatorListenerAdapter() {
                    override fun onAnimationEnd(animation: Animator) {
                        backgroundDimView.visibility = View.GONE
                    }
                })
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
     * Sets listener to the onCollpase and onExpand events.
     *
     * @param listener the events listener
     */
    fun setBottomSheetListener(listener: BottomSheetListener) {
        this.listener = listener
    }

    private val bottomSheetBehaviorCallback: BottomSheetBehavior.BottomSheetCallback = object : BottomSheetBehavior.BottomSheetCallback() {
        override fun onStateChanged(bottomSheet: View, newState: Int) {
            when (newState) {
                BottomSheetBehavior.STATE_HIDDEN -> {
                    // not used
                }
                BottomSheetBehavior.STATE_EXPANDED -> {
                    listener?.onExpanded().also {
                        updateStateFromBehavior(newState)
                        resolveBackgroundDim(createConfig())
                    }
                }
                BottomSheetBehavior.STATE_COLLAPSED -> {
                    listener?.onCollapsed().also {
                        updateStateFromBehavior(newState)
                        resolveBackgroundDim(createConfig())
                    }
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

        private fun updateStateFromBehavior(bottomSheetBehaviorState: Int) {
            when(bottomSheetBehaviorState) {
                BottomSheetBehavior.STATE_EXPANDED -> state = AndesBottomSheetState.EXPANDED
                BottomSheetBehavior.STATE_COLLAPSED -> state = AndesBottomSheetState.COLLAPSED
            }
        }
    }

    companion object {
        private const val DEFAULT_PEEK_HEIGHT = 0
        private const val DEFAULT_CORNER_RADIUS = 6
        private const val DEFAULT_BACKGROUND_DIM = false
        private const val DIM_MAX_ALPHA = 1f
        private const val DIM_MIN_ALPHA = 0f
        private const val DIM_ANIMATION_TIME = 150
        private val DEFAULT_BOTTOM_SHEET_STATE = AndesBottomSheetState.COLLAPSED
        private val DEFAULT_TITLE = null // no title
        private val DEFAULT_TITLE_ALIGNMENT = AndesBottomSheetTitleAlignment.CENTERED
    }
}
