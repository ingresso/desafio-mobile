package com.schmitttech.ingresso.domain.usecase

import com.schmitttech.ingresso.domain.model.Movie
import com.schmitttech.ingresso.domain.repository.MoviesRepository
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test
import java.time.OffsetDateTime

class GetComingSoonMoviesUseCaseTest {

    private val repository: MoviesRepository = mockk()
    private val useCase = GetComingSoonMoviesUseCase(repository)

    @Test
    fun `invoke should return movies sorted by premiereDate with nulls last`() = runTest {
        // Given
        val movie1 = mockMovie(id = "1", date = "2026-05-01T00:00:00+00:00")
        val movie2 = mockMovie(id = "2", date = "2026-04-15T00:00:00+00:00")
        val movie3 = mockMovie(id = "3", date = null)

        every { repository.observeMovies() } returns flowOf(listOf(movie1, movie2, movie3))

        // When
        val result = useCase().first()

        // Then
        assertEquals("2", result[0].id)
        assertEquals("1", result[1].id)
        assertEquals("3", result[2].id)
    }

    private fun mockMovie(id: String, date: String?): Movie {
        return Movie(
            id = id,
            title = "Movie $id",
            posterUrl = null,
            premiereDate = date?.let { OffsetDateTime.parse(it) },
            inPreSale = false,
            synopsis = "",
            genres = emptyList(),
            duration = null,
            rating = null
        )
    }
}
