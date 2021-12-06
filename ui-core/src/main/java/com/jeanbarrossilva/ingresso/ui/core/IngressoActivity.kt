package com.jeanbarrossilva.ingresso.ui.core

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.isVisible
import androidx.navigation.NavDestination
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import androidx.viewbinding.ViewBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.jeanbarrossilva.ingresso.extensions.view.searchFor
import kotlin.reflect.KClass

/** [AppCompatActivity] that uses view binding by default and configures navigation automatically. **/
abstract class IngressoActivity<T: ViewBinding>: AppCompatActivity() {
    private val navController
        get() = supportFragmentManager
            .fragments
            .filterIsInstance<NavHostFragment>()
            .firstOrNull()
            ?.findNavController()
            ?: throw NullPointerException("No NavHostFragment found.")
    private val toolbar
        get() = binding.root.searchFor<Toolbar>()
    private val bottomNavigationView
        get() = binding.root.searchFor<BottomNavigationView>()

    abstract val bindingClass: KClass<T>

    lateinit var binding: T
        private set

    private fun setUpNavigation() {
        setSupportActionBar(toolbar)
        toolbar?.setupWithNavController(navController)
        bottomNavigationView?.setupWithNavController(navController)
        navController.addOnDestinationChangedListener { _, destination, _ ->
            bottomNavigationView?.isVisible = !shouldHideBottomNavigationViewOn(destination)
        }
    }

    @Suppress("UNCHECKED_CAST")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = bindingClass.java
            .getDeclaredMethod("inflate", LayoutInflater::class.java)
            .invoke(null, layoutInflater) as T
        setContentView(binding.root)
        setUpNavigation()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return false
    }

    /** Whether [bottomNavigationView] should be hidden when the user navigates to [destination]. **/
    open fun shouldHideBottomNavigationViewOn(destination: NavDestination): Boolean {
        return false
    }
}