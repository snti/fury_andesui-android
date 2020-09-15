package com.mercadolibre.android.andesui.carousel.utils

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.mercadolibre.android.andesui.carousel.AndesCarousel

class AndesCarouselAdapter(
    private val andesCarouselView: AndesCarousel,
    private val delegate: AndesCarouselDelegate
) : RecyclerView.Adapter<AndesCarouselAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, position: Int): ViewHolder{
        val view = LayoutInflater.from(parent.context).inflate(delegate.getLayoutItem(andesCarouselView), parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = delegate.getDataSetSize(andesCarouselView)

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(andesCarouselView, position, delegate)
        holder.itemView.setOnClickListener {
            delegate.onClickItem(andesCarouselView, position)
        }
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bind(andesCarouselView: AndesCarousel, position: Int, delegate: AndesCarouselDelegate) {
            delegate.bind(andesCarouselView, itemView, position)
        }
    }
}
