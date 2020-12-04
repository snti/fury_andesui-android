package com.mercadolibre.android.andesui.dropdown.factory


internal data class AndesDropdownConfiguration(
        val triggerType: AndesDropdownTriggerType,
        val menuType: AndesDropdownMenuType
)

internal object AndesDropdownConfigurationFactory {

    fun create(andesDropdownAttrs: AndesDropdownAttrs): AndesDropdownConfiguration {
        return with(andesDropdownAttrs) {
            AndesDropdownConfiguration(
                    triggerType = andesDropdownAttrs.andesDropdownTriggerType,
                    menuType = andesDropdownAttrs.andesDropdownMenuType
            )
        }
    }

}