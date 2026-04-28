package com.roque.meuingresso.ui.features.movielist

import com.roque.meuingresso.domain.model.Movie
import com.roque.meuingresso.util.UiEffect
import com.roque.meuingresso.util.UiIntent
import com.roque.meuingresso.util.UiState

class MovieListContract {

    data class State(
        val isLoading: Boolean = false,
        val movies: List<Movie> = emptyList(),
        val error: String? = null
    ) : UiState

    sealed class Intent : UiIntent {
        object LoadMovies : Intent()
        object Refresh : Intent()
        data class OnMovieClicked(val movieId: String) : Intent()
    }

    sealed class Effect : UiEffect {
        data class ShowError(val message: String) : Effect()
        data class NavigateToDetails(val movieId: String) : Effect()
    }
}