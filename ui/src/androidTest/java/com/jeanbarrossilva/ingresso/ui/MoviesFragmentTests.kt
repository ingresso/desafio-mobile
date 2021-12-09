package com.jeanbarrossilva.ingresso.ui

import android.content.Context
import androidx.core.os.bundleOf
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.navigation.Navigation
import androidx.navigation.testing.TestNavHostController
import androidx.recyclerview.widget.RecyclerView
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition
import androidx.test.espresso.matcher.ViewMatchers.assertThat
import androidx.test.espresso.matcher.ViewMatchers.withId
import com.jeanbarrossilva.ingresso.model.Movie
import com.jeanbarrossilva.ingresso.ui.adapter.recyclerview.MoviePosterAdapter
import com.jeanbarrossilva.ingresso.ui.fragment.MoviesFragment
import com.jeanbarrossilva.ingresso.ui.viewholder.MoviePosterViewHolder
import org.hamcrest.CoreMatchers.`is`
import org.junit.Before
import org.junit.Test

class MoviesFragmentTests {
    private val MoviesFragment.moviesView
        get() = view?.findViewById<RecyclerView>(R.id.movies_view)

    private val scenario
        get() = launchFragmentInContainer<MoviesFragment>(themeResId = R.style.Theme_Ingresso)
    private val context
        get() = ApplicationProvider.getApplicationContext<Context>()
    private val navController = TestNavHostController(context)

    private fun setUpNavigation() {
        scenario.onFragment { fragment ->
            fragment.activity?.runOnUiThread { navController.setGraph(R.navigation.navigation_main) }
            fragment.view?.let { Navigation.setViewNavController(it, navController) }
        }
    }

    private fun setUpMoviesViewAdapter() {
        scenario.onFragment { fragment ->
            fragment.moviesView?.adapter = MoviePosterAdapter(Movie.samples) {
                navController.setCurrentDestination(R.id.movieInfoFragment, bundleOf("movie" to it))
            }
        }
    }

    @Before
    fun setUp() {
        setUpNavigation()
        setUpMoviesViewAdapter()
    }

    @Test
    fun navigateToMovieDetails() {
        0.until(Movie.samples.lastIndex).forEach { index ->
            onView(withId(R.id.movies_view))
                .perform(actionOnItemAtPosition<MoviePosterViewHolder>(index, click()))
            assertThat(navController.currentDestination?.id, `is`(R.id.movieInfoFragment))
        }
    }
}