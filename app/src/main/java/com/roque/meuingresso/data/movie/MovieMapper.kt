package com.roque.meuingresso.data.movie

import com.roque.meuingresso.domain.model.Movie
import com.roque.meuingresso.domain.model.MovieDto

fun MovieDto.toDomain() = Movie(
    id = id,
    title = title,
    synopsis = synopsis,
    thumbUrl = images.find { it.type == "PosterPortrait" }?.url ?: imageFeatured,
    imageFeatured = this.images.find { it.type == "PosterHorizontal" }?.url ?: "",
    bannerUrl = images.find { it.type == "PosterHorizontal" }?.url ?: imageFeatured,
    genres = genres
)