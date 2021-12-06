package com.jeanbarrossilva.ingresso.ui.adapter.recyclerview

import android.widget.Filter
import android.widget.Filterable
import com.jeanbarrossilva.ingresso.model.Movie
import com.jeanbarrossilva.ingresso.repository.Repository
import com.jeanbarrossilva.ingresso.ui.core.IngressoAdapter
import com.jeanbarrossilva.ingresso.ui.databinding.ViewMovieSearchResultBinding
import com.jeanbarrossilva.ingresso.ui.viewholder.MovieSearchResultViewHolder

class MovieSearchResultAdapter(override val items: List<Movie>):
    IngressoAdapter<ViewMovieSearchResultBinding, MovieSearchResultViewHolder, Movie>(), Filterable {
    private var movies = items
        @Suppress("NotifyDataSetChanged")
        set(movies) {
            field = movies
            notifyDataSetChanged()
        }

    override val bindingClass = ViewMovieSearchResultBinding::class

    override fun onCreateViewHolder(): MovieSearchResultViewHolder {
        return MovieSearchResultViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MovieSearchResultViewHolder, position: Int) {
        holder.bind(movies[position])
    }

    override fun getItemCount(): Int {
        return movies.size
    }

    override fun getFilter(): Filter {
        return object: Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults? {
                return constraint?.toString()?.let {
                    FilterResults().apply {
                        values = Repository.search(it)
                    }
                }
            }

            @Suppress("UNCHECKED_CAST")
            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                movies = (results?.values as? List<*>).orEmpty() as List<Movie>
            }
        }
    }
}