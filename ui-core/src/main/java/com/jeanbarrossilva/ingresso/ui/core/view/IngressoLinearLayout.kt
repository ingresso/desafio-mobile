package com.jeanbarrossilva.ingresso.ui.core.view

import android.content.Context
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.widget.LinearLayout
import com.jeanbarrossilva.ingresso.extensions.any.orIf
import com.jeanbarrossilva.ingresso.extensions.context.withStyledAttributes
import com.jeanbarrossilva.ingresso.ui.core.R
import top.defaults.drawabletoolbox.DrawableBuilder

/** [LinearLayout] that allows setting spacing between its children without having to create a [Drawable]. **/
class IngressoLinearLayout: LinearLayout {
    var spacing = 0
        set(spacing) {
            field = spacing
            dividerDrawable = DrawableBuilder()
                .orIf(orientation == HORIZONTAL) { width(spacing) }
                .orIf(orientation == VERTICAL) { height(spacing) }
                .build()
        }

    constructor(context: Context): super(context) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet?): super(context, attrs) {
        init(attrs)
    }

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int): super(context, attrs, defStyleAttr) {
        init(attrs, defStyleAttr)
    }

    private fun init(attrs: AttributeSet? = null, defStyleAttr: Int = 0) {
        showDividers = SHOW_DIVIDER_MIDDLE
        getAttrs(attrs, defStyleAttr)
    }

    /** Gets the attributes that were set in XML and sets them to their according field. **/
    private fun getAttrs(attrs: AttributeSet?, defStyleAttr: Int) {
        context.withStyledAttributes(R.styleable.IngressoLinearLayout, attrs, defStyleAttr) { index ->
            if (index == R.styleable.IngressoLinearLayout_spacing) {
                spacing = getDimensionPixelSize(index, spacing)
            }
        }
    }
}