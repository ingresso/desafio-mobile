package com.gabrielribeiro.desafio_mobile.data.remote.api

import com.gabrielribeiro.desafio_mobile.data.remote.models.MoviesListResponse
import retrofit2.Response
import retrofit2.http.GET

interface MovieAPi {

    @GET("events/coming-soon/partnership/desafio")
    suspend fun getMovies() : Response<MoviesListResponse>
}