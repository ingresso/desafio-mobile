package com.schmitttech.ingresso.presentation.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.schmitttech.ingresso.domain.model.Movie
import com.schmitttech.ingresso.domain.usecase.GetMovieDetailsUseCase
import com.schmitttech.ingresso.domain.usecase.ToggleFavoriteUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

sealed interface DetailsUiState {
    data object Loading : DetailsUiState
    data class Success(
        val movie: Movie,
        val relatedMovies: List<Movie> = emptyList()
    ) : DetailsUiState
    data class Error(val message: String) : DetailsUiState
}

class DetailsViewModel(
    private val getMovieDetailsUseCase: GetMovieDetailsUseCase,
    private val toggleFavoriteUseCase: ToggleFavoriteUseCase
) : ViewModel() {

    private val _movieId = MutableStateFlow<String?>(null)

    @OptIn(ExperimentalCoroutinesApi::class)
    val uiState: StateFlow<DetailsUiState> = _movieId
        .flatMapLatest { id ->
            if (id == null) {
                MutableStateFlow(DetailsUiState.Loading)
            } else {
                getMovieDetailsUseCase.observeMovie(id)
                    .flatMapLatest { movie ->
                        if (movie == null) {
                            MutableStateFlow(DetailsUiState.Error("Filme não encontrado"))
                        } else {
                            getMovieDetailsUseCase.getRelatedMovies(movie).map { related ->
                                DetailsUiState.Success(movie, related)
                            }
                        }
                    }
            }
        }
        .catch {
            emit(DetailsUiState.Error(it.message ?: "Erro ao carregar detalhes"))
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = DetailsUiState.Loading
        )

    fun getMovieDetails(movieId: String) {
        _movieId.value = movieId
    }

    fun toggleFavorite(id: String, isFavorite: Boolean) {
        viewModelScope.launch {
            toggleFavoriteUseCase(id, isFavorite)
        }
    }
}
