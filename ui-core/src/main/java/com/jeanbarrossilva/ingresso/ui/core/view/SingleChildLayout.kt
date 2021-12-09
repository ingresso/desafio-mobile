package com.jeanbarrossilva.ingresso.ui.core.view

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout

/** A [ViewGroup] that has only one child [View]. **/
open class SingleChildLayout: FrameLayout {
    constructor(context: Context): super(context)

    constructor(context: Context, attrs: AttributeSet?): super(context, attrs)

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int): super(context, attrs, defStyleAttr)

    override fun addView(child: View?, index: Int, params: ViewGroup.LayoutParams?) {
        if (childCount == 1) {
            throw IllegalStateException("A SingleChildLayout should only have one child.")
        } else {
            super.addView(child, index, params)
        }
    }
}