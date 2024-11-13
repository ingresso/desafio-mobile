package br.com.hectorfortuna.ingressoapp.di.modules

import br.com.hectorfortuna.ingressoapp.data.repository.AllMoviesRepository
import br.com.hectorfortuna.ingressoapp.data.repository.AllMoviesRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindCharacterRepository(
        characterRepositoryImpl: AllMoviesRepositoryImpl
    ): AllMoviesRepository
}