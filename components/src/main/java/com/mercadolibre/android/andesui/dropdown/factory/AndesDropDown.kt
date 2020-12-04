package com.mercadolibre.android.andesui.dropdown.factory

import android.content.Context
import android.support.constraint.ConstraintLayout
import android.util.AttributeSet

class AndesDropDown : ConstraintLayout {
    private lateinit var andesDropdownAttrs: AndesDropdownAttrs

    /**
     * Getter and setter for [andesDropdownTrigger].
     */
    var triggerType: AndesDropdownTriggerType
        get() = andesDropdownAttrs.andesDropdownTriggerType
        set(value) {
            andesDropdownAttrs = andesDropdownAttrs.copy(andesDropdownTriggerType = value)
        }

    /**
     * Getter and setter for [andesDropdownTrigger].
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
            triggerType: AndesDropdownTriggerType = AndesDropdownTriggerType.FORMDROPDOWN,
            menuType: AndesDropdownMenuType = AndesDropdownMenuType.BOTTOMSHEET
    ) : super(context) {
        initAttrs(triggerType, menuType)
    }

    /**
     * Sets the proper [config] for this component based on the [attrs] received via XML.
     *
     * @param attrs attributes from the XML.
     */
    private fun initAttrs(attrs: AttributeSet?) {
        andesDropdownAttrs = AndesDropdownAttrParser.parse(context, attrs)
    }

    private fun initAttrs(triggerType: AndesDropdownTriggerType, menuType: AndesDropdownMenuType) {
        andesDropdownAttrs = AndesDropdownAttrs(triggerType, menuType)
        val config = AndesDropdownConfigurationFactory.create(andesDropdownAttrs)
        setupComponents(config)
    }

    /**
     * Responsible for setting up all properties of each component that is part of this andesDropdown.
     * Is like a choreographer 😉
     */
    private fun setupComponents(config: AndesDropdownConfiguration) {

    }


}