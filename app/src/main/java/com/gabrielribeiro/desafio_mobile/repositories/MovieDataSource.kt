package com.gabrielribeiro.desafio_mobile.repositories

import com.gabrielribeiro.desafio_mobile.data.remote.api.MovieAPi

class MovieDataSource(private val api: MovieAPi) {
    suspend fun getMovies() = api.getMovies()
}