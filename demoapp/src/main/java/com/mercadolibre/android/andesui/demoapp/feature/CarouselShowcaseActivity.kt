package com.mercadolibre.android.andesui.demoapp.feature

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import com.mercadolibre.android.andesui.carousel.AndesCarousel
import com.mercadolibre.android.andesui.carousel.padding.AndesCarouselPadding
import com.mercadolibre.android.andesui.carousel.utils.AndesCarouselDelegate
import com.mercadolibre.android.andesui.demoapp.R
import kotlinx.android.synthetic.main.andesui_carousel_showcase.*

class CarouselShowcaseActivity : AndesCarouselDelegate, AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.andesui_carousel_showcase)
        setSupportActionBar(findViewById(R.id.andesui_nav_bar))
        supportActionBar?.title = resources.getString(R.string.andesui_demoapp_screen_carousel)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        carouselMain.delegate = this

        ArrayAdapter.createFromResource(
    this,
            R.array.carousel_padding_spinner,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            paddingSpinner.adapter = adapter
        }

        ArrayAdapter.createFromResource(
                this,
                R.array.carousel_center_spinner,
                android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            centerSpinner.adapter = adapter
        }

        updateButton.setOnClickListener {
            val padding: AndesCarouselPadding = when(paddingSpinner.selectedItem.toString()) {
                "None" -> AndesCarouselPadding.NONE
                "Small" -> AndesCarouselPadding.SMALL
                "Medium" -> AndesCarouselPadding.MEDIUM
                else -> AndesCarouselPadding.LARGE
            }
            val center = when(centerSpinner.selectedItem.toString()) {
                "False" -> false
                else -> true
            }
            carouselMain.padding = padding
            carouselMain.center = center
        }
    }

    @SuppressLint("SetTextI18n")
    override fun bind(andesCarouselView: AndesCarousel, view: View, position: Int) {
        val model = getDataSet()[position]
        if (andesCarouselView == carouselMain) {
            view.findViewById<Button>(R.id.button).apply {
                setBackgroundColor(model.backgroundColor)
                text = model.label
            }
            view.findViewById<ImageView>(R.id.image).apply {
                setImageResource(model.image)
            }
        }
    }

    override fun getLayoutItem(andesCarouselView: AndesCarousel) = R.layout.andesui_carousel_item

    override fun onClickItem(andesCarouselView: AndesCarousel, position: Int) {
        Toast.makeText(this@CarouselShowcaseActivity, getDataSet()[position].toString(), Toast.LENGTH_SHORT).show()
    }

    override fun getDataSetSize(andesCarouselView: AndesCarousel) = getDataSet().size

    private fun getDataSet() = listOf(
        Model(Color.RED, "Button Nº1", R.drawable.andes_navegacion_ventas_24),
        Model(Color.GREEN, "Button Nº2", R.drawable.andes_navegacion_carrito_idle_24),
        Model(Color.CYAN, "Button Nº3", R.drawable.andes_navegacion_inicio_24),
        Model(Color.RED, "Button Nº4", R.drawable.andes_navegacion_ventas_24),
        Model(Color.GREEN, "Button Nº5", R.drawable.andes_navegacion_carrito_idle_24),
        Model(Color.CYAN, "Button Nº6", R.drawable.andes_navegacion_inicio_24)
    )
}

data class Model(val backgroundColor: Int, val label: String, val image: Int)

