package com.jeanbarrossilva.ingresso.repository

import com.jeanbarrossilva.ingresso.model.Movie
import com.jeanbarrossilva.ingresso.network.IngressoService
import com.jeanbarrossilva.ingresso.network.dto.MovieDto
import kotlinx.coroutines.rx3.awaitFirst

object Repository {
    private val service = IngressoService()

    suspend fun getMovies(): List<Movie> {
        return service
            .getMovies()
            .awaitFirst()
            .items
            .map(MovieDto::toMovie)
    }

    suspend fun search(query: String): List<Movie> {
        return getMovies().filter {
            query in it
        }
    }
}