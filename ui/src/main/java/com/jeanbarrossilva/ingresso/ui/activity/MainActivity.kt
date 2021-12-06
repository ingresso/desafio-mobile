package com.jeanbarrossilva.ingresso.ui.activity

import androidx.navigation.NavDestination
import com.jeanbarrossilva.ingresso.ui.R
import com.jeanbarrossilva.ingresso.ui.core.IngressoActivity
import com.jeanbarrossilva.ingresso.ui.databinding.ActivityMainBinding

class MainActivity: IngressoActivity<ActivityMainBinding>() {
    override val bindingClass = ActivityMainBinding::class

    override fun shouldHideBottomNavigationViewOn(destination: NavDestination): Boolean {
        return destination.id in arrayOf(R.id.movieInfoFragment, R.id.searchFragment)
    }
}