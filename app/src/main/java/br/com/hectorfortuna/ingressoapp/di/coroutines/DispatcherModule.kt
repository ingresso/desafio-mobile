package br.com.hectorfortuna.ingressoapp.di.coroutines

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import br.com.hectorfortuna.ingressoapp.di.coroutines.qualifiers.Default
import br.com.hectorfortuna.ingressoapp.di.coroutines.qualifiers.Io
import br.com.hectorfortuna.ingressoapp.di.coroutines.qualifiers.Main
import br.com.hectorfortuna.ingressoapp.di.coroutines.qualifiers.Unconfined
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DispatcherModule{

    @Singleton
    @Provides
    @Io
    fun ioDispatcher(): CoroutineDispatcher = Dispatchers.IO

    @Singleton
    @Provides
    @Default
    fun defaultDispatcher(): CoroutineDispatcher = Dispatchers.Default

    @Singleton
    @Provides
    @Main
    fun mainDispatcher(): CoroutineDispatcher = Dispatchers.Main

    @Singleton
    @Provides
    @Unconfined
    fun unconfinedDispatcher(): CoroutineDispatcher = Dispatchers.Unconfined
}
