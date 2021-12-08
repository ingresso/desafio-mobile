package com.jeanbarrossilva.ingresso.extensions.date

import java.text.SimpleDateFormat
import java.util.Date

val Date.displayable: String
    get() = SimpleDateFormat.getDateInstance(SimpleDateFormat.SHORT).format(this)