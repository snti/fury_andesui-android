package com.mercadolibre.android.andesui.demoapp.feature

import android.content.Context
import android.os.Bundle
import android.support.v4.view.PagerAdapter
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CalendarView
import android.widget.ScrollView
import android.widget.Toast
import com.mercadolibre.android.andesui.datepicker.AndesDatePicker
import com.mercadolibre.android.andesui.demoapp.R
import com.mercadolibre.android.andesui.demoapp.feature.utils.PageIndicator
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

class DatePickerShowcaseActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.andesui_showcase_main)

        setSupportActionBar(findViewById(R.id.andesui_nav_bar))
        supportActionBar?.title = resources.getString(R.string.andesui_demoapp_screen_datepicker)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val viewPager = findViewById<ViewPager>(R.id.andesui_viewpager)
        viewPager.adapter = AndesShowcasePagerAdapter(this)
        val indicator = findViewById<PageIndicator>(R.id.page_indicator)
        indicator.attach(viewPager)
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

            val staticDatePickerLayout = addStaticDatePicker(inflater)

            return listOf(staticDatePickerLayout)
        }

        private fun addStaticDatePicker(inflater: LayoutInflater): View {
            val layoutDatePicker = inflater.inflate(
                    R.layout.andesui_date_picker_showcase,
                    null,
                    false
            ) as ScrollView

            val datepicker: AndesDatePicker = layoutDatePicker.findViewById(R.id.andesDatePicker)

            datepicker.setupMinDate("04/11/2020", "dd/MM/yyyy")

            datepicker.setupMaxDate("2020-11-15", "yyyy-MM-dd")

            datepicker.setupButtonText("Aplicar")

            datepicker.setupBtnVisibility(false)


            datepicker.setDateListener(object : AndesDatePicker.ApplyDatePickerClickListener {
                override fun onDateApply(date: Calendar) {
                    val dateFormatter = DateFormat.getDateInstance(DateFormat.SHORT)
                    val formattedDate = dateFormatter.format(date.time)
                    Toast.makeText(context, formattedDate, Toast.LENGTH_SHORT).show()
                }})
            return layoutDatePicker

        }
    }
}