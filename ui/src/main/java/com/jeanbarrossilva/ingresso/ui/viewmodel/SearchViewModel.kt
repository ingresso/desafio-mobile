package com.jeanbarrossilva.ingresso.ui.viewmodel

import androidx.lifecycle.ViewModel
import com.jeanbarrossilva.ingresso.repository.Repository
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow

class SearchViewModel: ViewModel() {
    val moviesFlow = flow {
        Repository.getMoviesFlow().collect {
            emit(it)
        }
    }
}