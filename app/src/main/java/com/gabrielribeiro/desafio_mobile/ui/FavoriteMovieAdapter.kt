package com.gabrielribeiro.desafio_mobile.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.gabrielribeiro.desafio_mobile.R
import com.gabrielribeiro.desafio_mobile.data.local.entity.MovieEntity
import com.gabrielribeiro.desafio_mobile.utils.OnMovieClickListener

class FavoriteMovieAdapter(private val onDeleteMovieClickListener : OnDeleteMovieClickListener, private val onMovieClickListener : OnMovieClickListener) : RecyclerView.Adapter<FavoriteMovieAdapter.FavoriteMovieViewHolder>() {

    private var movieList = listOf<MovieEntity>()

    fun submitList(movieResponses : List<MovieEntity>) {
        movieList = movieResponses
        notifyDataSetChanged()
    }

    inner class FavoriteMovieViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
        private val imageViewMovieSearch : ImageView = itemView.findViewById(R.id.image_view_movie_search_item)
        private val textViewMovieTitleSearch : TextView = itemView.findViewById(R.id.text_view_movie_title_search)
        private val textViewMovieGenderSearch : TextView = itemView.findViewById(R.id.text_view_movie_gender_search)
        private val textViewMoviePremierDateSearch : TextView = itemView.findViewById(R.id.text_view_movie_premier_date_search)
        private val imageViewArrow : ImageView = itemView.findViewById(R.id.image_view_arrow)
        private val imageDeleteMovie : ImageView = itemView.findViewById(R.id.image_delete_movie)

        fun bind(movieEntity : MovieEntity) {
            if (movieEntity.dateFormatted != null) {
                textViewMoviePremierDateSearch.text = movieEntity.dateFormatted
            }
            textViewMovieTitleSearch.text = movieEntity.title
            if (!movieEntity.genres.isNullOrEmpty()) {
                textViewMovieGenderSearch.text = movieEntity.genresFormatted
            }

            Glide.with(itemView.context)
                .load(if (movieEntity.images.isEmpty()) R.drawable.bg_place_holder_movie else movieEntity.images.first().url)
                .placeholder(R.drawable.bg_place_holder_movie)
                .error(R.drawable.bg_place_holder_movie)
                .into(imageViewMovieSearch)
        }

        init {
            imageViewArrow.visibility = View.GONE
            imageDeleteMovie.visibility = View.VISIBLE

            itemView.setOnClickListener {
                val movieResponse = movieList[adapterPosition]
                onMovieClickListener.onMovieClick(movieResponse.toMovieResponse())
            }

            imageDeleteMovie.setOnClickListener {
                val movie = movieList[adapterPosition]
                onDeleteMovieClickListener.onDeleteMovie(movie.idDatabase)
                notifyItemRemoved(adapterPosition)
                notifyDataSetChanged()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteMovieViewHolder {
        return FavoriteMovieViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.row_movie_search_item, parent, false))
    }

    override fun onBindViewHolder(holder: FavoriteMovieViewHolder, position: Int) {
        val movie = movieList[position]
        holder.bind(movie)
    }

    override fun getItemCount(): Int = movieList.size

    interface OnDeleteMovieClickListener {
        fun onDeleteMovie(id : Int?)
    }
}

