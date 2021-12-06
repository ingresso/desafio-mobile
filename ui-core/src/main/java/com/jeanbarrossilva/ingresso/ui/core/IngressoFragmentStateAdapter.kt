package com.jeanbarrossilva.ingresso.ui.core

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.jeanbarrossilva.ingresso.extensions.any.orIf
import kotlin.reflect.KClass
import kotlin.reflect.full.primaryConstructor

abstract class IngressoFragmentStateAdapter(fragment: Fragment): FragmentStateAdapter(fragment) {
    abstract val fragmentClasses: Array<KClass<out Fragment>>

    override fun getItemCount(): Int {
        return fragmentClasses.size
    }

    override fun createFragment(position: Int): Fragment {
        return fragmentClasses[position]
            .primaryConstructor
            ?.call()
            .orIf({ this == null }) { Fragment() }!!
            .also(::willCreateFragment)
    }

    /**
     * Called before [createFragment].
     *
     * @param fragment [Fragment] that's about to be created.
     **/
    open fun willCreateFragment(fragment: Fragment) {
    }
}