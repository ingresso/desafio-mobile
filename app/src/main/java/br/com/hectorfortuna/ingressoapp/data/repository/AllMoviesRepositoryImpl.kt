package br.com.hectorfortuna.ingressoapp.data.repository

import br.com.hectorfortuna.ingressoapp.data.model.AllMoviesResponse
import br.com.hectorfortuna.ingressoapp.data.network.Service
import javax.inject.Inject

class AllMoviesRepositoryImpl @Inject constructor(private val api: Service) : AllMoviesRepository {
    override suspend fun getAllMovies(): AllMoviesResponse =
        api.getAllMovies()
}