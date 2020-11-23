package com.mercadolibre.android.andesui.demoapp.feature

import android.content.Context
import android.os.Bundle
import android.support.v4.view.PagerAdapter
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.mercadolibre.android.andesui.demoapp.R
import com.mercadolibre.android.andesui.demoapp.feature.utils.PageIndicator
import com.mercadolibre.android.andesui.list.AndesListViewItem
import com.mercadolibre.android.andesui.list.AndesListViewItemChevron
import com.mercadolibre.android.andesui.list.utils.AndesListDelegate
import com.mercadolibre.android.andesui.list.AndesListViewItemSimple
import com.mercadolibre.android.andesui.list.size.AndesListViewItemSize
import kotlinx.android.synthetic.main.andesui_list_showcase.view.*

class ListShowcaseActivity : AppCompatActivity(), AndesListDelegate {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.andesui_showcase_main)

        setSupportActionBar(findViewById(R.id.andesui_nav_bar))
        supportActionBar?.title = resources.getString(R.string.andesui_demoapp_screen_list)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val viewPager = findViewById<ViewPager>(R.id.andesui_viewpager)
        viewPager.adapter = AndesShowcasePagerAdapter(this)
        val indicator = findViewById<PageIndicator>(R.id.page_indicator)
        indicator.attach(viewPager)

        val adapter = viewPager.adapter as AndesShowcasePagerAdapter
        adapter.views[0].andesList.delegate = this
        adapter.views[0].andesList.size = AndesListViewItemSize.LARGE
    }

    class AndesShowcasePagerAdapter(private val context: Context) : PagerAdapter() {

        var views: List<View>

        init {
            views = initViews()
        }

        override fun instantiateItem(container: ViewGroup, position: Int): Any {
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
            val layout = inflater.inflate(R.layout.andesui_list_showcase, null, false)
            return listOf<View>(layout)
        }

    }

    override fun onItemClick(position: Int) {
        Toast.makeText(this, getDataSet()[position].title, Toast.LENGTH_SHORT).show()
    }

    override fun bind(view: View, position: Int): AndesListViewItem {
        val row = AndesListViewItemSimple(this,getDataSet()[position].title, getDataSet()[position].subtitle, AndesListViewItemSize.MEDIUM)
//        val row = AndesListViewItemChevron(this,getDataSet()[position].title, AndesListViewItemSize.MEDIUM)

//        val row = AndesListRow.Builder(getDataSet()[position].title)
////                .description(getDataSet()[position].description)
////                .isSelectable(getDataSet()[position].selectable)
////                .actionableComponent(ActionableComponent.AddButton(AndesButton(this, AndesButtonSize.MEDIUM, AndesButtonHierarchy.TRANSPARENT)))
//                .build()

        return row
    }

    override fun getDataSetSize(): Int = getDataSet().size


    private fun getDataSet() = listOf(
            Model("title 1", "Desc 1", false),
            Model("title 2", "Desc 2", true),
            Model("title 3", "Desc 3", false))

    class Model(val title: String, val subtitle: String, val selectable: Boolean)
}