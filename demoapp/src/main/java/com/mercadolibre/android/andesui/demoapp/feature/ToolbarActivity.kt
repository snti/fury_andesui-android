package com.mercadolibre.android.andesui.demoapp.feature

import android.graphics.PorterDuff
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.widget.Toast
import com.mercadolibre.android.andesui.demoapp.R
import kotlinx.android.synthetic.main.andesui_toolbar.*


class ToolbarActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.andesui_toolbar)

        if (false) {
            val toolbar: Toolbar = findViewById(R.id.andesui_nav_bar)
            toolbar.setBackgroundColor(ContextCompat.getColor(this, R.color.andes_blue_ml_500))
            // Set title and color
            toolbar.setTitleTextColor(ContextCompat.getColor(this, R.color.andes_white))
            setSupportActionBar(toolbar)
            supportActionBar?.title = "Buen titulo"
            // Set arrow and color
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
            val upArrow = ContextCompat.getDrawable(this, R.drawable.andes_ui_arrow_left_24)
            upArrow?.setColorFilter(ContextCompat.getColor(this, R.color.andes_white), PorterDuff.Mode.SRC_ATOP);
            supportActionBar?.setHomeAsUpIndicator(upArrow)
        } else {
            val toolbar: Toolbar = findViewById(R.id.andesui_nav_bar)
            toolbar.setBackgroundColor(ContextCompat.getColor(this, R.color.andes_transparent))
            // Set title and color
            toolbar.setTitleTextColor(ContextCompat.getColor(this, R.color.andes_gray_800_solid))
            setSupportActionBar(toolbar)
//            supportActionBar?.title = "Buen titulo"
            // Set arrow and color
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
            val upArrow = ContextCompat.getDrawable(this, R.drawable.andes_ui_arrow_left_24)
            upArrow?.setColorFilter(ContextCompat.getColor(this, R.color.andes_gray_800_solid), PorterDuff.Mode.SRC_ATOP);
            supportActionBar?.setHomeAsUpIndicator(upArrow)
        }




        screenView.viewTreeObserver.addOnScrollChangedListener {
            

            val scrollX: Int = screenView.scrollX //for horizontalScrollView
            val scrollY: Int = screenView.scrollY //for verticalScrollView

            Toast.makeText(this, scrollY.toString(), Toast.LENGTH_SHORT).show()

        }

    }


}
