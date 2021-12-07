package com.jeanbarrossilva.ingresso.ui.activity

import androidx.fragment.app.Fragment
import com.jeanbarrossilva.ingresso.ui.core.IngressoActivity
import com.jeanbarrossilva.ingresso.ui.databinding.ActivityMainBinding
import com.jeanbarrossilva.ingresso.ui.fragment.MovieInfoFragment
import com.jeanbarrossilva.ingresso.ui.fragment.SearchFragment

class MainActivity: IngressoActivity<ActivityMainBinding>() {
    override val bindingClass = ActivityMainBinding::class

    override fun shouldHideToolbarOn(fragment: Fragment): Boolean {
        return fragment is SearchFragment
    }

    override fun shouldHideBottomNavigationViewOn(fragment: Fragment): Boolean {
        return fragment is MovieInfoFragment || fragment is SearchFragment
    }
}