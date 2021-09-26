package com.gabrielribeiro.desafio_mobile.utils

import com.gabrielribeiro.desafio_mobile.data.remote.models.MovieResponse

interface OnMovieClickListener {
    fun onMovieClick(movieResponse: MovieResponse?)
}