package com.jeanbarrossilva.ingresso.extensions.context

import android.content.Context
import android.content.res.TypedArray
import android.graphics.Bitmap
import android.graphics.Color
import android.util.AttributeSet
import androidx.annotation.AttrRes
import androidx.annotation.ColorInt
import androidx.annotation.StyleableRes
import androidx.core.content.withStyledAttributes
import androidx.core.graphics.drawable.toBitmap
import coil.Coil
import coil.executeBlocking
import coil.request.ImageRequest
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 * Loads the image in [url] asynchronously and turns it into a [Bitmap].
 *
 * @param url URL in which the image is located.
 **/
suspend fun Context.bitmapOf(url: String): Bitmap? {
    return ImageRequest.Builder(this)
        .data(url)
        .build()
        .let { request ->
            withContext(Dispatchers.IO) {
                Coil.imageLoader(this@bitmapOf)
                    .executeBlocking(request)
                    .drawable
                    ?.toBitmap()
            }
        }
}

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