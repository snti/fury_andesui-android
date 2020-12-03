package com.mercadolibre.android.andesui.demoapp.feature

import android.content.Context
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v4.view.PagerAdapter
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.mercadolibre.android.andesui.button.AndesButton
import com.mercadolibre.android.andesui.checkbox.AndesCheckbox
import com.mercadolibre.android.andesui.checkbox.status.AndesCheckboxStatus
import com.mercadolibre.android.andesui.demoapp.R
import com.mercadolibre.android.andesui.demoapp.feature.utils.PageIndicator
import com.mercadolibre.android.andesui.list.*
import com.mercadolibre.android.andesui.list.size.AndesListViewItemSize
import com.mercadolibre.android.andesui.list.type.AndesListType
import com.mercadolibre.android.andesui.list.utils.AndesListDelegate
import com.mercadolibre.android.andesui.radiobutton.type.AndesRadioButtonType
import com.mercadolibre.android.andesui.radiobuttongroup.AndesRadioButtonGroup
import com.mercadolibre.android.andesui.radiobuttongroup.RadioButtonItem
import kotlinx.android.synthetic.main.andesui_list_showcase.view.*

class ListShowcaseActivity : AppCompatActivity(), AndesListDelegate {
    private var itemSelectedPosition: Int = 0
    private lateinit var andesList: AndesList
    private lateinit var adapter: AndesShowcasePagerAdapter
    private var andesListItemTitle = "Title"
    private var andesListItemSubtitle = "Subtitle"
    private var avatar: Drawable? = null
    private var icon: Drawable? = null
    private var titleNumberOfLines: Int = 50
    private var showItemSelections: Boolean = false

    private lateinit var buttonClear: AndesButton
    private lateinit var buttonUpdate: AndesButton
    private lateinit var sizeSpinner: Spinner
    private lateinit var itemTitle: EditText
    private lateinit var itemSubtitle: EditText
    private lateinit var itemTitleNumberOfLines: EditText
    private lateinit var andesListDivider: AndesCheckbox
    private lateinit var andesListSelection: AndesCheckbox

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.andesui_showcase_main)

        setSupportActionBar(findViewById(R.id.andesui_nav_bar))
        supportActionBar?.title = resources.getString(R.string.andesui_demoapp_screen_list)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val viewPager = findViewById<ViewPager>(R.id.andesui_viewpager)
        viewPager.adapter = AndesShowcasePagerAdapter(this)
        val indicator = findViewById<PageIndicator>(R.id.page_indicator)
        indicator.attach(viewPager)

        adapter = viewPager.adapter as AndesShowcasePagerAdapter
        andesList = adapter.views[0].andesList
        andesList.dividerItemEnabled = true
        andesList.delegate = this
        //  andesList.size = AndesListViewItemSize.LARGE

        handleListeners(adapter.views[0])
    }

    private fun handleListeners(container: View) {
        buttonClear = container.findViewById(R.id.buttonClear)
        buttonUpdate = container.findViewById(R.id.buttonUpdate)
        sizeSpinner = container.findViewById(R.id.andes_list_show_case_spinner)
        itemTitle = container.findViewById(R.id.editTextListItemTitle)
        itemSubtitle = container.findViewById(R.id.editTextListItemSubtitle)
        itemTitleNumberOfLines = container.findViewById(R.id.editTextNumberOfLines)
        andesListDivider = container.findViewById(R.id.andesListShowCaseDivider)
        andesListSelection = container.findViewById(R.id.andesListShowCaseSelection)

        sizeSpinner.setSelection(1) // medium value

        sizeSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                    parentView: AdapterView<*>?,
                    selectedItemView: View,
                    position: Int,
                    id: Long
            ) {
                andesList.size = when (sizeSpinner.getItemAtPosition(position).toString().toLowerCase()) {
                    "small" -> {
                        AndesListViewItemSize.SMALL
                    }
                    "medium" -> {
                        AndesListViewItemSize.MEDIUM
                    }
                    "large" -> {
                        AndesListViewItemSize.LARGE
                    }
                    else -> AndesListViewItemSize.MEDIUM
                }

            }

            override fun onNothingSelected(parentView: AdapterView<*>?) {
                // Do nothing.
            }
        }

        buttonClear.setOnClickListener {
            clear()
        }

        buttonUpdate.setOnClickListener {
            update()
        }

    }

    private fun getItemTypeSelected(): AndesListType {
        return when (adapter.andesListItemTypeSelected) {
            0 -> AndesListType.SIMPLE
            1 -> AndesListType.CHEVRON
            2 -> AndesListType.CHECK_BOX
            3 -> AndesListType.RADIO_BUTTON
            else -> AndesListType.SIMPLE
        }
    }

    private fun update() {
        andesListItemTitle = itemTitle.text.toString()
        andesListItemSubtitle = itemSubtitle.text.toString()
        andesList.type = getItemTypeSelected()

        showItemSelections = andesListSelection.status == AndesCheckboxStatus.SELECTED

        andesList.dividerItemEnabled = andesListDivider.status == AndesCheckboxStatus.SELECTED

        if (itemTitleNumberOfLines.text.toString().isNotEmpty()) {
            titleNumberOfLines = itemTitleNumberOfLines.text.toString().toInt()
        }

        avatar = null
        icon = null

        if (adapter.assetTypeSelected == 0) {
            icon = ContextCompat.getDrawable(this, R.drawable.andes_envio_envio_24)
        } else {
            avatar = ContextCompat.getDrawable(this, R.drawable.andes_otros_almanaque_20)
        }

        andesList.refreshListAdapter()
    }

    private fun clear() {
        andesListItemTitle = "Title"
        andesListItemSubtitle = "Subtitle"

        itemTitle.setText("Title")
        itemSubtitle.setText("Subtitle")
        itemTitleNumberOfLines.setText("")

        sizeSpinner.setSelection(1)

        avatar = null
        icon = null

        andesList.refreshListAdapter()
    }

    class AndesShowcasePagerAdapter(private val context: Context) : PagerAdapter() {
        var andesListItemTypeSelected = 0
        var assetTypeSelected = 0

        var views: List<View>

        init {
            views = initViews()
        }

        override fun instantiateItem(container: ViewGroup, position: Int): Any {
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
            val layout = inflater.inflate(R.layout.andesui_list_showcase, null, false)

            val dynamicRadioButtonGroupLayout = addDynamicRadioButtonGroupLayout(layout)

            val spinnerType: Spinner = layout.findViewById(R.id.andes_list_show_case_spinner)
            ArrayAdapter.createFromResource(
                    context,
                    R.array.type_list_spinner,
                    android.R.layout.simple_spinner_item
            )
                    .also { adapter ->
                        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                        spinnerType.adapter = adapter
                    }

            return listOf<View>(layout, dynamicRadioButtonGroupLayout)
        }

        private fun addDynamicRadioButtonGroupLayout(layout: View): View {

            val radioButtons = arrayListOf<RadioButtonItem>()
            radioButtons.add(RadioButtonItem(context.getString(R.string.andes_radiobutton_text_list_simple), AndesRadioButtonType.IDLE))
            radioButtons.add(RadioButtonItem(context.getString(R.string.andes_radiobutton_text_list_chevron), AndesRadioButtonType.IDLE))

            val radioButtons2 = arrayListOf<RadioButtonItem>()
            radioButtons2.add(RadioButtonItem(context.getString(R.string.andes_radiobutton_text_list_icon), AndesRadioButtonType.IDLE))
            radioButtons2.add(RadioButtonItem(context.getString(R.string.andes_radiobutton_text_list_thumbnail), AndesRadioButtonType.IDLE))

            val radioButtonGroup = layout.findViewById<AndesRadioButtonGroup>(R.id.radioButtonGroup1)
            radioButtonGroup.selected = 0
            radioButtonGroup.radioButtons = radioButtons
            radioButtonGroup.setupCallback(
                    object : AndesRadioButtonGroup.OnRadioButtonCheckedChanged {
                        override fun onRadioButtonCheckedChanged(index: Int) {
                            andesListItemTypeSelected = index
                        }
                    }
            )

            val radioButtonGroup2 = layout.findViewById<AndesRadioButtonGroup>(R.id.radioButtonGroup2)
            radioButtonGroup2.radioButtons = radioButtons2
            radioButtonGroup.selected = 0
            radioButtonGroup2.setupCallback(
                    object : AndesRadioButtonGroup.OnRadioButtonCheckedChanged {
                        override fun onRadioButtonCheckedChanged(index: Int) {
                            assetTypeSelected = index
                        }
                    }
            )

            return layout
        }
    }

    override fun onItemClick(position: Int) {
        Toast.makeText(this, getDataSet()[position].title, Toast.LENGTH_SHORT).show()
        itemSelectedPosition = position

        andesList.refreshListAdapter()
    }

    override fun bind(view: View, position: Int): AndesListViewItem {
        val row: AndesListViewItem?

        val showItemSelected = showItemSelections && itemSelectedPosition == position

        if (andesList.type == AndesListType.SIMPLE) {
            row = AndesListViewItemSimple(
                    this,
                    andesListItemTitle,
                    subtitle = andesListItemSubtitle,
                    size = andesList.size,
                    icon = icon,
                    avatar = avatar,
                    titleMaxLines = titleNumberOfLines,
                    itemSelected = showItemSelected
            )

        } else {
            row = AndesListViewItemChevron(
                    this,
                    title = andesListItemTitle,
                    subtitle = andesListItemSubtitle,
                    size = andesList.size,
                    icon = icon,
                    avatar = avatar,
                    titleMaxLines = titleNumberOfLines,
                    itemSelected = showItemSelected
            )

        }

//        if (position == 1) {
//            val drawable = ContextCompat.getDrawable(this, R.drawable.andes_otros_almanaque_20)
//            row = AndesListViewItemSimple(this, getDataSet()[position].title, itemSelected = (position == itemSelectedPosition), avatar = drawable, size = andesList.size)
//        } else {
//            row = AndesListViewItemSimple(this, getDataSet()[position].title, getDataSet()[position].subtitle, itemSelected = (position == itemSelectedPosition), size = andesList.size)
//        }

        return row
    }

    override fun getDataSetSize(): Int = getDataSet().size

    private fun getDataSet() = listOf(
            Model("title 1", "Desc 1", false),
            Model("title 2 largaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa", "Desc 2", true),
            Model("title 3", "Descripcion largaaaaaaaaaaaaaa", false),
            Model("title 4", "Descripcion", false),
            Model("title 5", "Descripcion", false),
            Model("title 6", "Descripcion", false),
            Model("title 7", "Descripcion", false),
            Model("title 8", "Descripcion", false))

    class Model(val title: String, val subtitle: String, val selected: Boolean)
}