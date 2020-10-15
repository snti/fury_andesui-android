package com.mercadolibre.android.andesui.carousel

import android.content.Context
import android.support.constraint.ConstraintLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.PagerSnapHelper
import android.support.v7.widget.RecyclerView
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import com.mercadolibre.android.andesui.R
import com.mercadolibre.android.andesui.carousel.factory.AndesCarouselAttrParser
import com.mercadolibre.android.andesui.carousel.factory.AndesCarouselAttrs
import com.mercadolibre.android.andesui.carousel.factory.AndesCarouselConfiguration
import com.mercadolibre.android.andesui.carousel.factory.AndesCarouselConfigurationFactory
import com.mercadolibre.android.andesui.carousel.padding.AndesCarouselPadding
import com.mercadolibre.android.andesui.carousel.utils.AndesCarouselAdapter
import com.mercadolibre.android.andesui.carousel.utils.AndesCarouselDelegate
import com.mercadolibre.android.andesui.carousel.utils.AndesCarouselPaddingItemDecoration

class AndesCarousel : ConstraintLayout {

    private lateinit var andesCarouselAttrs: AndesCarouselAttrs
    private lateinit var recyclerViewComponent: RecyclerView
    private lateinit var viewManager: LinearLayoutManager
    private lateinit var andesCarouselDelegate: AndesCarouselDelegate
    private lateinit var paddingItemDecoration: AndesCarouselPaddingItemDecoration
    private lateinit var snapHelper: PagerSnapHelper

    /**
     * Getter and setter for [padding].
     */
    var padding: AndesCarouselPadding
        get() = andesCarouselAttrs.andesCarouselPadding
        set(value) {
            andesCarouselAttrs = andesCarouselAttrs.copy(andesCarouselPadding = value)
            setupPaddingRecyclerView(createConfig())
        }

    /**
     * Getter and setter for [center].
     */
    var center: Boolean
        get() = andesCarouselAttrs.andesCarouselCenter
        set(value) {
            andesCarouselAttrs = andesCarouselAttrs.copy(andesCarouselCenter = value)
            setupCenterRecyclerView(createConfig())
        }

    /**
     * Getter and setter for [delegate].
     */
    var delegate: AndesCarouselDelegate
        get() = andesCarouselDelegate
        set(value) {
            andesCarouselDelegate = value
            val carouselAdapter = AndesCarouselAdapter(this, value)
            recyclerViewComponent.adapter = carouselAdapter
        }

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        initAttrs(attrs)
    }

    constructor(
        context: Context,
        center: Boolean = false,
        padding: AndesCarouselPadding = AndesCarouselPadding.SMALL
    ) : super(context) {
        initAttrs(center, padding)
    }

    /**
     * Sets the proper [config] for this component based on the [attrs] received via XML.
     *
     * @param attrs attributes from the XML.
     */
    private fun initAttrs(attrs: AttributeSet?) {
        andesCarouselAttrs = AndesCarouselAttrParser.parse(context, attrs)
        setupComponents(createConfig())
    }

    private fun initAttrs(
        center: Boolean,
        padding: AndesCarouselPadding
    ) {
        andesCarouselAttrs = AndesCarouselAttrs(center, padding)
        setupComponents(createConfig())
    }

    /**
     * Responsible for setting up all properties of each component that is part of this andesCarousel.
     * Is like a choreographer 😉
     */
    private fun setupComponents(config: AndesCarouselConfiguration) {
        initComponents()
        setupViewId()
        updateDynamicComponents()
        updateComponentsAlignment(config)
    }

    /**
     * Creates all the views that are part of this andesCarousel.
     * After a view is created then a view id is added to it.
     */
    private fun initComponents() {
        val container = LayoutInflater.from(context).inflate(R.layout.andes_layout_carousel, this)
        recyclerViewComponent = container.findViewById(R.id.andes_carousel_recyclerview)
        paddingItemDecoration = AndesCarouselPaddingItemDecoration(createConfig().padding)
        snapHelper = PagerSnapHelper()
    }

    /**
     * Update recyclerview
     */
    private fun updateDynamicComponents() {
        setupRecyclerViewComponent()
    }

    /**
     * Update attributes of recyclerview
     */
    private fun updateComponentsAlignment(config: AndesCarouselConfiguration) {
        setupCenterRecyclerView(config)
        setupPaddingRecyclerView(config)
    }

    /**
     * Set recyclerview
     */
    private fun setupRecyclerViewComponent() {
        viewManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        recyclerViewComponent.layoutManager = viewManager
        recyclerViewComponent.overScrollMode = View.OVER_SCROLL_NEVER
        recyclerViewComponent.clipToPadding = false
    }

    /**
     * Gets data from the config and sets to center of this carousel.
     */
    private fun setupCenterRecyclerView(config: AndesCarouselConfiguration) {
        if (config.center) {
            snapHelper.attachToRecyclerView(recyclerViewComponent)
        } else {
            snapHelper.attachToRecyclerView(null)
        }
    }

    /**
     * Gets data from the config and sets to the padding of this carousel.
     */
    private fun setupPaddingRecyclerView(config: AndesCarouselConfiguration) {
        if (recyclerViewComponent.itemDecorationCount != 0) {
            recyclerViewComponent.removeItemDecoration(paddingItemDecoration)
        }

        paddingItemDecoration = AndesCarouselPaddingItemDecoration(config.padding)
        recyclerViewComponent.addItemDecoration(paddingItemDecoration)

        val padding = getPaddingRecyclerView()
        recyclerViewComponent.setPadding(padding, 0, padding, 0)
    }

    /**
     * Sets a view id to this andesCarousel.
     */
    private fun setupViewId() {
        if (id == NO_ID) { // If this view has no id
            id = View.generateViewId()
        }
    }

    private fun createConfig() = AndesCarouselConfigurationFactory.create(context, andesCarouselAttrs)

    /**
     * Define percentage of padding
     */
    private fun getPaddingRecyclerView() =
        if (andesCarouselAttrs.andesCarouselPadding != AndesCarouselPadding.NONE)
            (context.resources.displayMetrics.widthPixels * PERCENTAGE).toInt()
        else
           ZERO

    companion object {
        const val PERCENTAGE = 0.10
        const val ZERO = 0
    }
}
