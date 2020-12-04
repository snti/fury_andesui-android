package com.mercadolibre.android.andesui.list

import android.content.Context
import android.graphics.Typeface
import android.graphics.drawable.Drawable
import com.mercadolibre.android.andesui.list.factory.AndesListViewItemConfiguration
import com.mercadolibre.android.andesui.list.factory.AndesListViewItemConfigurationFactory
import com.mercadolibre.android.andesui.list.size.AndesListViewItemSize
import com.mercadolibre.android.andesui.thumbnail.size.AndesThumbnailSize

open class AndesListViewItem {
    var title: String = ""
    var subtitle: String? = ""
    var paddingLeft: Int = 0
    var paddingRight: Int = 0
    var paddingTop: Int = 0
    var paddingBottom: Int = 0
    var height: Float = 0.0f
    var titleColor: Int = 0
    var titleFontSize: Float = 0.0f
    var titleTypeFace: Typeface = Typeface.DEFAULT
    var subtitleColor: Int = 0
    var subtitleFontSize: Float = 0.0f
    var subtitleTypeFace: Typeface = Typeface.DEFAULT
    var titleMaxLines: Int = 50
    var spaceTitleSubtitle = 0
    var itemSelected: Boolean? = false
    var thumbnailSize: AndesThumbnailSize = AndesThumbnailSize.SIZE_32
    var separatorThumbnailWidth: Int = 0
    var iconSize: Int = 0
    var icon: Drawable? = null
    var avatar: Drawable? = null

    internal fun andesListViewItemConfig(title: String, subtitle: String?, config: AndesListViewItemConfiguration, itemSelected: Boolean? = false, icon: Drawable? = null, avatar: Drawable? = null, titleMaxLines: Int = 50) {
        this.title = title
        this.subtitle = subtitle
        this.paddingBottom = config.paddingBottom
        this.paddingLeft = config.paddingLeft
        this.paddingRight = config.paddingRight
        this.paddingTop = config.paddingTop
        this.subtitleFontSize = config.subTitleFontSize
        this.titleFontSize = config.titleFontSize
        this.height = config.height
        this.titleTypeFace = config.titleTypeface
        this.subtitleTypeFace = config.subTitleTypeface
        this.titleColor = config.titleColor
        this.subtitleColor = config.subTitleColor
        this.titleMaxLines = config.titleMaxLines
        this.spaceTitleSubtitle = config.spaceTitleSubtitle
        this.itemSelected = itemSelected
        this.thumbnailSize = config.avatarSize
        this.separatorThumbnailWidth = config.separatorThumbnailWidth
        this.iconSize = config.iconSize
        this.icon = icon
        this.avatar = avatar
        this.titleMaxLines = titleMaxLines
    }
}

class AndesListViewItemSimple(
        context: Context,
        title: String,
        subtitle: String?,
        itemSelected: Boolean = false,
        size: AndesListViewItemSize = AndesListViewItemSize.MEDIUM,
        icon: Drawable? = null,
        avatar: Drawable? = null,
        titleMaxLines: Int = 50

) : AndesListViewItem() {

    init {
        val config = AndesListViewItemConfigurationFactory.create(context, size)
        this.andesListViewItemSimpleConfig(title, subtitle, config, itemSelected, icon, avatar, titleMaxLines)
    }

    private fun andesListViewItemSimpleConfig(title: String, subtitle: String? = "", config: AndesListViewItemConfiguration, itemSelected: Boolean? = false, icon: Drawable? = null, avatar: Drawable? = null, titleMaxLines: Int = 50) {
        super.andesListViewItemConfig(title, subtitle, config, itemSelected, icon, avatar, titleMaxLines)
    }

}

class AndesListViewItemChevron(
        context: Context,
        title: String,
        subtitle: String,
        itemSelected: Boolean = false,
        size: AndesListViewItemSize = AndesListViewItemSize.MEDIUM,
        icon: Drawable? = null,
        avatar: Drawable? = null,
        titleMaxLines: Int = 50

) : AndesListViewItem() {

    init {
        val config = AndesListViewItemConfigurationFactory.create(context, size)
        this.andesListViewItemSimpleConfig(title, subtitle, config, itemSelected, icon, avatar, titleMaxLines)
    }

    private fun andesListViewItemSimpleConfig(title: String, subtitle: String = "", config: AndesListViewItemConfiguration, itemSelected: Boolean? = false, icon: Drawable? = null, avatar: Drawable? = null, titleMaxLines: Int = 50) {
        super.andesListViewItemConfig(title, subtitle, config, itemSelected, icon, avatar, titleMaxLines)
    }

}