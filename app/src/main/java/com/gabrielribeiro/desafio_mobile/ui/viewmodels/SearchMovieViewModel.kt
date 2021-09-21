package com.gabrielribeiro.desafio_mobile.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.gabrielribeiro.desafio_mobile.repositories.MovieRepository

class SearchMovieViewModel(private val repository : MovieRepository)  {

    class SearchViewModelFactory(private val repository : MovieRepository) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return SearchMovieViewModel(repository) as T
        }

    }
}