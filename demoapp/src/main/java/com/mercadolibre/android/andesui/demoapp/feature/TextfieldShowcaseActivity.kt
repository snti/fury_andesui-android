package com.mercadolibre.android.andesui.demoapp.feature

import android.content.Context
import android.os.Bundle
import android.support.v4.view.PagerAdapter
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ScrollView
import com.mercadolibre.android.andesui.demoapp.PageIndicator
import com.mercadolibre.android.andesui.demoapp.R

class TextfieldShowcaseActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.andesui_showcase_main)

        val viewPager = findViewById<ViewPager>(R.id.andesui_viewpager)
        viewPager.adapter = AndesShowcasePagerAdapter(this)
        val indicator = findViewById<PageIndicator>(R.id.page_indicator)
        indicator.attach(viewPager)
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

        val textfieldLayout = addTextfield(inflater)

        return listOf(textfieldLayout)
    }


    private fun addTextfield(inflater: LayoutInflater): View {
        val layoutTextfield = inflater.inflate(R.layout.andesui_textfield_showcase, null, false) as ScrollView

        return layoutTextfield
    }
}