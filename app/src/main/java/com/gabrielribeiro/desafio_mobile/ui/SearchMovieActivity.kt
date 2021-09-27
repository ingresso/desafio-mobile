package com.gabrielribeiro.desafio_mobile.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.gabrielribeiro.desafio_mobile.data.database.MovieDatabase
import com.gabrielribeiro.desafio_mobile.data.remote.models.MovieResponse
import com.gabrielribeiro.desafio_mobile.databinding.ActivitySearchMovieBinding
import com.gabrielribeiro.desafio_mobile.repositories.MovieRepositoryImplement
import com.gabrielribeiro.desafio_mobile.singletons.RetrofitInstance
import com.gabrielribeiro.desafio_mobile.ui.viewmodels.SearchHomeViewModel
import com.gabrielribeiro.desafio_mobile.utils.OnMovieClickListener
import com.gabrielribeiro.desafio_mobile.utils.Resource

class SearchMovieActivity : AppCompatActivity(), OnMovieClickListener {
    private var _binding : ActivitySearchMovieBinding? = null
    private val binding : ActivitySearchMovieBinding get() = _binding!!

    private lateinit var searchAdapter : SearchAdapter
    private val listLock = Any()
    private var movieArray = mutableListOf<MovieResponse>()
    private var filteredMovieArray = mutableListOf<MovieResponse>()
    private lateinit var viewModel : SearchHomeViewModel

    companion object {
        fun newIntent(context: Context) = Intent(context, SearchMovieActivity::class.java)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        _binding = ActivitySearchMovieBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Log.d("SearchMovieActivity", "onCreate: ")
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        viewModel = ViewModelProvider(this, SearchHomeViewModel.SearchViewModelFactory(
            MovieRepositoryImplement(RetrofitInstance().getApi(), MovieDatabase(this))
        )).get(SearchHomeViewModel::class.java)

        searchAdapter = SearchAdapter(this)
        binding.recyclerViewSearch.adapter = searchAdapter
        binding.editTextSearch.requestFocus()
        setupHooks()
        observerViewModel()

    }

    private fun observerViewModel() {
        viewModel.moviesListResponse.observe(this, {
            when (it) {
                is Resource.Loading -> {
                    binding.includeProgressIndicator.progressIndicator.visibility = View.VISIBLE
                    Log.d("FeedFragment", "Loading:")
                }
                is Resource.Failure -> {
                    Log.d("FeedFragment", "Failure: ${it.message}")
                    binding.includeProgressIndicator.progressIndicator.visibility = View.GONE

                }
                is Resource.Success -> {
                    if (it.data != null) {
                        Log.d("Resource", "observerViewModel: ${it.data.movieResponses}")
                        setMovieList(it.data.movieResponses.sortedBy { movie -> movie.dateMillis })
                        updateMovieList()
                        binding.includeProgressIndicator.progressIndicator.visibility = View.GONE
                    }
                }
            }

        })
    }

    override fun onResume() {
        super.onResume()
        viewModel.getMovies()
        Log.d("SearchMovieActivity", "onCreate: ")
    }
    private fun setMovieList(movieResponses: List<MovieResponse>) {
        synchronized(listLock) {
            movieArray = movieResponses as MutableList<MovieResponse>
        }
    }

    private fun setupHooks() {
        binding.editTextSearch.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun onTextChanged(char: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if (char != null) {
                    if (char.isEmpty()) {
                        binding.imageClear.visibility = View.INVISIBLE
                    } else {
                        binding.imageClear.visibility = View.VISIBLE
                    }
                }
                updateMovieList()
            }

            override fun afterTextChanged(p0: Editable?) { }

        })

        binding.imageClear.setOnClickListener {
            binding.editTextSearch.setText("")
            filteredMovieArray.clear()
        }

        binding.imageBack.setOnClickListener {
            finish()
        }
    }

    private fun updateMovieList() {
        synchronized(listLock) {
            filteredMovieArray = movieArray.filter {
                it.title.contains(binding.editTextSearch.text.toString(), ignoreCase = true)
            } as MutableList<MovieResponse>
           searchAdapter.submitList(filteredMovieArray)
        }
    }

    override fun onMovieClick(movieResponse: MovieResponse?) {
        if (movieResponse != null) {
            startActivity(MovieDetailsActivity.newIntent(applicationContext, movieResponse))
        }
    }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }

}