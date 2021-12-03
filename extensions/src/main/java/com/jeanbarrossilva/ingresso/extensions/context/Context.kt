package com.jeanbarrossilva.ingresso.extensions.context

import android.content.Context
import android.content.res.TypedArray
import android.graphics.Color
import android.util.AttributeSet
import android.util.TypedValue
import androidx.annotation.AttrRes
import androidx.annotation.ColorInt
import androidx.annotation.StyleableRes
import androidx.core.content.withStyledAttributes

/** Gets the color that corresponds to [colorAttrRes]. **/
@ColorInt
fun Context.colorOf(@AttrRes colorAttrRes: Int): Int {
    val typedArray = theme.obtainStyledAttributes(intArrayOf(colorAttrRes))
    val color = typedArray.getColor(0, Color.TRANSPARENT)

    typedArray.recycle()
    return color
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