package com.mercadolibre.android.andesui.list.utils

import android.support.constraint.ConstraintSet
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.mercadolibre.android.andesui.R
import com.mercadolibre.android.andesui.list.ActionableComponent
import com.mercadolibre.android.andesui.list.row.size.AndesListViewItemSize
import kotlinx.android.synthetic.main.andes_layout_list_row.view.*

class AndesListAdapter(
        private val delegate: AndesListDelegate,
        private val size: Int
) : RecyclerView.Adapter<AndesListAdapter.ViewHolder>() {

    override fun getItemCount() = delegate.getDataSetSize()

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
            holder.bind(delegate, size, position)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
            ViewHolder(LayoutInflater
                    .from(parent.context)
                    .inflate(R.layout.andes_layout_list_row, parent, false))

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(delegate: AndesListDelegate, size: Int, position: Int) {
            itemView.setOnClickListener { delegate.onClickItem(position) }

            itemView.text_view_row_description?.visibility = View.GONE

            val row = delegate.bind(itemView, position)

            itemView.view_row_selected.visibility = if (row.isSelectable) View.VISIBLE else View.GONE

            itemView.text_view_row_title?.text = row.title

            val layoutParams = itemView.layoutParams
            layoutParams.height = size
            itemView.layoutParams = layoutParams

//            row.color?.let {
////                        itemView.text_view_row_title?
//            }

            row.description?.let {
                itemView.text_view_row_description?.visibility = View.VISIBLE
                itemView.text_view_row_description?.text = row.description

//                row.colorDescription?.let {
////                        itemView.text_view_row_description?
//                }
            }


            row.actionableComponent?.let {

                val constraintSet = ConstraintSet()
                constraintSet.clone(itemView.list_row_content_right)
//                constraintSet.connect(childView.getId(), ConstraintSet.TOP, parentLayout.getId(), ConstraintSet.TOP, 60);

                when (it) {
                    is ActionableComponent.AddButton -> {
                        itemView.list_row_content_right.addView(it.button)
                        it.button.id = View.generateViewId()
                        it.button.setOnClickListener {
//                            delegate.
                        }
                    }
                }


            }

        }


    }

}