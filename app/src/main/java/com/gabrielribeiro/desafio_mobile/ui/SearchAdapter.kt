package com.gabrielribeiro.desafio_mobile.ui

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.gabrielribeiro.desafio_mobile.R
import com.gabrielribeiro.desafio_mobile.data.remote.model.MovieResponse

class SearchAdapter() : RecyclerView.Adapter<SearchAdapter.SearchViewHolder>() {
    private var movieList = listOf<MovieResponse>()

    fun submitList(movieResponses : List<MovieResponse>) {
        movieList = movieResponses
        notifyDataSetChanged()
    }

    class SearchViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
        val imageViewMovieSearch : ImageView = itemView.findViewById(R.id.image_view_movie_search_item)
        val textViewMovieTitleSearch : TextView = itemView.findViewById(R.id.text_view_movie_title_search)
        val textViewMovieGenderSearch : TextView = itemView.findViewById(R.id.text_view_movie_gender_search)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder {
        return SearchViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.row_movie_search_item, parent, false))
    }

    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
        val movie = movieList[position]
        try {
            Glide.with(holder.itemView.context)
                .load(if (movie.images.isEmpty()) R.drawable.ic_debut else movie.images.first().url) // image url
                .placeholder(R.drawable.ic_debut)
                .error(R.drawable.ic_debut)  // any image in case of error
                .into(holder.imageViewMovieSearch);  // imageview
        }catch (e : Exception) {
            Log.d("dddd", "onBindViewHolder: ${e.message} ${movie.id} ${movie.title} ${movie.images.toString()}")
        }
        holder.textViewMovieTitleSearch.text = movie.title
        holder.textViewMovieGenderSearch.text = "${movie.genres.first()} * ${movie.genres.last()}"
    }

    override fun getItemCount(): Int = movieList.size
}