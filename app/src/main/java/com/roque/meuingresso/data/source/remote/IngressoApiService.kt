package com.roque.meuingresso.data.source.remote

import com.roque.meuingresso.data.model.MovieResponse
import retrofit2.Response
import retrofit2.http.GET

interface IngressoApiService {
    @GET("v0/events/coming-soon/partnership/desafio")
    suspend fun getMovies(): Response<MovieResponse>
}