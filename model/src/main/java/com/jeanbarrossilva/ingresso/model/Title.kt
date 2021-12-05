package com.jeanbarrossilva.ingresso.model

data class Title(val original: String, val localized: String) {
    constructor(original: String): this(original, original)

    operator fun contains(other: String): Boolean {
        return original.contains(other, ignoreCase = true) || localized.contains(other, ignoreCase = true)
    }
}