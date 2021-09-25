package com.gabrielribeiro.desafio_mobile.ui.viewmodels

import androidx.lifecycle.*
import com.gabrielribeiro.desafio_mobile.data.remote.model.MovieResponse
import com.gabrielribeiro.desafio_mobile.data.remote.model.MoviesListResponse
import com.gabrielribeiro.desafio_mobile.repositories.MovieRepository
import com.gabrielribeiro.desafio_mobile.utils.Resource
import kotlinx.coroutines.launch

open class MovieViewModel(private val repository : MovieRepository) : ViewModel() {

    val moviesListResponse = MutableLiveData<Resource<MoviesListResponse>>()

    fun getMovies()  {
        viewModelScope.launch {
            moviesListResponse.value = Resource.Loading()
            moviesListResponse.value = repository.getMovies()
            //emit(Resource.Loading())
            //emit(repository.getMovies())
        }
    }

    class MovieViewModelFactory(private val repository : MovieRepository) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return MovieViewModel(repository) as T
        }
    }

}