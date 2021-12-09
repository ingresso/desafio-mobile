package com.jeanbarrossilva.ingresso.ui.fragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.jeanbarrossilva.ingresso.extensions.context.colorOf
import com.jeanbarrossilva.ingresso.extensions.view.edittext.closeKeyboard
import com.jeanbarrossilva.ingresso.extensions.view.edittext.doOnTextChanged
import com.jeanbarrossilva.ingresso.extensions.view.edittext.openKeyboard
import com.jeanbarrossilva.ingresso.model.Movie
import com.jeanbarrossilva.ingresso.repository.Repository
import com.jeanbarrossilva.ingresso.ui.R
import com.jeanbarrossilva.ingresso.ui.adapter.recyclerview.MovieSearchResultAdapter
import com.jeanbarrossilva.ingresso.ui.core.IngressoActivity
import com.jeanbarrossilva.ingresso.ui.core.IngressoFragment
import com.jeanbarrossilva.ingresso.ui.core.data.SystemBarsColors
import com.jeanbarrossilva.ingresso.ui.databinding.FragmentSearchBinding
import com.jeanbarrossilva.ingresso.ui.viewmodel.SearchViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class SearchFragment: IngressoFragment<FragmentSearchBinding>() {
    private val viewModel by viewModels<SearchViewModel>()

    override val bindingClass = FragmentSearchBinding::class

    private fun navigateToDetailsOf(movie: Movie) {
        binding.textInputLayout.editText?.closeKeyboard()
        findNavController().navigate(SearchFragmentDirections.detailsOf(movie))
    }

    private fun setUpSearchField() {
        binding.textInputLayout.editText?.openKeyboard()
        binding.textInputLayout.editText?.doOnTextChanged(::search)
    }

    private fun search(query: String) {
        lifecycleScope.launch {
            Repository.search(query).collect {
                updateResultsView(it)
            }
        }
    }

    private fun setUpResults() {
        lifecycleScope.launch {
            viewModel.moviesFlow.collect {
                updateResultsView(it)
            }
        }
    }

    private fun updateResultsView(movies: List<Movie>) {
        binding.resultsView.adapter = MovieSearchResultAdapter(movies, onClick = ::navigateToDetailsOf)
    }

    override fun IngressoActivity<*>.onSetSystemBarsColors(): SystemBarsColors {
        return SystemBarsColors(colorOf(R.attr.colorSurface))
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpSearchField()
        setUpResults()
    }
}