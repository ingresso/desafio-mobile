package com.schmitttech.ingresso.data.local.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.schmitttech.ingresso.data.local.entity.MovieEntity
import kotlinx.coroutines.flow.Flow

/**
 * Data Access Object for Movie persistence.
 */
@Dao
interface MovieDao {

    /** Inserts or updates a full list of movies from the API. */
    @Upsert
    suspend fun upsertAll(movies: List<MovieEntity>)

    /** Emits the full movie list reactively whenever the table changes. */
    @Query("SELECT * FROM movies ORDER BY CASE WHEN premiereDate IS NULL THEN 1 ELSE 0 END ASC, premiereDate ASC")
    fun observeAll(): Flow<List<MovieEntity>>

    /** Returns a single movie by ID synchronously (for details screen). */
    @Query("SELECT * FROM movies WHERE id = :id LIMIT 1")
    fun observeById(id: String): Flow<MovieEntity?>

    /** Emits only the favorite movies reactively. */
    @Query("SELECT * FROM movies WHERE isFavorite = 1 ORDER BY title ASC")
    fun observeFavorites(): Flow<List<MovieEntity>>

    /** Toggles the favorite flag without touching other fields. */
    @Query("UPDATE movies SET isFavorite = :isFavorite WHERE id = :id")
    suspend fun setFavorite(id: String, isFavorite: Boolean)

    /**
     * Returns movies that are in pre-sale.
     */
    @Query("SELECT * FROM movies WHERE inPreSale = 1 ORDER BY CASE WHEN premiereDate IS NULL THEN 1 ELSE 0 END ASC, premiereDate ASC")
    fun observePreSale(): Flow<List<MovieEntity>>

    /**
     * Deletes movies that are NOT in the provided [currentIds].
     * This keeps the cache strictly synced with the API.
     */
    @Query("DELETE FROM movies WHERE id NOT IN (:currentIds)")
    suspend fun deleteStaleMovies(currentIds: List<String>)

    /**
     * Returns movies that share at least one genre with the given comma-separated [genre] string,
     * excluding the movie with [excludeId].
     */
    @Query("SELECT * FROM movies WHERE id != :excludeId AND genres LIKE '%' || :genre || '%' LIMIT 10")
    fun observeRelatedByGenre(excludeId: String, genre: String): Flow<List<MovieEntity>>
}
