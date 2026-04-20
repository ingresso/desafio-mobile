package com.schmitttech.ingresso.data.remote.model

import kotlinx.serialization.Serializable

@Serializable
data class MoviesResponse(
    val items: List<MovieResponse>
)

@Serializable
data class MovieResponse(
    val id: String,
    val title: String,
    val inPreSale: Boolean,
    val premiereDate: PremiereDateResponse? = null,
    val images: List<ImageResponse> = emptyList(),
    val synopsis: String? = null,
    val cast: String? = null,
    val director: String? = null,
    val duration: String? = null,
    val genres: List<String> = emptyList(),
    val ratingDetails: RatingDetailsResponse? = null
)

@Serializable
data class PremiereDateResponse(
    val localDate: String? = null,
    val dayAndMonth: String? = null,
    val year: String? = null,
    val dayOfWeek: String? = null
)

@Serializable
data class ImageResponse(
    val url: String,
    val type: String
)

@Serializable
data class RatingDetailsResponse(
    val name: String? = null,
    val label: String? = null,
    val description: String? = null,
    val color: String? = null
)
