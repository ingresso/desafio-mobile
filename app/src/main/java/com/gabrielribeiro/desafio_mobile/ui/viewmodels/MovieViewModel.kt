package com.gabrielribeiro.desafio_mobile.ui.viewmodels

import androidx.lifecycle.*
import com.gabrielribeiro.desafio_mobile.data.entity.MovieEntity
import com.gabrielribeiro.desafio_mobile.data.remote.models.MoviesListResponse
import com.gabrielribeiro.desafio_mobile.repositories.MovieRepository
import com.gabrielribeiro.desafio_mobile.utils.Resource
import kotlinx.coroutines.launch

open class MovieViewModel(private val repository : MovieRepository) : ViewModel() {

    private val _moviesListResponse = MutableLiveData<Resource<MoviesListResponse>>()
    val moviesListResponse : LiveData<Resource<MoviesListResponse>> get() = _moviesListResponse

    fun getMovies()  {
        viewModelScope.launch {
            _moviesListResponse.value = Resource.Loading()
            _moviesListResponse.value = repository.getMovies()
        }
    }

    class MovieViewModelFactory(private val repository : MovieRepository) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return MovieViewModel(repository) as T
        }
    }

}