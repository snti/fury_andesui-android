package com.mercadolibre.android.andesui.list

import android.content.Context
import android.graphics.Typeface
import com.mercadolibre.android.andesui.list.factory.AndesListViewItemConfiguration
import com.mercadolibre.android.andesui.list.factory.AndesListViewItemConfigurationFactory
import com.mercadolibre.android.andesui.list.size.AndesListViewItemSize

open class AndesListViewItem {
    var title: String = ""
    var subtitle: String = ""
    var paddingLeft: Int = 0
    var paddingRight: Int = 0
    var paddingTop: Int = 0
    var paddingBottom: Int = 0
    var subtitleFontSize: Float = 0.0f
    var height: Float = 0.0f
    var titleFontSize: Float = 0.0f
    var titleTypeFace: Typeface = Typeface.DEFAULT
    var subtitleTypeFace: Typeface = Typeface.DEFAULT
    var titleColor: Int = 0
    var subtitleColor : Int = 0
    var titleMaxLines : Int = 50
    var spaceTitleSubtitle = 0

    internal fun andesListViewItemConfig(title: String, subtitle: String, config: AndesListViewItemConfiguration) {
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
    }
}

class AndesListViewItemSimple : AndesListViewItem {

    constructor(context: Context, title: String, size: AndesListViewItemSize = AndesListViewItemSize.MEDIUM) {
        val config = AndesListViewItemConfigurationFactory.create(context, size, false)
        this.andesListViewItemSimpleConfig(title = title, config = config)
    }

    constructor(context: Context, title: String, subtitle: String, size: AndesListViewItemSize = AndesListViewItemSize.MEDIUM) {
        val config = AndesListViewItemConfigurationFactory.create(context, size, subtitle.isNotEmpty())
        this.andesListViewItemSimpleConfig(title, subtitle, config)
    }

    private fun andesListViewItemSimpleConfig(title: String, subtitle: String = "", config: AndesListViewItemConfiguration) {
        super.andesListViewItemConfig(title, subtitle, config)
    }

}


class AndesListViewItemChevron : AndesListViewItem {
//    var thumbnail: AndesThumbnail? = null

    constructor(context: Context, title: String, size: AndesListViewItemSize = AndesListViewItemSize.MEDIUM) {
        val config = AndesListViewItemConfigurationFactory.create(context, size, false)
        this.andesListViewChevronItemConfig(title = title, config = config)
    }

    constructor(context: Context, title: String, subtitle: String, size: AndesListViewItemSize = AndesListViewItemSize.MEDIUM) {
        val config = AndesListViewItemConfigurationFactory.create(context, size, subtitle.isNotEmpty())
        this.andesListViewChevronItemConfig(title, subtitle, config)
    }

    private fun andesListViewChevronItemConfig(title: String, subtitle: String = "", config: AndesListViewItemConfiguration) {
        super.andesListViewItemConfig(title, subtitle, config)

    }

}