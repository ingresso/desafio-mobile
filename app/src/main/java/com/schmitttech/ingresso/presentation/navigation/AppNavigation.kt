package com.schmitttech.ingresso.presentation.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.schmitttech.ingresso.R
import com.schmitttech.ingresso.presentation.details.DetailsRoute
import com.schmitttech.ingresso.presentation.details.DetailsViewModel
import com.schmitttech.ingresso.presentation.favorites.FavoritesRoute
import com.schmitttech.ingresso.presentation.favorites.FavoritesViewModel
import com.schmitttech.ingresso.presentation.home.HomeRoute
import com.schmitttech.ingresso.presentation.home.HomeViewModel
import com.schmitttech.ingresso.presentation.presale.PreSaleRoute
import com.schmitttech.ingresso.presentation.presale.PreSaleViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = {
            if (currentDestination?.hierarchy?.any { it.hasRoute<HomeRoute>() || it.hasRoute<PreSaleRoute>() || it.hasRoute<FavoritesRoute>() } == true) {
                AppBottomBar(navController = navController, currentDestination = currentDestination)
            }
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = HomeRoute,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable<HomeRoute> {
                val viewModel: HomeViewModel = koinViewModel()
                HomeRoute(
                    viewModel = viewModel,
                    onMovieClick = { movieId ->
                        navController.navigate(DetailsRoute(movieId))
                    }
                )
            }

            composable<PreSaleRoute> {
                val viewModel: PreSaleViewModel = koinViewModel()
                PreSaleRoute(
                    viewModel = viewModel,
                    onMovieClick = { movieId ->
                        navController.navigate(DetailsRoute(movieId))
                    }
                )
            }

            composable<FavoritesRoute> {
                val viewModel: FavoritesViewModel = koinViewModel()
                FavoritesRoute(
                    viewModel = viewModel,
                    onMovieClick = { movieId ->
                        navController.navigate(DetailsRoute(movieId))
                    }
                )
            }

            composable<DetailsRoute> { backStackEntry ->
                val route: DetailsRoute = backStackEntry.toRoute()
                val viewModel: DetailsViewModel = koinViewModel()
                DetailsRoute(
                    movieId = route.movieId,
                    viewModel = viewModel,
                    onBackClick = {
                        navController.popBackStack()
                    },
                    onMovieClick = { id ->
                        navController.navigate(DetailsRoute(id)) {
                            popUpTo<DetailsRoute> { inclusive = true }
                        }
                    }
                )
            }
        }
    }
}

@Composable
private fun AppBottomBar(
    navController: NavHostController,
    currentDestination: androidx.navigation.NavDestination?
) {
    NavigationBar(
        containerColor = MaterialTheme.colorScheme.surface,
        contentColor = MaterialTheme.colorScheme.primary
    ) {
        val items = listOf(
            BottomNavItem.Home,
            BottomNavItem.PreSale,
            BottomNavItem.Favorites
        )

        items.forEach { item ->
            NavigationBarItem(
                selected = currentDestination?.hierarchy?.any { it.hasRoute(item.route::class) } == true,
                onClick = {
                    navController.navigate(item.route) {
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                },
                icon = { Icon(item.icon, contentDescription = null) },
                label = { Text(stringResource(item.labelRes)) }
            )
        }
    }
}

private sealed class BottomNavItem(
    val route: Any,
    val labelRes: Int,
    val icon: androidx.compose.ui.graphics.vector.ImageVector
) {
    object Home : BottomNavItem(HomeRoute, R.string.movies, Icons.Default.Home)
    object PreSale : BottomNavItem(PreSaleRoute, R.string.pre_sale, Icons.Default.Star)
    object Favorites : BottomNavItem(FavoritesRoute, R.string.favorites, Icons.Default.Favorite)
}
