package com.jeanbarrossilva.ingresso.ui.decoration

import android.graphics.Canvas
import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlin.reflect.KClass

/** [RecyclerView.ItemDecoration] capable of adding spacing between items. **/
sealed class SpacedItemDecoration<T: RecyclerView.LayoutManager>: RecyclerView.ItemDecoration() {
    /** Whether the given layout manager of type [T] is compatible with this [SpacedItemDecoration]. **/
    private val isLayoutManagerCompatible
        get() = layoutManager == null

    /** [view]'s layout manager cast to [T]. **/
    @Suppress("UNCHECKED_CAST")
    protected val layoutManager
        get() = view.layoutManager as? T

    /** Name of this [SpacedItemDecoration] used to create a meaningful message when calling [] **/
    abstract val name: String

    /** [RecyclerView] to which this [SpacedItemDecoration] will be added. **/
    abstract val view: RecyclerView

    /** The [RecyclerView.LayoutManager] that's compatible with this [SpacedItemDecoration]. **/
    abstract val layoutManagerClass: KClass<T>

    /** Spacing that will be added between the items. **/
    abstract val spacing: Int

    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        if (isLayoutManagerCompatible) {
            super.getItemOffsets(outRect, view, parent, state)
            onGetItemOffsets(layoutManager!!, outRect, view, parent, state)
        }
    }

    /** Applies the given [spacing]. **/
    abstract fun onGetItemOffsets(layoutManager: T, outRect: Rect, itemView: View, parent: RecyclerView, state: RecyclerView.State)

    /** [SpacedItemDecoration] that's compatible with a [GridLayoutManager]. **/
    data class Grid(override val view: RecyclerView, override val spacing: Int): SpacedItemDecoration<GridLayoutManager>() {
        override val name = "SpacedItemDecoration.Grid"
        override val layoutManagerClass = GridLayoutManager::class

        override fun onGetItemOffsets(
            layoutManager: GridLayoutManager,
            outRect: Rect,
            itemView: View,
            parent: RecyclerView,
            state: RecyclerView.State
        ) {
            val spanCount = layoutManager.spanCount
            val position = parent.getChildAdapterPosition(itemView)
            val column = position % spanCount
            val isAtTheTop = position >= spanCount
            val isAtTheEnd = position == this.view.adapter?.itemCount

            outRect.left = column * spacing / spanCount
            outRect.top = if (isAtTheTop) 0 else spacing
            outRect.right = if (isAtTheEnd) 0 else spacing
        }
    }
}