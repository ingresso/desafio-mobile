package com.jeanbarrossilva.ingresso.ui.viewholder

import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.jeanbarrossilva.ingresso.model.Movie
import com.jeanbarrossilva.ingresso.ui.R
import com.jeanbarrossilva.ingresso.ui.databinding.ViewMovieSearchResultBinding

class MovieSearchResultViewHolder(private val binding: ViewMovieSearchResultBinding): RecyclerView.ViewHolder(binding.root) {
    fun bind(movie: Movie) {
        binding.imageView.load(movie.imageUrl)
        binding.imageView.contentDescription =
            binding.root.context.getString(R.string.view_movie_search_result_poster_content_description, movie.title.localized)
        binding.titleView.text = movie.title.localized
        binding.genreView.text = movie.genres.joinToString()
    }
}