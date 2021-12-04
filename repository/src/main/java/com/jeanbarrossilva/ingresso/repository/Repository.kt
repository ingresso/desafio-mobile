package com.jeanbarrossilva.ingresso.repository

import com.jeanbarrossilva.ingresso.model.Movie

class Repository {
    val movies
        get() = Movie.samples
}