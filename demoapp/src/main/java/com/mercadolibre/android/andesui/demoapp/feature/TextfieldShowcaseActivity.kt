package com.mercadolibre.android.andesui.demoapp.feature

import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.view.PagerAdapter
import android.support.v4.view.ViewPager
import android.text.InputType
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

            val inputTypeSpinner: Spinner = layoutTextfield.findViewById(R.id.textType_spinner)
            val adapter = ArrayAdapter(context, android.R.layout.simple_spinner_item, getInputTypesArray())
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            inputTypeSpinner.adapter = adapter

            val stateSpinner: Spinner = layoutTextfield.findViewById(R.id.state_spinner)
            ArrayAdapter.createFromResource(
                    context,
                    R.array.textfield_state_spinner,
                    android.R.layout.simple_spinner_item
            ).also { adapter ->
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                stateSpinner.adapter = adapter
            }

            val preffixSpinner: Spinner = layoutTextfield.findViewById(R.id.prefix_spinner)
            ArrayAdapter.createFromResource(
                    context,
                    R.array.prefix_spinner,
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

                textfield.state = AndesTextfieldState.valueOf(stateSpinner.selectedItem.toString().toUpperCase())

                textfield.leftContent = AndesTextfieldLeftContent.valueOf(preffixSpinner.selectedItem.toString().toUpperCase())

                textfield.rightContent = AndesTextfieldRightContent.valueOf(suffixSpinner.selectedItem.toString().toUpperCase())

                val selectedInputType = getInputTypesArray().filter { it.name == inputTypeSpinner.selectedItem.toString() }.single().value
                textfield.inputType = selectedInputType
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
                inputTypeSpinner.setSelection(getInputTypesArray().filter { it.name == "text" }.single().value)

                //reset AndesTextfield's properties.
                textfield.label = null
                textfield.helper = null
                textfield.placeholder = null
                textfield.counter = AndesTextfieldCounter(0,0)
                textfield.state = AndesTextfieldState.ENABLED
                textfield.leftContent = null
                textfield.rightContent = null
                textfield.inputType = InputType.TYPE_CLASS_TEXT
            }

            return layoutTextfield
        }

        private fun addStaticTextfieldLayout(inflater: LayoutInflater): View {
            val layoutTextfield = inflater.inflate(R.layout.andesui_textfield_showcase, null, false) as ScrollView

            val textfield = layoutTextfield.findViewById<AndesTextfield>(R.id.textfield_enabled)

            textfield.setupPrefix("+546666")

            return layoutTextfield
        }

        private fun getInputTypesArray(): ArrayList<InputTypeItem> {
            val inputTypes = ArrayList<InputTypeItem>()

            inputTypes.add(InputTypeItem("date", InputType.TYPE_CLASS_DATETIME or InputType.TYPE_DATETIME_VARIATION_DATE))
            inputTypes.add(InputTypeItem("datetime", InputType.TYPE_CLASS_DATETIME or InputType.TYPE_DATETIME_VARIATION_NORMAL))
            inputTypes.add(InputTypeItem("none", InputType.TYPE_NULL))
            inputTypes.add(InputTypeItem("number", InputType.TYPE_CLASS_NUMBER or InputType.TYPE_NUMBER_VARIATION_NORMAL))
            inputTypes.add(InputTypeItem("numberDecimal", InputType.TYPE_CLASS_NUMBER or InputType.TYPE_NUMBER_FLAG_DECIMAL))
            inputTypes.add(InputTypeItem("numberPassword", InputType.TYPE_CLASS_NUMBER or InputType.TYPE_NUMBER_VARIATION_PASSWORD))
            inputTypes.add(InputTypeItem("numberSigned", InputType.TYPE_CLASS_NUMBER or InputType.TYPE_NUMBER_FLAG_SIGNED))
            inputTypes.add(InputTypeItem("phone", InputType.TYPE_CLASS_PHONE))
            inputTypes.add(InputTypeItem("text", InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_NORMAL))
            inputTypes.add(InputTypeItem("textAutoComplete", InputType.TYPE_TEXT_FLAG_AUTO_COMPLETE))
            inputTypes.add(InputTypeItem("textAutoCorrect", InputType.TYPE_TEXT_FLAG_AUTO_CORRECT))
            inputTypes.add(InputTypeItem("textCapCharacters", InputType.TYPE_TEXT_FLAG_CAP_CHARACTERS))
            inputTypes.add(InputTypeItem("textCapSentences", InputType.TYPE_TEXT_FLAG_CAP_SENTENCES))
            inputTypes.add(InputTypeItem("textCapWords", InputType.TYPE_TEXT_FLAG_CAP_WORDS))
            inputTypes.add(InputTypeItem("textEmailAddress", InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS))
            inputTypes.add(InputTypeItem("textEmailSubject", InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_EMAIL_SUBJECT))
            inputTypes.add(InputTypeItem("textFilter", InputType.TYPE_TEXT_FLAG_IME_MULTI_LINE))
            inputTypes.add(InputTypeItem("textLongMessage", InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_LONG_MESSAGE))
            inputTypes.add(InputTypeItem("textMultiLine", InputType.TYPE_TEXT_FLAG_MULTI_LINE))
            inputTypes.add(InputTypeItem("textNoSuggestions", InputType.TYPE_TEXT_FLAG_NO_SUGGESTIONS))
            inputTypes.add(InputTypeItem("textPassword", InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD))
            inputTypes.add(InputTypeItem("textPersonName", InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PERSON_NAME))
            inputTypes.add(InputTypeItem("textPhonetic", InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PHONETIC))
            inputTypes.add(InputTypeItem("textPostalAddress", InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_POSTAL_ADDRESS))
            inputTypes.add(InputTypeItem("textShortMessage", InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_SHORT_MESSAGE))
            inputTypes.add(InputTypeItem("textUri", InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_URI))
            inputTypes.add(InputTypeItem("textVisiblePassword", InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD))
            inputTypes.add(InputTypeItem("textWebEditText", InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_WEB_EDIT_TEXT))
            inputTypes.add(InputTypeItem("textWebEmailAddress", InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_WEB_EMAIL_ADDRESS))
            inputTypes.add(InputTypeItem("textWebPassword", InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_WEB_PASSWORD))
            inputTypes.add(InputTypeItem("time", InputType.TYPE_CLASS_DATETIME or InputType.TYPE_DATETIME_VARIATION_TIME))

            return inputTypes
        }

        internal class InputTypeItem(val name: String, val value: Int) {
            override fun toString(): String {
                return this.name
            }
        }

    }
}

