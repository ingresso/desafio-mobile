package com.gabrielribeiro.desafio_mobile.data.remote.api

import com.gabrielribeiro.desafio_mobile.data.remote.model.MoviesListResponse
import com.gabrielribeiro.desafio_mobile.utils.Resource
import io.reactivex.Single
import retrofit2.Response
import retrofit2.http.GET

interface MovieAPi {

    @GET("events/coming-soon/partnership/desafio")
    suspend fun getMovies() : Response<MoviesListResponse>
}