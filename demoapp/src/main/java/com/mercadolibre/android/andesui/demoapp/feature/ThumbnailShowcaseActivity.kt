package com.mercadolibre.android.andesui.demoapp.feature

import android.content.Context
import android.os.Bundle
import android.support.v4.view.PagerAdapter
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.LinearLayout
import android.widget.Spinner
import android.widget.Switch
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
import kotlinx.android.synthetic.main.andesui_thumbnail_showcase_change.*

class ThumbnailShowcaseActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.andesui_showcase_main)

        setSupportActionBar(findViewById(R.id.andesui_nav_bar))
        supportActionBar?.title = resources.getString(R.string.andesui_demoapp_screen_thumbnail)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val viewPager = findViewById<ViewPager>(R.id.andesui_viewpager)
        viewPager.adapter = AndesShowcasePagerAdapter(this)
        val indicator = findViewById<PageIndicator>(R.id.page_indicator)
        indicator.attach(viewPager)

        val adapter = viewPager.adapter as AndesShowcasePagerAdapter
        addDynamicThumbnails(adapter.views[0])
        // addThumbnailsQuiet(adapter.views[1])
    }

    private fun addDynamicThumbnails(container: View) {
        val hierarchySpinner: Spinner = container.findViewById(R.id.hierarchy_spinner)
        ArrayAdapter.createFromResource(
            this, R.array.thumbnail_hierarchy_spinner, android.R.layout.simple_spinner_item)
            .also { adapter ->
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                hierarchySpinner.adapter = adapter
            }

        val typeSpinner: Spinner = container.findViewById(R.id.type_spinner)
        ArrayAdapter.createFromResource(
            this, R.array.thumbnail_type_spinner, android.R.layout.simple_spinner_item)
            .also { adapter ->
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                typeSpinner.adapter = adapter
            }

        val sizeSpinner: Spinner = container.findViewById(R.id.size_spinner)
        ArrayAdapter.createFromResource(
            this, R.array.thumbnail_size_spinner, android.R.layout.simple_spinner_item)
            .also { adapter ->
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                sizeSpinner.adapter = adapter
            }

        val stateSwitch: Switch = container.findViewById(R.id.state_switch)

        val clearButton: AndesButton = container.findViewById(R.id.clear_button)
        val changeButton: AndesButton = container.findViewById(R.id.change_button)

        val andesThumbnail: AndesThumbnail = container.findViewById(R.id.andes_thumbnail)

        hierarchySpinner.setSelection(1)
        typeSpinner.setSelection(0)
        sizeSpinner.setSelection(3)
        stateSwitch.isChecked = true

        clearButton.setOnClickListener {
            group.visibility = View.VISIBLE
            andesThumbnail.visibility = View.VISIBLE

            hierarchySpinner.setSelection(1)
            typeSpinner.setSelection(0)
            sizeSpinner.setSelection(3)
            stateSwitch.isChecked = true

            andesThumbnail.type = AndesThumbnailType.ICON
            andesThumbnail.hierarchy = AndesThumbnailHierarchy.DEFAULT
            andesThumbnail.size = AndesThumbnailSize.SIZE_80
            andesThumbnail.accentColor = AndesColor(R.color.andes_red_700)
            andesThumbnail.state = AndesThumbnailState.ENABLED
        }

        changeButton.setOnClickListener {
                    val type = when (typeSpinner.getItemAtPosition(typeSpinner.selectedItemPosition)) {
                        "Icon" -> AndesThumbnailType.ICON
                        else -> AndesThumbnailType.ICON
                    }

                    val hierarchy = when (hierarchySpinner.getItemAtPosition(hierarchySpinner.selectedItemPosition)) {
                        "Loud" -> AndesThumbnailHierarchy.LOUD
                        "Quiet" -> AndesThumbnailHierarchy.QUIET
                        "Default" -> AndesThumbnailHierarchy.DEFAULT
                        else -> AndesThumbnailHierarchy.LOUD
                    }

                    val size = when (sizeSpinner.getItemAtPosition(sizeSpinner.selectedItemPosition)) {
                        "Size 24" -> AndesThumbnailSize.SIZE_24
                        "Size 32" -> AndesThumbnailSize.SIZE_32
                        "Size 40" -> AndesThumbnailSize.SIZE_40
                        "Size 48" -> AndesThumbnailSize.SIZE_48
                        "Size 56" -> AndesThumbnailSize.SIZE_56
                        "Size 64" -> AndesThumbnailSize.SIZE_64
                        "Size 72" -> AndesThumbnailSize.SIZE_72
                        "Size 80" -> AndesThumbnailSize.SIZE_80
                        else -> AndesThumbnailSize.SIZE_48
                    }

                    andesThumbnail.type = type
                    andesThumbnail.hierarchy = hierarchy
                    andesThumbnail.size = size
                    andesThumbnail.state = if (stateSwitch.isChecked) AndesThumbnailState.ENABLED else AndesThumbnailState.DISABLED
            }
        }

    private fun addThumbnailsQuiet(container: View) {
        val linearQuiet = container.findViewById<LinearLayout>(R.id.andes_thumbnail_quiet_container)

        val andesThumbnail24Enabled = AndesThumbnail(this, AndesColor(R.color.andes_blue_ml_500),
            AndesThumbnailHierarchy.QUIET, applicationContext.resources.getDrawable(R.drawable.andes_ui_close_12),
            AndesThumbnailType.ICON, AndesThumbnailSize.SIZE_24, AndesThumbnailState.ENABLED)

        val andesThumbnail32Enabled = AndesThumbnail(this, AndesColor(R.color.andes_blue_ml_500),
            AndesThumbnailHierarchy.QUIET, applicationContext.resources.getDrawable(R.drawable.andes_ui_close_12),
            AndesThumbnailType.ICON, AndesThumbnailSize.SIZE_32, AndesThumbnailState.ENABLED)

        val andesThumbnail40Disabled = AndesThumbnail(this, AndesColor(R.color.andes_blue_ml_500),
            AndesThumbnailHierarchy.QUIET, applicationContext.resources.getDrawable(R.drawable.andes_ui_close_12),
            AndesThumbnailType.ICON, AndesThumbnailSize.SIZE_40, AndesThumbnailState.DISABLED)

        val andesThumbnail48Disabled = AndesThumbnail(this, AndesColor(R.color.andes_blue_ml_500),
            AndesThumbnailHierarchy.QUIET, applicationContext.resources.getDrawable(R.drawable.andes_ui_close_12),
            AndesThumbnailType.ICON, AndesThumbnailSize.SIZE_48, AndesThumbnailState.DISABLED)

        val andesThumbnail56Enabled = AndesThumbnail(this, AndesColor(R.color.andes_red_800),
            AndesThumbnailHierarchy.QUIET, applicationContext.resources.getDrawable(R.drawable.andes_ui_close_12),
            AndesThumbnailType.ICON, AndesThumbnailSize.SIZE_56, AndesThumbnailState.ENABLED)

        val andesThumbnail64Enabled = AndesThumbnail(this, AndesColor(R.color.andes_red_800),
            AndesThumbnailHierarchy.QUIET, applicationContext.resources.getDrawable(R.drawable.andes_ui_close_12),
            AndesThumbnailType.ICON, AndesThumbnailSize.SIZE_64, AndesThumbnailState.ENABLED)

        val andesThumbnail72Disabled = AndesThumbnail(this, AndesColor(R.color.andes_red_800),
            AndesThumbnailHierarchy.QUIET, applicationContext.resources.getDrawable(R.drawable.andes_ui_close_12),
            AndesThumbnailType.ICON, AndesThumbnailSize.SIZE_72, AndesThumbnailState.DISABLED)

        val andesThumbnail80Disabled = AndesThumbnail(this, AndesColor(R.color.andes_red_800),
            AndesThumbnailHierarchy.QUIET, applicationContext.resources.getDrawable(R.drawable.andes_ui_close_12),
            AndesThumbnailType.ICON, AndesThumbnailSize.SIZE_80, AndesThumbnailState.DISABLED)
        andesThumbnail24Enabled.size = AndesThumbnailSize.SIZE_80
        andesThumbnail24Enabled.accentColor = AndesColor(R.color.andes_orange_800)

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

    private fun bindAndesSpecsButton(container: View) {
        container.findViewById<AndesButton>(R.id.andesui_demoapp_andes_specs_button).setOnClickListener {
            launchSpecs(container.context, AndesSpecs.THUMBNAIL)
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
            val layoutChangeThumbnails = inflater.inflate(R.layout.andesui_thumbnail_showcase_change, null, false)
            // val layoutQuietThumbnails = inflater.inflate(R.layout.andesui_quiet_thumbnails_showcase, null, false)

            return listOf<View>(layoutChangeThumbnails)
        }
    }
}
