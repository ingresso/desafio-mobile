package com.gabrielribeiro.desafio_mobile.ui.viewmodels

import android.util.Log
import androidx.lifecycle.*
import com.gabrielribeiro.desafio_mobile.data.entity.MovieEntity
import com.gabrielribeiro.desafio_mobile.repositories.MovieRepository
import kotlinx.coroutines.launch
import java.lang.Exception

class MovieDetailsViewModel(private val movieRepository: MovieRepository) : ViewModel() {
    private var _allMoviesSaved = MutableLiveData<List<MovieEntity>>()
    val allMoviesSaved : LiveData<List<MovieEntity>> get() = _allMoviesSaved
    fun saveMovie(movieEntity: MovieEntity) {
        viewModelScope.launch {
            try {
                movieRepository.saveMovie(movieEntity)
            }catch (e : Exception) {
                Log.d("MovieDetailsViewModel", "saveMovie: $e")
            }

        }
    }

    //fun getAllMoviesSaved() = movieRepository.getAllMoviesSaved()

    fun getAllArticlesSaved() = movieRepository.getAllMoviesSaved()

    class MovieDetailsViewModelFactory(private val movieRepository: MovieRepository) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return MovieDetailsViewModel(movieRepository) as T
        }
    }
}