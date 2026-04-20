package com.schmitttech.ingresso.data.repository

import android.util.Log
import com.schmitttech.ingresso.data.local.dao.MovieDao
import com.schmitttech.ingresso.data.mapper.toDomain
import com.schmitttech.ingresso.data.mapper.toEntity
import com.schmitttech.ingresso.data.remote.api.IngressoApi
import com.schmitttech.ingresso.domain.model.Movie
import com.schmitttech.ingresso.domain.repository.MoviesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map

class MoviesRepositoryImpl(
    private val api: IngressoApi,
    private val dao: MovieDao
) : MoviesRepository {

    companion object {
        private const val TAG = "MoviesRepository"
    }

    override suspend fun refreshMovies(): Result<Unit> {
        Log.d(TAG, "refreshMovies: Fetching from network")
        return runCatching {
            val networkResponse = api.getComingSoonMovies().items.map { it.toDomain() }
            Log.d(TAG, "refreshMovies: Received ${networkResponse.size} movies from API")
            
            val existingFavorites = dao.observeFavorites().first().map { it.id }.toSet()
            val entities = networkResponse.map { movie -> 
                movie.toEntity(isFavorite = existingFavorites.contains(movie.id)) 
            }

            val currentIds = entities.map { it.id }

            Log.d(TAG, "refreshMovies: Syncing DB, deleting stale and upserting ${entities.size} entities")
            dao.deleteStaleMovies(currentIds)
            dao.upsertAll(entities)
        }.onFailure {
            Log.e(TAG, "refreshMovies: Failed to refresh movies", it)
        }
    }

    override fun observeMovies(): Flow<List<Movie>> {
        return dao.observeAll().map { list -> list.map { it.toDomain() } }
    }

    override fun observeMovieById(id: String): Flow<Movie?> {
        return dao.observeById(id).map { it?.toDomain() }
    }

    override fun observeFavorites(): Flow<List<Movie>> {
        return dao.observeFavorites().map { list -> list.map { it.toDomain() } }
    }

    override suspend fun toggleFavorite(id: String, isFavorite: Boolean) {
        dao.setFavorite(id, isFavorite)
    }

    override fun observePreSale(): Flow<List<Movie>> {
        return dao.observePreSale().map { list -> list.map { it.toDomain() } }
    }

    override fun observeRelatedByGenre(excludeId: String, genre: String): Flow<List<Movie>> {
        return dao.observeRelatedByGenre(excludeId, genre).map { list -> list.map { it.toDomain() } }
    }
}

