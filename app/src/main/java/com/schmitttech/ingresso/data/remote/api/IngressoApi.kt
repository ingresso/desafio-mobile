package com.schmitttech.ingresso.data.remote.api

import com.schmitttech.ingresso.data.remote.model.MoviesResponse
import retrofit2.http.GET

interface IngressoApi {

    @GET(NetworkConstants.COMING_SOON_ENDPOINT)
    suspend fun getComingSoonMovies(): MoviesResponse
}

