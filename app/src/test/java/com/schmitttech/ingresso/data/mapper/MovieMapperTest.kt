package com.schmitttech.ingresso.data.mapper

import com.schmitttech.ingresso.data.remote.model.ImageResponse
import com.schmitttech.ingresso.data.remote.model.MovieResponse
import com.schmitttech.ingresso.data.remote.model.PremiereDateResponse
import com.schmitttech.ingresso.data.remote.model.RatingDetailsResponse
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Test
import java.time.OffsetDateTime

class MovieMapperTest {

    @Test
    fun `toDomain should correctly map MovieResponse to Movie`() {
        // Given
        val movieResponse = MovieResponse(
            id = "1",
            title = "Test Movie",
            inPreSale = true,
            synopsis = "Awesome synopsis",
            genres = listOf("Action", "Drama"),
            duration = "120",
            premiereDate = PremiereDateResponse(
                localDate = "2026-04-18T00:00:00+00:00"
            ),
            images = listOf(
                ImageResponse(url = "horizontal.jpg", type = "PosterHorizontal"),
                ImageResponse(url = "portrait.jpg", type = "PosterPortrait")
            ),
            ratingDetails = RatingDetailsResponse(
                label = "14",
                color = "#FF0000",
                description = "Violence"
            )
        )

        // When
        val movie = movieResponse.toDomain()

        // Then
        assertEquals("1", movie.id)
        assertEquals("Test Movie", movie.title)
        assertEquals(true, movie.inPreSale)
        assertEquals("Awesome synopsis", movie.synopsis)
        assertEquals(listOf("Action", "Drama"), movie.genres)
        assertEquals("120", movie.duration)
        assertEquals("portrait.jpg", movie.posterUrl)
        assertEquals(OffsetDateTime.parse("2026-04-18T00:00:00+00:00"), movie.premiereDate)
        assertEquals("14", movie.rating?.label)
        assertEquals("#FF0000", movie.rating?.color)
    }

    @Test
    fun `toDomain should handle null values gracefully`() {
        // Given
        val movieResponse = MovieResponse(
            id = "2",
            title = "Simple Movie",
            inPreSale = false
        )

        // When
        val movie = movieResponse.toDomain()

        // Then
        assertEquals("2", movie.id)
        assertNull(movie.premiereDate)
        assertNull(movie.posterUrl)
        assertNull(movie.rating)
        assertEquals("", movie.synopsis)
    }
}
