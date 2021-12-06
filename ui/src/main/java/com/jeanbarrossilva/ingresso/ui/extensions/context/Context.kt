package com.jeanbarrossilva.ingresso.ui.extensions.context

import android.content.Context
import android.graphics.Color
import com.jeanbarrossilva.ingresso.extensions.color.copy
import com.jeanbarrossilva.ingresso.extensions.context.colorOf
import com.jeanbarrossilva.ingresso.ui.R

val Context.defaultRippleColor
    get() = colorOf(R.attr.colorSecondary)
        .let(Color::valueOf)
        .copy(alpha = .026f)
        .toArgb()