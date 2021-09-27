package com.gabrielribeiro.desafio_mobile.ui.viewmodels

import com.gabrielribeiro.desafio_mobile.repositories.MovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MovieDetailsViewModel @Inject constructor(private val repository: MovieRepository) : MovieParentViewModel(repository) {

}