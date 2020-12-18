package com.mercadolibre.android.andesui.dropdown

import android.content.Context
import android.os.Bundle
import android.text.InputType
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.view.View.OnFocusChangeListener
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.mercadolibre.android.andesui.R
import com.mercadolibre.android.andesui.dropdown.factory.AndesDropdownAttrParser
import com.mercadolibre.android.andesui.dropdown.factory.AndesDropdownAttrs
import com.mercadolibre.android.andesui.dropdown.factory.AndesDropdownConfiguration
import com.mercadolibre.android.andesui.dropdown.factory.AndesDropdownConfigurationFactory
import com.mercadolibre.android.andesui.dropdown.size.AndesDropdownSize
import com.mercadolibre.android.andesui.dropdown.type.AndesDropdownMenuType
import com.mercadolibre.android.andesui.dropdown.utils.AndesDropdownDelegate
import com.mercadolibre.android.andesui.dropdown.utils.DropdownBottomSheetDialog
import com.mercadolibre.android.andesui.list.AndesList
import com.mercadolibre.android.andesui.list.AndesListViewItem
import com.mercadolibre.android.andesui.list.AndesListViewItemSimple
import com.mercadolibre.android.andesui.list.utils.AndesListDelegate
import com.mercadolibre.android.andesui.textfield.AndesTextfield


class AndesDropDownForm : ConstraintLayout, AndesListDelegate {
    private lateinit var andesDropdownDelegate: AndesDropdownDelegate
    private lateinit var andesDropdownAttrs: AndesDropdownAttrs
    private lateinit var andesTextfield: AndesTextfield
    private lateinit var dropdownBottomSheetDialog: DropdownBottomSheetDialog
    var listItems: MutableList<AndesDropDownItem> = mutableListOf()

    /**
     * Getter and setter for [label].
     */
    var label: String?
        get() = andesDropdownAttrs.andesDropdownLabel
        set(value) {
            andesDropdownAttrs = andesDropdownAttrs.copy(andesDropdownLabel = value)
            setupLabelComponent(createConfig())
        }

    /**
     * Getter and setter for [helper].
     */
    var helper: String?
        get() = andesDropdownAttrs.andesDropdownHelper
        set(value) {
            andesDropdownAttrs = andesDropdownAttrs.copy(andesDropdownHelper = value)
            setupHelperComponent(createConfig())
        }

    /**
     * Getter and setter for [placeholder].
     */
    var placeholder: String?
        get() = andesDropdownAttrs.andesDropdownPlaceHolder
        set(value) {
            andesDropdownAttrs = andesDropdownAttrs.copy(andesDropdownPlaceHolder = value)
            setupPlaceHolderComponent(createConfig())
        }

    /**
     * Getter and setter for [delegate].
     */
    var delegate: AndesDropdownDelegate
        get() = andesDropdownDelegate
        set(value) {
            andesDropdownDelegate = value
        }

    /**
     * Getter and setter for [menuType].
     */
    var menuType: AndesDropdownMenuType
        get() = andesDropdownAttrs.andesDropdownMenuType
        set(value) {
            andesDropdownAttrs = andesDropdownAttrs.copy(andesDropdownMenuType = value)
        }

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        initAttrs(attrs)
    }

    constructor(
            context: Context,
            menuType: AndesDropdownMenuType = AndesDropdownMenuType.BOTTOMSHEET,
            label: String,
            helper: String,
            placeHolder: String
    ) : super(context) {
        initAttrs(menuType, label, helper, placeHolder)
    }

    /**
     * Sets the proper [config] for this component based on the [attrs] received via XML.
     *
     * @param attrs attributes from the XML.
     */
    private fun initAttrs(attrs: AttributeSet?) {
        andesDropdownAttrs = AndesDropdownAttrParser.parse(context, attrs)
        setupComponents(createConfig())
    }

    private fun initAttrs(
            menuType: AndesDropdownMenuType,
            label: String,
            helper: String,
            placerHolder: String
    ) {
        andesDropdownAttrs = AndesDropdownAttrs(
                andesDropdownMenuType = menuType,
                andesDropdownLabel = label,
                andesDropdownHelper = helper,
                andesDropdownPlaceHolder = placerHolder)
        setupComponents(createConfig())
    }

    /**
     * Responsible for setting up all properties of each component that is part of this andesDropdown.
     * Is like a choreographer 😉
     */
    private fun setupComponents(config: AndesDropdownConfiguration) {
        initComponents()
        setupViewId()
        setupAndesTextFieldComponent(config)
//        resolveBottomSheetBackground()
    }

    /**
     * Sets a view id to this dropdown.
     */
    private fun setupViewId() {
        if (id == NO_ID) { // If this view has no id
            id = View.generateViewId()
        }
    }

    /**
     * Creates all the views that are part of this Dropdown.
     * After a view is created then a view id is added to it.
     */
    private fun initComponents() {
        val container = LayoutInflater.from(context).inflate(R.layout.andes_layout_dropdown_form, this)
        andesTextfield = container.findViewById(R.id.andesTextfield)

        //TODO esto va en el menu type standalone
        dropdownBottomSheetDialog = DropdownBottomSheetDialog.newInstance(Bundle())
    }

    val dialog = BottomSheetDialog(context)
    private val onFocusChange = OnFocusChangeListener { v, hasFocus ->
        if (hasFocus) {
            openBottomSheet()
        }

    }

    private fun openBottomSheet() {
        val dialogView: View = (context as AppCompatActivity).layoutInflater.inflate(R.layout.andes_layout_test_bs, null)
        val andesList = dialogView.findViewById<AndesList>(R.id.andesListDropdown)
        andesList.delegate = this

        dialog.setContentView(dialogView)

        dialog.setOnShowListener {
            andesTextfield.setRightIcon(
                    "andes_ui_chevron_up_12",
                    null,
                    R.color.andes_blue_ml_500)
        }

        dialog.setOnDismissListener {
            andesTextfield.clearFocus()
            andesTextfield.setRightIcon(
                    "andes_ui_chevron_down_12",
                    null,
                    R.color.andes_blue_ml_500)
        }

        dialog.show()


//            (context as AppCompatActivity).supportFragmentManager.let {
//                dropdownBottomSheetDialog.apply {
//                    dropdownBottomSheetDialog.mDelegate = this@AndesDropDown
//                    dropdownBottomSheetDialog.show(it, tag)
//                }
//            }
    }


    /**
     * Gets data from the config and sets to the AndesTextField Helper component.
     */
    private fun setupHelperComponent(config: AndesDropdownConfiguration) {
        andesTextfield.helper = config.helper
    }

    /**
     * Gets data from the config and sets to the AndesTextField PlaceHolder component.
     */
    private fun setupPlaceHolderComponent(config: AndesDropdownConfiguration) {
        andesTextfield.placeholder = config.placeHolder
    }

    /**
     * Gets data from the config and sets to the AndesTextField Label component.
     */
    private fun setupLabelComponent(config: AndesDropdownConfiguration) {
        andesTextfield.label = config.label
    }

    private fun setupAndesTextFieldComponent(config: AndesDropdownConfiguration) {
            andesTextfield.setRightIcon(
                    "andes_ui_chevron_down_24",
                    null,
                    R.color.andes_blue_ml_500)

            andesTextfield.onFocusChangeListener = onFocusChange
            andesTextfield.isEnabled = false
            andesTextfield.inputType = InputType.TYPE_NULL


        //TODO definir standalone form

//        val bottomSheet = AndesBottomSheet(context)
//        bottomSheet.layoutParams = LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT)
//        bottomSheet.state = AndesBottomSheetState.COLLAPSED
//
//        //TODO
//        (this.rootView as ViewGroup).addView(bottomSheet)
//        val list = AndesList(context)
//        val button = Button(context)
//        bottomSheet.setContent(list)
//        bottomSheet.setContent(button)
//

//        val behaviour = BottomSheetBehavior.from(bs.activity.view)

//        behaviour.peekHeight = 0

//        val bottomSheetBehavior: BottomSheetBehavior<*> = BottomSheetBehavior.from(bs.view)
//        bottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
    }


    override fun onItemClick(position: Int) {
        val itemSelected = listItems[position]

        listItems.forEach {
            it.isSelected = itemSelected == it
        }

        andesTextfield.text = itemSelected.title

        delegate.onItemSelected(this, position)

        dialog.dismiss()
        andesTextfield.clearFocus()
    }

    override fun bind(andesList: AndesList, view: View, position: Int): AndesListViewItem {

        val item = listItems[position]

        val row: AndesListViewItem?

        row = AndesListViewItemSimple(
                context,
                item.title,
                size = andesList.size,
                avatar = item.avatar,
                itemSelected = item.isSelected,
                titleMaxLines = 50
        )

        return row
    }

    override fun getDataSetSize(): Int = listItems.size

    private fun createConfig() = AndesDropdownConfigurationFactory.create(andesDropdownAttrs)

}