package com.mercadolibre.android.andesui.carousel.utils

import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

class CarouselAdapter(
    private var itemLayout: Int,
    private var dataSet: List<Any>
) :
        RecyclerView.Adapter<CarouselAdapter.ViewHolder>() {

    private var viewHolderListener: ViewHolderListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, position: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(itemLayout, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = dataSet.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(dataSet[position], viewHolderListener)
    }

    fun setViewHolderListener(listener: ViewHolderListener) {
        viewHolderListener = listener
    }

    fun updateDataSet(data: List<Any>) {
        dataSet = data
        notifyDataSetChanged()
    }

    fun updateLayoutItem(layout: Int) {
        itemLayout = layout
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bind(model: Any, listener: ViewHolderListener?) {
            listener?.bind(itemView, model) ?: run {
                Log.d(
                        "CustomAdapter:",
                        "ViewHolder {bind}: Listener implementation not found"
                )
            }
        }
    }
}
