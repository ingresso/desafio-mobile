package com.jeanbarrossilva.ingresso.ui.core.data

import android.graphics.drawable.ColorDrawable
import androidx.annotation.ColorInt
import androidx.core.view.isVisible
import com.jeanbarrossilva.ingresso.extensions.context.colorOf
import com.jeanbarrossilva.ingresso.ui.core.IngressoActivity

data class SystemBarsColors(@ColorInt val statusBar: Int, @ColorInt val navigationBar: Int) {
    constructor(@ColorInt color: Int): this(color, color)

    companion object {
        fun getDefault(activity: IngressoActivity<*>): SystemBarsColors {
            val toolbar = activity.toolbar
            val bottomNavigationView = activity.bottomNavigationView
            val backgroundColor = activity.colorOf(android.R.attr.colorBackground)
            val surfaceColor = activity.colorOf(com.google.android.material.R.attr.colorSurface)
            val isToolbarPresent = toolbar != null && toolbar.isVisible
            val toolbarBackgroundColor = (toolbar?.background as? ColorDrawable)?.color ?: surfaceColor
            val isBottomNavigationViewPresent = bottomNavigationView != null && bottomNavigationView.isVisible
            val bottomNavigationViewBackgroundColor = (bottomNavigationView?.background as? ColorDrawable)?.color ?: surfaceColor
            val statusBarColor = if (isToolbarPresent) toolbarBackgroundColor else backgroundColor
            val navigationBarColor = if (isBottomNavigationViewPresent) bottomNavigationViewBackgroundColor else backgroundColor

            return SystemBarsColors(statusBarColor, navigationBarColor)
        }
    }
}