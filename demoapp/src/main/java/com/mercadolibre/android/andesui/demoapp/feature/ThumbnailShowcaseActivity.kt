package com.mercadolibre.android.andesui.demoapp.feature

import android.content.Context
import android.os.Bundle
import android.support.v4.view.PagerAdapter
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import com.mercadolibre.android.andesui.button.AndesButton
import com.mercadolibre.android.andesui.color.AndesColor
import com.mercadolibre.android.andesui.demoapp.AndesSpecs
import com.mercadolibre.android.andesui.demoapp.PageIndicator
import com.mercadolibre.android.andesui.demoapp.R
import com.mercadolibre.android.andesui.demoapp.launchSpecs
import com.mercadolibre.android.andesui.thumbnail.AndesThumbnail
import com.mercadolibre.android.andesui.thumbnail.hierarchy.AndesThumbnailHierarchy
import com.mercadolibre.android.andesui.thumbnail.size.AndesThumbnailSize
import com.mercadolibre.android.andesui.thumbnail.state.AndesThumbnailState
import com.mercadolibre.android.andesui.thumbnail.type.AndesThumbnailType

class ThumbnailShowcaseActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.andesui_showcase_main)

        setSupportActionBar(findViewById(R.id.andesui_nav_bar))
        supportActionBar?.title = resources.getString(R.string.andesui_demoapp_screen_button)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val viewPager = findViewById<ViewPager>(R.id.andesui_viewpager)
        viewPager.adapter = AndesShowcasePagerAdapter(this)
        val indicator = findViewById<PageIndicator>(R.id.page_indicator)
        indicator.attach(viewPager)

        val adapter = viewPager.adapter as AndesShowcasePagerAdapter
        addThumbnailsQuiet(adapter.views[0])
        addThumbnailsLoud(adapter.views[1])
        addThumbnailsDefault(adapter.views[2])
    }

    private fun addThumbnailsQuiet(container: View) {
        val linearQuiet = container.findViewById<LinearLayout>(R.id.andes_thumbnail_quiet_container)

        val andesThumbnail24Enabled = AndesThumbnail(this, AndesColor(R.color.andes_blue_ml_500), "andes_otros_almanaque_24",
            AndesThumbnailHierarchy.QUIET, R.drawable.andes_otros_almanaque_24, AndesThumbnailType.ICON,
            AndesThumbnailSize.SIZE_24, AndesThumbnailState.ENABLED)

        val andesThumbnail32Enabled = AndesThumbnail(this, AndesColor(R.color.andes_blue_ml_500), "",
            AndesThumbnailHierarchy.QUIET, R.drawable.andes_otros_almanaque_24, AndesThumbnailType.ICON,
            AndesThumbnailSize.SIZE_32, AndesThumbnailState.ENABLED)

        val andesThumbnail40Disabled = AndesThumbnail(this, AndesColor(R.color.andes_blue_ml_500), "",
            AndesThumbnailHierarchy.QUIET, R.drawable.andes_otros_almanaque_24, AndesThumbnailType.ICON,
            AndesThumbnailSize.SIZE_40, AndesThumbnailState.DISABLED)

        val andesThumbnail48Disabled = AndesThumbnail(this, AndesColor(R.color.andes_blue_ml_500), "",
            AndesThumbnailHierarchy.QUIET, R.drawable.andes_otros_almanaque_24, AndesThumbnailType.ICON,
            AndesThumbnailSize.SIZE_48, AndesThumbnailState.DISABLED)

        val andesThumbnail56Enabled = AndesThumbnail(this, AndesColor(R.color.andes_red_800), "",
            AndesThumbnailHierarchy.QUIET, R.drawable.andes_otros_almanaque_24, AndesThumbnailType.ICON,
            AndesThumbnailSize.SIZE_56, AndesThumbnailState.ENABLED)

        val andesThumbnail64Enabled = AndesThumbnail(this, AndesColor(R.color.andes_red_800), "",
            AndesThumbnailHierarchy.QUIET, R.drawable.andes_otros_almanaque_24, AndesThumbnailType.ICON,
            AndesThumbnailSize.SIZE_64, AndesThumbnailState.ENABLED)

        val andesThumbnail72Disabled = AndesThumbnail(this, AndesColor(R.color.andes_red_800), "",
            AndesThumbnailHierarchy.QUIET, R.drawable.andes_otros_almanaque_24, AndesThumbnailType.ICON,
            AndesThumbnailSize.SIZE_72, AndesThumbnailState.DISABLED)

        val andesThumbnail80Disabled = AndesThumbnail(this, AndesColor(R.color.andes_red_800), "",
            AndesThumbnailHierarchy.QUIET, R.drawable.andes_otros_almanaque_24, AndesThumbnailType.ICON,
            AndesThumbnailSize.SIZE_80, AndesThumbnailState.DISABLED)

        linearQuiet.addView(andesThumbnail24Enabled, linearQuiet.childCount - 1)
        linearQuiet.addView(andesThumbnail32Enabled, linearQuiet.childCount - 1)
        linearQuiet.addView(andesThumbnail40Disabled, linearQuiet.childCount - 1)
        linearQuiet.addView(andesThumbnail48Disabled, linearQuiet.childCount - 1)
        linearQuiet.addView(andesThumbnail56Enabled, linearQuiet.childCount - 1)
        linearQuiet.addView(andesThumbnail64Enabled, linearQuiet.childCount - 1)
        linearQuiet.addView(andesThumbnail72Disabled, linearQuiet.childCount - 1)
        linearQuiet.addView(andesThumbnail80Disabled, linearQuiet.childCount - 1)

        bindAndesSpecsButton(container)
    }

    private fun addThumbnailsLoud(container: View) {
        val linearLoud = container.findViewById<LinearLayout>(R.id.andes_loud_container)

        val andesThumbnail24Enabled = AndesThumbnail(this, AndesColor(R.color.andes_blue_ml_500), "",
            AndesThumbnailHierarchy.LOUD, R.drawable.andes_otros_almanaque_24, AndesThumbnailType.ICON,
            AndesThumbnailSize.SIZE_24, AndesThumbnailState.ENABLED)

        val andesThumbnail32Enabled = AndesThumbnail(this, AndesColor(R.color.andes_blue_ml_500), "",
            AndesThumbnailHierarchy.LOUD, R.drawable.andes_otros_almanaque_24, AndesThumbnailType.ICON,
            AndesThumbnailSize.SIZE_32, AndesThumbnailState.ENABLED)

        val andesThumbnail40Disabled = AndesThumbnail(this, AndesColor(R.color.andes_blue_ml_500), "",
            AndesThumbnailHierarchy.LOUD, R.drawable.andes_otros_almanaque_24, AndesThumbnailType.ICON,
            AndesThumbnailSize.SIZE_40, AndesThumbnailState.DISABLED)

        val andesThumbnail48Disabled = AndesThumbnail(this, AndesColor(R.color.andes_blue_ml_500), "",
            AndesThumbnailHierarchy.LOUD, R.drawable.andes_otros_almanaque_24, AndesThumbnailType.ICON,
            AndesThumbnailSize.SIZE_48, AndesThumbnailState.DISABLED)

        val andesThumbnail56Enabled = AndesThumbnail(this, AndesColor(R.color.andes_red_800), "",
            AndesThumbnailHierarchy.LOUD, R.drawable.andes_otros_almanaque_24, AndesThumbnailType.ICON,
            AndesThumbnailSize.SIZE_56, AndesThumbnailState.ENABLED)

        val andesThumbnail64Enabled = AndesThumbnail(this, AndesColor(R.color.andes_red_800), "",
            AndesThumbnailHierarchy.LOUD, R.drawable.andes_otros_almanaque_24, AndesThumbnailType.ICON,
            AndesThumbnailSize.SIZE_64, AndesThumbnailState.ENABLED)

        val andesThumbnail72Disabled = AndesThumbnail(this, AndesColor(R.color.andes_red_800), "",
            AndesThumbnailHierarchy.LOUD, R.drawable.andes_otros_almanaque_24, AndesThumbnailType.ICON,
            AndesThumbnailSize.SIZE_72, AndesThumbnailState.DISABLED)

        val andesThumbnail80Disabled = AndesThumbnail(this, AndesColor(R.color.andes_red_800), "",
            AndesThumbnailHierarchy.LOUD, R.drawable.andes_otros_almanaque_24, AndesThumbnailType.ICON,
            AndesThumbnailSize.SIZE_80, AndesThumbnailState.DISABLED)

        linearLoud.addView(andesThumbnail24Enabled, linearLoud.childCount - 1)
        linearLoud.addView(andesThumbnail32Enabled, linearLoud.childCount - 1)
        linearLoud.addView(andesThumbnail40Disabled, linearLoud.childCount - 1)
        linearLoud.addView(andesThumbnail48Disabled, linearLoud.childCount - 1)
        linearLoud.addView(andesThumbnail56Enabled, linearLoud.childCount - 1)
        linearLoud.addView(andesThumbnail64Enabled, linearLoud.childCount - 1)
        linearLoud.addView(andesThumbnail72Disabled, linearLoud.childCount - 1)
        linearLoud.addView(andesThumbnail80Disabled, linearLoud.childCount - 1)

        bindAndesSpecsButton(container)
    }

    private fun addThumbnailsDefault(container: View) {
        val linearDefault = container.findViewById<LinearLayout>(R.id.andes_thumbnail_default_container)

        val andesThumbnail24Enabled = AndesThumbnail(this, AndesColor(R.color.andes_yellow_ml_500), "",
            AndesThumbnailHierarchy.DEFAULT, R.drawable.andes_otros_almanaque_24, AndesThumbnailType.ICON,
            AndesThumbnailSize.SIZE_24, AndesThumbnailState.ENABLED)

        val andesThumbnail32Enabled = AndesThumbnail(this, AndesColor(R.color.andes_yellow_ml_500), "",
            AndesThumbnailHierarchy.DEFAULT, R.drawable.andes_otros_almanaque_24, AndesThumbnailType.ICON,
            AndesThumbnailSize.SIZE_32, AndesThumbnailState.ENABLED)

        val andesThumbnail40Disabled = AndesThumbnail(this, AndesColor(R.color.andes_yellow_ml_500), "",
            AndesThumbnailHierarchy.DEFAULT, R.drawable.andes_otros_almanaque_24, AndesThumbnailType.ICON,
            AndesThumbnailSize.SIZE_40, AndesThumbnailState.DISABLED)

        val andesThumbnail48Disabled = AndesThumbnail(this, AndesColor(R.color.andes_yellow_ml_500), "",
            AndesThumbnailHierarchy.DEFAULT, R.drawable.andes_otros_almanaque_24, AndesThumbnailType.ICON,
            AndesThumbnailSize.SIZE_48, AndesThumbnailState.DISABLED)

        val andesThumbnail56Enabled = AndesThumbnail(this, AndesColor(R.color.andes_yellow_ml_500), "",
            AndesThumbnailHierarchy.DEFAULT, R.drawable.andes_otros_almanaque_24, AndesThumbnailType.ICON,
            AndesThumbnailSize.SIZE_56, AndesThumbnailState.ENABLED)

        val andesThumbnail64Enabled = AndesThumbnail(this, AndesColor(R.color.andes_yellow_ml_500), "",
            AndesThumbnailHierarchy.DEFAULT, R.drawable.andes_otros_almanaque_24, AndesThumbnailType.ICON,
            AndesThumbnailSize.SIZE_64, AndesThumbnailState.ENABLED)

        val andesThumbnail72Disabled = AndesThumbnail(this, AndesColor(R.color.andes_yellow_ml_500), "",
            AndesThumbnailHierarchy.DEFAULT, R.drawable.andes_otros_almanaque_24, AndesThumbnailType.ICON,
            AndesThumbnailSize.SIZE_72, AndesThumbnailState.DISABLED)

        val andesThumbnail80Disabled = AndesThumbnail(this, AndesColor(R.color.andes_yellow_ml_500), "",
            AndesThumbnailHierarchy.DEFAULT, R.drawable.andes_otros_almanaque_24, AndesThumbnailType.ICON,
            AndesThumbnailSize.SIZE_80, AndesThumbnailState.DISABLED)

        linearDefault.addView(andesThumbnail24Enabled, linearDefault.childCount - 1)
        linearDefault.addView(andesThumbnail32Enabled, linearDefault.childCount - 1)
        linearDefault.addView(andesThumbnail40Disabled, linearDefault.childCount - 1)
        linearDefault.addView(andesThumbnail48Disabled, linearDefault.childCount - 1)
        linearDefault.addView(andesThumbnail56Enabled, linearDefault.childCount - 1)
        linearDefault.addView(andesThumbnail64Enabled, linearDefault.childCount - 1)
        linearDefault.addView(andesThumbnail72Disabled, linearDefault.childCount - 1)
        linearDefault.addView(andesThumbnail80Disabled, linearDefault.childCount - 1)

        bindAndesSpecsButton(container)
    }

    private fun bindAndesSpecsButton(container: View) {
        container.findViewById<AndesButton>(R.id.andesui_demoapp_andes_specs_button).setOnClickListener {
            launchSpecs(container.context, AndesSpecs.BUTTON)
        }
    }

    class AndesShowcasePagerAdapter(private val context: Context) : PagerAdapter() {

        var views: List<View>

        init {
            views = initViews()
        }

        override fun instantiateItem(container: ViewGroup, position: Int): View {
            container.addView(views[position])
            return views[position]
        }

        override fun destroyItem(container: ViewGroup, position: Int, view: Any) {
            container.removeView(view as View?)
        }

        override fun isViewFromObject(view: View, other: Any): Boolean {
            return view == other
        }

        override fun getCount(): Int = views.size

        private fun initViews(): List<View> {
            val inflater = LayoutInflater.from(context)
            val layoutLoudThumbnails = inflater.inflate(R.layout.andesui_quiet_thumbnails_showcase, null, false)
            val layoutQuietThumbnails = inflater.inflate(R.layout.andesui_loud_thumbnails_showcase, null, false)
            val layoutDefaultThumbnails = inflater.inflate(R.layout.andesui_default_thumbnails_showcase, null, false)

            return listOf<View>(layoutLoudThumbnails, layoutQuietThumbnails, layoutDefaultThumbnails)
        }
    }
}
