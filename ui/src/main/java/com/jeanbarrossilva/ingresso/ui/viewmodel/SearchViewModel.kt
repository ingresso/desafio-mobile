package com.jeanbarrossilva.ingresso.ui.viewmodel

import androidx.lifecycle.ViewModel
import com.jeanbarrossilva.ingresso.repository.Repository
import kotlinx.coroutines.flow.flow

class SearchViewModel: ViewModel() {
    val moviesFlow = flow {
        emit(Repository.getMovies())
    }
}