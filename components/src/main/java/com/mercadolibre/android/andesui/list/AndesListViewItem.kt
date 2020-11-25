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
    var subtitle: String = ""
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
    var itemSelected = false
    var thumbnailSize : AndesThumbnailSize = AndesThumbnailSize.SIZE_32
    var separatorThumbnailWidth: Int = 0
    var icon: Drawable? = null
    var avatar: Drawable? = null

    internal fun andesListViewItemConfig(title: String, subtitle: String, config: AndesListViewItemConfiguration, itemSelected: Boolean = false, icon : Drawable? = null, avatar : Drawable? = null) {
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
        this.icon = icon
        this.avatar = avatar
    }
}

class AndesListViewItemSimple : AndesListViewItem {

    constructor(context: Context, title: String, itemSelected: Boolean = false, size: AndesListViewItemSize = AndesListViewItemSize.MEDIUM, icon : Drawable? = null, avatar : Drawable? = null) {
        val config = AndesListViewItemConfigurationFactory.create(context, size, false)
        this.andesListViewItemSimpleConfig(title = title, config = config, itemSelected = itemSelected, avatar = avatar)
    }

    constructor(context: Context, title: String, itemSelected: Boolean = false, size: AndesListViewItemSize = AndesListViewItemSize.MEDIUM, icon : Drawable? = null) {
        val config = AndesListViewItemConfigurationFactory.create(context, size, false)
        this.andesListViewItemSimpleConfig(title = title, config = config, itemSelected = itemSelected)
    }

    constructor(context: Context, title: String, subtitle: String, itemSelected: Boolean = false, size: AndesListViewItemSize = AndesListViewItemSize.MEDIUM, icon : Drawable? = null, avatar : Drawable? = null) {
        val config = AndesListViewItemConfigurationFactory.create(context, size, subtitle.isNotEmpty())
        this.andesListViewItemSimpleConfig(title, subtitle, config, itemSelected)
    }

    private fun andesListViewItemSimpleConfig(title: String, subtitle: String = "", config: AndesListViewItemConfiguration, itemSelected: Boolean = false, icon : Drawable? = null, avatar : Drawable? = null) {
        super.andesListViewItemConfig(title, subtitle, config, itemSelected, icon, avatar)
    }

}


class AndesListViewItemChevron : AndesListViewItem {

    constructor(context: Context, title: String, itemSelected: Boolean = false, size: AndesListViewItemSize = AndesListViewItemSize.MEDIUM, icon : Drawable? = null, avatar : Drawable? = null) {
        val config = AndesListViewItemConfigurationFactory.create(context, size, false)
        this.andesListViewChevronItemConfig( title, itemSelected = itemSelected, config = config, icon = icon, avatar = avatar)
    }

    constructor(context: Context, title: String, subtitle: String, itemSelected: Boolean = false, size: AndesListViewItemSize = AndesListViewItemSize.MEDIUM, icon : Drawable? = null, avatar : Drawable? = null) {
        val config = AndesListViewItemConfigurationFactory.create(context, size, subtitle.isNotEmpty())
        this.andesListViewChevronItemConfig(title, subtitle, itemSelected, config, icon, avatar)
    }

    private fun andesListViewChevronItemConfig(title: String, subtitle: String = "", itemSelected: Boolean = false, config: AndesListViewItemConfiguration, icon : Drawable? = null, avatar : Drawable? = null) {
        super.andesListViewItemConfig(title, subtitle, config, itemSelected, icon, avatar)

    }

}