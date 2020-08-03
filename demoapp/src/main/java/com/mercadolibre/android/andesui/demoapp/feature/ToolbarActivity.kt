package com.mercadolibre.android.andesui.demoapp.feature

import android.graphics.PorterDuff
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.widget.LinearLayout
import android.widget.TextView
import com.mercadolibre.android.andesui.demoapp.R
import kotlinx.android.synthetic.main.andesui_toolbar.*

class ToolbarActivity : AppCompatActivity() {

    var toolbarIsShowing: Boolean = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.andesui_toolbar)




        val showActionBar = false
        val title = "Titulo de la pantalla"





        val toolbar: Toolbar = findViewById(R.id.andes_toolbar)
        if (showActionBar) {
            renderToolbar(
                    backgroundColor = R.color.andes_blue_ml_500,
                    titleColor = R.color.andes_white,
                    iconColor = R.color.andes_white,
                    title = title,
                    showTitle = true
            )
        } else {
            renderToolbar(
                    backgroundColor = R.color.andes_transparent,
                    titleColor = R.color.andes_gray_800_solid,
                    iconColor = R.color.andes_gray_800_solid,
                    title = title,
                    showTitle = false
            )

            val size = getActionBarSize()
            andes_scroll.viewTreeObserver.addOnScrollChangedListener {
                val scrollY: Float = andes_scroll.scrollY.toFloat()
                if (scrollY > size/2 && !toolbarIsShowing) {
                    // Muestro toolbar
                    toolbar.setBackgroundColor(ContextCompat.getColor(this, R.color.andes_blue_ml_500))
                    toolbar.setTitleTextColor(ContextCompat.getColor(this, R.color.andes_white))
                    setSupportActionBar(toolbar)
                    val upArrow = ContextCompat.getDrawable(this, R.drawable.andes_ui_arrow_left_24)
                    upArrow?.setColorFilter(ContextCompat.getColor(this, R.color.andes_white), PorterDuff.Mode.SRC_ATOP)
                    supportActionBar?.setHomeAsUpIndicator(upArrow)
                    supportActionBar?.setDisplayShowTitleEnabled(true)
                    toolbarIsShowing = true
                } else if (scrollY < size/2 && toolbarIsShowing) {
                    // Oculto toolbar
                    toolbar.setBackgroundColor(ContextCompat.getColor(this, R.color.andes_transparent))
                    setSupportActionBar(toolbar)
                    supportActionBar?.setDisplayShowTitleEnabled(false)
                    val upArrow = ContextCompat.getDrawable(this, R.drawable.andes_ui_arrow_left_24)
                    upArrow?.setColorFilter(ContextCompat.getColor(this, R.color.andes_gray_800_solid), PorterDuff.Mode.SRC_ATOP)
                    supportActionBar?.setHomeAsUpIndicator(upArrow)
                    toolbarIsShowing = false
                }
            }
        }








        val linearLayout = LinearLayout(this)
        linearLayout.orientation = LinearLayout.VERTICAL
        for(num in 1..30) {
            val tv = TextView(this)
            tv.text = "fasd fasd fa sdf asd fasdf asdf asfasd fasd fa sdf asd fasdf asdf as fasd fasd fa sdf asd fasdf asdf as"
            tv.setPadding(48,0,48,0)
            linearLayout.addView(tv)
        }
        andes_view.removeAllViews()
        andes_view.addView(linearLayout)
    }

    private fun renderToolbar(backgroundColor: Int, titleColor: Int, iconColor: Int, title: String, showTitle: Boolean) {
        val toolbar: Toolbar = findViewById(R.id.andes_toolbar)
        toolbar.setBackgroundColor(ContextCompat.getColor(this, backgroundColor))
        toolbar.setTitleTextColor(ContextCompat.getColor(this, titleColor))
        setSupportActionBar(toolbar)
        // Title
        supportActionBar?.title = title
        supportActionBar?.setDisplayShowTitleEnabled(showTitle)
        // Arrow
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        val upArrow = ContextCompat.getDrawable(this, R.drawable.andes_ui_arrow_left_24)
        upArrow?.setColorFilter(ContextCompat.getColor(this, iconColor), PorterDuff.Mode.SRC_ATOP)
        supportActionBar?.setHomeAsUpIndicator(upArrow)

        toolbarIsShowing = showTitle
    }

    private fun getActionBarSize(): Int {
        val styledAttributes = theme.obtainStyledAttributes(intArrayOf(android.R.attr.actionBarSize))
        val actionBarSize = styledAttributes.getDimension(0, 0f).toInt()
        styledAttributes.recycle()
        return actionBarSize
    }

}
