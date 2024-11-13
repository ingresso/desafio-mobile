package br.com.hectorfortuna.ingressoapp.data.repository

import br.com.hectorfortuna.ingressoapp.data.model.AllMoviesResponse

interface AllMoviesRepository {
    suspend fun getAllMovies(): AllMoviesResponse
}