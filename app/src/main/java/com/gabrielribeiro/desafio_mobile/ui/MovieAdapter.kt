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
import com.bumptech.glide.request.RequestOptions





class MovieAdapter(val itemWidth : Int, val itemHeight : Int, private val onMovieClickListener: OnMovieClickListener) : RecyclerView.Adapter<MovieAdapter.MovieViewHolder>(){
    private var movieList = listOf<MovieResponse>()

    fun submitList(movieResponses : List<MovieResponse>) {
        movieList = movieResponses
        notifyDataSetChanged()
    }

    inner class MovieViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
        private val imageViewMovie : ImageView = itemView.findViewById(R.id.image_view_movie_item)
        private val textViewMovieTitle : TextView = itemView.findViewById(R.id.text_view_movie_title)
        private val textViewMovieDate : TextView = itemView.findViewById(R.id.text_view_movie_date)
         init {
             imageViewMovie.apply {
                 layoutParams.width = itemWidth
                 layoutParams.height = itemHeight
             }
         }

        fun bind(movie : MovieResponse) {
            val options = RequestOptions()

            Glide.with(itemView.context)
                .load(if (movie.images.isEmpty()) R.drawable.bg_empty_movie else movie.images.first().url)
                .apply(options.fitCenter())
                .centerCrop()
                .placeholder(R.drawable.bg_empty_movie)
                .error(R.drawable.bg_empty_movie)
                .into(imageViewMovie)

            textViewMovieTitle.text = movie.title
            textViewMovieDate.text = movie.dateFormatted
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        return MovieViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.row_movie_item, parent, false))
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie = movieList[position]
        holder.bind(movie)

        holder.itemView.setOnClickListener {
            onMovieClickListener.onMovieClick(movie)
        }
    }

    override fun getItemCount(): Int = movieList.size

}