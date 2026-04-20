package com.schmitttech.ingresso.ui.util

import java.time.OffsetDateTime
import java.time.format.DateTimeFormatter
import java.util.Locale

/**
 * Utility for formatting dates in the UI.
 */
object DateFormatter {
    private val displayFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy", Locale("pt", "BR"))

    /**
     * Formats an [OffsetDateTime] to a human-readable string (e.g., 18 Abr 2026).
     */
    fun format(dateTime: OffsetDateTime?): String {
        return dateTime?.format(displayFormatter)?.replaceFirstChar { it.uppercase() } ?: ""
    }
}
