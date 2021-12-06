package com.jeanbarrossilva.ingresso.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Title(val original: String, val localized: String): Parcelable {
    constructor(original: String): this(original, original)

    operator fun contains(other: String): Boolean {
        return original.contains(other, ignoreCase = true) || localized.contains(other, ignoreCase = true)
    }
}