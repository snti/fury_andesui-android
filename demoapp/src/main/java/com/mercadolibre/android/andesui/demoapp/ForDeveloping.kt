package com.mercadolibre.android.andesui.demoapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.mercadolibre.android.andesui.message.AndesMessage

class ForDeveloping : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.develop)


        Thread(Runnable {
            Thread.sleep(0)

            runOnUiThread {
                val andesMessage = findViewById<AndesMessage>(R.id.andesui_demoapp_changelog)
                andesMessage.setupThumbnail(resources.getDrawable(R.drawable.icon, null))
            }


        }).start()

    }
}