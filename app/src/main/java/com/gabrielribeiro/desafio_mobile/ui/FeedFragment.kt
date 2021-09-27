package com.gabrielribeiro.desafio_mobile.ui

import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.gabrielribeiro.desafio_mobile.R
import com.gabrielribeiro.desafio_mobile.data.remote.models.MovieResponse
import com.gabrielribeiro.desafio_mobile.databinding.FragmentFeedBinding
import com.gabrielribeiro.desafio_mobile.ui.viewmodels.HomeViewModel
import com.gabrielribeiro.desafio_mobile.utils.OnMovieClickListener
import com.gabrielribeiro.desafio_mobile.utils.Resource
import com.gabrielribeiro.desafio_mobile.utils.Resource.Failure


class FeedFragment : Fragment(), OnMovieClickListener {
    private lateinit var binding :  FragmentFeedBinding

    private var movieResponseList = mutableListOf<MovieResponse>()
    private lateinit var viewModel: HomeViewModel
    private lateinit var movieAdapter: MovieAdapter
    private var currentActiveStatus = true

    companion object {
        private const val TAG = "FeedFragment"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = (activity as HomeActivity).viewModel

        binding = FragmentFeedBinding.inflate(inflater,container,false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupHooks()
        observerViewModel()

    }

    private fun setupHooks() {
        binding.radioGroupLabels.setOnCheckedChangeListener { _, id ->
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
            binding.textMoviesEmpty.visibility = View.VISIBLE
        } else {
            binding.textMoviesEmpty.visibility = View.INVISIBLE
        }
        movieAdapter.submitList(list)
    }


    private fun observerViewModel() {
        viewModel.moviesListResponse.observe(viewLifecycleOwner, {
            when (it) {
                is Resource.Loading -> {
                    binding.includeProgressIndicator.progressIndicator.visibility = View.VISIBLE
                }
                is Failure -> {
                    Log.d("FeedFragment", "Failure: ${it.message}")
                    binding.includeProgressIndicator.progressIndicator.visibility = View.GONE
                }
                is Resource.Success -> {
                    if (it.data != null) {
                        movieResponseList = it.data.movieResponses as MutableList<MovieResponse>
                        setupRecyclerView()
                        binding.includeProgressIndicator.progressIndicator.visibility = View.GONE
                    }
                }
            }
        })
    }


    private fun setupRecyclerView() {
        binding.recyclerViewFeed.apply {
            layoutManager = GridLayoutManager(activity, 2, GridLayoutManager.VERTICAL, false)
            viewTreeObserver.addOnPreDrawListener(object : ViewTreeObserver.OnPreDrawListener {
                override fun onPreDraw(): Boolean {
                    if (binding.recyclerViewFeed.viewTreeObserver
                            .isAlive
                    ) binding.recyclerViewFeed.viewTreeObserver.removeOnPreDrawListener(this)
                    val width = binding.recyclerViewFeed.width / 2
                    val height = binding.recyclerViewFeed.height / 2
                    movieAdapter = MovieAdapter(width, height, this@FeedFragment)
                    binding.recyclerViewFeed.adapter = movieAdapter
                    refreshRecycler()
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