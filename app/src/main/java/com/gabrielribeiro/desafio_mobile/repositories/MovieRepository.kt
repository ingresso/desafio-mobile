package com.gabrielribeiro.desafio_mobile.repositories

import com.gabrielribeiro.desafio_mobile.data.remote.model.MoviesListResponse
import com.gabrielribeiro.desafio_mobile.utils.Resource
import retrofit2.Response

interface MovieRepository {

    suspend fun getMovies() :Resource<MoviesListResponse>
}