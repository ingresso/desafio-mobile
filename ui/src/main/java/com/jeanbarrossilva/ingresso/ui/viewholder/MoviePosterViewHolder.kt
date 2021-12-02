package com.jeanbarrossilva.ingresso.ui.viewholder

import android.graphics.drawable.Drawable
import androidx.recyclerview.widget.RecyclerView
import com.jeanbarrossilva.ingresso.ui.R
import com.jeanbarrossilva.ingresso.ui.databinding.ViewMoviePosterBinding
import java.util.Calendar

class MoviePosterViewHolder(private val binding: ViewMoviePosterBinding): RecyclerView.ViewHolder(binding.root) {
    fun bind(image: Drawable, title: String, premiereDate: Calendar) {
        binding.imageView.setImageDrawable(image)
        binding.imageView.contentDescription = binding.root.context.getString(R.string.view_movie_poster_content_description, title)
        binding.premierDateView.text =
            with(premiereDate) { "${get(Calendar.DAY_OF_MONTH)}/${get(Calendar.MONTH)}/${get(Calendar.YEAR)}" }
        binding.titleView.text = title
    }
}