package com.schmitttech.ingresso.presentation.home

import android.util.Log
import com.schmitttech.ingresso.data.util.NetworkHelper
import com.schmitttech.ingresso.domain.model.Movie
import com.schmitttech.ingresso.domain.repository.MoviesRepository
import com.schmitttech.ingresso.domain.usecase.GetComingSoonMoviesUseCase
import io.mockk.called
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import io.mockk.mockkStatic
import io.mockk.unmockkStatic
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class HomeViewModelTest {

    private val useCase: GetComingSoonMoviesUseCase = mockk()
    private val repository: MoviesRepository = mockk()
    private val networkHelper: NetworkHelper = mockk()
    private lateinit var viewModel: HomeViewModel
    private val testDispatcher = UnconfinedTestDispatcher()

    @Before
    fun setup() {
        mockkStatic(Log::class)
        every { Log.d(any(), any()) } returns 0
        every { Log.e(any(), any()) } returns 0
        every { Log.e(any(), any(), any()) } returns 0
        every { networkHelper.isOnline() } returns true
        Dispatchers.setMain(testDispatcher)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
        unmockkStatic(Log::class)
    }

    @Test
    fun `when created then should transition to success state with movies`() = runTest {
        val movies = listOf(mockMovie("1"), mockMovie("2"))
        every { useCase() } returns flowOf(movies)
        coEvery { repository.refreshMovies() } returns Result.success(Unit)

        viewModel = HomeViewModel(useCase, repository, networkHelper)

        val collectJob = launch(UnconfinedTestDispatcher(testScheduler)) { 
            viewModel.uiState.collect() 
        }
        advanceUntilIdle()

        val state = viewModel.uiState.value
        assertTrue(state is HomeUiState.Success)
        assertEquals(2, (state as HomeUiState.Success).movies.size)

        collectJob.cancel()
    }

    @Test
    fun `when search query changes then should filter movies`() = runTest {
        val movies = listOf(mockMovie("1", title = "Batman"), mockMovie("2", title = "Spider-Man"))
        every { useCase() } returns flowOf(movies)
        coEvery { repository.refreshMovies() } returns Result.success(Unit)

        viewModel = HomeViewModel(useCase, repository, networkHelper)
        val collectJob = launch(UnconfinedTestDispatcher(testScheduler)) { 
            viewModel.uiState.collect() 
        }
        advanceUntilIdle()

        viewModel.toggleSearch()
        viewModel.onSearchQueryChange("Bat")
        advanceUntilIdle()

        val state = viewModel.uiState.value as HomeUiState.Success
        assertEquals(1, state.movies.size)
        assertEquals("Batman", state.movies.first().title)

        collectJob.cancel()
    }

    @Test
    fun `when genre selected then should filter movies`() = runTest {
        val movies = listOf(
            mockMovie("1", genres = listOf("Action")),
            mockMovie("2", genres = listOf("Drama"))
        )
        every { useCase() } returns flowOf(movies)
        coEvery { repository.refreshMovies() } returns Result.success(Unit)

        viewModel = HomeViewModel(useCase, repository, networkHelper)
        val collectJob = launch(UnconfinedTestDispatcher(testScheduler)) { 
            viewModel.uiState.collect() 
        }
        advanceUntilIdle()

        viewModel.onGenreSelected("Action")
        advanceUntilIdle()

        val state = viewModel.uiState.value as HomeUiState.Success
        assertEquals(1, state.movies.size)
        assertEquals("Action", state.movies.first().genres.first())

        collectJob.cancel()
    }

    @Test
    fun `extracted genres should be unique and sorted`() = runTest {
        val movies = listOf(
            mockMovie("1", genres = listOf("Drama", "Action")),
            mockMovie("2", genres = listOf("Action", "Comedy"))
        )
        every { useCase() } returns flowOf(movies)
        coEvery { repository.refreshMovies() } returns Result.success(Unit)

        viewModel = HomeViewModel(useCase, repository, networkHelper)
        val collectJob = launch(UnconfinedTestDispatcher(testScheduler)) { 
            viewModel.uiState.collect() 
        }
        advanceUntilIdle()

        val state = viewModel.uiState.value as HomeUiState.Success
        assertEquals(listOf("Action", "Comedy", "Drama"), state.genres)

        collectJob.cancel()
    }

    @Test
    fun `refreshMovies failure should update error state if movies empty`() = runTest {
        every { useCase() } returns flowOf(emptyList())
        coEvery { repository.refreshMovies() } returns Result.failure(Exception("Generic Error"))

        viewModel = HomeViewModel(useCase, repository, networkHelper)
        val collectJob = launch(UnconfinedTestDispatcher(testScheduler)) { 
            viewModel.uiState.collect() 
        }
        advanceUntilIdle()

        val state = viewModel.uiState.value
        assertTrue(state is HomeUiState.Error)
        assertEquals("Generic Error", (state as HomeUiState.Error).message)

        collectJob.cancel()
    }

    @Test
    fun `refreshMovies failure should NOT update error state if movies exist`() = runTest {
        val movies = listOf(mockMovie("1"))
        every { useCase() } returns flowOf(movies)
        coEvery { repository.refreshMovies() } returns Result.failure(Exception("Network Error"))

        viewModel = HomeViewModel(useCase, repository, networkHelper)
        val collectJob = launch(UnconfinedTestDispatcher(testScheduler)) { 
            viewModel.uiState.collect() 
        }
        advanceUntilIdle()

        val state = viewModel.uiState.value
        assertTrue(state is HomeUiState.Success)
        assertEquals(1, (state as HomeUiState.Success).movies.size)

        collectJob.cancel()
    }

    @Test
    fun `refreshMovies should NOT be called when offline`() = runTest {
        every { useCase() } returns flowOf(emptyList())
        every { networkHelper.isOnline() } returns false

        viewModel = HomeViewModel(useCase, repository, networkHelper)
        val collectJob = launch(UnconfinedTestDispatcher(testScheduler)) { 
            viewModel.uiState.collect() 
        }
        advanceUntilIdle()

        val state = viewModel.uiState.value
        assertTrue(state is HomeUiState.Error)
        assertEquals("No internet connection", (state as HomeUiState.Error).message)

        io.mockk.verify { repository wasNot called }

        collectJob.cancel()
    }

    private fun mockMovie(
        id: String,
        title: String = "Movie $id",
        genres: List<String> = emptyList()
    ) = Movie(
        id = id,
        title = title,
        posterUrl = null,
        premiereDate = null,
        inPreSale = false,
        synopsis = "",
        genres = genres,
        duration = null,
        rating = null
    )
}
