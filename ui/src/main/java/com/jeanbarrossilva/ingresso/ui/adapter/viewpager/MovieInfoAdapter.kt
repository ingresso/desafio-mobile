package com.jeanbarrossilva.ingresso.ui.adapter.viewpager

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.jeanbarrossilva.ingresso.model.Movie
import com.jeanbarrossilva.ingresso.ui.core.IngressoFragmentStateAdapter
import com.jeanbarrossilva.ingresso.ui.fragment.MovieDetailsFragment
import com.jeanbarrossilva.ingresso.ui.fragment.MovieInfoFragment
import kotlin.reflect.KClass

class MovieInfoAdapter(fragment: MovieInfoFragment, private val movie: Movie): IngressoFragmentStateAdapter(fragment) {
    override val fragmentClasses = arrayOf<KClass<out Fragment>>(MovieDetailsFragment::class)

    override fun willCreateFragment(fragment: Fragment) {
        if (fragment is MovieDetailsFragment) {
            fragment.arguments = Bundle().apply {
                putParcelable(MovieDetailsFragment.EXTRA_MOVIE, movie)
            }
        }
    }
}