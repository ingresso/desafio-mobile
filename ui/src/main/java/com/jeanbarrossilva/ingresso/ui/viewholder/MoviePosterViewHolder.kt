package com.jeanbarrossilva.ingresso.ui.viewholder

import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.jeanbarrossilva.ingresso.extensions.date.displayable
import com.jeanbarrossilva.ingresso.model.Movie
import com.jeanbarrossilva.ingresso.ui.R
import com.jeanbarrossilva.ingresso.ui.databinding.ViewMoviePosterBinding

class MoviePosterViewHolder(private val binding: ViewMoviePosterBinding): RecyclerView.ViewHolder(binding.root) {
    private val context
        get() = binding.root.context

    private fun showPremiereDateOf(movie: Movie) {
        binding.premiereDateLayout.isVisible = movie.premiereDate != null
        movie.premiereDate?.displayable?.let { binding.premierDateView.text = it }
    }

    fun bind(movie: Movie, onClick: (Movie) -> Unit) {
        binding.root.setOnClickListener { onClick(movie) }
        binding.imagePortraitView.load(movie.imageUrl) { crossfade(true) }
        binding.imagePortraitView.contentDescription = context.getString(R.string.movie_image_content_description, movie.title)
        showPremiereDateOf(movie)
        binding.titleView.text = movie.title.localized
    }
}