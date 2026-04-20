package com.schmitttech.ingresso.presentation.home

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.schmitttech.ingresso.data.util.NetworkHelper
import com.schmitttech.ingresso.domain.repository.MoviesRepository
import com.schmitttech.ingresso.domain.usecase.GetComingSoonMoviesUseCase
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class HomeViewModel(
    getComingSoonMoviesUseCase: GetComingSoonMoviesUseCase,
    private val repository: MoviesRepository,
    private val networkHelper: NetworkHelper
) : ViewModel() {

    companion object {
        private const val TAG = "HomeViewModel"
    }

    private val _searchQuery = MutableStateFlow("")
    private val _isSearchActive = MutableStateFlow(false)
    private val _selectedGenre = MutableStateFlow<String?>(null)
    private val _isLoading = MutableStateFlow(true)
    private val _error = MutableStateFlow<String?>(null)
    private val _eventFlow = MutableSharedFlow<String>()

    val searchQuery: StateFlow<String> = _searchQuery
    val isSearchActive: StateFlow<Boolean> = _isSearchActive
    val isRefreshing: StateFlow<Boolean> = _isLoading
    val eventFlow: SharedFlow<String> = _eventFlow.asSharedFlow()

    private val filterFlow = combine(_searchQuery, _isSearchActive, _selectedGenre) { query, active, genre ->
        FilterOptions(query, active, genre)
    }

    val uiState: StateFlow<HomeUiState> = combine(
        getComingSoonMoviesUseCase(), filterFlow, _isLoading, _error
    ) { movies, filters, isLoading, error ->
        val (query, searchActive, genre) = filters
        
        val filtered = movies.filter { movie ->
            val matchesSearch = if (searchActive && query.isNotBlank()) {
                movie.title.contains(query, ignoreCase = true)
            } else true

            val matchesGenre = if (genre != null) {
                movie.genres.contains(genre)
            } else true

            matchesSearch && matchesGenre
        }

        when {
            movies.isNotEmpty() -> {
                HomeUiState.Success(
                    movies = filtered,
                    genres = movies.flatMap { it.genres }.distinct().sorted(),
                    selectedGenre = genre
                )
            }
            error != null -> HomeUiState.Error(error)
            isLoading -> HomeUiState.Loading
            else -> {
                HomeUiState.Success(
                    movies = emptyList(),
                    genres = emptyList(),
                    selectedGenre = genre
                )
            }
        }
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = HomeUiState.Loading
    )

    init {
        getMovies()
    }

    fun getMovies() {
        Log.d(TAG, "getMovies: Refreshing movies from repository")
        viewModelScope.launch {
            try {
                _isLoading.value = true
                _error.value = null

                if (!networkHelper.isOnline()) {
                    val errorMessage = "No internet connection"
                    handleRequestError(errorMessage)
                    return@launch
                }

                repository.refreshMovies().onFailure { exception ->
                    Log.e(TAG, "getMovies: Error refreshing movies", exception)
                    handleRequestError(exception.message ?: "An unexpected error occurred")
                }
            } finally {
                _isLoading.value = false
            }
        }
    }

    private suspend fun handleRequestError(message: String) {
        val currentState = uiState.value
        if (currentState is HomeUiState.Success && currentState.movies.isNotEmpty()) {
            _eventFlow.emit(message)
        } else {
            _error.value = message
        }
    }

    fun onSearchQueryChange(query: String) {
        _searchQuery.value = query
    }

    fun toggleSearch() {
        _isSearchActive.value = !_isSearchActive.value
        if (!_isSearchActive.value) {
            _searchQuery.value = ""
        }
    }

    fun onGenreSelected(genre: String?) {
        _selectedGenre.value = genre
    }

    private data class FilterOptions(
        val query: String,
        val searchActive: Boolean,
        val genre: String?
    )
}
