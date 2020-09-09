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
import com.mercadolibre.android.andesui.carousel.utils.CarouselAdapter
import com.mercadolibre.android.andesui.carousel.utils.PaddingItemDecoration
import com.mercadolibre.android.andesui.carousel.utils.ViewHolderListener

class AndesCarousel : ConstraintLayout {

    private lateinit var andesCarouselAttrs: AndesCarouselAttrs
    private lateinit var recyclerViewComponent: RecyclerView
    private lateinit var carouselAdapter: CarouselAdapter
    private lateinit var viewManager: LinearLayoutManager

    /**
     * Getter and setter for [type].
     */
    var padding: AndesCarouselPadding
        get() = andesCarouselAttrs.andesCarouselPadding
        set(value) {
            andesCarouselAttrs = andesCarouselAttrs.copy(andesCarouselPadding = value)
            setupPaddingRecyclerView(createConfig())
        }

    var layout: Int
        get() = andesCarouselAttrs.andesCarouselItemLayout
        set(value) {
            andesCarouselAttrs = andesCarouselAttrs.copy(andesCarouselItemLayout = value)
            carouselAdapter.updateLayoutItem(createConfig().layout)
        }

    var data: List<Any>
        get() = andesCarouselAttrs.andesCarouselDataSet
        set(value) {
            andesCarouselAttrs = andesCarouselAttrs.copy(andesCarouselDataSet = value)
            carouselAdapter.updateDataSet(value)
        }

    fun setViewHolderListener(listener: ViewHolderListener) {
        carouselAdapter.setViewHolderListener(listener)
    }

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        initAttrs(attrs)
    }

    constructor(
        context: Context,
        center: Boolean = false,
        padding: AndesCarouselPadding = AndesCarouselPadding.SMALL,
        layout: Int
    ) : super(context) {
        initAttrs(center, padding, layout)
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
        padding: AndesCarouselPadding,
        layout: Int
    ) {
        andesCarouselAttrs = AndesCarouselAttrs(center, layout, padding)
        setupComponents(createConfig())
    }

    /**
     * Responsible for setting up all properties of each component that is part of this andesCarousel.
     * Is like a choreographer 😉
     */
    private fun setupComponents(config: AndesCarouselConfiguration) {
        initComponents()
        setupViewId()
        updateDynamicComponents(config)
        updateComponentsAlignment(config)
    }

    /**
     * Creates all the views that are part of this andesCarousel.
     * After a view is created then a view id is added to it.
     */
    private fun initComponents() {
        val container = LayoutInflater.from(context).inflate(R.layout.andes_layout_carousel, this)
        recyclerViewComponent = container.findViewById(R.id.andes_carousel_recyclerview)
    }

    private fun updateDynamicComponents(config: AndesCarouselConfiguration) {
        setupRecyclerViewComponent(config)
    }

    private fun updateComponentsAlignment(config: AndesCarouselConfiguration) {
        setupPaddingRecyclerView(config)
    }

    private fun setupRecyclerViewComponent(config: AndesCarouselConfiguration) {
        viewManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        recyclerViewComponent.layoutManager = viewManager
        carouselAdapter = CarouselAdapter(config.layout, config.dataSet)
        recyclerViewComponent.adapter = carouselAdapter

        if (config.center) {
            PagerSnapHelper().attachToRecyclerView(recyclerViewComponent)
        }

        recyclerViewComponent.overScrollMode = View.OVER_SCROLL_NEVER
        recyclerViewComponent.clipToPadding = false
        recyclerViewComponent.setPadding(getPaddingRecyclerView(), 0, getPaddingRecyclerView(), 0)
    }

    private fun setupPaddingRecyclerView(config: AndesCarouselConfiguration) {
        recyclerViewComponent.addItemDecoration(PaddingItemDecoration(config.padding))
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

    private fun getPaddingRecyclerView() = (context.resources.displayMetrics.widthPixels * PERCENTAGE).toInt()

    companion object {
        const val PERCENTAGE = 0.10
    }
}
