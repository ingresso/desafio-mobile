package com.jeanbarrossilva.ingresso.extensions.context.activity

import android.app.Activity
import android.widget.FrameLayout
import androidx.core.view.children

val Activity.view
    get() = findViewById<FrameLayout>(android.R.id.content).children.first()