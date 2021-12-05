package com.jeanbarrossilva.ingresso.ui.core

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import kotlin.reflect.KClass

abstract class IngressoFragment<T: ViewBinding>: Fragment() {
    abstract val bindingClass: KClass<T>

    lateinit var binding: T
        private set

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    @Suppress("UNCHECKED_CAST")
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = bindingClass.java
            .getDeclaredMethod("inflate", LayoutInflater::class.java, ViewGroup::class.java, Boolean::class.java)
            .invoke(null, inflater, container, false) as T
        return binding.root
    }
}