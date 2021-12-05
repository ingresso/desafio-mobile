package com.jeanbarrossilva.ingresso.ui.fragment

import android.os.Bundle
import android.view.View
import androidx.appcompat.widget.Toolbar
import androidx.core.view.isVisible
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.jeanbarrossilva.ingresso.extensions.context.activity.view
import com.jeanbarrossilva.ingresso.extensions.view.edittext.doOnTextChanged
import com.jeanbarrossilva.ingresso.extensions.view.edittext.openKeyboard
import com.jeanbarrossilva.ingresso.extensions.view.searchFor
import com.jeanbarrossilva.ingresso.repository.Repository
import com.jeanbarrossilva.ingresso.ui.adapter.MovieSearchResultAdapter
import com.jeanbarrossilva.ingresso.ui.core.IngressoFragment
import com.jeanbarrossilva.ingresso.ui.databinding.FragmentSearchBinding

class SearchFragment: IngressoFragment<FragmentSearchBinding>() {
    private val adapter = MovieSearchResultAdapter(Repository.movies)

    override val bindingClass = FragmentSearchBinding::class

    private fun setBarsVisible(areBarsVisible: Boolean) {
        activity?.view?.searchFor<Toolbar>()?.isVisible = areBarsVisible
        activity?.view?.searchFor<BottomNavigationView>()?.isVisible = areBarsVisible
    }

    private fun setUpSearchField() {
        binding.textInputLayout.editText?.openKeyboard()
        binding.textInputLayout.editText?.doOnTextChanged { text -> adapter.filter.filter(text) }
    }

    private fun setUpResults() {
        binding.resultsView.adapter = adapter
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setBarsVisible(false)
        setUpSearchField()
        setUpResults()
    }

    override fun onDestroy() {
        setBarsVisible(true)
        super.onDestroy()
    }
}