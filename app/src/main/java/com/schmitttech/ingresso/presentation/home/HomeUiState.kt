package com.schmitttech.ingresso.presentation.home

import com.schmitttech.ingresso.domain.model.Movie

/**
 * Represents the different UI states for the Home screen.
 */
sealed interface HomeUiState {
    data object Loading : HomeUiState
    data class Success(
        val movies: List<Movie>,
        val genres: List<String>,
        val selectedGenre: String? = null
    ) : HomeUiState
    data class Error(val message: String) : HomeUiState
}
