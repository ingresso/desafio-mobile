package com.jeanbarrossilva.ingresso.repository

import com.jeanbarrossilva.ingresso.model.Movie
import com.jeanbarrossilva.ingresso.network.IngressoService
import com.jeanbarrossilva.ingresso.network.dto.MovieDto
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.rx3.asFlow

object Repository {
    private val service = IngressoService()

    fun getMoviesFlow(): Flow<List<Movie>> {
        return service
            .getMovies()
            .asFlow()
            .map { result -> result.items }
            .map { movieDtos -> movieDtos.map(MovieDto::toMovie) }
    }

    fun search(query: String): Flow<List<Movie>> {
        return getMoviesFlow().map { movies ->
            movies.filter {
                query in it
            }
        }
    }
}