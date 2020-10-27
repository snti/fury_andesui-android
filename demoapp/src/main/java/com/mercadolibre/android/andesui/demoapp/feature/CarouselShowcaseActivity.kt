package com.mercadolibre.android.andesui.demoapp.feature

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.support.v4.view.PagerAdapter
import android.support.v7.app.AppCompatActivity
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.mercadolibre.android.andesui.card.AndesCard
import com.mercadolibre.android.andesui.carousel.AndesCarousel
import com.mercadolibre.android.andesui.carousel.margin.AndesCarouselMargin
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
            R.array.carousel_margin_spinner,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            marginSpinner.adapter = adapter
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
            val padding: AndesCarouselMargin = when(marginSpinner.selectedItem.toString()) {
                "None" -> AndesCarouselMargin.NONE
                else -> AndesCarouselMargin.DEFAULT
            }
            val center = when(centerSpinner.selectedItem.toString()) {
                "False" -> false
                else -> true
            }
            carouselMain.margin = padding
            carouselMain.center = center
        }
    }

    @SuppressLint("SetTextI18n")
    override fun bind(andesCarouselView: AndesCarousel, view: View, position: Int) {
        val model = getDataSet()[position]
        val textView1 = TextView(applicationContext)
        textView1.setTextSize(
                TypedValue.COMPLEX_UNIT_PX,
                applicationContext.resources.getDimension(R.dimen.title_text_size_card)
        )
        textView1.setTextColor(applicationContext.resources.getColor(R.color.andes_gray_800))
        textView1.text = model.label
        if (andesCarouselView == carouselMain) {
            view.findViewById<AndesCard>(R.id.cardCarousel).apply {
                title = "Andes Card"
                if (position != 2)
                    setLinkAction(
                            "Action",
                            View.OnClickListener {
                                Toast.makeText(context, "Action clicked!", Toast.LENGTH_SHORT).show()
                            }
                    )
                cardView = textView1
            }
        }
    }

    override fun getLayoutItem(andesCarouselView: AndesCarousel) = R.layout.andesui_carousel_item

    override fun onClickItem(andesCarouselView: AndesCarousel, position: Int) {
        Toast.makeText(this@CarouselShowcaseActivity, getDataSet()[position].toString(), Toast.LENGTH_SHORT).show()
    }

    override fun getDataSetSize(andesCarouselView: AndesCarousel) = getDataSet().size

    private fun getDataSet() = listOf(
            Model(Color.RED, "Button Nº4", R.drawable.andes_navegacion_ventas_24),
            Model(Color.RED, "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book.", R.drawable.andes_navegacion_ventas_24),
        Model(Color.GREEN, "Lorem Ipsum is simply dummy text of the printing and typesetting industry.", R.drawable.andes_navegacion_carrito_idle_24),
            Model(Color.CYAN, "The standard chunk of Lorem Ipsum used since the 1500s is reproduced below for those interested. Sections 1.10.32 and 1.10.33 from \"de Finibus Bonorum et Malorum\" by Cicero are also reproduced in their exact original form, accompanied by English versions from the 1914 translation by H. Rackham.", R.drawable.andes_navegacion_inicio_24)
            )
}

data class Model(val backgroundColor: Int, val label: String, val image: Int)

