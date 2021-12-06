package com.jeanbarrossilva.ingresso.network.dto

data class MovieDto(
    val id: String,
    val title: String,
    val originalTitle: String,
    val countryOrigin: String,
    val synopsis: String,
    val cast: String,
    val director: String,
    val distributor: String,
    val premiereDate: PremiereDateDto?,
    val siteURL: String,
    val images: Pair<Image, Image>,
    val genres: List<String>,
    val rottenTomatoe: RottenTomatoe?
)