package com.schmitttech.ingresso.domain.model

import java.time.OffsetDateTime

/**
 * Domain model representing a Movie.
 * This class is independent of mapping libraries (like Serialization) and the API.
 */
data class Movie(
    val id: String,
    val title: String,
    val posterUrl: String?,
    val premiereDate: OffsetDateTime?,
    val inPreSale: Boolean,
    val synopsis: String,
    val genres: List<String>,
    val duration: String?,
    val rating: Rating?,
    val isFavorite: Boolean = false
)

data class Rating(
    val label: String,
    val color: String,
    val description: String
)
