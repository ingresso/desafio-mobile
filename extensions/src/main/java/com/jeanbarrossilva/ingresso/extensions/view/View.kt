package com.jeanbarrossilva.ingresso.extensions.view

import android.view.View
import android.view.ViewGroup
import androidx.core.view.children
import kotlin.reflect.KClass
import kotlin.reflect.full.isSubclassOf

/**
 * Searches for a [View] of type [V]. If [isInclusive] is set to `true` and the [View] from which this function is being called
 * conforms to [V], it will be returned.
 **/
@Suppress("UNCHECKED_CAST")
fun <V: View> View.searchFor(viewClass: KClass<V>, isInclusive: Boolean): V? {
    return when {
        this::class.isSubclassOf(viewClass) && isInclusive -> this as V
        this is ViewGroup -> children.map { view -> view.searchFor(viewClass, isInclusive = true) }.filterNotNull().firstOrNull()
        else -> null
    }
}

/** @see searchFor **/
inline fun <reified V: View> View.searchFor(isInclusive: Boolean = true): V? {
    return searchFor(V::class, isInclusive)
}