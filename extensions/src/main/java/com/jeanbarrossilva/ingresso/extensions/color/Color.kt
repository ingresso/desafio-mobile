package com.jeanbarrossilva.ingresso.extensions.color

import android.graphics.Color
import androidx.annotation.FloatRange

/**
 * Creates a new color with the new provided values. Similar to a data class' `copy` function.
 **/
fun Color.copy(
    @FloatRange(from = 0.0, to = 1.0) alpha: Float = alpha(),
    red: Float = red(),
    blue: Float = blue(),
    green: Float = green()
): Color {
    return Color.valueOf(Color.argb(alpha, red, green, blue))
}