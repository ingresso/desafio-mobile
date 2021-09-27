package com.gabrielribeiro.desafio_mobile.ui.viewmodels

import android.util.Log
import androidx.lifecycle.*
import com.gabrielribeiro.desafio_mobile.data.entity.MovieEntity
import com.gabrielribeiro.desafio_mobile.repositories.MovieRepository
import com.gabrielribeiro.desafio_mobile.utils.Resource
import kotlinx.coroutines.launch
import java.lang.Exception

class MovieDetailsViewModel(private val movieRepository: MovieRepository) : MovieParentViewModel(movieRepository) {

    class MovieDetailsViewModelFactory(private val movieRepository: MovieRepository) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return MovieDetailsViewModel(movieRepository) as T
        }
    }
}