package br.com.hectorfortuna.ingressoapp.data.network

import br.com.hectorfortuna.ingressoapp.data.model.AllMoviesResponse
import retrofit2.http.GET

interface Service {
    @GET("/events/coming-soon/partnership/desafio")
    suspend fun getAllMovies(
    ): AllMoviesResponse
}