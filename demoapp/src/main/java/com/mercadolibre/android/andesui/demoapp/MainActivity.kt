package com.mercadolibre.android.andesui.demoapp

import android.content.Intent
import android.content.Intent.ACTION_VIEW
import android.net.Uri
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.andesui_demoapp_main.*

/**
 * Main activity class
 */
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.andesui_demoapp_main)

        setSupportActionBar(findViewById(R.id.andesui_nav_bar))
        supportActionBar?.title = resources.getString(R.string.andesui_demoapp_app_name)

        setupBadges()
        setupButtons()
        setupMessages()
        setupTextfield()
        setupWhatsNew()
        setupAndesSpecsWeb()
    }

    private fun setupBadges() {
        andesui_badges.setOnClickListener {
            launchIntent("meli://andes/badge")
        }
    }

    private fun setupButtons() {
        andesui_buttons.setOnClickListener {
            launchIntent("meli://andes/button")
        }
    }

    private fun setupMessages() {
        andesui_messages.setOnClickListener {
            launchIntent("meli://andes/message")
        }
    }

    private fun setupTextfield() {
        andesui_textfield.setOnClickListener {
            launchIntent("meli://andes/textfield")
        }
    }

    private fun setupWhatsNew() {
        andesui_demoapp_changelog.setOnClickListener {
            launchIntent("meli://andes/whats-new")
        }
    }

    private fun setupAndesSpecsWeb() {
        andesui_demoapp_andes_specs.setOnClickListener {
            launchSpecs(this, AndesSpecs.HOME_PAGE)
        }
    }

    private fun launchIntent(uri: String) {
        val launchIntent = Intent(ACTION_VIEW, Uri.parse(uri))
        startActivity(launchIntent)
    }
}