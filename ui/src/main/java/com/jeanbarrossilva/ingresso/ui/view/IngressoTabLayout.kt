package com.jeanbarrossilva.ingresso.ui.view

import android.content.Context
import android.content.res.ColorStateList
import android.util.AttributeSet
import com.google.android.material.tabs.TabLayout
import com.jeanbarrossilva.ingresso.ui.extensions.context.defaultRippleColor

class IngressoTabLayout: TabLayout {
    constructor(context: Context): super(context)

    constructor(context: Context, attrs: AttributeSet?): super(context, attrs)

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int): super(context, attrs, defStyleAttr)

    init {
        tabRippleColor = ColorStateList.valueOf(context.defaultRippleColor)
    }
}