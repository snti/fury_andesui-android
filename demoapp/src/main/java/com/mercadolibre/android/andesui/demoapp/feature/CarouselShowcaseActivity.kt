package com.mercadolibre.android.andesui.demoapp.feature

import android.graphics.Color
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Button
import android.widget.TextView
import com.mercadolibre.android.andesui.carousel.util.ViewHolderListener
import com.mercadolibre.android.andesui.demoapp.R
import kotlinx.android.synthetic.main.andesui_carousel_showcase.*

class CarouselShowcaseActivity : AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.andesui_carousel_showcase)

        carouselMain.layout = R.layout.andesui_carousel_item
        carouselMain.data = arrayListOf(
                Model(Color.RED,"Coti"),
                Model(Color.GREEN,"Como"),
                Model(Color.CYAN,"Andas")
        )

        carouselMain.setViewHolderListener(object : ViewHolderListener {
            override fun bind(view: View, model: Any) {
                (model as Model)
                view.findViewById<Button>(R.id.button2).text = model.label
                view.findViewById<Button>(R.id.button).apply {
                    setBackgroundColor(model.backgroundColor)
                    text = model.label
                }
            }
        })
    }
}

data class Model(val backgroundColor: Int, val label: String)
