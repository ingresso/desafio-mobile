package com.roque.meuingresso.data.repository

import com.roque.meuingresso.data.movie.toDomain
import com.roque.meuingresso.data.source.remote.IngressoApiService
import com.roque.meuingresso.domain.model.Movie
import com.roque.meuingresso.domain.repository.MovieRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class MovieRepositoryImpl(
    private val apiService: IngressoApiService
) : MovieRepository {
    override fun getMovies(): Flow<List<Movie>> = flow {
        val response = apiService.getMovies()
        if (response.isSuccessful) {
            val movies = response.body()?.items?.map { it.toDomain() } ?: emptyList()
            emit(movies)
        } else {
            throw Exception("Erro na busca dos filmes: ${response.code()}")
        }
    }.flowOn(Dispatchers.IO)
}