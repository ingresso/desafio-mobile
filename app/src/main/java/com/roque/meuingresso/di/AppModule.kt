package com.roque.meuingresso.di

import com.roque.meuingresso.data.repository.MovieRepositoryImpl
import com.roque.meuingresso.data.source.remote.IngressoApiService
import com.roque.meuingresso.domain.repository.MovieRepository
import com.roque.meuingresso.ui.features.details.MovieDetailsViewModel
import com.roque.meuingresso.ui.features.movielist.MovieListViewModel
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val networkModule = module {
    single {
        HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
    }

    single {
        OkHttpClient.Builder()
            .addInterceptor(get<HttpLoggingInterceptor>())
            .build()
    }

    single {
        Retrofit.Builder()
            .baseUrl("https://api-content.ingresso.com/")
            .client(get())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    single { get<Retrofit>().create(IngressoApiService::class.java) }
}

val repositoryModule = module {
    single<MovieRepository> { MovieRepositoryImpl(apiService = get()) }
}

val viewModelModule = module {
    viewModel { MovieListViewModel(repository = get()) }
    viewModel { MovieDetailsViewModel(repository = get()) }
}