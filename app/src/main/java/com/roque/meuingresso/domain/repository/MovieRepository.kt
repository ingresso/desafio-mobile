package com.roque.meuingresso.domain.repository

import com.roque.meuingresso.domain.model.Movie
import kotlinx.coroutines.flow.Flow

interface MovieRepository {
    fun getMovies(): Flow<List<Movie>>
}