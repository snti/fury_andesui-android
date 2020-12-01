package com.mercadolibre.android.andesui.list.utils

import android.support.constraint.ConstraintLayout
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewTreeObserver
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.mercadolibre.android.andesui.R
import com.mercadolibre.android.andesui.list.AndesListViewItem
import com.mercadolibre.android.andesui.list.AndesListViewItemChevron
import com.mercadolibre.android.andesui.list.AndesListViewItemSimple
import com.mercadolibre.android.andesui.list.type.AndesListType
import com.mercadolibre.android.andesui.thumbnail.AndesThumbnail


class AndesListAdapter(
        private val delegate: AndesListDelegate,
        private var listType: AndesListType
) : RecyclerView.Adapter<AndesListAdapter.ViewHolder>() {

    override fun getItemCount() = delegate.getDataSetSize()

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
            holder.bind(delegate, position)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val layout = when (viewType) {
            0 -> R.layout.andes_layout_list_item_simple
            1 -> R.layout.andes_layout_list_item_chevron
            2 -> R.layout.andes_layout_list_item_check_box
            3 -> R.layout.andes_layout_list_item_radio_button
            else -> R.layout.andes_layout_list_item_simple
        }

/*
       val layout = when (listType) {
           AndesListType.SIMPLE -> R.layout.andes_layout_list_item_simple
           AndesListType.CHEVRON -> R.layout.andes_layout_list_item_chevron
           AndesListType.CHECK_BOX -> R.layout.andes_layout_list_item_check_box
           AndesListType.RADIO_BUTTON -> R.layout.andes_layout_list_item_radio_button
       }
*/
        return ViewHolder(LayoutInflater
                .from(parent.context)
                .inflate(layout, parent, false))
    }

    override fun getItemViewType(position: Int): Int {

        val layout = when (listType) {
            AndesListType.SIMPLE -> 0
            AndesListType.CHEVRON -> 1
            AndesListType.CHECK_BOX -> 2
            AndesListType.RADIO_BUTTON -> 3
        }


        return layout
    }

    internal fun changeAndesListType(listType: AndesListType) {
        this.listType = listType
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private lateinit var titleTextView: TextView
        private lateinit var subtitleTextView: TextView
        private lateinit var spaceTitleSubtitleView: View
        private lateinit var andesListItemContainer: View
        private lateinit var andesListItemSelectionView: View
        private lateinit var andesListItemAvatar: AndesThumbnail
        private lateinit var andesListItemIcon: ImageView
        private lateinit var andesViewThumbnailSeparator: View

        fun bind(delegate: AndesListDelegate, position: Int) {

            val andesListItemConfig = delegate.bind(itemView, position)

            titleTextView = itemView.findViewById(R.id.text_view_item_title)
            subtitleTextView = itemView.findViewById(R.id.text_view_item_sub_title)
            spaceTitleSubtitleView = itemView.findViewById<View>(R.id.view_space_title_subtitle)
            andesListItemContainer = itemView.findViewById<View>(R.id.andes_list_item_container)
            andesListItemSelectionView = itemView.findViewById<View>(R.id.view_item_selected)
            andesListItemAvatar = itemView.findViewById(R.id.andes_list_item_asset) // TODO esta en todos los items?
            andesViewThumbnailSeparator = itemView.findViewById(R.id.andesViewThumbnailSeparator) // TODO esta en todos los items?
            andesListItemIcon = itemView.findViewById(R.id.image_view_list_item_icon) // TODO esta en todos los items?

            // Default visibility state
            subtitleTextView.visibility = View.GONE
            spaceTitleSubtitleView.visibility = View.GONE
            andesListItemSelectionView.visibility = View.GONE
            andesViewThumbnailSeparator.visibility = View.GONE
            andesListItemAvatar.visibility = View.GONE
            andesListItemIcon.visibility = View.GONE

            itemView.setOnClickListener { delegate.onItemClick(position) }

            when (andesListItemConfig) {
                is AndesListViewItemSimple -> bindSimpleItem(andesListItemConfig)

                is AndesListViewItemChevron -> bindChevronItem(andesListItemConfig)
            }

        }

        private fun bindSimpleItem(andesListItemConfig: AndesListViewItemSimple) {
            bindItemCommons(andesListItemConfig)
        }

        private fun bindChevronItem(andesListItemConfig: AndesListViewItemChevron) {
            bindItemCommons(andesListItemConfig)
        }

        private fun bindItemCommons(andesListItemConfig: AndesListViewItem) {
            andesListItemConfig.icon?.let {
                andesListItemIcon.visibility = View.VISIBLE
                andesListItemIcon.layoutParams.width = andesListItemConfig.iconSize
                andesListItemIcon.layoutParams.height = andesListItemConfig.iconSize
                andesListItemIcon.setImageDrawable(it)

                showSpaceBetweenAssetAndTitle(andesListItemConfig.separatorThumbnailWidth)

                titleTextView.viewTreeObserver.addOnPreDrawListener(object : ViewTreeObserver.OnPreDrawListener {
                    override fun onPreDraw(): Boolean {
                        titleTextView.viewTreeObserver.removeOnPreDrawListener(this)

                        val lp = andesListItemIcon.layoutParams as LinearLayout.LayoutParams

                        lp.setMargins(
                                0,
                                calculateIconTopMargin(),
                                0,
                                0
                        )

                        andesListItemIcon.layoutParams = lp

                        return true
                    }

                })
            }

            andesListItemConfig.avatar?.let {
                andesListItemAvatar.visibility = View.VISIBLE
                andesListItemAvatar.size = andesListItemConfig.thumbnailSize
                andesListItemAvatar.image = it

                showSpaceBetweenAssetAndTitle(andesListItemConfig.separatorThumbnailWidth)

                titleTextView.viewTreeObserver.addOnPreDrawListener(object : ViewTreeObserver.OnPreDrawListener {
                    override fun onPreDraw(): Boolean {
                        titleTextView.viewTreeObserver.removeOnPreDrawListener(this)

                        val lp = andesListItemAvatar.layoutParams as LinearLayout.LayoutParams

                        lp.setMargins(
                                0,
                                calculateAssetTopMargin(),
                                0,
                                0
                        )

                        andesListItemAvatar.layoutParams = lp

                        return true
                    }

                })
            }

            andesListItemContainer.setPadding(
                    andesListItemConfig.paddingLeft,
                    0,
                    andesListItemConfig.paddingRight,
                    0
            )

            titleTextView.text = andesListItemConfig.title
            titleTextView.maxLines = andesListItemConfig.titleMaxLines
            titleTextView.setTextSize(TypedValue.COMPLEX_UNIT_PX, andesListItemConfig.titleFontSize)
            titleTextView.typeface = andesListItemConfig.titleTypeFace
            titleTextView.setTextColor(andesListItemConfig.titleColor)

            if (andesListItemConfig.subtitle.isNotEmpty()) {
                showSpaceBetweenTitleAndSubtitle(andesListItemConfig.spaceTitleSubtitle)

                subtitleTextView.visibility = View.VISIBLE
                subtitleTextView.text = andesListItemConfig.subtitle
                subtitleTextView.setTextColor(andesListItemConfig.subtitleColor)
                subtitleTextView.setTextSize(TypedValue.COMPLEX_UNIT_PX, andesListItemConfig.subtitleFontSize)

                val lpSubtitle = subtitleTextView.layoutParams as ConstraintLayout.LayoutParams

                lpSubtitle.setMargins(
                        0,
                        0,
                        0,
                        andesListItemConfig.paddingBottom
                )

                subtitleTextView.layoutParams = lpSubtitle
            }

            val lpTitle = titleTextView.layoutParams as ConstraintLayout.LayoutParams
            val titleMarginBottom = if (andesListItemConfig.subtitle.isEmpty()) andesListItemConfig.paddingBottom else 0

            lpTitle.setMargins(
                    0,
                    andesListItemConfig.paddingTop,
                    0,
                    titleMarginBottom
            )

            titleTextView.layoutParams = lpTitle

            if (andesListItemConfig.itemSelected) {
                andesListItemSelectionView.visibility = View.VISIBLE
            }


        }

        private fun calculateIconTopMargin(): Int {
            val layoutParams = titleTextView.layoutParams as ConstraintLayout.LayoutParams
            val assetHalfHeight = andesListItemIcon.height / 2
            val textViewTitleHeight = titleTextView.measuredHeight
            val numberOfLines = titleTextView.lineCount

            return  (textViewTitleHeight / numberOfLines) / 2 + layoutParams.topMargin - assetHalfHeight
        }

        private fun calculateAssetTopMargin(): Int {
            var topMargin = 0

            val layoutParams = titleTextView.layoutParams as ConstraintLayout.LayoutParams
            val assetHalfHeight = andesListItemAvatar.height / 2

            if (titleTextView.lineCount == 1 && subtitleTextView.text.isNotEmpty()) {
                topMargin = itemView.measuredHeight / 2 - assetHalfHeight

            } else if (titleTextView.lineCount == 1 && subtitleTextView.text.isEmpty() || titleTextView.lineCount == 2) {
                val textViewTitleHeight = titleTextView.measuredHeight

                topMargin = (textViewTitleHeight / 2) + layoutParams.topMargin - assetHalfHeight

            } else if (titleTextView.lineCount > 2) {
                val textViewTitleHeight = titleTextView.measuredHeight
                val numberOfLines = titleTextView.lineCount

                topMargin = (2 * textViewTitleHeight / numberOfLines) / 2 + layoutParams.topMargin - assetHalfHeight
            }

            return topMargin
        }

        private fun showSpaceBetweenTitleAndSubtitle(spaceHeight: Int) {
            spaceTitleSubtitleView.visibility = View.VISIBLE
            spaceTitleSubtitleView.layoutParams.height = spaceHeight
        }

        private fun showSpaceBetweenAssetAndTitle(spaceWidth: Int) {
            andesViewThumbnailSeparator.visibility = View.VISIBLE
            andesViewThumbnailSeparator.layoutParams.height = spaceWidth
        }

    }

}