package com.gabrielribeiro.desafio_mobile.ui

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.text.HtmlCompat
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.gabrielribeiro.desafio_mobile.R
import com.gabrielribeiro.desafio_mobile.data.database.MovieDatabase
import com.gabrielribeiro.desafio_mobile.data.remote.models.MovieResponse
import com.gabrielribeiro.desafio_mobile.databinding.ActivityMovieDetailsBinding
import com.gabrielribeiro.desafio_mobile.repositories.MovieRepositoryImplement
import com.gabrielribeiro.desafio_mobile.singletons.RetrofitInstance
import com.gabrielribeiro.desafio_mobile.ui.viewmodels.MovieDetailsViewModel
import com.google.android.material.appbar.AppBarLayout
import kotlin.math.log

class MovieDetailsActivity : AppCompatActivity() {
    private var _binding : ActivityMovieDetailsBinding? = null
    private val binding : ActivityMovieDetailsBinding get() = _binding!!
    private lateinit var viewModel : MovieDetailsViewModel

    private lateinit var movieResponse : MovieResponse
    private lateinit var nationalUrl : String

    companion object {
        private const val TAG = "MovieDetailsActivity"
        private const val ARG_MOVIE = "arg_movie"
        fun newIntent(context : Context, movieResponse : MovieResponse) =
            Intent(context, MovieDetailsActivity::class.java).apply {
                putExtra(ARG_MOVIE, movieResponse)
            }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        _binding = ActivityMovieDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this, MovieDetailsViewModel.MovieDetailsViewModelFactory(MovieRepositoryImplement(RetrofitInstance().getApi(), 
            MovieDatabase(this)))).get(MovieDetailsViewModel::class.java)
        
        movieResponse = intent.getParcelableExtra(ARG_MOVIE)!!
        nationalUrl = movieResponse.nationalSiteURL

        if (movieResponse.cast.isNullOrEmpty()) {
            binding.textViewCast.visibility = View.GONE
        }

        setupMovieDetails()
        setupHooks()
        observerViewModel()

        var isShow = true
        var scrollRange = -1
        binding.appBarLayout.addOnOffsetChangedListener(AppBarLayout.OnOffsetChangedListener { barLayout, verticalOffset ->
            if (scrollRange == -1){
                scrollRange = barLayout?.totalScrollRange!!
            }
            if (scrollRange + verticalOffset == 0){
                binding.collapsingDetails.title = movieResponse.title
                binding.textTitleMovie.visibility = View.GONE
                isShow = true
            } else if (isShow){
                binding.collapsingDetails.title = " "
                isShow = false
                binding.textTitleMovie.visibility = View.VISIBLE
            }
        })
    }

    private fun observerViewModel() {
        viewModel.getAllArticlesSaved().observe(this, {
            Log.d(TAG, "observerViewModel: ${it.size         }")
        })
    }

    private fun setupHooks() {
        binding.buttonOpenTrailer.setOnClickListener {
            val uri = Uri.parse(movieResponse.trailers!!.first().url)
            startActivity(Intent(Intent.ACTION_VIEW, uri))
        }

        binding.buttonShareMovie.setOnClickListener {
            val shareIntent: Intent = Intent().apply {
                action = Intent.ACTION_SEND
                putExtra(Intent.EXTRA_TEXT, generateShareContent())
                type = "text/plain"
            }
            startActivity(Intent.createChooser(shareIntent, "Desafio Mobile"))
        }
        
        binding.buttonFavorite.setOnClickListener { 
            viewModel.saveMovie(movieResponse.toMovieEntity())
        }
    }

    private fun generateShareContent(): String {
        return "${getString(R.string.share_movie_text)} \n$nationalUrl"
    }

    private fun setupMovieDetails() {
        movieResponse.apply {
            binding.textTitleMovie.text = title
            binding.textViewSynopsis.text = synopsis
            binding.textViewCast.text = HtmlCompat.fromHtml(
                getString(R.string.cast_text, cast),
                HtmlCompat.FROM_HTML_MODE_LEGACY
            )
            binding.textViewDirector.text = HtmlCompat.fromHtml(
                getString(R.string.director_text, director),
                HtmlCompat.FROM_HTML_MODE_LEGACY
            )

            binding.textViewDistributor.text = HtmlCompat.fromHtml(
                getString(R.string.distribuidor_text, distributor),
                HtmlCompat.FROM_HTML_MODE_LEGACY
            )

            binding.textViewCountry.text = HtmlCompat.fromHtml(
                getString(R.string.country_text, countryOrigin),
                HtmlCompat.FROM_HTML_MODE_LEGACY
            )

            if (!genres.isNullOrEmpty()) {
                binding.textViewGenres.text = HtmlCompat.fromHtml(
                    getString(R.string.genres_text, genresFormatted),
                    HtmlCompat.FROM_HTML_MODE_LEGACY
                )
                binding.textViewGenres.visibility = View.VISIBLE
            }

            if (premiereDate != null) {
                binding.textViewPremierDate.text = HtmlCompat.fromHtml(
                    getString(R.string.premiere_text, dateFormatted),
                    HtmlCompat.FROM_HTML_MODE_LEGACY
                )
                binding.textViewPremierDate.visibility = View.VISIBLE
            }
            if (!trailers.isNullOrEmpty()) {
                binding.buttonOpenTrailer.visibility = View.VISIBLE
            }

            Glide.with(applicationContext)
                .load(if (movieResponse.images.isEmpty()) R.drawable.bg_empty_movie else movieResponse.images.first().url)
                .placeholder(R.drawable.bg_empty_movie)
                .override(80, 80)
                .error(R.drawable.bg_empty_movie)
                .into(binding.imageMovieBgDetails)

            Glide.with(applicationContext)
                .load(if (movieResponse.images.isEmpty()) R.drawable.bg_empty_movie else movieResponse.images.first().url)
                .placeholder(R.drawable.bg_empty_movie)
                .error(R.drawable.bg_empty_movie)
                .into(binding.imageMovieDetails)

        }
    }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }
}