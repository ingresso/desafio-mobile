package com.jeanbarrossilva.ingresso.ui.viewholder

import android.graphics.drawable.Drawable
import androidx.recyclerview.widget.RecyclerView
import com.jeanbarrossilva.ingresso.ui.R
import com.jeanbarrossilva.ingresso.ui.databinding.ViewMovieSearchResultBinding

class MovieSearchResultViewHolder(private val binding: ViewMovieSearchResultBinding): RecyclerView.ViewHolder(binding.root) {
    fun bind(image: Drawable, title: String, genre: String) {
        binding.imageView.setImageDrawable(image)
        binding.imageView.contentDescription =
            binding.root.context.getString(R.string.view_movie_search_result_poster_content_description, title)
        binding.titleView.text = title
        binding.genreView.text = genre
    }
}