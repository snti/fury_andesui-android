package com.mercadolibre.android.andesui.list

import android.content.Context
import android.support.constraint.ConstraintLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import com.mercadolibre.android.andesui.R
import com.mercadolibre.android.andesui.list.factory.AndesListAttrParser
import com.mercadolibre.android.andesui.list.factory.AndesListAttrs
import com.mercadolibre.android.andesui.list.factory.AndesListConfiguration
import com.mercadolibre.android.andesui.list.factory.AndesListConfigurationFactory
import com.mercadolibre.android.andesui.list.row.size.AndesListViewItemSize
import com.mercadolibre.android.andesui.list.utils.AndesListAdapter
import com.mercadolibre.android.andesui.list.utils.AndesListDelegate

class AndesList : ConstraintLayout {

    private lateinit var andesListDelegate: AndesListDelegate
    private lateinit var andesListViewItemSize: AndesListViewItemSize
    private lateinit var recyclerViewComponent: RecyclerView

    /**
     * Getter and setter for [delegate].
     */
    var delegate: AndesListDelegate
        get() = andesListDelegate
        set(value) {
            andesListDelegate = value
        }

    /**
     * Getter and setter for [size].
     */
    var size: AndesListViewItemSize
        get() = andesListAttrs.andesListViewItemSize
        set(value) {
            andesListAttrs = andesListAttrs.copy(andesListViewItemSize = value)
            val config = createConfig()
            andesListViewItemSize = config.size
        }

    private lateinit var andesListAttrs: AndesListAttrs

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        initAttrs(attrs)
    }

    constructor(
            context: Context,
            size: AndesListViewItemSize = SIZE_DEFAULT
    ) : super(context) {
        initAttrs(size)
    }

    /**
     * Sets the proper [config] for this component based on the [attrs] received via XML.
     *
     * @param attrs attributes from the XML.
     */
    private fun initAttrs(attrs: AttributeSet?) {
        andesListAttrs = AndesListAttrParser.parse(context, attrs)
        val config = AndesListConfigurationFactory.create(andesListAttrs)
        setupComponents(config)
    }

    private fun initAttrs(size: AndesListViewItemSize) {
        andesListAttrs = AndesListAttrs(size)
        val config = AndesListConfigurationFactory.create(andesListAttrs)
        setupComponents(config)
    }

    /**
     * Responsible for setting up all properties of each component that is part of this andesList.
     * Is like a choreographer 😉
     */
    private fun setupComponents(config: AndesListConfiguration) {
        initComponents()
        setupViewId()
        setupRecyclerViewComponent()
    }

    /**
     * Set recyclerview
     */
    private fun setupRecyclerViewComponent() {
        recyclerViewComponent.layoutManager = LinearLayoutManager(context)
        val listAdapter = AndesListAdapter(andesListDelegate, andesListViewItemSize.size.lisViewItemSize(context).toInt())
        recyclerViewComponent.adapter = listAdapter
    }

    /**
     * Creates all the views that are part of this andesList.
     * After a view is created then a view id is added to it.
     */
    private fun initComponents() {
        val container = LayoutInflater.from(context).inflate(R.layout.andes_layout_list, this)
        recyclerViewComponent = container.findViewById(R.id.andes_list)
    }

    /**
     * Sets a view id to this andesList.
     */
    private fun setupViewId() {
        if (id == NO_ID) { // If this view has no id
            id = View.generateViewId()
        }
    }

    private fun createConfig() = AndesListConfigurationFactory.create(andesListAttrs)

    companion object {
        private val SIZE_DEFAULT = AndesListViewItemSize.MEDIUM
    }

}