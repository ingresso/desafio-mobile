package com.jeanbarrossilva.ingresso.ui.core

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import kotlin.reflect.KClass

/** [RecyclerView.Adapter] that automatically creates an instance of a [ViewBinding] of type [B]. **/
abstract class IngressoAdapter<B: ViewBinding, H: RecyclerView.ViewHolder, I>: RecyclerView.Adapter<H>() {
    abstract val bindingClass: KClass<B>
    abstract val items: List<I>

    lateinit var binding: B
        private set

    /** @see [RecyclerView.Adapter.onCreateViewHolder] **/
    abstract fun onCreateViewHolder(): H

    @Suppress("UNCHECKED_CAST")
    final override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): H {
        LayoutInflater.from(parent.context).let { layoutInflater ->
            binding = bindingClass.java
                .getDeclaredMethod("inflate", LayoutInflater::class.java, ViewGroup::class.java, Boolean::class.java)
                .invoke(null, layoutInflater, parent, false) as B
        }

        return onCreateViewHolder()
    }

    override fun getItemCount(): Int {
        return items.size
    }
}