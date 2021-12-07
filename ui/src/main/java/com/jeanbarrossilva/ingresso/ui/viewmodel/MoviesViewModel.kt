package com.jeanbarrossilva.ingresso.ui.viewmodel

import androidx.lifecycle.ViewModel
import com.jeanbarrossilva.ingresso.model.Movie
import com.jeanbarrossilva.ingresso.repository.Repository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collect

class MoviesViewModel: ViewModel() {
    private val moviesFlow = MutableStateFlow(emptyList<Movie>())

    fun getMoviesFlow(): Flow<List<Movie>> {
        return moviesFlow
    }

    suspend fun refresh() {
        Repository.getMoviesFlow().collect {
            moviesFlow.emit(it)
        }
    }
}