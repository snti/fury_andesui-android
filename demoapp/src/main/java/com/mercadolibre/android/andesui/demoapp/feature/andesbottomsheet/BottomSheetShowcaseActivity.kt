package com.mercadolibre.android.andesui.demoapp.feature.andesbottomsheet

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.widget.Toast
import com.mercadolibre.android.andesui.bottomsheet.AndesBottomSheet
import com.mercadolibre.android.andesui.bottomsheet.button.BottomSheetButtonListener
import com.mercadolibre.android.andesui.bottomsheet.BottomSheetListener
import com.mercadolibre.android.andesui.demoapp.R

class BottomSheetShowcaseActivity : AppCompatActivity(), BottomSheetListener, BottomSheetButtonListener {
    private lateinit var bottomSheet: AndesBottomSheet

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.andesui_bottom_sheet_showcase)

        bottomSheet = findViewById(R.id.andes_bottom_sheet)
        bottomSheet.setBottomSheetListener(this)
        bottomSheet.bottomSheetButtonText = "Toast Mother focker"
        bottomSheet.setBottomSheetButtonListener(this)
    }

    override fun onCollapsed() {
        Toast.makeText(applicationContext, "Collapsed!", Toast.LENGTH_SHORT).show()
    }

    override fun onExpanded() {
        Toast.makeText(applicationContext, "Expanded!", Toast.LENGTH_SHORT).show()
    }

    fun onAttachViewButtonClicked(view: View) {
        bottomSheet.removeViews()

        val testView = View(applicationContext)
        val params = ViewGroup.LayoutParams(MATCH_PARENT, 1000)
        testView.layoutParams = params

        bottomSheet.setView(testView)
    }

    fun onAttachFragmentButtonClicked(view: View) {
        bottomSheet.removeViews()

        bottomSheet.setFragment(supportFragmentManager, TestFragment())
    }

    override fun onBottomSheetButtonClicked() {
        Toast.makeText(applicationContext, "Button clicked!", Toast.LENGTH_SHORT).show()
    }
}
