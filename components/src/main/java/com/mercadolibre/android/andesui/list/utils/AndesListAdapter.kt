package com.mercadolibre.android.andesui.list.utils

import android.support.constraint.ConstraintLayout
import android.support.v7.widget.RecyclerView
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewTreeObserver
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.mercadolibre.android.andesui.R
import com.mercadolibre.android.andesui.list.AndesList
import com.mercadolibre.android.andesui.list.AndesListViewItem
import com.mercadolibre.android.andesui.list.AndesListViewItemChevron
import com.mercadolibre.android.andesui.list.AndesListViewItemSimple
import com.mercadolibre.android.andesui.list.type.AndesListType
import com.mercadolibre.android.andesui.thumbnail.AndesThumbnail


class AndesListAdapter(
        private val andesList: AndesList,
        private val delegate: AndesListDelegate,
        private var listType: AndesListType
) : RecyclerView.Adapter<AndesListAdapter.ViewHolder>() {

    private enum class LayoutType(val value: Int) {
        SIMPLE(0),
        CHEVRON(1),
        CHECK_BOX(2),
        RADIO_BUTTON(3)
    }

    override fun getItemCount() = delegate.getDataSetSize()

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
            holder.bind(andesList, delegate, position)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val layout = when (viewType) {
            LayoutType.SIMPLE.value -> R.layout.andes_layout_list_item_simple
            LayoutType.CHEVRON.value -> R.layout.andes_layout_list_item_chevron
            LayoutType.CHECK_BOX.value -> R.layout.andes_layout_list_item_check_box
            LayoutType.RADIO_BUTTON.value -> R.layout.andes_layout_list_item_radio_button
            else -> R.layout.andes_layout_list_item_simple
        }

        return ViewHolder(LayoutInflater
                .from(parent.context)
                .inflate(layout, parent, false))
    }

    override fun getItemViewType(position: Int): Int {
        return when (listType) {
            AndesListType.SIMPLE -> LayoutType.SIMPLE.value
            AndesListType.CHEVRON -> LayoutType.CHEVRON.value
            AndesListType.CHECK_BOX -> LayoutType.CHECK_BOX.value
            AndesListType.RADIO_BUTTON -> LayoutType.RADIO_BUTTON.value
        }
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

        fun bind(andesList: AndesList, delegate: AndesListDelegate, position: Int) {

            val andesListItemConfig = delegate.bind(andesList, itemView, position)

            // Common view
            titleTextView = itemView.findViewById(R.id.text_view_item_title)
            subtitleTextView = itemView.findViewById(R.id.text_view_item_sub_title)
            spaceTitleSubtitleView = itemView.findViewById<View>(R.id.view_space_title_subtitle)
            andesListItemContainer = itemView.findViewById<View>(R.id.andes_list_item_container)
            andesListItemSelectionView = itemView.findViewById<View>(R.id.view_item_selected)
            andesListItemAvatar = itemView.findViewById(R.id.andes_list_item_asset)
            andesViewThumbnailSeparator = itemView.findViewById(R.id.andesViewThumbnailSeparator)
            andesListItemIcon = itemView.findViewById(R.id.image_view_list_item_icon)

            // Default common view visibility state
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

            val andesListItemChevron: ImageView = itemView.findViewById(R.id.andes_thumbnail_chevron)

            val layoutParamsChevron = andesListItemChevron.layoutParams as ConstraintLayout.LayoutParams

            layoutParamsChevron.height = andesListItemConfig.chevronSize
            layoutParamsChevron.width = andesListItemConfig.chevronSize


            setChevronPosition(andesListItemChevron) {
                calculateChevronTopMargin(andesListItemConfig, andesListItemChevron)
            }

        }

        private fun calculateChevronTopMargin(andesListItemConfig: AndesListViewItemChevron, andesListItemChevron: View): Int {
            return if (andesListItemConfig.avatar != null) {
                getChevronTopMarginBasedInAvatar(andesListItemChevron)
            } else {
                getChevronTopMarginBasedInTitleFirstLine(andesListItemChevron)
            }
        }

        private fun setChevronPosition(asset: View, functionToCalculateTopMargin: () -> Int) {
            titleTextView.viewTreeObserver.addOnPreDrawListener(object : ViewTreeObserver.OnPreDrawListener {
                override fun onPreDraw(): Boolean {
                    titleTextView.viewTreeObserver.removeOnPreDrawListener(this)

                    val layoutParams = asset.layoutParams as ConstraintLayout.LayoutParams
                    val topMargin = functionToCalculateTopMargin()

                    layoutParams.setMargins(
                            0,
                            topMargin,
                            0,
                            0
                    )

                    asset.layoutParams = layoutParams

                    return true
                }

            })
        }

        private fun setAssetItemPosition(asset: View, functionToCalculateTopMargin: () -> Int) {
            titleTextView.viewTreeObserver.addOnPreDrawListener(object : ViewTreeObserver.OnPreDrawListener {
                override fun onPreDraw(): Boolean {
                    titleTextView.viewTreeObserver.removeOnPreDrawListener(this)

                    val layoutParams = asset.layoutParams as LinearLayout.LayoutParams
                    val topMargin = functionToCalculateTopMargin()

                    layoutParams.setMargins(
                            0,
                            topMargin,
                            0,
                            0
                    )

                    asset.layoutParams = layoutParams

                    return true
                }

            })
        }

        private fun bindItemCommons(itemConfig: AndesListViewItem) {
            titleTextView.text = itemConfig.title
            titleTextView.maxLines = itemConfig.titleMaxLines
            titleTextView.setTextSize(TypedValue.COMPLEX_UNIT_PX, itemConfig.titleFontSize)
            titleTextView.typeface = itemConfig.titleTypeFace
            titleTextView.setTextColor(itemConfig.titleColor)

            if (itemConfig.showSubtitle && !itemConfig.subtitle.isNullOrEmpty()) {
                showSpaceBetweenTitleAndSubtitle(itemConfig.spaceTitleSubtitle)

                subtitleTextView.visibility = View.VISIBLE
                subtitleTextView.text = itemConfig.subtitle
                subtitleTextView.setTextColor(itemConfig.subtitleColor)
                subtitleTextView.setTextSize(TypedValue.COMPLEX_UNIT_PX, itemConfig.subtitleFontSize)

                val layoutParamSubtitle = subtitleTextView.layoutParams as ConstraintLayout.LayoutParams

                layoutParamSubtitle.setMargins(
                        0,
                        0,
                        0,
                        itemConfig.paddingBottom
                )

                subtitleTextView.layoutParams = layoutParamSubtitle
            }

            val layoutParamsTitle = titleTextView.layoutParams as ConstraintLayout.LayoutParams
            val titleMarginBottom = if (itemConfig.showSubtitle && itemConfig.subtitle.isNullOrEmpty()) {
                itemConfig.paddingBottom
            } else {
                0
            }

            layoutParamsTitle.setMargins(
                    0,
                    itemConfig.paddingTop,
                    0,
                    titleMarginBottom
            )

            titleTextView.layoutParams = layoutParamsTitle

            itemConfig.itemSelected?.let {
                if (it) {
                    andesListItemSelectionView.visibility = View.VISIBLE
                }
            }

            itemConfig.icon?.let {
                andesListItemIcon.visibility = View.VISIBLE
                andesListItemIcon.layoutParams.width = itemConfig.iconSize
                andesListItemIcon.layoutParams.height = itemConfig.iconSize
                andesListItemIcon.setImageDrawable(it)

                showSpaceBetweenAssetAndTitle(itemConfig.separatorThumbnailWidth)

                setAssetItemPosition(andesListItemIcon) {
                    calculateIconTopMargin()
                }
            }

            itemConfig.avatar?.let {
                andesListItemAvatar.visibility = View.VISIBLE
                andesListItemAvatar.size = itemConfig.thumbnailSize
                andesListItemAvatar.image = it

                showSpaceBetweenAssetAndTitle(itemConfig.separatorThumbnailWidth)

                setAssetItemPosition(andesListItemAvatar) {
                    calculateAvatarTopMargin()
                }
            }

            andesListItemContainer.setPadding(
                    itemConfig.paddingLeft,
                    0,
                    itemConfig.paddingRight,
                    0
            )

        }

        private fun getChevronTopMarginBasedInAvatar(andesListItemChevron: View): Int {
            val chevronHalfHeight = andesListItemChevron.height / 2
            val assetHalfHeight = andesListItemAvatar.height / 2
            val layoutParams = andesListItemAvatar.layoutParams as LinearLayout.LayoutParams
            return assetHalfHeight + layoutParams.topMargin - chevronHalfHeight
        }

        private fun getChevronTopMarginBasedInTitleFirstLine(andesListItemChevron: View): Int {
            val layoutParams = titleTextView.layoutParams as ConstraintLayout.LayoutParams
            val chevronHalfHeight = andesListItemChevron.height / 2

            return getTitleHeightInTheFirstLine() / 2 + layoutParams.topMargin - chevronHalfHeight
        }

        private fun calculateIconTopMargin(): Int {
            val layoutParams = titleTextView.layoutParams as ConstraintLayout.LayoutParams
            val assetHalfHeight = andesListItemIcon.height / 2

            return getTitleHeightInTheFirstLine() / 2 + layoutParams.topMargin - assetHalfHeight
        }

        private fun getTitleHeightInTheFirstLine(): Int {
            val textViewTitleHeight = titleTextView.measuredHeight
            val numberOfLines = titleTextView.lineCount

            return (textViewTitleHeight / numberOfLines)
        }

        //TODO documentar
        private fun calculateAvatarTopMargin(): Int {
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