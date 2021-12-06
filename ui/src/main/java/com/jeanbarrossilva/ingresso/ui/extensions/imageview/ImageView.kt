package com.jeanbarrossilva.ingresso.ui.extensions.imageview

import android.widget.ImageView
import coil.load
import com.jeanbarrossilva.ingresso.model.ImageUrl
import com.jeanbarrossilva.ingresso.ui.R

fun ImageView.loadLandscapeOf(imageUrl: ImageUrl) {
    load(imageUrl.landscape) {
        crossfade(true)
    }
}

fun ImageView.loadPortraitOf(imageUrl: ImageUrl) {
    load(imageUrl.portrait) {
        R.drawable.movie_image_portrait_placeholder.let { defaultDrawableRes ->
            crossfade(true)
            placeholder(defaultDrawableRes)
            fallback(defaultDrawableRes)
            listener(onCancel = { setImageResource(defaultDrawableRes) }, onError = { _, _ -> setImageResource(defaultDrawableRes) })
        }
    }
}