package com.jeanbarrossilva.ingresso.ui.core.extensions.window

import android.view.Window
import com.jeanbarrossilva.ingresso.ui.core.data.SystemBarsColors

fun Window.setSystemBarsColors(colors: SystemBarsColors) {
    statusBarColor = colors.statusBar
    navigationBarColor = colors.navigationBar
}