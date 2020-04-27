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
import com.mercadolibre.android.andesui.badge.AndesBadgePill
import com.mercadolibre.android.andesui.badge.border.AndesBadgePillBorder
import com.mercadolibre.android.andesui.badge.hierarchy.AndesBadgePillHierarchy
import com.mercadolibre.android.andesui.badge.size.AndesBadgePillSize
import com.mercadolibre.android.andesui.badge.type.AndesBadgeType
import com.mercadolibre.android.andesui.button.AndesButton
import com.mercadolibre.android.andesui.demoapp.AndesSpecs
import com.mercadolibre.android.andesui.demoapp.PageIndicator
import com.mercadolibre.android.andesui.demoapp.R
import com.mercadolibre.android.andesui.demoapp.launchSpecs

class BadgeShowcaseActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.andesui_showcase_main)

        setSupportActionBar(findViewById(R.id.andesui_nav_bar))
        supportActionBar?.title = resources.getString(R.string.andesui_demoapp_screen_badge)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val viewPager = findViewById<ViewPager>(R.id.andesui_viewpager)
        viewPager.adapter = AndesShowcasePagerAdapter(this)
        val indicator = findViewById<PageIndicator>(R.id.page_indicator)
        indicator.attach(viewPager)

        val adapter = viewPager.adapter as AndesShowcasePagerAdapter
        addLoudBadges(adapter.views[0])
        addQuietBadges(adapter.views[1])
    }

    private fun addQuietBadges(container: View) {
        val andesBadgeSmallNeutral = AndesBadgePill(this, AndesBadgePillHierarchy.QUIET, AndesBadgeType.NEUTRAL, AndesBadgePillBorder.STANDARD, AndesBadgePillSize.SMALL)
        andesBadgeSmallNeutral.text = "small neutral quiet"

        val andesBadgeSmallHigh = AndesBadgePill(this, AndesBadgePillHierarchy.QUIET, AndesBadgeType.HIGHLIGHT, AndesBadgePillBorder.STANDARD, AndesBadgePillSize.SMALL)
        andesBadgeSmallHigh.text = "small highlight quiet"

        val andesBadgeSmallWarning = AndesBadgePill(this, AndesBadgePillHierarchy.QUIET, AndesBadgeType.WARNING, AndesBadgePillBorder.STANDARD, AndesBadgePillSize.SMALL)
        andesBadgeSmallWarning.text = "small warning quiet"

        val andesBadgeSmallError = AndesBadgePill(this, AndesBadgePillHierarchy.QUIET, AndesBadgeType.ERROR, AndesBadgePillBorder.STANDARD, AndesBadgePillSize.SMALL)
        andesBadgeSmallError.text = "small error quiet"

        val andesBadgeSmallSuccess = AndesBadgePill(this, AndesBadgePillHierarchy.QUIET, AndesBadgeType.SUCCESS, AndesBadgePillBorder.STANDARD, AndesBadgePillSize.SMALL)
        andesBadgeSmallSuccess.text = "small success quiet"

        val andesBadgeLargeNeutral = AndesBadgePill(this, AndesBadgePillHierarchy.QUIET, AndesBadgeType.NEUTRAL, AndesBadgePillBorder.STANDARD, AndesBadgePillSize.LARGE)
        andesBadgeLargeNeutral.text = "large neutral quiet"

        val andesBadgeLargeHighlight = AndesBadgePill(this, AndesBadgePillHierarchy.QUIET, AndesBadgeType.HIGHLIGHT, AndesBadgePillBorder.STANDARD, AndesBadgePillSize.LARGE)
        andesBadgeLargeHighlight.text = "large highlight quiet"

        val andesBadgeLargeWarning = AndesBadgePill(this, AndesBadgePillHierarchy.QUIET, AndesBadgeType.WARNING, AndesBadgePillBorder.STANDARD, AndesBadgePillSize.LARGE)
        andesBadgeLargeWarning.text = "large warning quiet"

        val andesBadgeLargeError = AndesBadgePill(this, AndesBadgePillHierarchy.QUIET, AndesBadgeType.ERROR, AndesBadgePillBorder.STANDARD, AndesBadgePillSize.LARGE)
        andesBadgeLargeError.text = "large error quiet"

        val andesBadgeLargeSuccess = AndesBadgePill(this, AndesBadgePillHierarchy.QUIET, AndesBadgeType.SUCCESS, AndesBadgePillBorder.STANDARD, AndesBadgePillSize.LARGE)
        andesBadgeLargeSuccess.text = "large success quiet"

        val params = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        )
        params.setMargins(0, 0, 0, resources.getDimension(R.dimen.badge_margin_vertical).toInt())

        andesBadgeSmallNeutral.layoutParams = params
        andesBadgeSmallHigh.layoutParams = params
        andesBadgeSmallWarning.layoutParams = params
        andesBadgeSmallError.layoutParams = params
        andesBadgeSmallSuccess.layoutParams = params
        andesBadgeLargeNeutral.layoutParams = params
        andesBadgeLargeHighlight.layoutParams = params
        andesBadgeLargeWarning.layoutParams = params
        andesBadgeLargeError.layoutParams = params
        andesBadgeLargeSuccess.layoutParams = params

        val linearQuiet = container.findViewById<LinearLayout>(R.id.andes_badges_quiet_container)
        linearQuiet.addView(andesBadgeSmallNeutral, linearQuiet.childCount - 1)
        linearQuiet.addView(andesBadgeSmallHigh, linearQuiet.childCount - 1)
        linearQuiet.addView(andesBadgeSmallWarning, linearQuiet.childCount - 1)
        linearQuiet.addView(andesBadgeSmallError, linearQuiet.childCount - 1)
        linearQuiet.addView(andesBadgeSmallSuccess, linearQuiet.childCount - 1)
        linearQuiet.addView(andesBadgeLargeNeutral, linearQuiet.childCount - 1)
        linearQuiet.addView(andesBadgeLargeHighlight, linearQuiet.childCount - 1)
        linearQuiet.addView(andesBadgeLargeWarning, linearQuiet.childCount - 1)
        linearQuiet.addView(andesBadgeLargeError, linearQuiet.childCount - 1)
        linearQuiet.addView(andesBadgeLargeSuccess, linearQuiet.childCount - 1)

        bindAndesSpecsButton(container)
    }

    private fun addLoudBadges(container: View) {
        val andesBadgeSmallNeutral = AndesBadgePill(this, AndesBadgePillHierarchy.LOUD, AndesBadgeType.NEUTRAL, AndesBadgePillBorder.STANDARD, AndesBadgePillSize.SMALL)
        andesBadgeSmallNeutral.text = "small neutral loud"

        val andesBadgeSmallHigh = AndesBadgePill(this, AndesBadgePillHierarchy.LOUD, AndesBadgeType.HIGHLIGHT, AndesBadgePillBorder.STANDARD, AndesBadgePillSize.SMALL)
        andesBadgeSmallHigh.text = "small highlight loud"

        val andesBadgeSmallWarning = AndesBadgePill(this, AndesBadgePillHierarchy.LOUD, AndesBadgeType.WARNING, AndesBadgePillBorder.STANDARD, AndesBadgePillSize.SMALL)
        andesBadgeSmallWarning.text = "small warning loud"

        val andesBadgeSmallError = AndesBadgePill(this, AndesBadgePillHierarchy.LOUD, AndesBadgeType.ERROR, AndesBadgePillBorder.STANDARD, AndesBadgePillSize.SMALL)
        andesBadgeSmallError.text = "small error loud"

        val andesBadgeSmallSuccess = AndesBadgePill(this, AndesBadgePillHierarchy.LOUD, AndesBadgeType.SUCCESS, AndesBadgePillBorder.STANDARD, AndesBadgePillSize.SMALL)
        andesBadgeSmallSuccess.text = "small success loud"

        val andesBadgeLargeNeutral = AndesBadgePill(this, AndesBadgePillHierarchy.LOUD, AndesBadgeType.NEUTRAL, AndesBadgePillBorder.STANDARD, AndesBadgePillSize.LARGE)
        andesBadgeLargeNeutral.text = "large neutral loud"

        val andesBadgeLargeHighlight = AndesBadgePill(this, AndesBadgePillHierarchy.LOUD, AndesBadgeType.HIGHLIGHT, AndesBadgePillBorder.STANDARD, AndesBadgePillSize.LARGE)
        andesBadgeLargeHighlight.text = "large highlight loud"

        val andesBadgeLargeWarning = AndesBadgePill(this, AndesBadgePillHierarchy.LOUD, AndesBadgeType.WARNING, AndesBadgePillBorder.STANDARD, AndesBadgePillSize.LARGE)
        andesBadgeLargeWarning.text = "large warning loud"

        val andesBadgeLargeError = AndesBadgePill(this, AndesBadgePillHierarchy.LOUD, AndesBadgeType.ERROR, AndesBadgePillBorder.STANDARD, AndesBadgePillSize.LARGE)
        andesBadgeLargeError.text = "large error loud"

        val params = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        )
        params.setMargins(0, 0, 0, resources.getDimension(R.dimen.badge_margin_vertical).toInt())

        andesBadgeSmallNeutral.layoutParams = params
        andesBadgeSmallHigh.layoutParams = params
        andesBadgeSmallWarning.layoutParams = params
        andesBadgeSmallError.layoutParams = params
        andesBadgeSmallSuccess.layoutParams = params
        andesBadgeLargeNeutral.layoutParams = params
        andesBadgeLargeHighlight.layoutParams = params
        andesBadgeLargeWarning.layoutParams = params
        andesBadgeLargeError.layoutParams = params

        val linearLoud = container.findViewById<LinearLayout>(R.id.andes_badges_loud_container)
        linearLoud.addView(andesBadgeSmallNeutral, linearLoud.childCount - 1)
        linearLoud.addView(andesBadgeSmallHigh, linearLoud.childCount - 1)
        linearLoud.addView(andesBadgeSmallWarning, linearLoud.childCount - 1)
        linearLoud.addView(andesBadgeSmallError, linearLoud.childCount - 1)
        linearLoud.addView(andesBadgeSmallSuccess, linearLoud.childCount - 1)
        linearLoud.addView(andesBadgeLargeNeutral, linearLoud.childCount - 1)
        linearLoud.addView(andesBadgeLargeHighlight, linearLoud.childCount - 1)
        linearLoud.addView(andesBadgeLargeWarning, linearLoud.childCount - 1)
        linearLoud.addView(andesBadgeLargeError, linearLoud.childCount - 1)

        bindAndesSpecsButton(container)
    }

    private fun bindAndesSpecsButton(container: View) {
        container.findViewById<AndesButton>(R.id.andesui_demoapp_andes_badge_specs_button).setOnClickListener {
            launchSpecs(container.context, AndesSpecs.BADGE)
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
            val layoutLoudButtons = inflater.inflate(R.layout.andesui_loud_badges_showcase, null, false)
            val layoutQuietButtons = inflater.inflate(R.layout.andesui_quiet_badges_showcase, null, false)

            return listOf<View>(layoutLoudButtons, layoutQuietButtons)
        }
    }
}
