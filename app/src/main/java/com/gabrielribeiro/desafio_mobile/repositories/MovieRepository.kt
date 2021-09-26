package com.gabrielribeiro.desafio_mobile.repositories

import androidx.lifecycle.LiveData
import com.gabrielribeiro.desafio_mobile.data.entity.MovieEntity
import com.gabrielribeiro.desafio_mobile.data.remote.models.MoviesListResponse
import com.gabrielribeiro.desafio_mobile.utils.Resource

interface MovieRepository {

    suspend fun getMovies() : Resource<MoviesListResponse>

    suspend fun saveMovie(movie : MovieEntity) : Long

    fun getAllMoviesSaved() : LiveData<List<MovieEntity>>


}