package com.schmitttech.ingresso.presentation.presale

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.schmitttech.ingresso.domain.model.Movie
import com.schmitttech.ingresso.domain.usecase.GetPreSaleMoviesUseCase
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn

class PreSaleViewModel(
    getPreSaleMoviesUseCase: GetPreSaleMoviesUseCase
) : ViewModel() {

    val preSaleMovies: StateFlow<List<Movie>> = getPreSaleMoviesUseCase()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )
}
