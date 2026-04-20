package com.schmitttech.ingresso.presentation.common

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.schmitttech.ingresso.domain.model.Movie
import com.schmitttech.ingresso.ui.theme.Cream
import com.schmitttech.ingresso.ui.theme.Green
import com.schmitttech.ingresso.ui.theme.Noir
import com.schmitttech.ingresso.ui.theme.White
import com.schmitttech.ingresso.ui.theme.WhiteAlpha90
import com.schmitttech.ingresso.ui.util.DateFormatter

/**
 * Movie card following the Noir Cinema design:
 * - Full-bleed poster image
 * - Cream (#FFF1D7) overlay panel at the bottom with rounded top corners
 * - Rating badge, genre chips, title, synopsis snippet and premiere date
 */
@OptIn(ExperimentalLayoutApi::class)
@Composable
fun MovieCard(
    movie: Movie,
    onClick: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .aspectRatio(2f / 3f)
            .clip(RoundedCornerShape(16.dp))
            .clickable { onClick(movie.id) }
    ) {
        if (movie.posterUrl != null) {
            AsyncImage(
                model = movie.posterUrl,
                contentDescription = movie.title,
                modifier = Modifier.fillMaxWidth(),
                contentScale = ContentScale.Crop
            )
        } else {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(White),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = movie.title.first().uppercase(),
                    fontSize = 64.sp,
                    color = White,
                    fontWeight = FontWeight.ExtraBold
                )
            }
        }

        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
                .align(Alignment.BottomCenter),
            shape = RoundedCornerShape(12.dp),
            color = WhiteAlpha90,
            tonalElevation = 0.dp,
            shadowElevation = 0.dp
        ) {
            Column(
                modifier = Modifier.padding(8.dp),
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {

                Text(
                    text = movie.title,
                    style = MaterialTheme.typography.labelLarge,
                    fontWeight = FontWeight.ExtraBold,
                    color = Noir,
                    maxLines = 2,
                    lineHeight = 18.sp,
                    overflow = TextOverflow.Ellipsis
                )

                if (movie.synopsis.isNotBlank()) {
                    Text(
                        text = movie.synopsis,
                        style = MaterialTheme.typography.bodySmall,
                        color = Noir,
                        maxLines = 3,
                        overflow = TextOverflow.Ellipsis,
                        lineHeight = 14.sp
                    )
                }

                val date = DateFormatter.format(movie.premiereDate)
                if (date.isNotEmpty()) {
                    Spacer(Modifier.height(4.dp))
                    Text(
                        text = date,
                        style = MaterialTheme.typography.labelLarge,
                        fontWeight = FontWeight.SemiBold,
                        color = Cream,
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(Green, RoundedCornerShape(100))
                            .padding(horizontal = 8.dp, vertical = 4.dp)
                    )
                }
            }
        }
    }
}
