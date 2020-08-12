package com.mercadolibre.android.andesui.textfield.maskTextField

import android.text.Editable
import android.text.TextWatcher

class TextFieldMaskWatcher(private var mask: String = "", private var textChange: OnTextChange?) : TextWatcher {
    private var isRunning = false
    private var isDeleting = false

    override fun beforeTextChanged(charSequence: CharSequence, start: Int, count: Int, after: Int) {
        isDeleting = count > after
    }

    override fun onTextChanged(charSequence: CharSequence, start: Int, before: Int, count: Int) {
        textChange?.onChange(charSequence.toString())
    }

    override fun afterTextChanged(editable: Editable) {
        if (isRunning || isDeleting) {
            return
        }
        isRunning = true

        val editableLength = editable.length
        if (mask.isNotBlank() && editableLength < mask.length && editableLength > 0) {
            if (mask[editableLength] != '#') {
                editable.append(mask[editableLength])
            } else if (mask[editableLength - 1] != '#') {
                editable.insert(editableLength - 1, mask, editableLength - 1, editableLength)
            }
        }

        isRunning = false
    }

    fun setMask(mask: String) {
        this.mask = mask
    }

    interface OnTextChange {
        fun onChange(text: String)
    }
}
