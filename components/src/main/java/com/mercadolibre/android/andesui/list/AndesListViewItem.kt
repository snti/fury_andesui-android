package com.mercadolibre.android.andesui.list

import com.mercadolibre.android.andesui.button.AndesButton
import com.mercadolibre.android.andesui.color.AndesColor

//open class AndesListRow {
//
//
//}

//class AndesListRowSimple(
//        val title: String,
//        val color: AndesColor? = AndesColor(R.color.andes_gray_800),
//        val description: String? = null,
//        val colorDescription: AndesColor? = AndesColor(R.color.andes_gray_800),
//        val isSelectable: Boolean = false
//
//) : AndesListRow()


class AndesListRow private constructor(
        val title: String,
        val description: String?,
        val isSelectable: Boolean,
        val actionableComponent: ActionableComponent?) {

    data class Builder(
            var title: String,
            var description: String? = null,
            var isSelectable: Boolean = false,
            var actionableComponent: ActionableComponent? = null) {

        fun title(title: String) = apply { this.title = title }
        fun description(description: String) = apply { this.description = description }
        fun isSelectable(isSelectable: Boolean) = apply { this.isSelectable = isSelectable }
        fun actionableComponent(actionableComponent: ActionableComponent) = apply { this.actionableComponent = actionableComponent }
        fun build() = AndesListRow(title, description, isSelectable, actionableComponent)
    }

}

sealed class ActionableComponent {
    class AddButton(val button: AndesButton) : ActionableComponent()
}