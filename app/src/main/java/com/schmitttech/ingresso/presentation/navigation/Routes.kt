package com.schmitttech.ingresso.presentation.navigation

import kotlinx.serialization.Serializable

@Serializable
data object HomeRoute

@Serializable
data object PreSaleRoute

@Serializable
data object FavoritesRoute

@Serializable
data class DetailsRoute(
    val movieId: String
)
