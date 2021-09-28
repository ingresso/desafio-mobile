package com.gabrielribeiro.desafio_mobile.di

import android.content.Context
import androidx.room.Room
import com.gabrielribeiro.desafio_mobile.data.local.database.MovieDatabase
import com.gabrielribeiro.desafio_mobile.data.remote.api.MovieAPi
import com.gabrielribeiro.desafio_mobile.utils.Constants
import com.gabrielribeiro.desafio_mobile.utils.Constants.Companion.MOVIE_DATABASE_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ModulesProvide {

    @Singleton
    @Provides
    fun provideMovieDatabase(@ApplicationContext context: Context) = Room.databaseBuilder(
        context, MovieDatabase::class.java, MOVIE_DATABASE_NAME
    ).build()

    @Singleton
    @Provides
    fun provideMovieApi() : MovieAPi {
        val logging = HttpLoggingInterceptor()
        logging.setLevel(HttpLoggingInterceptor.Level.BODY)
        val client = OkHttpClient.Builder()
            .addInterceptor(logging)
            .build()

        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .baseUrl(Constants.BASE_URL)
            .client(client)
            .build()
            .create(MovieAPi::class.java)
    }

}