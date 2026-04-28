package com.roque.meuingresso.ui.features.details

import androidx.lifecycle.viewModelScope
import com.roque.meuingresso.domain.repository.MovieRepository
import com.roque.meuingresso.util.BaseViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class MovieDetailsViewModel(
    private val repository: MovieRepository
) : BaseViewModel<MovieDetailsContract.State, MovieDetailsContract.Intent, MovieDetailsContract.Effect>(
    initialState = MovieDetailsContract.State()
) {

    override fun handleIntent(intent: MovieDetailsContract.Intent) {
        when (intent) {
            is MovieDetailsContract.Intent.LoadMovieDetail -> fetchMovieDetail(intent.movieId)
            is MovieDetailsContract.Intent.OnBackClicked -> setEffect(MovieDetailsContract.Effect.NavigateBack)
        }
    }

    private fun fetchMovieDetail(movieId: String) {
        viewModelScope.launch {
            setState { copy(isLoading = true) }
            repository.getMovies()
                .catch { e -> setState { copy(isLoading = false, error = e.message) } }
                .collect { movies ->
                    val movie = movies.find { it.id == movieId }
                    setState { copy(isLoading = false, movie = movie) }
                }
        }
    }
}