package com.jeanbarrossilva.ingresso.ui.adapter.recyclerview

import com.jeanbarrossilva.ingresso.model.Movie
import com.jeanbarrossilva.ingresso.ui.core.IngressoAdapter
import com.jeanbarrossilva.ingresso.ui.databinding.ViewMovieSearchResultBinding
import com.jeanbarrossilva.ingresso.ui.viewholder.MovieSearchResultViewHolder

class MovieSearchResultAdapter(override val items: List<Movie>, private val onClick: (Movie) -> Unit):
    IngressoAdapter<ViewMovieSearchResultBinding, MovieSearchResultViewHolder, Movie>() {
    override val bindingClass = ViewMovieSearchResultBinding::class

    override fun onCreateViewHolder(): MovieSearchResultViewHolder {
        return MovieSearchResultViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MovieSearchResultViewHolder, position: Int) {
        holder.bind(items[position], onClick)
    }
}