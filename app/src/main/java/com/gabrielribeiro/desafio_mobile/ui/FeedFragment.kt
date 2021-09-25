package com.gabrielribeiro.desafio_mobile.ui

import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.gabrielribeiro.desafio_mobile.R
import com.gabrielribeiro.desafio_mobile.data.remote.model.MovieResponse
import com.gabrielribeiro.desafio_mobile.data.remote.model.MoviesListResponse
import com.gabrielribeiro.desafio_mobile.utils.Resource
import com.gabrielribeiro.desafio_mobile.utils.Resource.Failure
import com.gabrielribeiro.desafio_mobile.ui.viewmodels.MovieViewModel
import com.gabrielribeiro.desafio_mobile.utils.OnMovieClickListener
import kotlinx.android.synthetic.main.fragment_feed.*
import kotlinx.android.synthetic.main.include_progress_layout.view.*


class FeedFragment : Fragment(), OnMovieClickListener {
    private var movieResponseList = mutableListOf<MovieResponse>()
    private lateinit var viewModel: MovieViewModel
    private lateinit var movieAdapter: MovieAdapter
    private var currentActiveStatus = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        viewModel = (activity as MainActivity).viewModel
        return inflater.inflate(R.layout.fragment_feed, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerView()
        setupHooks()
        observerViewModel()

    }

    private fun setupHooks() {
        radio_group_labels.setOnCheckedChangeListener { _, id ->
            if (id == R.id.radio_button_exhibition) {
                setActiveStatus(false)
            } else if (id == R.id.radio_button_coming_soon) {
                setActiveStatus(true)
            }
        }
    }

    private fun setActiveStatus(status: Boolean) {
        currentActiveStatus = status
        refreshRecycler()
    }

    private fun refreshRecycler() {
        val list = if (currentActiveStatus) {
            movieResponseList.filter { movie -> movie.premiereDate != null }.sortedBy { movie -> movie.dateMillis }
        } else {
            movieResponseList.filter { a -> a.isPlaying }
        }
        if (list.isEmpty()) {
            text_movies_empty.visibility = View.VISIBLE
        }
        movieAdapter.submitList(list)
    }


    private fun observerViewModel() {
        viewModel.moviesListResponse.observe(viewLifecycleOwner, {
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
                        movieResponseList = it.data.movieResponses as MutableList<MovieResponse>
                        refreshRecycler()
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

    override fun onResume() {
        super.onResume()
        viewModel.getMovies()
    }

}