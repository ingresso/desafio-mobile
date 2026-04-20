package com.schmitttech.ingresso.presentation.details.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.schmitttech.ingresso.R
import com.schmitttech.ingresso.domain.model.Movie
import com.schmitttech.ingresso.presentation.common.MovieCard
import com.schmitttech.ingresso.presentation.common.RatingBadge
import com.schmitttech.ingresso.ui.util.DateFormatter

@Composable
fun MovieDetailLayout(
    movie: Movie,
    relatedMovies: List<Movie>,
    onMovieClick: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    val scrollState = rememberScrollState()

    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
            .padding(vertical = 16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp),
            verticalAlignment = Alignment.Top,
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Box(
                modifier = Modifier
                    .weight(0.5f)
                    .aspectRatio(2f / 3f)
                    .clip(RoundedCornerShape(12.dp))
            ) {
                if (movie.posterUrl != null) {
                    AsyncImage(
                        model = movie.posterUrl,
                        contentDescription = movie.title,
                        modifier = Modifier.fillMaxSize(),
                        contentScale = ContentScale.Crop
                    )
                } else {
                    Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                        Text(
                            text = movie.title.first().uppercase(),
                            style = MaterialTheme.typography.displayMedium,
                            fontWeight = FontWeight.ExtraBold
                        )
                    }
                }
            }

            Column(modifier = Modifier.weight(0.5f), verticalArrangement = Arrangement.spacedBy(8.dp)) {
                RatingBadge(rating = movie.rating)

                if (!movie.duration.isNullOrBlank()) {
                    DetailSection(label = stringResource(R.string.duration), content = stringResource(R.string.duration_minutes, movie.duration))
                }

                val date = DateFormatter.format(movie.premiereDate)
                if (date.isNotEmpty()) {
                    DetailSection(label = stringResource(R.string.premiere), content = date)
                }

                if (movie.genres.isNotEmpty()) {
                    DetailSection(label = stringResource(R.string.genres), content = movie.genres.joinToString(", "))
                }

                if (!movie.rating?.description.isNullOrBlank()) {
                    DetailSection(label = stringResource(R.string.rating), content = movie.rating.description)
                }
            }
        }

        if (movie.synopsis.isNotBlank()) {
            Box(Modifier.padding(horizontal = 16.dp)) {
                DetailSection(label = stringResource(R.string.synopsis), content = movie.synopsis)
            }
        }

        if (relatedMovies.isNotEmpty()) {
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = stringResource(R.string.related_movies),
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(horizontal = 16.dp)
            )
            LazyRow(
                contentPadding = PaddingValues(horizontal = 16.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                items(relatedMovies, key = { it.id }) { relatedMovie ->
                    MovieCard(
                        movie = relatedMovie,
                        onClick = { onMovieClick(relatedMovie.id) },
                        modifier = Modifier.width(160.dp)
                    )
                }
            }
        }
    }
}

@Composable
private fun DetailSection(label: String, content: String) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(2.dp)
    ) {
        Text(
            text = label,
            style = MaterialTheme.typography.labelMedium,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = content,
            style = MaterialTheme.typography.bodySmall
        )
    }
}
