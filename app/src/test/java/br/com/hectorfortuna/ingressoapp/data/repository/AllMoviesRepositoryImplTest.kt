package br.com.hectorfortuna.ingressoapp.data.repository

import br.com.hectorfortuna.ingressoapp.data.model.AllMoviesResponse
import br.com.hectorfortuna.ingressoapp.data.network.Service
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

class AllMoviesRepositoryImplTest {
    private lateinit var repository: AllMoviesRepositoryImpl
    private val serviceMock: Service = mock()
    @Before
    fun setup() {
        repository = AllMoviesRepositoryImpl(serviceMock)
    }

    @Test
    fun `test getAllMovies success`(): Unit = runBlocking {
        val mockResponse = AllMoviesResponse(items = emptyList(), count = 0)
        whenever(serviceMock.getAllMovies()).thenReturn(mockResponse)

        val result = repository.getAllMovies()

        assertEquals(mockResponse, result)

        verify(serviceMock).getAllMovies()
    }

    @Test(expected = Exception::class)
    fun `test getAllMovies throws exception`(): Unit = runBlocking {
        whenever(serviceMock.getAllMovies()).thenThrow(Exception("Network error"))
        repository.getAllMovies()
    }
}