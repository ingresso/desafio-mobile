package com.jeanbarrossilva.ingresso.ui.adapter

import com.jeanbarrossilva.ingresso.model.Movie
import com.jeanbarrossilva.ingresso.ui.core.IngressoAdapter
import com.jeanbarrossilva.ingresso.ui.databinding.ViewMoviePosterBinding
import com.jeanbarrossilva.ingresso.ui.viewholder.MoviePosterViewHolder

class MoviePosterAdapter(override val items: List<Movie>): IngressoAdapter<ViewMoviePosterBinding, MoviePosterViewHolder, Movie>() {
    override val bindingClass = ViewMoviePosterBinding::class

    override fun onCreateViewHolder(): MoviePosterViewHolder {
        return MoviePosterViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MoviePosterViewHolder, position: Int) {
        holder.bind(items[position])
    }
}