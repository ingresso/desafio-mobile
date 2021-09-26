package com.gabrielribeiro.desafio_mobile.repositories

import androidx.lifecycle.LiveData
import com.gabrielribeiro.desafio_mobile.data.database.MovieDatabase
import com.gabrielribeiro.desafio_mobile.data.entity.MovieEntity
import com.gabrielribeiro.desafio_mobile.data.remote.api.MovieAPi
import com.gabrielribeiro.desafio_mobile.data.remote.models.MoviesListResponse
import com.gabrielribeiro.desafio_mobile.utils.Resource

class MovieRepositoryImplement(private val api: MovieAPi, private val movieDatabase: MovieDatabase) : MovieRepository {
    override suspend fun getMovies(): Resource<MoviesListResponse> {
        return try {
            val response = api.getMovies()
            if (response.isSuccessful) {
                response.body()?.let {
                    return@let Resource.Success(it)
                } ?: Resource.Failure(null, response.message())

            } else {
                Resource.Failure(null, response.message())
            }

        } catch (e: Exception) {
            Resource.Failure(e, null)
        }

    }

    override suspend fun saveMovie(movie : MovieEntity): Long {
       return movieDatabase.getMovieDAO().saveMovie(movie)
    }

    override fun getAllMoviesSaved(): LiveData<List<MovieEntity>> {
        return movieDatabase.getMovieDAO().getAllMoviesSaved()
    }
}