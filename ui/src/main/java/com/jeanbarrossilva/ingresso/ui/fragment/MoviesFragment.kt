package com.jeanbarrossilva.ingresso.ui.fragment

import android.os.Bundle
import android.view.View
import com.jeanbarrossilva.ingresso.repository.Repository
import com.jeanbarrossilva.ingresso.ui.adapter.MoviePosterAdapter
import com.jeanbarrossilva.ingresso.ui.core.IngressoFragment
import com.jeanbarrossilva.ingresso.ui.databinding.FragmentMoviesBinding

class MoviesFragment: IngressoFragment<FragmentMoviesBinding>() {
    override val bindingClass = FragmentMoviesBinding::class

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.moviesView.adapter = MoviePosterAdapter(Repository.movies)
    }
}