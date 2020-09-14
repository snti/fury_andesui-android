package com.mercadolibre.android.andesui.demoapp.feature

import android.graphics.Color
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import com.mercadolibre.android.andesui.carousel.utils.AndesCarouselDelegate
import com.mercadolibre.android.andesui.demoapp.R
import kotlinx.android.synthetic.main.andesui_carousel_showcase.*

class CarouselShowcaseActivity : AndesCarouselDelegate, AppCompatActivity() {

    private lateinit var dataSet: List<Model>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.andesui_carousel_showcase)
        carouselMain.delegate = this
        dataSet = listOf(Model(Color.RED, "Button Nº1", R.drawable.andes_navegacion_ventas_24),
            Model(Color.GREEN, "Button Nº2", R.drawable.andes_navegacion_carrito_idle_24),
            Model(Color.CYAN, "Button Nº3", R.drawable.andes_navegacion_inicio_24),
            Model(Color.RED, "Button Nº4", R.drawable.andes_navegacion_ventas_24),
            Model(Color.GREEN, "Button Nº5", R.drawable.andes_navegacion_carrito_idle_24),
            Model(Color.CYAN, "Button Nº6", R.drawable.andes_navegacion_inicio_24)
        )
    }

    override fun bind(view: View, position: Int) {
        val model = dataSet[position]
        view.findViewById<Button>(R.id.button).apply {
            setBackgroundColor(model.backgroundColor)
            text = model.label
        }
        view.findViewById<ImageView>(R.id.image).apply {
            setImageResource(model.image)
        }
    }

    override fun getLayoutItem() = R.layout.andesui_carousel_item

    override fun onClickItem(position: Int) {
        Toast.makeText(this@CarouselShowcaseActivity, dataSet[position].toString(), Toast.LENGTH_SHORT).show()
    }

    override fun getDataSetSize() = dataSet.size
}

data class Model(val backgroundColor: Int, val label: String, val image: Int)

