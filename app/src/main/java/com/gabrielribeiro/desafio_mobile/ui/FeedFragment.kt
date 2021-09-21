package com.gabrielribeiro.desafio_mobile.ui

import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.gabrielribeiro.desafio_mobile.R
import com.gabrielribeiro.desafio_mobile.data.remote.model.MovieResponse
import com.gabrielribeiro.desafio_mobile.utils.OnMovieClickListener
import com.gabrielribeiro.desafio_mobile.utils.Resource
import com.gabrielribeiro.desafio_mobile.utils.Resource.Failure
import com.gabrielribeiro.desafio_mobile.ui.viewmodels.MovieViewModel
import kotlinx.android.synthetic.main.fragment_feed.*
import kotlinx.android.synthetic.main.include_progress_layout.view.*


class FeedFragment : Fragment(), OnMovieClickListener {
    private lateinit var viewModel: MovieViewModel
    private lateinit var movieAdapter: MovieAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_feed, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerView()

        viewModel = (activity as MainActivity).viewModel
        observerViewModel()

    }

    private fun observerViewModel() {
        viewModel.getMovies().observe(viewLifecycleOwner, {
            when (it) {
                is Resource.Loading -> {
                    include_progress_indicator.progress_indicator.visibility = View.VISIBLE
                    Log.d("FeedFragment", "Loading:")
                }
                is Failure -> {
                    Log.d("FeedFragment", "Failure: ${it.message}")
                    include_progress_indicator.progress_indicator.visibility = View.GONE
                }
                is Resource.Success -> {
                    if (it.data != null) {
                        val premiereDateFiltered =
                            it.data.movieResponses.filter { movie -> movie.premiereDate != null }
                        movieAdapter.submitList(premiereDateFiltered.sortedBy { movie -> movie.dateMillis })
                        include_progress_indicator.progress_indicator.visibility = View.GONE
                    }
                }
            }
        })
    }

    private fun setupRecyclerView() {
        recycler_view_feed.apply {
            layoutManager = GridLayoutManager(activity, 2, GridLayoutManager.VERTICAL, false)
            viewTreeObserver.addOnPreDrawListener(object : ViewTreeObserver.OnPreDrawListener {
                override fun onPreDraw(): Boolean {
                    if (recycler_view_feed.viewTreeObserver
                            .isAlive
                    ) recycler_view_feed.viewTreeObserver.removeOnPreDrawListener(this)
                    val width = recycler_view_feed.width / 2
                    val height = recycler_view_feed.height / 2
                    movieAdapter = MovieAdapter(width, height, this@FeedFragment)
                    recycler_view_feed.adapter = movieAdapter
                    return true
                }
            })
        }
    }

    override fun onMovieClick(movieResponse: MovieResponse?) {
        if (movieResponse != null) {
            startActivity(MovieDetailsActivity.newIntent(requireContext(), movieResponse))
        }
    }


}