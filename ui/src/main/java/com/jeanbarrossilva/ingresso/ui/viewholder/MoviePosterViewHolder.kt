package com.jeanbarrossilva.ingresso.ui.viewholder

import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.jeanbarrossilva.ingresso.extensions.calendar.displayable
import com.jeanbarrossilva.ingresso.model.Movie
import com.jeanbarrossilva.ingresso.ui.R
import com.jeanbarrossilva.ingresso.ui.databinding.ViewMoviePosterBinding

class MoviePosterViewHolder(private val binding: ViewMoviePosterBinding): RecyclerView.ViewHolder(binding.root) {
    private val context
        get() = binding.root.context

    private fun showPremiereDateOf(movie: Movie) {
        movie.premiereDate?.displayable?.let {
            binding.premierDateView.text = it
        }
    }

    fun bind(movie: Movie) {
        binding.imageView.load(movie.imageUrl) { crossfade(true) }
        binding.imageView.contentDescription = context.getString(R.string.view_movie_poster_content_description, movie.title)
        showPremiereDateOf(movie)
        binding.titleView.text = movie.title.localized
    }
}