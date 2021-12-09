package com.jeanbarrossilva.ingresso.extensions.view.edittext

import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import androidx.core.widget.doOnTextChanged
import com.jeanbarrossilva.ingresso.extensions.context.inputMethodManager

/** @see doOnTextChanged **/
fun EditText.doOnTextChanged(operation: (text: String) -> Unit) {
    doOnTextChanged { text, _, _, _ ->
        text?.toString()?.let(operation)
    }
}

/** Gets the focus out of this [EditText] and closes the keyboard. **/
fun EditText.closeKeyboard() {
    clearFocus()
    context.inputMethodManager?.hideSoftInputFromWindow(windowToken, InputMethodManager.HIDE_NOT_ALWAYS and InputMethodManager.HIDE_IMPLICIT_ONLY)
}

/** Focuses on this [EditText] and opens the keyboard. **/
fun EditText.openKeyboard() {
    requestFocus()
    context.inputMethodManager?.showSoftInput(this@openKeyboard, InputMethodManager.SHOW_FORCED and InputMethodManager.SHOW_IMPLICIT)
}