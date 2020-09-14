package com.mercadolibre.android.andesui.carousel.utils

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

class CarouselAdapter(private val listener: AndesCarouselDelegate) : RecyclerView.Adapter<CarouselAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, position: Int): ViewHolder{
        val view = LayoutInflater.from(parent.context).inflate(listener.getLayoutItem(), parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = listener.getDataSetSize()

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(position, listener)
        holder.itemView.setOnClickListener {
            listener.onClickItem(position)
        }
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bind(position: Int, listener: AndesCarouselDelegate) {
            listener.bind(itemView, position)
        }
    }
}
