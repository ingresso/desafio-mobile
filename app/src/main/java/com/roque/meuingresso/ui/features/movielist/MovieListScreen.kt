package com.roque.meuingresso.ui.features.movielist

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.roque.meuingresso.ui.features.movielist.components.MovieListContent
import org.koin.androidx.compose.koinViewModel

@Composable
fun MovieListScreen(
    onMovieClick: (String) -> Unit,
    viewModel: MovieListViewModel = koinViewModel()
) {

    val state by viewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        viewModel.handleIntent(MovieListContract.Intent.LoadMovies)
    }

    MovieListContent(
        state = state,
        onMovieClick = { id ->
            viewModel.handleIntent(MovieListContract.Intent.OnMovieClicked(id))
        }
    )

    LaunchedEffect(Unit) {
        viewModel.effect.collect { effect ->
            when (effect) {
                is MovieListContract.Effect.NavigateToDetails -> {
                    onMovieClick(effect.movieId)
                }
                is MovieListContract.Effect.ShowError -> {
                    //TODO("Em caso de erro apresentar snackbar")
                }
            }
        }
    }

    Scaffold(
    ) { innerPadding ->
        MovieListContent(
            state = state,
            modifier = Modifier.padding(innerPadding),
            onMovieClick = { movie ->
                viewModel.handleIntent(MovieListContract.Intent.OnMovieClicked(movie))
            }
        )
    }
}