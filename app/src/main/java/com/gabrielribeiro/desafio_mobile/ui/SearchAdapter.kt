package com.gabrielribeiro.desafio_mobile.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.gabrielribeiro.desafio_mobile.R
import com.gabrielribeiro.desafio_mobile.data.remote.models.MovieResponse
import com.gabrielribeiro.desafio_mobile.utils.OnMovieClickListener

class SearchAdapter(private val onMovieClickListener : OnMovieClickListener) : RecyclerView.Adapter<SearchAdapter.SearchViewHolder>() {
    private var movieList = listOf<MovieResponse>()

    fun submitList(movieResponses : List<MovieResponse>) {
        movieList = movieResponses
        notifyDataSetChanged()
    }

    inner class SearchViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
        private val imageViewMovieSearch : ImageView = itemView.findViewById(R.id.image_view_movie_search_item)
        private val textViewMovieTitleSearch : TextView = itemView.findViewById(R.id.text_view_movie_title_search)
        private val textViewMovieGenderSearch : TextView = itemView.findViewById(R.id.text_view_movie_gender_search)
        private val textViewMoviePremierDateSearch : TextView = itemView.findViewById(R.id.text_view_movie_premier_date_search)

        fun bind(movieResponse: MovieResponse) {
            if (movieResponse.dateFormatted != null) {
                textViewMoviePremierDateSearch.text = movieResponse.dateFormatted
            }
            textViewMovieTitleSearch.text = movieResponse.title
            if (!movieResponse.genres.isNullOrEmpty()) {
                textViewMovieGenderSearch.text = movieResponse.genresFormatted
            }

            Glide.with(itemView.context)
                .load(if (movieResponse.images.isEmpty()) R.drawable.bg_empty_movie else movieResponse.images.first().url)
                .placeholder(R.drawable.bg_empty_movie)
                .error(R.drawable.bg_empty_movie)
                .into(imageViewMovieSearch)
        }

        init {
            itemView.setOnClickListener {
                val movieResponse = movieList[adapterPosition]
                onMovieClickListener.onMovieClick(movieResponse)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder {
        return SearchViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.row_movie_search_item, parent, false))
    }

    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
        val movie = movieList[position]
        holder.bind(movie)

    }

    override fun getItemCount(): Int = movieList.size

}