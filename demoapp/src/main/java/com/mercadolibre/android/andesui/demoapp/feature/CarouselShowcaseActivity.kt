package com.mercadolibre.android.andesui.demoapp.feature

import android.graphics.Color
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import com.mercadolibre.android.andesui.carousel.utils.ViewHolderListener
import com.mercadolibre.android.andesui.demoapp.R
import kotlinx.android.synthetic.main.andesui_carousel_showcase.*

class CarouselShowcaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.andesui_carousel_showcase)

        carouselMain.data = arrayListOf(
                Model(Color.RED, "Coti", R.drawable.andes_navegacion_ventas_24),
                Model(Color.GREEN, "Como", R.drawable.andes_navegacion_carrito_idle_24),
                Model(Color.CYAN, "Andas", R.drawable.andes_navegacion_inicio_24),
                Model(Color.RED, "Coti", R.drawable.andes_navegacion_ventas_24),
                Model(Color.GREEN, "Como", R.drawable.andes_navegacion_carrito_idle_24),
                Model(Color.CYAN, "Andas", R.drawable.andes_navegacion_inicio_24)
        )

        carouselMain.setViewHolderListener(object : ViewHolderListener {
            override fun bind(view: View, model: Any) {
                (model as Model)
                view.findViewById<Button>(R.id.button).apply {
                    setBackgroundColor(model.backgroundColor)
                    text = model.label
                    setOnClickListener {
                        Toast.makeText(context, model.label, Toast.LENGTH_LONG).show()
                    }
                }
                view.findViewById<ImageView>(R.id.image).apply {
                    setImageResource(model.image)
                }
            }
        })
    }
}

data class Model(val backgroundColor: Int, val label: String, val image: Int)
