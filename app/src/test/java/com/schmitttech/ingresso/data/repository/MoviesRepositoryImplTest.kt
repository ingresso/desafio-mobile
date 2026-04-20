package com.schmitttech.ingresso.data.repository

import android.util.Log
import com.schmitttech.ingresso.data.local.dao.MovieDao
import com.schmitttech.ingresso.data.remote.api.IngressoApi
import com.schmitttech.ingresso.data.remote.model.MovieResponse
import com.schmitttech.ingresso.data.remote.model.MoviesResponse
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.mockk
import io.mockk.mockkStatic
import io.mockk.unmockkStatic
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

class MoviesRepositoryImplTest {

    private val api: IngressoApi = mockk()
    private val dao: MovieDao = mockk()
    private lateinit var repository: MoviesRepositoryImpl

    @Before
    fun setup() {
        mockkStatic(Log::class)
        every { Log.d(any(), any()) } returns 0
        every { Log.e(any(), any()) } returns 0
        every { Log.e(any(), any(), any()) } returns 0
        repository = MoviesRepositoryImpl(api, dao)
    }

    @After
    fun tearDown() {
        unmockkStatic(Log::class)
    }

    @Test
    fun `refreshMovies should fetch from API and sync with DAO`() = runTest {
        // Given
        val apiMovies = listOf(
            mockMovieResponse("1"),
            mockMovieResponse("2")
        )
        coEvery { api.getComingSoonMovies() } returns MoviesResponse(apiMovies)
        every { dao.observeFavorites() } returns flowOf(emptyList())
        coEvery { dao.deleteStaleMovies(any()) } returns Unit
        coEvery { dao.upsertAll(any()) } returns Unit

        // When
        val result = repository.refreshMovies()

        // Then
        assertTrue(result.isSuccess)
        coVerify { dao.deleteStaleMovies(listOf("1", "2")) }
        coVerify { dao.upsertAll(any()) }
    }

    @Test
    fun `refreshMovies should handle API errors gracefully`() = runTest {
        // Given
        coEvery { api.getComingSoonMovies() } throws Exception("API Error")

        // When
        val result = repository.refreshMovies()

        // Then
        assertTrue(result.isFailure)
    }

    private fun mockMovieResponse(id: String) = MovieResponse(
        id = id,
        title = "Title $id",
        images = emptyList(),
        genres = emptyList(),
        premiereDate = null,
        inPreSale = false,
        synopsis = null,
        duration = null,
        ratingDetails = null
    )
}
