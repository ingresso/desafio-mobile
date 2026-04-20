package com.schmitttech.ingresso.data.mapper

import com.schmitttech.ingresso.data.remote.model.MovieResponse
import com.schmitttech.ingresso.domain.model.Movie
import com.schmitttech.ingresso.domain.model.Rating
import java.time.OffsetDateTime

/**
 * Mapper responsible for converting API DTOs into Domain Models.
 * Centralizes the logic for null handling and type conversion.
 */
fun MovieResponse.toDomain(): Movie {
    return Movie(
        id = id,
        title = title,
        posterUrl = images.firstOrNull { it.type == "PosterPortrait" }?.url,
        premiereDate = premiereDate?.localDate?.parseToOffsetDateTime(),
        inPreSale = inPreSale,
        synopsis = synopsis ?: "",
        genres = genres,
        duration = duration,
        rating = ratingDetails?.let {
            Rating(
                label = it.label ?: "",
                color = it.color ?: "#666666",
                description = it.description ?: ""
            )
        }
    )
}

/**
 * Extension function to parse a date string into OffsetDateTime safely.
 */
private fun String.parseToOffsetDateTime(): OffsetDateTime? {
    return try {
        OffsetDateTime.parse(this)
    } catch (_: Exception) {
        null
    }
}
