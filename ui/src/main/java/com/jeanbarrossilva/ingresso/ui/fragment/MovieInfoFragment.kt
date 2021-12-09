package com.jeanbarrossilva.ingresso.ui.fragment

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import androidx.navigation.fragment.navArgs
import com.jeanbarrossilva.ingresso.extensions.context.share
import com.jeanbarrossilva.ingresso.ui.R
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

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.toolbar_movie_info, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.menu_item_share -> {
                context?.share(R.string.movie_info_share_sheet_title, movie.url)
                true
            }
            else -> false
        }
    }
}