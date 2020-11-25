package com.mercadolibre.android.andesui.list.utils

import android.support.constraint.ConstraintLayout
import android.support.v7.widget.RecyclerView
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewTreeObserver.OnGlobalLayoutListener
import android.widget.TextView
import com.mercadolibre.android.andesui.R
import com.mercadolibre.android.andesui.list.AndesListViewItem
import com.mercadolibre.android.andesui.list.AndesListViewItemChevron
import com.mercadolibre.android.andesui.list.AndesListViewItemSimple
import com.mercadolibre.android.andesui.list.type.AndesListType
import com.mercadolibre.android.andesui.thumbnail.AndesThumbnail


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
            AndesListType.CHECK_BOX -> R.layout.andes_layout_list_item_check_box
            AndesListType.RADIO_BUTTON -> R.layout.andes_layout_list_item_radio_button
        }

        return ViewHolder(LayoutInflater
                .from(parent.context)
                .inflate(layout, parent, false))
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private lateinit var titleTextView: TextView
        private lateinit var subtitleTextView: TextView
        private lateinit var spaceTitleSubtitleView: View
        private lateinit var andesListItemContainer: View
        private lateinit var andesListItemSelectionView: View
        private lateinit var andesListItemAsset: AndesThumbnail
        private lateinit var andesViewThumbnailSeparator: View

        fun bind(delegate: AndesListDelegate, position: Int) {

            val andesListItemConfig = delegate.bind(itemView, position)

            titleTextView = itemView.findViewById(R.id.text_view_item_title)
            subtitleTextView = itemView.findViewById(R.id.text_view_item_sub_title)
            spaceTitleSubtitleView = itemView.findViewById<View>(R.id.view_space_title_subtitle)
            andesListItemContainer = itemView.findViewById<View>(R.id.andes_list_item_container)
            andesListItemSelectionView = itemView.findViewById<View>(R.id.view_item_selected)
            andesListItemAsset = itemView.findViewById(R.id.andes_list_item_asset) // TODO esta en todos los items?
            andesViewThumbnailSeparator = itemView.findViewById(R.id.andesViewThumbnailSeparator) // TODO esta en todos los items?

            // Default visibility state
            subtitleTextView.visibility = View.GONE
            spaceTitleSubtitleView.visibility = View.GONE
            andesListItemSelectionView.visibility = View.GONE
            andesViewThumbnailSeparator.visibility = View.GONE
            andesListItemAsset.visibility = View.GONE

            itemView.setOnClickListener { delegate.onItemClick(position) }

            when (andesListItemConfig) {
                is AndesListViewItemSimple -> bindSimpleItem(andesListItemConfig)

                is AndesListViewItemChevron -> bindChevronItem(andesListItemConfig)
            }

        }

        private fun bindSimpleItem(andesListItemConfig: AndesListViewItemSimple) {
            bindItemCommons(andesListItemConfig)
            andesListItemConfig.avatar?.let {
                andesListItemAsset.visibility = View.VISIBLE
                andesListItemAsset.size = andesListItemConfig.thumbnailSize
                andesListItemAsset.image = it

                showSpaceBetweenAssetAndTitle(andesListItemConfig.separatorThumbnailWidth)

                titleTextView.viewTreeObserver.addOnGlobalLayoutListener(object : OnGlobalLayoutListener {
                    override fun onGlobalLayout() {
                        titleTextView.viewTreeObserver.removeOnGlobalLayoutListener(this)

                        val lp = andesListItemAsset.layoutParams as ConstraintLayout.LayoutParams

                        lp.setMargins(
                                0,
                                calculateAssetTopMargin(),
                                0,
                                0
                        )

                        andesListItemAsset.layoutParams = lp
                    }
                })
            }
        }

        private fun bindChevronItem(item: AndesListViewItemChevron) {
            bindItemCommons(item)
        }

        private fun bindItemCommons(andesListItemConfig: AndesListViewItem) {
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

        private fun calculateAssetTopMargin(): Int {
            var topMargin = 0

            if (titleTextView.lineCount <= 2) {
                val numberOfLines = titleTextView.lineCount


                titleTextView.measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED)
                val height = titleTextView.measuredHeight

                val layoutParams = titleTextView.layoutParams as ConstraintLayout.LayoutParams

                topMargin = (height * numberOfLines / 2) + layoutParams.topMargin - (andesListItemAsset.height / 2)
            }


//            title == 1  titleTextView.height / 2 + layoutParams.topMargin
//            title == 2 titleTextView.height / 2 + layoutParams.topMargin
//            title > 2, (2 * height / cantidad de lineas) /2
            // title == 1 && subtitle == 1 (title height + space height + subtitle height) / 2 o centrado verticalmente
//            n description. ?


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