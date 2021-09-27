package com.gabrielribeiro.desafio_mobile.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.gabrielribeiro.desafio_mobile.repositories.MovieRepository

class SearchHomeViewModel(private val repository: MovieRepository) : HomeViewModel(repository)  {

    class SearchViewModelFactory(private val repository : MovieRepository) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return SearchHomeViewModel(repository) as T
        }
    }
}