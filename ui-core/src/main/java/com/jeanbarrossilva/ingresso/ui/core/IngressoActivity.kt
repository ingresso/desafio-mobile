package com.jeanbarrossilva.ingresso.ui.core

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.navigation.NavDestination
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import androidx.viewbinding.ViewBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.jeanbarrossilva.ingresso.extensions.view.searchFor
import com.jeanbarrossilva.ingresso.ui.core.extensions.window.setSystemBarsColors
import kotlin.reflect.KClass

/** [AppCompatActivity] that uses view binding by default and configures navigation and system bars colors automatically. **/
abstract class IngressoActivity<T: ViewBinding>: AppCompatActivity() {
    private val navHostFragment
        get() = supportFragmentManager
            .fragments
            .filterIsInstance<NavHostFragment>()
            .firstOrNull()
    private val navController
        get() = navHostFragment?.findNavController() ?: throw NullPointerException("No NavHostFragment found.")
    private val currentFragment
        get() = navHostFragment?.childFragmentManager?.fragments?.lastOrNull() ?: supportFragmentManager.fragments.lastOrNull()

    internal val toolbar
        get() = binding.root.searchFor<Toolbar>()
    internal val bottomNavigationView
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

    private fun setUpSystemBarsColors() {
        currentFragment?.let(::onFragmentChange)
        navHostFragment?.childFragmentManager?.addFragmentOnAttachListener { _, fragment -> onFragmentChange(fragment) }
        navHostFragment?.childFragmentManager?.addOnBackStackChangedListener { currentFragment?.let(::onFragmentChange) }
    }

    private fun onFragmentChange(fragment: Fragment) {
        with(fragment) {
            if (this is IngressoFragment<*>) {
                window?.setSystemBarsColors(onSetSystemBarsColors())
                Log.d(TAG, "setUpSystemBarsColors: System bars colors have been updated for $this.")
            }
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
        setUpSystemBarsColors()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return false
    }

    /** Whether [bottomNavigationView] should be hidden when the user navigates to [destination]. **/
    open fun shouldHideBottomNavigationViewOn(destination: NavDestination): Boolean {
        return false
    }

    companion object {
        private const val TAG = "IngressoActivity"
    }
}