package com.jeanbarrossilva.ingresso.network.extensions.image

import com.jeanbarrossilva.ingresso.model.ImageUrl
import com.jeanbarrossilva.ingresso.network.dto.Image

fun List<Image>.toImageUrl(): ImageUrl {
    val (portraitImageUrl, landscapeImageUrl) = firstOrNull()?.url to elementAtOrNull(1)?.url
    return ImageUrl(portraitImageUrl, landscapeImageUrl)
}