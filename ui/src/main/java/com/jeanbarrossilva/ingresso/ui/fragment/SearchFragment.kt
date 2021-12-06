package com.jeanbarrossilva.ingresso.ui.fragment

import android.os.Bundle
import android.view.View
import androidx.appcompat.widget.Toolbar
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.jeanbarrossilva.ingresso.extensions.context.activity.view
import com.jeanbarrossilva.ingresso.extensions.view.edittext.doOnTextChanged
import com.jeanbarrossilva.ingresso.extensions.view.edittext.openKeyboard
import com.jeanbarrossilva.ingresso.extensions.view.searchFor
import com.jeanbarrossilva.ingresso.ui.adapter.recyclerview.MovieSearchResultAdapter
import com.jeanbarrossilva.ingresso.ui.core.IngressoFragment
import com.jeanbarrossilva.ingresso.ui.databinding.FragmentSearchBinding
import com.jeanbarrossilva.ingresso.ui.viewmodel.SearchViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class SearchFragment: IngressoFragment<FragmentSearchBinding>() {
    private val viewModel by viewModels<SearchViewModel>()
    private var adapter = MovieSearchResultAdapter(this, emptyList())

    override val bindingClass = FragmentSearchBinding::class

    private fun setToolbarVisible(isToolbarVisible: Boolean) {
        activity?.view?.searchFor<Toolbar>()?.isVisible = isToolbarVisible
    }

    private fun setUpSearchField() {
        binding.textInputLayout.editText?.openKeyboard()
        binding.textInputLayout.editText?.doOnTextChanged { text -> adapter.filter.filter(text) }
    }

    private fun setUpResults() {
        lifecycleScope.launch {
            viewModel.moviesFlow.collect { movies ->
                adapter = MovieSearchResultAdapter(this@SearchFragment, movies)
                binding.resultsView.adapter = adapter
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setToolbarVisible(false)
        setUpSearchField()
        setUpResults()
    }

    override fun onDestroy() {
        setToolbarVisible(true)
        super.onDestroy()
    }
}