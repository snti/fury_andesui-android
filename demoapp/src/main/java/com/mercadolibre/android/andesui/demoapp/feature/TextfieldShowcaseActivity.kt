package com.mercadolibre.android.andesui.demoapp.feature

import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.view.PagerAdapter
import android.support.v4.view.ViewPager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.ScrollView
import android.widget.Spinner
import com.mercadolibre.android.andesui.button.AndesButton
import com.mercadolibre.android.andesui.demoapp.PageIndicator
import com.mercadolibre.android.andesui.demoapp.R
import com.mercadolibre.android.andesui.textfield.AndesTextfield
import com.mercadolibre.android.andesui.textfield.content.AndesTextfieldLeftContent
import com.mercadolibre.android.andesui.textfield.content.AndesTextfieldRightContent
import com.mercadolibre.android.andesui.textfield.factory.AndesTextfieldCounter
import com.mercadolibre.android.andesui.textfield.state.AndesTextfieldState

class TextfieldShowcaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.andesui_showcase_main)

        val viewPager = findViewById<ViewPager>(R.id.andesui_viewpager)
        viewPager.adapter = AndesShowcasePagerAdapter(this)
        val indicator = findViewById<PageIndicator>(R.id.page_indicator)
        indicator.attach(viewPager)
    }

    class AndesShowcasePagerAdapter(private val context: Context) : PagerAdapter() {

        var views: List<View>

        init {
            views = initViews()
        }

        override fun instantiateItem(container: ViewGroup, position: Int): View {
            container.addView(views[position])
            return views[position]
        }

        override fun destroyItem(container: ViewGroup, position: Int, view: Any) {
            container.removeView(view as View?)
        }

        override fun isViewFromObject(view: View, other: Any): Boolean {
            return view == other
        }

        override fun getCount(): Int = views.size

        private fun initViews(): List<View> {
            val inflater = LayoutInflater.from(context)

            val dynamicTextfieldLayout = addDynamicTextfieldLayout(inflater)
            val staticTextfieldLayout = addStaticTextfieldLayout(inflater)

            return listOf(dynamicTextfieldLayout, staticTextfieldLayout)
        }

        private fun addDynamicTextfieldLayout(inflater: LayoutInflater): View {
            val layoutTextfield = inflater.inflate(R.layout.andesui_textfield_showcase_change, null, false) as ScrollView
            val textfield = layoutTextfield.findViewById<AndesTextfield>(R.id.andesui_textfield)
            val button = layoutTextfield.findViewById<AndesButton>(R.id.change_button)
            val clearButton = layoutTextfield.findViewById<AndesButton>(R.id.clear_button)
            val label = layoutTextfield.findViewById<EditText>(R.id.label_text)
            val helper = layoutTextfield.findViewById<EditText>(R.id.helper_text)
            val placeholder = layoutTextfield.findViewById<EditText>(R.id.placeholder_text)
            val counterMin = layoutTextfield.findViewById<EditText>(R.id.counter_min)
            val counterMax = layoutTextfield.findViewById<EditText>(R.id.counter_max)

            val stateSpinner: Spinner = layoutTextfield.findViewById(R.id.state_spinner)
            ArrayAdapter.createFromResource(
                    context,
                    R.array.textfield_state_spinner,
                    android.R.layout.simple_spinner_item
            ).also { adapter ->
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                stateSpinner.adapter = adapter
            }

            val preffixSpinner: Spinner = layoutTextfield.findViewById(R.id.preffix_spinner)
            ArrayAdapter.createFromResource(
                    context,
                    R.array.preffix_spinner,
                    android.R.layout.simple_spinner_item
            ).also { adapter ->
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                preffixSpinner.adapter = adapter
            }

            val suffixSpinner: Spinner = layoutTextfield.findViewById(R.id.suffix_spinner)
            ArrayAdapter.createFromResource(
                    context,
                    R.array.suffix_spinner,
                    android.R.layout.simple_spinner_item
            ).also { adapter ->
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                suffixSpinner.adapter = adapter
            }

            button.setOnClickListener {

                textfield.label = label.text.toString()
                textfield.helper = helper.text.toString()
                textfield.placeholder = placeholder.text.toString()

                val min = counterMin.text.toString().toIntOrNull() ?: 0
                val max = counterMax.text.toString().toIntOrNull() ?: 0
                textfield.counter = AndesTextfieldCounter(min,max)

                when (stateSpinner.selectedItem.toString()) {
                    "Enabled" -> textfield.state = AndesTextfieldState.ENABLED
                    "Disabled" -> textfield.state = AndesTextfieldState.DISABLED
                    "Error" -> textfield.state = AndesTextfieldState.ERROR
                    "Readonly" -> textfield.state = AndesTextfieldState.READONLY
                }

                when (preffixSpinner.selectedItem.toString()) {
                    "Preffix" -> textfield.leftContent = AndesTextfieldLeftContent.PREFIX
                    "Icon" -> textfield.leftContent = AndesTextfieldLeftContent.ICON
                }

                when (suffixSpinner.selectedItem.toString()) {
                    "Suffix" -> textfield.rightContent = AndesTextfieldRightContent.SUFFIX
                    "Icon" -> textfield.rightContent = AndesTextfieldRightContent.ICON
                    "Validated" -> textfield.rightContent = AndesTextfieldRightContent.VALIDATED
                    "Clear" -> textfield.rightContent = AndesTextfieldRightContent.CLEAR
                    "Action" -> textfield.rightContent = AndesTextfieldRightContent.ACTION
                }
            }

            clearButton.setOnClickListener {
                //reset UI
                label.setText(null)
                placeholder.setHint(context.resources.getString(R.string.andes_textfield_placeholder_text))
                placeholder.setText(null)
                stateSpinner.setSelection(0)
                helper.setText(null)
                counterMin.setText(null)
                counterMax.setText(null)

                //reset AndesTextfield's properties.
                textfield.label = null
                textfield.helper = null
                textfield.placeholder = null
                textfield.counter = AndesTextfieldCounter(0,0)
                textfield.state = AndesTextfieldState.ENABLED
                textfield.leftContent = null
                textfield.rightContent = null
            }

            return layoutTextfield
        }

        private fun addStaticTextfieldLayout(inflater: LayoutInflater): View {
            val layoutTextfield = inflater.inflate(R.layout.andesui_textfield_showcase, null, false) as ScrollView

            val textfield = layoutTextfield.findViewById<AndesTextfield>(R.id.textfield_enabled)

            textfield.setupPrefix("+546666")

            return layoutTextfield
        }
    }
}

