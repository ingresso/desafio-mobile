package com.gabrielribeiro.desafio_mobile.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.gabrielribeiro.desafio_mobile.R
import com.gabrielribeiro.desafio_mobile.data.remote.models.MovieResponse
import com.gabrielribeiro.desafio_mobile.databinding.FragmentFavoriteBinding
import com.gabrielribeiro.desafio_mobile.databinding.FragmentFeedBinding
import com.gabrielribeiro.desafio_mobile.ui.viewmodels.HomeViewModel
import com.gabrielribeiro.desafio_mobile.utils.OnMovieClickListener
import kotlin.math.log


class FavoriteFragment : Fragment(), FavoriteMovieAdapter.OnDeleteMovieClickListener, OnMovieClickListener {
    private lateinit var binding : FragmentFavoriteBinding
    private lateinit var viewModel: HomeViewModel
    private lateinit var favoriteMovieAdapter: FavoriteMovieAdapter

    companion object {
        private const val TAG = "FavoriteFragment"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        viewModel = (activity as HomeActivity).viewModel
        binding = FragmentFavoriteBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        favoriteMovieAdapter = FavoriteMovieAdapter(this, this)
        binding.recyclerViewMovieSaved.adapter = favoriteMovieAdapter

        observerViewModel()
    }

    private fun observerViewModel() {
        viewModel.getAllMoviesSaved().observe(viewLifecycleOwner, { response ->
            Log.d(TAG, "onViewCreated: $response")
            if (response != null) {
                if (response.isEmpty()) {
                    binding.textMoviesEmpty.visibility = View.VISIBLE
                    binding.recyclerViewMovieSaved.visibility = View.GONE
                } else {
                    binding.textMoviesEmpty.visibility = View.INVISIBLE
                    binding.recyclerViewMovieSaved.visibility = View.VISIBLE
                    favoriteMovieAdapter.submitList(response)
                }
            }
        })
    }

    override fun onDeleteMovie(id: Int?) {
        if (id != null) {
            viewModel.deleteMovie(id)
        }
    }

    override fun onMovieClick(movieResponse: MovieResponse?) {
        if (movieResponse != null) {
            startActivity(MovieDetailsActivity.newIntent(requireContext(), movieResponse))
        }
    }
}