package com.roque.meuingresso.ui.features.movielist.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.roque.meuingresso.ui.components.MovieSection
import com.roque.meuingresso.ui.features.movielist.MovieListContract

@Composable
fun MovieListContent(
    state: MovieListContract.State,
    onMovieClick: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .windowInsetsPadding(WindowInsets.statusBars),
        contentAlignment = Alignment.Center
    ) {
        when {
            state.isLoading -> CircularProgressIndicator()
            state.error != null -> Text(text = state.error, color = Color.Red)
            state.movies.isEmpty() -> Text(text = "Nenhum filme foi encontrado.")
            else -> {
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    contentPadding = PaddingValues(bottom = 16.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    item {
                        val estreias = state.movies.take(4)
                        MovieSection(
                            title = "Estreias em breve",
                            movies = estreias,
                            onMovieClick = onMovieClick
                        )
                    }

                    val categorias = state.movies.groupBy { it.genres.firstOrNull() ?: "Diversos" }

                    categorias.forEach { (nome, filmes) ->
                        item {
                            MovieSection(
                                title = nome,
                                movies = filmes,
                                onMovieClick = onMovieClick
                            )
                        }
                    }
                }
            }
        }
    }
}