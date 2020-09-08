package com.mercadolibre.android.andesui.carousel

import android.content.Context
import android.support.constraint.ConstraintLayout
import android.support.constraint.ConstraintSet
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.PagerSnapHelper
import android.support.v7.widget.RecyclerView
import android.util.AttributeSet
import android.view.View
import android.view.ViewGroup
import com.mercadolibre.android.andesui.carousel.factory.AndesCarouselAttrParser
import com.mercadolibre.android.andesui.carousel.factory.AndesCarouselAttrs
import com.mercadolibre.android.andesui.carousel.factory.AndesCarouselConfiguration
import com.mercadolibre.android.andesui.carousel.factory.AndesCarouselConfigurationFactory
import com.mercadolibre.android.andesui.carousel.padding.AndesCarouselPadding
import com.mercadolibre.android.andesui.carousel.utils.CustomAdapter
import com.mercadolibre.android.andesui.carousel.utils.PaddingItemDecoration
import com.mercadolibre.android.andesui.carousel.utils.ViewHolderListener

class AndesCarousel : ConstraintLayout {

    private lateinit var andesCarouselAttrs: AndesCarouselAttrs
    private lateinit var recyclerViewComponent: RecyclerView
    private lateinit var adapterView: CustomAdapter
    private lateinit var viewManager: LinearLayoutManager
    private var dataSet: List<Any> = emptyList()

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
            adapterView.updateLayoutItem(createConfig().layout)
        }

    var data: List<Any>
        get() = dataSet
        set(value) {
            dataSet = value
            adapterView.updateDataSet(value)
        }

    fun setViewHolderListener(listener: ViewHolderListener) {
        adapterView.setViewHolderListener(listener)
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
        addView(recyclerViewComponent)

        updateComponentsAlignment(config)
    }

    /**
     * Creates all the views that are part of this andesCarousel.
     * After a view is created then a view id is added to it.
     */
    private fun initComponents() {
        recyclerViewComponent = RecyclerView(context)
        recyclerViewComponent.id = View.generateViewId()
    }

    private fun updateDynamicComponents(config: AndesCarouselConfiguration) {
        setupRecyclerViewComponent(config)
    }

    private fun updateComponentsAlignment(config: AndesCarouselConfiguration) {
        setupConstraint()
        setupPaddingRecyclerView(config)
    }

    private fun setupRecyclerViewComponent(config: AndesCarouselConfiguration) {
        viewManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        recyclerViewComponent.layoutManager = viewManager
        adapterView = CustomAdapter(config.layout, dataSet)
        recyclerViewComponent.adapter = adapterView

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

    private fun setupConstraint() {
        val set = ConstraintSet()
        set.clone(this)
        set.constrainWidth(recyclerViewComponent.id, ViewGroup.LayoutParams.MATCH_PARENT)
        set.applyTo(this)
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

    private fun getPaddingRecyclerView() = (context.resources.displayMetrics.widthPixels * 0.10).toInt()
}
