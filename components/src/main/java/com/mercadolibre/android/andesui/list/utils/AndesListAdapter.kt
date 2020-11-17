package com.mercadolibre.android.andesui.list.utils

import android.support.constraint.ConstraintLayout
import android.support.v7.widget.RecyclerView
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.mercadolibre.android.andesui.R
import com.mercadolibre.android.andesui.list.AndesListViewItem
import com.mercadolibre.android.andesui.list.AndesListViewItemChevron
import com.mercadolibre.android.andesui.list.AndesListViewItemSimple
import com.mercadolibre.android.andesui.list.type.AndesListType

class AndesListAdapter(
        private val delegate: AndesListDelegate,
        private val listType: AndesListType
) : RecyclerView.Adapter<AndesListAdapter.ViewHolder>() {

    override fun getItemCount() = delegate.getDataSetSize()

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
            holder.bind(delegate, position)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val layout = when (listType) {
            AndesListType.SIMPLE -> R.layout.andes_layout_list_item_simple

            AndesListType.CHEVRON -> R.layout.andes_layout_list_item_chevron
        }

        return ViewHolder(LayoutInflater
                .from(parent.context)
                .inflate(layout, parent, false))
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(delegate: AndesListDelegate, position: Int) {

            val andesListItem = delegate.bind(itemView, position)

            itemView.setOnClickListener { delegate.onItemClick(position) }

            itemView.setPadding(
                    andesListItem.paddingLeft,
                    andesListItem.paddingTop,
                    andesListItem.paddingRight,
                    andesListItem.paddingBottom
            )

            when (andesListItem) {
                is AndesListViewItemSimple -> bindSimpleItem(andesListItem)

                is AndesListViewItemChevron -> bindChevronItem(andesListItem)
            }

//            itemView.view_row_selected.visibility = if (row.isSelectable) View.VISIBLE else View.GONE

        }

        private fun bindItemCommons(item: AndesListViewItem) {
            val titleTextView = itemView.findViewById<TextView>(R.id.text_view_item_title)
            val subtitleTextView = itemView.findViewById<TextView>(R.id.text_view_item_sub_title)
            val spaceTitleSubtitleView = itemView.findViewById<View>(R.id.view_space_title_subtitle)

            // Default state
            subtitleTextView.visibility = View.GONE
            spaceTitleSubtitleView.visibility = View.GONE

            titleTextView.text = item.title
            titleTextView.maxLines = item.titleMaxLines
            titleTextView.setTextSize(TypedValue.COMPLEX_UNIT_PX, item.titleFontSize)
            titleTextView.typeface = item.titleTypeFace
            titleTextView.setTextColor(item.titleColor)

            item.subtitle.isNotEmpty().let {
                spaceTitleSubtitleView.visibility = View.VISIBLE

                val params = ConstraintLayout.LayoutParams(ConstraintLayout.LayoutParams.WRAP_CONTENT, ConstraintLayout.LayoutParams.WRAP_CONTENT)
                params.height = item.spaceTitleSubtitle
                spaceTitleSubtitleView.layoutParams = params

                subtitleTextView.visibility = View.VISIBLE
                subtitleTextView.text = item.subtitle
                subtitleTextView.setTextColor(item.subtitleColor)
                subtitleTextView.setTextSize(TypedValue.COMPLEX_UNIT_PX, item.subtitleFontSize)
            }
        }

        private fun bindSimpleItem(item: AndesListViewItemSimple) {
            bindItemCommons(item)
        }

        private fun bindChevronItem(item: AndesListViewItemChevron) {
            bindItemCommons(item)
        }

    }

}