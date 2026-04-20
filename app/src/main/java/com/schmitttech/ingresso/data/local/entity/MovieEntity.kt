package com.schmitttech.ingresso.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Room Entity representing a cached Movie.
 * [isFavorite] is a purely local field — never sent to or received from the API.
 */
@Entity(tableName = "movies")
data class MovieEntity(
    @PrimaryKey val id: String,
    val title: String,
    val posterUrl: String?,
    val premiereDate: String?,
    val inPreSale: Boolean,
    val synopsis: String,
    val genres: String,
    val duration: String?,
    val ratingLabel: String?,
    val ratingColor: String?,
    val ratingDescription: String?,
    val isFavorite: Boolean = false
)
