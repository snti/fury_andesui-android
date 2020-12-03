package com.mercadolibre.android.andesui.demoapp.feature

import android.os.Bundle
import androidx.viewpager.widget.ViewPager
import androidx.appcompat.app.AppCompatActivity
import com.mercadolibre.android.andesui.demoapp.InflateTextfieldHelper
import com.mercadolibre.android.andesui.demoapp.feature.utils.PageIndicator
import com.mercadolibre.android.andesui.demoapp.R

class TextfieldShowcaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.andesui_showcase_main)

        setSupportActionBar(findViewById(R.id.andesui_nav_bar))
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val viewPager = findViewById<ViewPager>(R.id.andesui_viewpager)
        val adapter = AndesTextfieldShowcasePagerAdapter(initViews())
        viewPager.adapter = adapter
        viewPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrollStateChanged(position: Int) = Unit
            override fun onPageScrolled(position: Int, p1: Float, p2: Int) = Unit
            override fun onPageSelected(position: Int) {
                supportActionBar?.title = getString(adapter.getTitleResource(position))
            }
        })
        val indicator = findViewById<PageIndicator>(R.id.page_indicator)
        indicator.attach(viewPager)
    }

    private fun initViews(): List<TextFieldPage> {
        val dynamicTextfieldLayout = InflateTextfieldHelper.inflateAndesTextfield(this)
        val dynamicTextareaLayout =  InflateTextfieldHelper.inflateAndesTextfieldArea(this)
        val dynamicTextfieldCodeLayout = InflateTextfieldHelper.inflateAndesTextfieldCode(this)
        val staticTextfieldLayout = InflateTextfieldHelper.inflateStaticTextfieldLayout(this)

        return listOf(
            TextFieldPage(dynamicTextfieldLayout),
            TextFieldPage(dynamicTextareaLayout),
            TextFieldPage(dynamicTextfieldCodeLayout, R.string.andesui_demoapp_screen_textfield_code),
            TextFieldPage(staticTextfieldLayout)
        )
    }
}
