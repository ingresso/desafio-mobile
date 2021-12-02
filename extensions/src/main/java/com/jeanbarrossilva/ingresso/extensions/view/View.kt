package com.jeanbarrossilva.ingresso.extensions.view

import android.util.TypedValue
import android.view.View

val View.dp: Int.() -> Int
    get() = {
        val value = this.toFloat()
        TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, value, this@dp.resources.displayMetrics).toInt()
    }