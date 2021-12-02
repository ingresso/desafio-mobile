package com.jeanbarrossilva.ingresso.extensions.calendar

import androidx.annotation.IntRange
import java.time.Month
import java.util.Calendar
import java.util.Calendar.DAY_OF_MONTH
import java.util.Calendar.MONTH
import java.util.Calendar.YEAR

/** Gets an instance of [Calendar] and sets [year], [month] and [day] according to their equivalent fields. **/
fun calendarOf(year: Int, @IntRange(from = 1, to = 12) month: Int, @IntRange(from = 1, to = 31) day: Int): Calendar {
    return Calendar.getInstance().apply {
        set(YEAR, year)
        set(MONTH, month)
        set(DAY_OF_MONTH, day)
    }
}