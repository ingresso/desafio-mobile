package com.gabrielribeiro.desafio_mobile.ui.viewmodels

import androidx.lifecycle.*
import com.gabrielribeiro.desafio_mobile.data.remote.models.MoviesListResponse
import com.gabrielribeiro.desafio_mobile.repositories.MovieRepository
import com.gabrielribeiro.desafio_mobile.utils.Resource
import kotlinx.coroutines.launch

open class HomeViewModel(private val repository : MovieRepository) : MovieParentViewModel(repository) {

    class MovieViewModelFactory(private val repository : MovieRepository) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return HomeViewModel(repository) as T
        }
    }

}