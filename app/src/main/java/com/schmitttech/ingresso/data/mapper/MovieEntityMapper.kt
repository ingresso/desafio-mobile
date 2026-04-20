package com.schmitttech.ingresso.data.mapper

import com.schmitttech.ingresso.data.local.entity.MovieEntity
import com.schmitttech.ingresso.domain.model.Movie
import com.schmitttech.ingresso.domain.model.Rating
import java.time.OffsetDateTime

/**
 * Converts a domain [Movie] to a [MovieEntity] for local storage.
 * [isFavorite] defaults to false when inserting from the network;
 * the DAO's UPSERT will preserve the existing value for known IDs.
 */
fun Movie.toEntity(isFavorite: Boolean = false) = MovieEntity(
    id = id,
    title = title,
    posterUrl = posterUrl,
    premiereDate = premiereDate?.toString(),
    inPreSale = inPreSale,
    synopsis = synopsis,
    genres = genres.joinToString(","),
    duration = duration,
    ratingLabel = rating?.label,
    ratingColor = rating?.color,
    ratingDescription = rating?.description,
    isFavorite = isFavorite
)

/**
 * Converts a [MovieEntity] (from the local DB) to a domain [Movie].
 * Preserves the favorite status stored in the DB.
 */
fun MovieEntity.toDomain() = Movie(
    id = id,
    title = title,
    posterUrl = posterUrl,
    premiereDate = premiereDate?.parseToOffsetDateTime(),
    inPreSale = inPreSale,
    synopsis = synopsis,
    genres = genres.split(",").filter { it.isNotBlank() },
    duration = duration,
    rating = if (ratingLabel != null) {
        Rating(
            label = ratingLabel,
            color = ratingColor ?: "#666666",
            description = ratingDescription ?: ""
        )
    } else null,
    isFavorite = isFavorite
)

private fun String.parseToOffsetDateTime(): OffsetDateTime? {
    return try { OffsetDateTime.parse(this) } catch (_: Exception) { null }
}
