package com.roque.meuingresso.ui.components.navigation

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.roque.meuingresso.ui.navigation.BottomNavItem
import com.roque.meuingresso.ui.navigation.Screen

@Composable
fun MainBottomNavigation(navController: NavController) {
    NavigationBar(
        containerColor = Color(0xFF121212),
        tonalElevation = 8.dp
    ) {
        BottomNavItem.items.forEach { item ->
            val isMovies = item.route == Screen.MovieList.route
            val isSelected = isMovies

            NavigationBarItem(
                icon = {
                    Icon(
                        imageVector = item.icon,
                        contentDescription = item.label
                    )
                },
                label = { Text(text = item.label) },
                selected = isSelected,
                onClick = {
                    println("Clique ignorado para manter a aba fixa")
                },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = Color(0xFFFF8C00),
                    selectedTextColor = Color(0xFFFF8C00),
                    unselectedIconColor = Color.Gray,
                    unselectedTextColor = Color.Gray,
                    indicatorColor = Color.Transparent
                )
            )
        }
    }
}