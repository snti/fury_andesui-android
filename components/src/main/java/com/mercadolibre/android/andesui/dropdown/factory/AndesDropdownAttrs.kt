package com.mercadolibre.android.andesui.dropdown.factory

import android.content.Context
import android.util.AttributeSet
import com.mercadolibre.android.andesui.R

internal data class AndesDropdownAttrs(
        val andesDropdownTriggerType: AndesDropdownTriggerType,
        val andesDropdownMenuType : AndesDropdownMenuType
)


/**
 * This object parse the attribute set and return an instance of AndesListAttrs
 * to be used by AndesList
 */
internal object AndesDropdownAttrParser {

    private const val ANDES_DROPDOWN_TRIGGER_TYPE_FORMDROPDOWN = "9000"
    private const val ANDES_DROPDOWN_TRIGGER_TYPE_STANDALONE = "9001"

    private const val ANDES_DROPDOWN_MENU_TYPE_BOTTOMSHEET = "9002"
    private const val ANDES_DROPDOWN_MENU_TYPE_FLOATINGMENU = "9003"

    fun parse(context: Context, attr: AttributeSet?): AndesDropdownAttrs {
        val typedArray = context.obtainStyledAttributes(attr, R.styleable.AndesList)

        val andesDropdownTriggerType = when (typedArray.getString(R.styleable.AndesDropdown_AndesDropdownTriggerType)) {
            ANDES_DROPDOWN_TRIGGER_TYPE_FORMDROPDOWN -> AndesDropdownTriggerType.FORMDROPDOWN
            ANDES_DROPDOWN_TRIGGER_TYPE_STANDALONE -> AndesDropdownTriggerType.STANDALONE
            else -> AndesDropdownTriggerType.FORMDROPDOWN
        }

        val andesDropdownMenuType = when (typedArray.getString(R.styleable.AndesDropdown_AndesDropdownMenuType)) {
            ANDES_DROPDOWN_MENU_TYPE_BOTTOMSHEET -> AndesDropdownMenuType.BOTTOMSHEET
            ANDES_DROPDOWN_MENU_TYPE_FLOATINGMENU -> AndesDropdownMenuType.FLOATINGMENU
            else -> AndesDropdownMenuType.BOTTOMSHEET
        }

        return AndesDropdownAttrs(
                andesDropdownTriggerType = andesDropdownTriggerType,
                andesDropdownMenuType = andesDropdownMenuType
        ).also { typedArray.recycle() }
    }

}