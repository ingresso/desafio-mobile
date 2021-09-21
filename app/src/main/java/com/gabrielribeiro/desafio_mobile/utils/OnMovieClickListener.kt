package com.gabrielribeiro.desafio_mobile.utils

import com.gabrielribeiro.desafio_mobile.data.remote.model.MovieResponse

interface OnMovieClickListener {
    fun onMovieClick(movieResponse : MovieResponse?)
}