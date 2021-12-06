package com.jeanbarrossilva.ingresso.network.dto

import java.util.Date

data class PremiereDateDto(val localDate: String) {
    fun toDate(): Date? {
        return localDate
            .run { substring(0, indexOf('T')) }
            .split('-')
            .map(String::toInt)
            .let { (year, month, day) -> Date(year, month, day) }
    }
}