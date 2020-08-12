package com.mercadolibre.android.andesui.textfield.maskTextField

import java.io.Serializable

data class TextFieldMask(
    val size: Int,
    val mask: String,
    val digits: String
) : Serializable
