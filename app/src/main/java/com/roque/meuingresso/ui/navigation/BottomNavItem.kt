package com.roque.meuingresso.ui.navigation


import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Movie
import androidx.compose.material.icons.filled.Newspaper
import androidx.compose.material.icons.filled.Shield
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.Theaters
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomNavItem(
    val route: String,
    val icon: ImageVector,
    val label: String
) {
    object Highlights : BottomNavItem("highlights", Icons.Default.Star, "Destaques")

    object Movies : BottomNavItem(
        route = Screen.MovieList.route,
        icon = Icons.Default.Movie,
        label = "Filmes"
    )

    object Cinemas : BottomNavItem("cinemas", Icons.Default.Theaters, "Cinemas")

    object News : BottomNavItem("news", Icons.Default.Newspaper, "Notícias")

    object Protection : BottomNavItem("protection", Icons.Default.Shield, "Prevenções")

    companion object {
        val items = listOf(
            Highlights,
            Movies,
            Cinemas,
            News,
            Protection
        )
    }
}