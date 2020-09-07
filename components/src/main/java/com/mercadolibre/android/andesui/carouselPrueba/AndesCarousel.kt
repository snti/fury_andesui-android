package com.mercadolibre.android.andesui.carouselPrueba

import android.content.Context
import android.support.constraint.ConstraintLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.PagerSnapHelper
import android.support.v7.widget.RecyclerView
import android.util.AttributeSet
import android.view.LayoutInflater
import com.mercadolibre.android.andesui.R
import com.mercadolibre.android.andesui.carousel.util.ViewHolderListener
import kotlinx.android.synthetic.main.andes_layout_carousel_test.view.*

class AndesCarousel : ConstraintLayout {

    private lateinit var viewAdapter: CustomAdapter
    private lateinit var viewManager: RecyclerView.LayoutManager
    private var itemLayout = R.layout.andes_layout_carousel_item
    private var dataSet = ArrayList<Any>()

    var layout: Int
        get() = itemLayout
        set(value) {
            itemLayout = value
            viewAdapter.updateLayoutItem(itemLayout)
        }

    /**
     *  cuando setea la lista, setear la lista de datos del adapter
     */
    var data: ArrayList<Any>
        get() = dataSet
        set(value) {
            dataSet = value
            viewAdapter.updateDataSet(dataSet)
        }

    constructor(context: Context) : super(context) {
        initView(context)
    }

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        initView(context)
    }

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        initView(context)
    }

    /**
     * Cuando setea el listener, setearselo al adapter
     */
    fun setViewHolderListener(listener: ViewHolderListener) {
        viewAdapter.setViewHolderListener(listener)
    }

    private fun initView(context: Context) {
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        inflater.inflate(R.layout.andes_layout_carousel_test, this, true)
        viewManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        viewAdapter = CustomAdapter(layout, dataSet)
        recyclerView.layoutManager = viewManager
        recyclerView.adapter = viewAdapter

        val snapHelper = PagerSnapHelper()
        snapHelper.attachToRecyclerView(recyclerView)
    }
}
