package com.roque.meuingresso.ui.features.movielist

import androidx.lifecycle.viewModelScope
import com.roque.meuingresso.domain.repository.MovieRepository
import com.roque.meuingresso.util.BaseViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class MovieListViewModel(
    private val repository: MovieRepository
) : BaseViewModel<MovieListContract.State, MovieListContract.Intent, MovieListContract.Effect>(
    initialState = MovieListContract.State()
) {

    override fun handleIntent(intent: MovieListContract.Intent) {
        when (intent) {
            is MovieListContract.Intent.LoadMovies -> fetchMovies()
            is MovieListContract.Intent.Refresh -> fetchMovies()
            is MovieListContract.Intent.OnMovieClicked -> {
                setEffect(MovieListContract.Effect.NavigateToDetails(intent.movieId))
            }
        }
    }

    private fun fetchMovies() {
        viewModelScope.launch {
            setState { copy(isLoading = true, error = null) }

            repository.getMovies()
                .catch { e ->
                    setState { copy(isLoading = false, error = e.message) }
                    setEffect(MovieListContract.Effect.ShowError(e.message ?: "Erro desconhecido"))
                }
                .collect { movieList ->
                    setState { copy(isLoading = false, movies = movieList) }
                }
        }
    }
}