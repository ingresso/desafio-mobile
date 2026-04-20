package com.schmitttech.ingresso.domain.usecase

import com.schmitttech.ingresso.domain.model.Movie
import com.schmitttech.ingresso.domain.repository.MoviesRepository
import kotlin.comparisons.compareBy
import kotlin.comparisons.nullsLast

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GetComingSoonMoviesUseCase(
    private val repository: MoviesRepository
) {
    operator fun invoke(): Flow<List<Movie>> {
        return repository.observeMovies().map { movies ->
            movies.sortedWith(
                compareBy(nullsLast()) { it.premiereDate }
            )
        }
    }
}


