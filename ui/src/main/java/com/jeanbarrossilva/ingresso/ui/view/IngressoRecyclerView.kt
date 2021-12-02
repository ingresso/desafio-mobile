package com.jeanbarrossilva.ingresso.ui.view

import android.content.Context
import android.util.AttributeSet
import androidx.core.content.res.getDimensionOrThrow
import androidx.core.view.setPadding
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.jeanbarrossilva.ingresso.extensions.context.withStyledAttributes
import com.jeanbarrossilva.ingresso.extensions.view.dp
import com.jeanbarrossilva.ingresso.ui.R
import com.jeanbarrossilva.ingresso.ui.decoration.SpacedItemDecoration

/** [RecyclerView] with a [GridLayoutManager] that mimics the layout of the one present in the Ingresso app. **/
class IngressoRecyclerView @JvmOverloads constructor(
    context: Context,
    private val attrs: AttributeSet? = null,
    private val defStyleAttr: Int = 0
): RecyclerView(context, attrs, defStyleAttr) {
    var spacing = 0
        set(spacing) {
            field = spacing
            addItemDecoration(SpacedItemDecoration.Grid(this, spacing))
        }

    init {
        setUp()
        getAttrs()
    }

    /** Sets this [IngressoRecyclerView] by performing some initial configurations. **/
    private fun setUp() {
        layoutManager = GridLayoutManager(context, 3)
        setPadding(8.dp())
        clipToPadding = false
        spacing = 14.dp()
    }

    /** Gets the attributes that were defined in XML. **/
    private fun getAttrs() {
        context?.withStyledAttributes(R.styleable.IngressoRecyclerView, attrs, defStyleAttr) { index ->
            if (index == R.styleable.IngressoRecyclerView_spacing) {
                spacing = getDimensionOrThrow(index).toInt()
            }
        }
    }

    override fun setLayoutManager(layout: LayoutManager?) {
        if (layoutManager is GridLayoutManager) {
            super.setLayoutManager(layout)
        }
    }
}