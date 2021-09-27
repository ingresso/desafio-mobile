package com.gabrielribeiro.desafio_mobile.utils

import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

object BackEndUtils {
    private val locale: Locale = Locale.forLanguageTag("pt-BR")
    private val dateFormat: DateFormat = SimpleDateFormat("yyyyy-MM-dd'T'HH:mm:ssZ", locale)

    private val dateFormatPremiereDate: DateFormat = SimpleDateFormat("dd/MM/yyyy", locale)

    fun parseDateTime(dateString: String): Date? {
        return dateFormat.parse(dateString)
    }

    fun formatDateTime(date: Date): String {
        return dateFormat.format(date)
    }

    fun formatDateFormatPremiereDate(date: Date): String? {
        return dateFormatPremiereDate.format(date)
    }
}