package com.jeanbarrossilva.ingresso.repository

import com.jeanbarrossilva.ingresso.model.Movie

object Repository {
    val movies
        get() = Movie.samples
}