package com.jeanbarrossilva.ingresso.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jeanbarrossilva.ingresso.model.Movie
import com.jeanbarrossilva.ingresso.repository.Repository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class MoviesViewModel: ViewModel() {
    private val moviesFlow = MutableStateFlow(emptyList<Movie>())

    init {
        refresh()
    }

    fun getMoviesFlow(): Flow<List<Movie>> {
        return moviesFlow
    }

    fun refresh() {
        viewModelScope.launch {
            moviesFlow.emit(Repository.getMovies())
        }
    }
}