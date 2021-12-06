package com.jeanbarrossilva.ingresso.ui.fragment

import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import androidx.navigation.fragment.findNavController
import com.jeanbarrossilva.ingresso.repository.Repository
import com.jeanbarrossilva.ingresso.ui.R
import com.jeanbarrossilva.ingresso.ui.adapter.recyclerview.MoviePosterAdapter
import com.jeanbarrossilva.ingresso.ui.core.IngressoFragment
import com.jeanbarrossilva.ingresso.ui.databinding.FragmentMoviesBinding

class MoviesFragment: IngressoFragment<FragmentMoviesBinding>() {
    override val bindingClass = FragmentMoviesBinding::class

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.moviesView.adapter = MoviePosterAdapter(Repository.movies, onClick = {
            findNavController().navigate(MoviesFragmentDirections.detailsOf(it))
        })
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.toolbar_movies, menu)
        Log.d("MoviesFragment", "onCreateOptionsMenu: Menu inflated successfully.")
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.menu_item_search -> {
                findNavController().navigate(MoviesFragmentDirections.search())
                true
            }
            else -> false
        }
    }
}