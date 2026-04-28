package com.roque.meuingresso.data.repository

import app.cash.turbine.test
import com.roque.meuingresso.data.model.MovieResponse
import com.roque.meuingresso.data.source.remote.IngressoApiService
import com.roque.meuingresso.domain.model.ImageDto
import com.roque.meuingresso.domain.model.MovieDto
import io.mockk.coEvery
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.test.runTest
import okhttp3.ResponseBody.Companion.toResponseBody
import org.junit.Before
import org.junit.Test
import retrofit2.Response

class MovieRepositoryImplTest {

    private val apiService: IngressoApiService = mockk()
    private lateinit var repository: MovieRepositoryImpl

    @Before
    fun setup() {
        repository = MovieRepositoryImpl(apiService)
    }

    @Test
    fun getMovies_should_emit_list_of_movies_when_api_returns_success() = runTest {
        val mockDto = MovieDto(
            id = "22021993",
            title = "O Diabo no banco dos reus",
            synopsis = "Em seu julgamento ele aparece !!!",
            imageFeatured = "url_featured",
            genres = listOf("Comédia", "Drama"),
            images = listOf(
                ImageDto(url = "url_portrait", type = "PosterPortrait"),
                ImageDto(url = "url_horizontal", type = "PosterHorizontal")
            )
        )

        val mockResponse = MovieResponse(items = listOf(mockDto))

        coEvery { apiService.getMovies() } returns Response.success(mockResponse)

        repository.getMovies().test {
            val result = awaitItem()

            assertEquals(1, result.size)
            assertEquals("O Diabo no banco dos reus", result[0].title)
            assertEquals("url_portrait", result[0].thumbUrl)

            awaitComplete()
        }
    }

    @Test
    fun getMovies_should_throw_exception_when_api_returns_error() = runTest {

        coEvery { apiService.getMovies() } returns Response.error(404, "".toResponseBody())

        repository.getMovies().test {
            val error = awaitError()
            assert(error is Exception)
            assert(error.message?.contains("404") == true)
        }
    }
}