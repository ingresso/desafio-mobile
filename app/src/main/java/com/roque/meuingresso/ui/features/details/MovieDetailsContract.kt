package com.roque.meuingresso.ui.features.details

import com.roque.meuingresso.domain.model.Movie
import com.roque.meuingresso.util.UiEffect
import com.roque.meuingresso.util.UiIntent
import com.roque.meuingresso.util.UiState

class MovieDetailsContract {
    data class State(
        val isLoading: Boolean = false,
        val movie: Movie? = null,
        val error: String? = null
    ) : UiState

    sealed class Intent : UiIntent {
        data class LoadMovieDetail(val movieId: String) : Intent()
        object OnBackClicked : Intent()
    }

    sealed class Effect : UiEffect {
        object NavigateBack : Effect()
    }
}