package com.jeanbarrossilva.ingresso.ui.fragment

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.navArgs
import com.jeanbarrossilva.ingresso.ui.adapter.viewpager.MovieInfoAdapter
import com.jeanbarrossilva.ingresso.ui.core.IngressoFragment
import com.jeanbarrossilva.ingresso.ui.databinding.FragmentMovieInfoBinding
import com.jeanbarrossilva.ingresso.ui.extensions.imageview.loadLandscapeOf
import com.jeanbarrossilva.ingresso.ui.extensions.imageview.loadPortraitOf

class MovieInfoFragment: IngressoFragment<FragmentMovieInfoBinding>() {
    private val navArgs by navArgs<MovieInfoFragmentArgs>()
    private val movie by lazy { navArgs.movie }

    override val bindingClass = FragmentMovieInfoBinding::class

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.imageLandscapeView.loadLandscapeOf(movie.imageUrl)
        binding.imagePortraitView.loadPortraitOf(movie.imageUrl)
        binding.titleView.text = movie.title.localized
        binding.genresView.text = movie.genres.joinToString()
        binding.viewpager.adapter = MovieInfoAdapter(this, movie)
    }
}