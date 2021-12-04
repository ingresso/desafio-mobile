package com.jeanbarrossilva.ingresso.ui.viewholder

import androidx.lifecycle.findViewTreeLifecycleOwner
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.RecyclerView
import com.jeanbarrossilva.ingresso.extensions.calendar.displayable
import com.jeanbarrossilva.ingresso.model.Movie
import com.jeanbarrossilva.ingresso.ui.R
import com.jeanbarrossilva.ingresso.ui.databinding.ViewMoviePosterBinding
import kotlinx.coroutines.launch

class MoviePosterViewHolder(private val binding: ViewMoviePosterBinding): RecyclerView.ViewHolder(binding.root) {
    private val context
        get() = binding.root.context
    private val coroutineScope
        get() = binding.root.findViewTreeLifecycleOwner()?.lifecycleScope

    private fun showImageOf(movie: Movie) {
        coroutineScope?.launch {
            movie.getImage(context).let {
                binding.imageView.setImageBitmap(it)
            }
        }
    }

    private fun showPremiereDateOf(movie: Movie) {
        movie.premiereDate?.displayable?.let {
            binding.premierDateView.text = it
        }
    }

    fun bind(movie: Movie) {
        showImageOf(movie)
        binding.imageView.contentDescription = context.getString(R.string.view_movie_poster_content_description, movie.title)
        showPremiereDateOf(movie)
        binding.titleView.text = movie.title.localized
    }
}