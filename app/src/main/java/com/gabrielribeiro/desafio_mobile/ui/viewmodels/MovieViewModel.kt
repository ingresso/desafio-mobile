package com.gabrielribeiro.desafio_mobile.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.liveData
import com.gabrielribeiro.desafio_mobile.data.remote.model.MoviesListResponse
import com.gabrielribeiro.desafio_mobile.repositories.MovieRepository
import com.gabrielribeiro.desafio_mobile.utils.Resource

open class MovieViewModel(private val repository : MovieRepository) : ViewModel() {

    fun getMovies() = liveData() {
        emit(Resource.Loading())
        emit(repository.getMovies())
    }

    class MovieViewModelFactory(private val repository : MovieRepository) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return MovieViewModel(repository) as T
        }
    }

}