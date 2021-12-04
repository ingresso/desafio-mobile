package com.jeanbarrossilva.ingresso.ui.adapter

import com.jeanbarrossilva.ingresso.model.Movie
import com.jeanbarrossilva.ingresso.ui.core.IngressoAdapter
import com.jeanbarrossilva.ingresso.ui.databinding.ViewMovieSearchResultBinding
import com.jeanbarrossilva.ingresso.ui.viewholder.MovieSearchResultViewHolder

class MovieSearchResultAdapter: IngressoAdapter<ViewMovieSearchResultBinding, MovieSearchResultViewHolder, Movie>() {
    override val bindingClass = ViewMovieSearchResultBinding::class
    override val items = emptyList<Movie>()

    override fun onCreateViewHolder(): MovieSearchResultViewHolder {
        return MovieSearchResultViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MovieSearchResultViewHolder, item: Movie) {
    }
}