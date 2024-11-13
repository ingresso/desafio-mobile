package br.com.hectorfortuna.ingressoapp.view.home.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import br.com.hectorfortuna.ingressoapp.core.State
import br.com.hectorfortuna.ingressoapp.data.model.AllMoviesResponse
import br.com.hectorfortuna.ingressoapp.data.repository.AllMoviesRepository
import br.com.hectorfortuna.utils.createComparisonPremiereDate
import br.com.hectorfortuna.utils.createItem
import br.com.hectorfortuna.utils.createPremiereDate
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

@ExperimentalCoroutinesApi
class MovieViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var viewModel: MovieViewModel
    private val repositoryMock: AllMoviesRepository = mock()
    private val testDispatcher = TestCoroutineDispatcher()

    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)
        viewModel = MovieViewModel(repositoryMock, Dispatchers.IO)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
        testDispatcher.cleanupTestCoroutines()
    }

    @Test
    fun `test getAllMoviesResponse success`() = testDispatcher.runBlockingTest {
        val mockResponse = AllMoviesResponse(
            items = listOf(
                createItem(
                    id = "1",
                    title = "Hamlet",
                    premiereDate = createPremiereDate()
                )
            ), count = 1
        )
        whenever(repositoryMock.getAllMovies()).thenReturn(mockResponse)

        val observer: Observer<State<AllMoviesResponse>> = mock()
        viewModel.response.observeForever(observer)
        viewModel.getAllMoviesResponse()
        verify(observer).onChanged(State.loading(true))

        verify(observer).onChanged(State.success(mockResponse))

        verify(repositoryMock).getAllMovies()
    }

    @Test
    fun `test sortItemsByPremiereDate with items having premiereDate`() {
        val items = listOf(
            createItem(id = "1", title = "Hamlet", premiereDate = createComparisonPremiereDate()),
            createItem(id = "2", title = "The Lion King", premiereDate = createPremiereDate())
        )
        val sortedItems = viewModel.sortItemsByPremiereDate(items)
        Assert.assertEquals("The Lion King", sortedItems.first().title)
        Assert.assertEquals("Hamlet", sortedItems.last().title)
    }

    @Test
    fun `test sortItemsByPremiereDate with items without premiereDate`() {
        val items = listOf(
            createItem(id = "1", title = "Hamlet", premiereDate = null),
            createItem(id = "2", title = "The Lion King", premiereDate = null)
        )
        val sortedItems = viewModel.sortItemsByPremiereDate(items)
        Assert.assertEquals("Hamlet", sortedItems.first().title)
        Assert.assertEquals("The Lion King", sortedItems.last().title)
    }
}