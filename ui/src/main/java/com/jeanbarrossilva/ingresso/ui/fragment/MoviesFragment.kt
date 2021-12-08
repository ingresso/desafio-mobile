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
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.snackbar.Snackbar
import com.jeanbarrossilva.ingresso.extensions.context.activity.view
import com.jeanbarrossilva.ingresso.extensions.context.colorOf
import com.jeanbarrossilva.ingresso.extensions.view.searchFor
import com.jeanbarrossilva.ingresso.model.Movie
import com.jeanbarrossilva.ingresso.ui.R
import com.jeanbarrossilva.ingresso.ui.adapter.recyclerview.MoviePosterAdapter
import com.jeanbarrossilva.ingresso.ui.core.IngressoFragment
import com.jeanbarrossilva.ingresso.ui.databinding.FragmentMoviesBinding
import com.jeanbarrossilva.ingresso.ui.viewmodel.MoviesViewModel
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import ru.beryukhov.reactivenetwork.ReactiveNetwork

class MoviesFragment: IngressoFragment<FragmentMoviesBinding>() {
    private val viewModel by viewModels<MoviesViewModel>()
    private val noInternetConnectionSnackbar by lazy {
        val view = requireView()
        val anchorView = activity?.view?.searchFor<BottomNavigationView>()
        val primaryTextColor = requireContext().colorOf(android.R.attr.textColorPrimary)

        Snackbar
            .make(view, R.string.no_internet_connection, Snackbar.LENGTH_INDEFINITE)
            .setTextColor(primaryTextColor)
            .setAnchorView(anchorView)
    }

    override val bindingClass = FragmentMoviesBinding::class

    private fun setUpSwipeRefresh() {
        with(binding.refreshLayout) {
            setOnRefreshListener {
                refresh(hasBeenTriggeredBySwipe = true)
            }
        }
    }

    private fun refresh(hasBeenTriggeredBySwipe: Boolean = false) {
        lifecycleScope.launch {
            coroutineScope {
                with(binding.refreshLayout) {
                    viewModel
                        .getMoviesFlow()
                        .onStart { if (!hasBeenTriggeredBySwipe) isRefreshing = true }
                        .catch { noInternetConnectionSnackbar.show() }
                        .onEach { noInternetConnectionSnackbar.dismiss() }
                        .onCompletion { isRefreshing = false }
                        .collect { updateMoviesView(it) }
                }
            }
        }
    }

    private fun updateMoviesView(movies: List<Movie>) {
        binding.moviesView.adapter = MoviePosterAdapter(movies, onClick = ::navigateToDetailsOf)
    }

    private fun navigateToDetailsOf(movie: Movie) {
        findNavController().navigate(MoviesFragmentDirections.detailsOf(movie))
    }

    @Suppress("MissingPermission")
    private fun refreshOnConnectivityChange() {
        lifecycleScope.launch {
            ReactiveNetwork()
                .observeNetworkConnectivity(requireContext())
                .collect { connectivity -> if (connectivity.available) refresh() }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpSwipeRefresh()
        refresh()
        refreshOnConnectivityChange()
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