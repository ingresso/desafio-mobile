package com.jeanbarrossilva.ingresso.ui.fragment

import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.jeanbarrossilva.ingresso.model.Movie
import com.jeanbarrossilva.ingresso.ui.R
import com.jeanbarrossilva.ingresso.ui.adapter.recyclerview.MoviePosterAdapter
import com.jeanbarrossilva.ingresso.ui.core.IngressoFragment
import com.jeanbarrossilva.ingresso.ui.databinding.FragmentMoviesBinding
import com.jeanbarrossilva.ingresso.ui.viewmodel.MoviesViewModel
import kotlinx.coroutines.flow.collect

class MoviesFragment: IngressoFragment<FragmentMoviesBinding>() {
    private val viewModel by viewModels<MoviesViewModel>()

    override val bindingClass = FragmentMoviesBinding::class

    private fun setUpMovies() {
        lifecycleScope.launchWhenResumed {
            viewModel.moviesFlow.collect { movies ->
                binding.moviesView.adapter = MoviePosterAdapter(movies, onClick = ::navigateToDetailsOf)
            }
        }
    }

    private fun navigateToDetailsOf(movie: Movie) {
        findNavController().navigate(MoviesFragmentDirections.detailsOf(movie))
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpMovies()
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