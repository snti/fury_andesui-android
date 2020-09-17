package com.mercadolibre.android.andesui.demoapp.feature

import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.widget.Toast
import com.mercadolibre.android.andesui.bottomsheet.AndesBottomSheet
import com.mercadolibre.android.andesui.bottomsheet.BottomSheetListener
import com.mercadolibre.android.andesui.demoapp.R

class BottomSheetShowcaseActivity : AppCompatActivity(), BottomSheetListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bottom_sheet_showcase)

        val view = View(applicationContext)
        val params = ViewGroup.LayoutParams(MATCH_PARENT, 1000)
        view.layoutParams = params

        val bottomSheet = findViewById<AndesBottomSheet>(R.id.andes_bottom_sheet)
        bottomSheet.setBottomSheetListener(this)
        bottomSheet.setView(view)
        bottomSheet.expand()
    }

    override fun onCollapsed() {
        Toast.makeText(applicationContext, "Collapsed!", Toast.LENGTH_SHORT).show()
    }

    override fun onExpanded() {
        Toast.makeText(applicationContext, "Expanded!", Toast.LENGTH_SHORT).show()
    }
}
