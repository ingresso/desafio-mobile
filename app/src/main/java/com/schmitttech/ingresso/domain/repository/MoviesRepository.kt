package com.schmitttech.ingresso.domain.repository

import com.schmitttech.ingresso.domain.model.Movie
import kotlinx.coroutines.flow.Flow

/**
 * Repository interface defining the data contract for movies.
 */
interface MoviesRepository {
    suspend fun refreshMovies(): Result<Unit>
    fun observeMovies(): Flow<List<Movie>>
    fun observeMovieById(id: String): Flow<Movie?>
    fun observeFavorites(): Flow<List<Movie>>
    suspend fun toggleFavorite(id: String, isFavorite: Boolean)
    fun observePreSale(): Flow<List<Movie>>
    fun observeRelatedByGenre(excludeId: String, genre: String): Flow<List<Movie>>
}

