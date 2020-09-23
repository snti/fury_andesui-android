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
import com.mercadolibre.android.andesui.bottomsheet.title.AndesBottomSheetTitleAlignment
import com.mercadolibre.android.andesui.demoapp.R

class BottomSheetShowcaseActivity : AppCompatActivity(), BottomSheetListener, BottomSheetButtonListener {
    private lateinit var bottomSheet: AndesBottomSheet
    private var showButton = false
    private var showBackgroundDim = false
    private var showTitle = false
    private var leftAlignTitle = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.andesui_bottom_sheet_showcase)

        bottomSheet = findViewById(R.id.andes_bottom_sheet)
        bottomSheet.setBottomSheetListener(this)
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

    fun onToggleButtonClicked(view: View) {
        showButton = !showButton

        if (showButton) {
            bottomSheet.bottomSheetButtonText = "Toast Please!"
            bottomSheet.setBottomSheetButtonListener(this)
        } else {
            bottomSheet.bottomSheetButtonText = null
        }
    }

    fun onToggleBackgroundDimButtonClicked(view: View) {
        showBackgroundDim = !showBackgroundDim
        bottomSheet.isBackgroundDimEnabled = showBackgroundDim
    }

    fun onToggleTitleClicked(view: View) {
        showTitle = !showTitle
        if (showTitle) {
            bottomSheet.bottomSheetTitleText = "Hello, I'm Title"
        } else {
            bottomSheet.bottomSheetTitleText = null
        }
    }
    fun onToggleAlignButtonClicked(view: View) {
        leftAlignTitle = !leftAlignTitle
        if (leftAlignTitle) {
            bottomSheet.bottomSheetTitleAlignment = AndesBottomSheetTitleAlignment.LEFT_ALIGN
        } else {
            bottomSheet.bottomSheetTitleAlignment = AndesBottomSheetTitleAlignment.CENTERED
        }
    }
}
