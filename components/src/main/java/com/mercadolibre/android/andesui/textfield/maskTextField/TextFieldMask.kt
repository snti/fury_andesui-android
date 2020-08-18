package com.mercadolibre.android.andesui.textfield.maskTextField

import java.io.Serializable

data class TextFieldMask(
    val mask: String,
    val digits: String? = null
) : Serializable
