package com.jeanbarrossilva.ingresso.extensions.context

import android.content.Context
import android.content.Intent
import android.content.res.TypedArray
import android.graphics.Color
import android.util.AttributeSet
import android.view.inputmethod.InputMethodManager
import androidx.annotation.AttrRes
import androidx.annotation.ColorInt
import androidx.annotation.StringRes
import androidx.annotation.StyleableRes
import androidx.core.content.getSystemService
import androidx.core.content.withStyledAttributes

/** Short-hand for getting the [InputMethodManager] from this [Context]. **/
val Context.inputMethodManager
    get() = getSystemService<InputMethodManager>()

/** Gets the color that corresponds to [colorAttrRes]. **/
@ColorInt
fun Context.colorOf(@AttrRes colorAttrRes: Int): Int {
    val typedArray = theme.obtainStyledAttributes(intArrayOf(colorAttrRes))
    val color = typedArray.getColor(0, Color.TRANSPARENT)

    typedArray.recycle()
    return color
}

/** @see share **/
fun Context.share(@StringRes titleRes: Int, text: String) {
    share(getString(titleRes), text)
}

/** Opens the share sheet with [title] to share the given [text]. **/
fun Context.share(title: String, text: String) {
    Intent(Intent.ACTION_SEND)
        .apply {
            type = "text/html"
            putExtra(Intent.EXTRA_TEXT, text)
        }
        .let { Intent.createChooser(it, title) }
        .let(::startActivity)
}

/** @see withStyledAttributes **/
fun Context.withStyledAttributes(
    @StyleableRes styleableRes: IntArray,
    attrs: AttributeSet?,
    defStyleAttr: Int,
    onEachIndex: TypedArray.(index: Int) -> Unit
) {
    withStyledAttributes(attrs, styleableRes, defStyleAttr) {
        0.until(indexCount).forEach { index ->
            onEachIndex(index)
        }
    }
}