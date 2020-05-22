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
import com.mercadolibre.android.andesui.button.AndesButton
import com.mercadolibre.android.andesui.demoapp.PageIndicator
import com.mercadolibre.android.andesui.demoapp.R
import com.mercadolibre.android.andesui.snackbar.AndesSnackbar
import com.mercadolibre.android.andesui.snackbar.type.AndesSnackbarType

class SnackbarShowcaseActivity : AppCompatActivity() {

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
        addDynamicSnackbar(adapter.views[0])
    }

    private fun addDynamicSnackbar(container: View) {
        bindAndesSpecsButton(container)
        val test: AndesButton = container.findViewById(R.id.test)

        test.setOnClickListener {

            val snackbar = AndesSnackbar(this, container, AndesSnackbarType.ERROR, "Algo es algo", 5000)

//            snackbar.text = "cambiamos"
//            snackbar.type = AndesSnackbarType.NEUTRAL
//            snackbar.duration = 4000

            snackbar.show()

        }

    }


    private fun bindAndesSpecsButton(container: View) {
//        container.findViewById<AndesButton>(R.id.andesui_demoapp_andes_badge_specs_button).setOnClickListener {
//            launchSpecs(container.context, AndesSpecs.BADGE)
//        }
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
            val layout = inflater.inflate(R.layout.andesui_snackbar_showcase_change, null, false)
            return listOf<View>(layout)
        }
    }
}
