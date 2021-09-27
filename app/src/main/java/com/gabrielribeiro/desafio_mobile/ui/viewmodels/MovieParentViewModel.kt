package com.gabrielribeiro.desafio_mobile.ui.viewmodels

import android.util.Log
import androidx.lifecycle.*
import com.gabrielribeiro.desafio_mobile.data.local.entity.MovieEntity
import com.gabrielribeiro.desafio_mobile.data.remote.models.MoviesListResponse
import com.gabrielribeiro.desafio_mobile.repositories.MovieRepository
import com.gabrielribeiro.desafio_mobile.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
open class MovieParentViewModel @Inject constructor(private val repository : MovieRepository) : ViewModel(){

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

}