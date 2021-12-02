package com.jeanbarrossilva.ingresso.extensions.context

import android.content.Context
import android.content.res.TypedArray
import android.util.AttributeSet
import androidx.annotation.StyleableRes
import androidx.core.content.withStyledAttributes

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