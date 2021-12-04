package com.jeanbarrossilva.ingresso.model

data class Title(val original: String, val localized: String) {
    constructor(original: String): this(original, original)
}