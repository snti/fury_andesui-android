package com.mercadolibre.android.andesui.demoapp.feature.andesbottomsheet

import android.app.Activity
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.view.ViewGroup
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import com.mercadolibre.android.andesui.bottomsheet.AndesBottomSheet
import com.mercadolibre.android.andesui.bottomsheet.BottomSheetListener
import com.mercadolibre.android.andesui.bottomsheet.title.AndesBottomSheetTitleAlignment
import com.mercadolibre.android.andesui.demoapp.R
import kotlinx.android.synthetic.main.andesui_bottom_sheet_showcase.*


class BottomSheetShowcaseActivity : AppCompatActivity(), BottomSheetListener {
    private lateinit var bottomSheet: AndesBottomSheet
    private var showBackgroundDim = false
    private var showTitle = false
    private var leftAlignTitle = false
    private var testView: View? = null
    private var testFragment = TestFragment()

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
        if (testView == null) {
            testView = View(applicationContext)
            val params = ViewGroup.LayoutParams(MATCH_PARENT, FRAGMENT_HEIGHT)
            testView?.layoutParams = params
        }

        bottomSheet.setView(testView!!)
        bottomSheet.expand()
    }

    fun onAttachFragmentButtonClicked(view: View) {
        bottomSheet.setFragment(supportFragmentManager, testFragment)
    }

    fun onExpandButtonClicked(view: View) {
        bottomSheet.expand()
    }

    fun onCollapseButtonClicked(view: View) {
        bottomSheet.collapse()
    }

    fun onToggleBackgroundDimButtonClicked(view: View) {
        showBackgroundDim = !showBackgroundDim
        bottomSheet.isBackgroundDimEnabled = showBackgroundDim
    }

    fun onToggleTitleClicked(view: View) {
        showTitle = !showTitle
        if (showTitle) {
            bottomSheet.titleText = "Hello, I'm Title"
        } else {
            bottomSheet.titleText = null
        }
    }
    fun onToggleAlignButtonClicked(view: View) {
        leftAlignTitle = !leftAlignTitle
        if (leftAlignTitle) {
            bottomSheet.titleAlignment = AndesBottomSheetTitleAlignment.LEFT_ALIGN
        } else {
            bottomSheet.titleAlignment = AndesBottomSheetTitleAlignment.CENTERED
        }
    }

    fun onSetPeekHeightButtonClicked(view: View) {
        if (!andes_bottom_sheet_peek_height_text_field.text.isNullOrEmpty()) {
            bottomSheet.peekHeight = andes_bottom_sheet_peek_height_text_field.text!!.toInt()
            closeKeyboard(view)
            andes_bottom_sheet_peek_height_text_field.text = ""
        }
    }

    private fun closeKeyboard(view: View) {
        val inputManager: InputMethodManager = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        inputManager.hideSoftInputFromWindow(view.windowToken, 0)
    }

    companion object {
        private const val FRAGMENT_HEIGHT = 800
    }
}
