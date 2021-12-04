package com.jeanbarrossilva.ingresso.ui.adapter

import com.jeanbarrossilva.ingresso.ui.core.IngressoAdapter
import com.jeanbarrossilva.ingresso.ui.databinding.ViewMovieSearchResultBinding
import com.jeanbarrossilva.ingresso.ui.viewholder.MovieSearchResultViewHolder

class MovieSearchResultAdapter: IngressoAdapter<ViewMovieSearchResultBinding, MovieSearchResultViewHolder>() {
    override val bindingClass = ViewMovieSearchResultBinding::class
    override val items = emptyList<Any>()

    override fun onCreateViewHolder(): MovieSearchResultViewHolder {
        return MovieSearchResultViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MovieSearchResultViewHolder, position: Int) {
    }
}