package com.jeanbarrossilva.ingresso.ui.view

import android.content.Context
import android.util.AttributeSet
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.jeanbarrossilva.ingresso.extensions.context.withStyledAttributes
import com.jeanbarrossilva.ingresso.ui.R
import com.jeanbarrossilva.ingresso.ui.decoration.GridSpacingItemDecoration

class IngressoRecyclerView @JvmOverloads constructor(
    context: Context,
    private val attrs: AttributeSet? = null,
    private val defStyleAttr: Int = 0
): RecyclerView(context, attrs, defStyleAttr) {
    var spacing = 0
        set(spacing) {
            field = spacing
            if (layoutManager is GridLayoutManager) {
                addItemDecoration(GridSpacingItemDecoration(spacing))
            } else {
                throw NotImplementedError()
            }
        }

    init {
        setUp()
        applyAttrs()
    }

    /** Sets this [IngressoRecyclerView] up with some initial configurations. **/
    private fun setUp() {
        layoutManager = GridLayoutManager(context, 3)
    }

    /** Applies the attributes that were set in XML. **/
    private fun applyAttrs() {
        context.withStyledAttributes(R.styleable.IngressoRecyclerView, attrs, defStyleAttr) { index ->
            if (index == R.styleable.IngressoRecyclerView_spacing) {
                spacing = getDimensionPixelSize(index, spacing)
            }
        }
    }
}