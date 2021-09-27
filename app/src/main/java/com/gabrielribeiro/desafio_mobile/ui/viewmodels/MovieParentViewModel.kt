package com.gabrielribeiro.desafio_mobile.ui.viewmodels

import android.util.Log
import androidx.lifecycle.*
import com.gabrielribeiro.desafio_mobile.data.entity.MovieEntity
import com.gabrielribeiro.desafio_mobile.data.remote.models.MoviesListResponse
import com.gabrielribeiro.desafio_mobile.repositories.MovieRepository
import com.gabrielribeiro.desafio_mobile.utils.Resource
import kotlinx.coroutines.launch
import java.lang.Exception

open class MovieParentViewModel(private val repository : MovieRepository) : ViewModel(){

    private val _moviesListResponse = MutableLiveData<Resource<MoviesListResponse>>()
    val moviesListResponse : LiveData<Resource<MoviesListResponse>> get() = _moviesListResponse

    fun getMovies()  {
        viewModelScope.launch {
            _moviesListResponse.value = Resource.Loading()
            _moviesListResponse.value = repository.getMovies()
        }
    }

    open fun getAllMoviesSaved()  = repository.getAllMoviesSaved()

    private var _saveMovie = MutableLiveData<Resource<Long>>()
    val saveMovie : LiveData<Resource<Long>> get() = _saveMovie

    fun saveMovie(movieEntity: MovieEntity) {
        viewModelScope.launch {
            _saveMovie.value =  Resource.Loading()
            try {
                _saveMovie.value =  Resource.Success(repository.saveMovie(movieEntity))

            }catch (e : Exception) {
                Log.d("MovieDetailsViewModel", "saveMovie: $e")
            }

        }
    }

    fun deleteMovie(idDatabase: Int) {
        viewModelScope.launch {
            repository.deleteMovie(idDatabase)
        }
    }

    class MovieParentViewModelFactory(private val repository : MovieRepository) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return MovieParentViewModel(repository) as T
        }
    }
}