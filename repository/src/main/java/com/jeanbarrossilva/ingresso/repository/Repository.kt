package com.jeanbarrossilva.ingresso.repository

import com.jeanbarrossilva.ingresso.model.Movie

object Repository {
    val movies
        get() = Movie.samples

    fun search(query: String): List<Movie> {
        return movies.filter {
            query in it
        }
    }
}