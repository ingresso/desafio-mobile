package com.gabrielribeiro.desafio_mobile.ui

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.core.text.HtmlCompat
import com.bumptech.glide.Glide
import com.gabrielribeiro.desafio_mobile.R
import com.gabrielribeiro.desafio_mobile.data.remote.model.MovieResponse
import com.google.android.material.appbar.AppBarLayout
import kotlinx.android.synthetic.main.activity_movie_details.*

class MovieDetailsActivity : AppCompatActivity() {
    private lateinit var movieResponse : MovieResponse
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
        setContentView(R.layout.activity_movie_details)

        movieResponse = intent.getParcelableExtra(ARG_MOVIE)!!

        if (movieResponse.cast.isNullOrEmpty()) {
            text_view_cast.visibility = View.GONE
        }

        setupMovieDetails()
        setupHooks()

        var isShow = true
        var scrollRange = -1
        app_bar_layout.addOnOffsetChangedListener(AppBarLayout.OnOffsetChangedListener { barLayout, verticalOffset ->
            if (scrollRange == -1){
                scrollRange = barLayout?.totalScrollRange!!
            }
            if (scrollRange + verticalOffset == 0){
                collapsing_details.title = movieResponse.title
                text_title_movie.visibility = View.GONE
                isShow = true
            } else if (isShow){
                collapsing_details.title = " "
                isShow = false
                text_title_movie.visibility = View.VISIBLE
            }
        })
    }

    private fun setupHooks() {
        button_open_trailer.setOnClickListener {
            val uri = Uri.parse(movieResponse.trailers!!.first().url)
            startActivity(Intent(Intent.ACTION_VIEW, uri))
        }
    }

    private fun setupMovieDetails() {
        movieResponse.apply {
            text_title_movie.text = title
            text_view_synopsis.text = synopsis
            text_view_cast.text = HtmlCompat.fromHtml(
                getString(R.string.cast_text, cast),
                HtmlCompat.FROM_HTML_MODE_LEGACY
            )
            text_view_director.text = HtmlCompat.fromHtml(
                getString(R.string.director_text, director),
                HtmlCompat.FROM_HTML_MODE_LEGACY
            )

            text_view_distributor.text = HtmlCompat.fromHtml(
                getString(R.string.distribuidor_text, distributor),
                HtmlCompat.FROM_HTML_MODE_LEGACY
            )

            text_view_country.text = HtmlCompat.fromHtml(
                getString(R.string.country_text, countryOrigin),
                HtmlCompat.FROM_HTML_MODE_LEGACY
            )

            if (!genres.isNullOrEmpty()) {
                text_view_genres.text = genresFormatted
                text_view_genres.visibility = View.VISIBLE
            }

            if (premiereDate != null) {
                text_view_premier_date.text = dateFormatted
                text_view_premier_date.visibility = View.VISIBLE
            }
            if (!trailers.isNullOrEmpty()) {
                button_open_trailer.visibility = View.VISIBLE
            }

            Glide.with(applicationContext)
                .load(if (movieResponse.images.isEmpty()) R.drawable.bg_empty_movie else movieResponse.images.first().url)
                .placeholder(R.drawable.bg_empty_movie)
                .override(80, 80)
                .error(R.drawable.bg_empty_movie)
                .into(image_movie_bg_details)

            Glide.with(applicationContext)
                .load(if (movieResponse.images.isEmpty()) R.drawable.bg_empty_movie else movieResponse.images.first().url)
                .placeholder(R.drawable.bg_empty_movie)
                .error(R.drawable.bg_empty_movie)
                .into(image_movie_details)

        }
    }
}