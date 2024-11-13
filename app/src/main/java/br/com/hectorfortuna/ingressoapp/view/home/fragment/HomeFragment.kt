package br.com.hectorfortuna.ingressoapp.view.home.fragment

import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.navigation.fragment.findNavController
import br.com.hectorfortuna.ingressoapp.R
import br.com.hectorfortuna.ingressoapp.core.Status
import br.com.hectorfortuna.ingressoapp.data.model.Item
import br.com.hectorfortuna.ingressoapp.databinding.FragmentHomeBinding
import br.com.hectorfortuna.ingressoapp.view.home.adapter.MovieAdapter
import br.com.hectorfortuna.ingressoapp.view.home.viewmodel.MovieViewModel
import com.facebook.shimmer.ShimmerFrameLayout
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@AndroidEntryPoint
class HomeFragment : Fragment() {
    private val viewModel: MovieViewModel by viewModels()
    private lateinit var shimmerFrameLayout: ShimmerFrameLayout
    private lateinit var binding: FragmentHomeBinding
    private lateinit var movieAdapter: MovieAdapter
    private var isFilteringCurrentMonth = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getAllMovies()
        setupToolbar()
        observeVMEvents()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun getAllMovies() {
        viewModel.getAllMoviesResponse()
        startShimmer()
    }

    private fun startShimmer() {
        shimmerFrameLayout = binding.shimmerLayout
        shimmerFrameLayout.startShimmer()
    }

    private fun stopShimmer() {
        shimmerFrameLayout.stopShimmer()
        shimmerFrameLayout.hideShimmer()
        shimmerFrameLayout.visibility = View.GONE
    }

    private fun search(menu: Menu) {
        val search = menu.findItem(R.id.action_search)
        val searchView = search.actionView as SearchView
        searchView.queryHint = getString(R.string.search)

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            @RequiresApi(Build.VERSION_CODES.O)
            override fun onQueryTextChange(newText: String?): Boolean {
                when (newText) {
                    "" -> getAllMovies()
                    else -> filterMovies(newText!!)
                }
                return true
            }
        })
    }

    private fun filterMovies(query: String) {
        val filteredMovies = viewModel.response.value?.data?.items?.filter {
            it.title?.contains(query, ignoreCase = true) == true ||
                    it.genres?.any { genre -> genre.contains(query, ignoreCase = true) } == true
        } ?: emptyList()

        setRecyclerView(filteredMovies)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun applyFilter() {
        val currentMonth = LocalDate.now().monthValue

        viewModel.response.observe(viewLifecycleOwner) {
            if (viewLifecycleOwner.lifecycle.currentState != Lifecycle.State.RESUMED) return@observe
            when (it.status) {
                Status.SUCCESS -> {
                    it.data?.let { response ->
                        Timber.tag("Sucesso").i(response.toString())
                        stopShimmer()

                        val filteredMovies = if (isFilteringCurrentMonth) {
                            response.items.filter { item ->
                                item.premiereDate?.let {
                                    val dateFormatter = DateTimeFormatter.ISO_DATE_TIME
                                    val premiereDate = LocalDate.parse(it.localDate, dateFormatter)
                                    val movieMonth = premiereDate.monthValue

                                    movieMonth == currentMonth
                                } ?: false
                            }
                        } else {
                            response.items
                        }

                        setRecyclerView(filteredMovies)

                    }
                }

                Status.ERROR -> {
                    Timber.tag("Erro").e(it.error)
                    stopShimmer()
                }

                Status.LOADING -> {
                    startShimmer()
                }
            }
        }
    }

    private fun setRecyclerView(movieList: List<Item>) {
        setAdapter(movieList)
        binding.rvHomeFragment.apply {
            setHasFixedSize(true)
            adapter = movieAdapter
        }
    }

    private fun setAdapter(movieList: List<Item>) {
        movieAdapter = MovieAdapter(movieList) {
            findNavController().navigate(
                R.id.action_homeFragment_to_detailFragment,
                Bundle().apply {
                    putParcelable("MOVIE", it)
                }
            )
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_filter_current_month -> {
                isFilteringCurrentMonth = !item.isChecked
                item.isChecked = isFilteringCurrentMonth
                if (isFilteringCurrentMonth) {
                    item.setIcon(R.drawable.ic_filter_checked)
                } else {
                    item.setIcon(R.drawable.ic_filter)
                }
                applyFilter()
                return true
            }
            else -> return super.onOptionsItemSelected(item)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.toolbar, menu)
        val filterMenuItem = menu.findItem(R.id.action_filter_current_month)
        if (isFilteringCurrentMonth) {
            filterMenuItem.setIcon(R.drawable.ic_filter_checked)
        } else {
            filterMenuItem.setIcon(R.drawable.ic_filter)
        }
        search(menu)
    }

    private fun setupToolbar() {
        setHasOptionsMenu(true)
        (activity as AppCompatActivity).setSupportActionBar(binding.includeToolbar.toolbarLayout)
        (activity as AppCompatActivity).supportActionBar?.setDisplayShowTitleEnabled(false)
    }

    private fun observeVMEvents() {
        viewModel.response.observe(viewLifecycleOwner) {
            if (viewLifecycleOwner.lifecycle.currentState != Lifecycle.State.RESUMED) return@observe
            when (it.status) {
                Status.SUCCESS -> {
                    it.data?.let { response ->
                        Timber.tag("Sucesso").i(response.toString())
                        stopShimmer()
                        setRecyclerView(response.items)
                    }
                }

                Status.ERROR -> {
                    Timber.tag("Erro").e(it.error)
                    stopShimmer()
                }

                Status.LOADING -> {
                    startShimmer()
                }
            }
        }
    }
}