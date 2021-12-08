package com.jeanbarrossilva.ingresso.ui.viewmodel

import androidx.lifecycle.ViewModel
import com.jeanbarrossilva.ingresso.model.Movie
import com.jeanbarrossilva.ingresso.repository.Repository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.flow

class MoviesViewModel: ViewModel() {
    private val moviesFlow = flow { emitAll(Repository.getMoviesFlow()) }

    fun getMoviesFlow(): Flow<List<Movie>> {
        return moviesFlow
    }
}