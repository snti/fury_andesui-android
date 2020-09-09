package com.mercadolibre.android.andesui.demoapp.feature

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.mercadolibre.android.andesui.bottomsheet.AndesBottomSheet
import com.mercadolibre.android.andesui.bottomsheet.BottomSheetListener
import com.mercadolibre.android.andesui.demoapp.R
class BottomSheetShowcaseActivity : AppCompatActivity(), BottomSheetListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bottom_sheet_showcase)

        val bottomSheet = findViewById<AndesBottomSheet>(R.id.andes_bottom_sheet)
//        bottomSheet.setPeekHeight(100)
        bottomSheet.setBottomSheetListener(this)
    }

    override fun onCollapsed() {
        Toast.makeText(applicationContext, "Collapsed!", Toast.LENGTH_SHORT).show()
    }

    override fun onExpanded() {
        Toast.makeText(applicationContext, "Expanded!", Toast.LENGTH_SHORT).show()
    }
}

