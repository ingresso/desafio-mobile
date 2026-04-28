package com.roque.meuingresso.domain.model

data class Movie(
    val id: String,
    val title: String,
    val synopsis: String,
    val thumbUrl: String,
    val imageFeatured: String,
    val bannerUrl: String,
    val genres: List<String>
)
