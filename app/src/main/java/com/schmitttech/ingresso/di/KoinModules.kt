package com.schmitttech.ingresso.di

import androidx.room.Room
import com.schmitttech.ingresso.BuildConfig
import com.schmitttech.ingresso.data.local.IngressoDatabase
import com.schmitttech.ingresso.data.local.dao.MovieDao
import com.schmitttech.ingresso.data.remote.api.IngressoApi
import com.schmitttech.ingresso.data.repository.MoviesRepositoryImpl
import com.schmitttech.ingresso.domain.repository.MoviesRepository
import com.schmitttech.ingresso.domain.usecase.GetComingSoonMoviesUseCase
import com.schmitttech.ingresso.domain.usecase.GetMovieDetailsUseCase
import com.schmitttech.ingresso.domain.usecase.GetPreSaleMoviesUseCase
import com.schmitttech.ingresso.domain.usecase.ToggleFavoriteUseCase
import com.schmitttech.ingresso.presentation.details.DetailsViewModel
import com.schmitttech.ingresso.presentation.favorites.FavoritesViewModel
import com.schmitttech.ingresso.presentation.home.HomeViewModel
import com.schmitttech.ingresso.presentation.presale.PreSaleViewModel
import com.schmitttech.ingresso.data.util.NetworkHelper
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.kotlinx.serialization.asConverterFactory

val networkModule = module {
    single {
        val logging = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
        OkHttpClient.Builder()
            .addInterceptor(logging)
            .build()
    }

    single<Retrofit> {
        val json = Json {
            ignoreUnknownKeys = true
            coerceInputValues = true
        }
        val contentType = "application/json".toMediaType()

        Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .client(get<OkHttpClient>())
            .addConverterFactory(json.asConverterFactory(contentType))
            .build()
    }

    single<IngressoApi> { get<Retrofit>().create(IngressoApi::class.java) }
    single { NetworkHelper(androidContext()) }
}

val localModule = module {
    single<IngressoDatabase> {
        Room.databaseBuilder(
            androidContext(),
            IngressoDatabase::class.java,
            "ingresso_database"
        ).build()
    }
    single<MovieDao> { get<IngressoDatabase>().movieDao() }
}

val dataModule = module {
    single<MoviesRepository> { MoviesRepositoryImpl(get(), get()) }
}

val domainModule = module {
    single { GetComingSoonMoviesUseCase(get()) }
    single { GetMovieDetailsUseCase(get()) }
    single { ToggleFavoriteUseCase(get()) }
    single { GetPreSaleMoviesUseCase(get()) }
}

val presentationModule = module {
    viewModel { HomeViewModel(get(), get(), get()) }
    viewModel { DetailsViewModel(get(), get()) }
    viewModel { FavoritesViewModel(get()) }
    viewModel { PreSaleViewModel(get()) }
}

val appModule = listOf(networkModule, localModule, dataModule, domainModule, presentationModule)
