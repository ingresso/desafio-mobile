package com.gabrielribeiro.desafio_mobile.ui

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.gabrielribeiro.desafio_mobile.R
import com.gabrielribeiro.desafio_mobile.repositories.MovieDataSource
import com.gabrielribeiro.desafio_mobile.repositories.MovieRepositoryImplement
import com.gabrielribeiro.desafio_mobile.singletons.RetrofitInstance
import com.gabrielribeiro.desafio_mobile.ui.viewmodels.MovieViewModel
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.include_custom_toolbar.view.*

class MainActivity : AppCompatActivity() {
    lateinit var viewModel : MovieViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setSupportActionBar(include_toolbar)
        supportActionBar?.title = ""

        supportFragmentManager.beginTransaction().replace(R.id.frame_layout_main, FeedFragment()).commit()
        bottom_navigation_main.selectedItemId = R.id.menu_feed
        bottom_navigation_main.setOnItemSelectedListener {item ->
            var selectedFragment : Fragment? = null
            when(item.itemId) {
                R.id.menu_feed -> {
                    selectedFragment = FeedFragment()
                }
                R.id.menu_favorite -> {
                    selectedFragment = FavoriteFragment()
                }
            }
            supportFragmentManager.beginTransaction().replace(R.id.frame_layout_main, selectedFragment!!).commit()
            true
        }


        viewModel = ViewModelProvider(this, MovieViewModel.MovieViewModelFactory(MovieRepositoryImplement(RetrofitInstance().getApi())
        )).get(MovieViewModel::class.java)

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_toolbar_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.menu_open_search) {
            startActivity(SearchMovieActivity.newIntent(this))
        }
        return super.onOptionsItemSelected(item)
    }

}