package com.gabrielribeiro.desafio_mobile.di

import com.gabrielribeiro.desafio_mobile.repositories.MovieRepository
import com.gabrielribeiro.desafio_mobile.repositories.MovieRepositoryImplement
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class ModulesAbstract {

    @Singleton
    @Binds
    abstract fun bindMovieRepository(repositoryImplement: MovieRepositoryImplement) : MovieRepository
}