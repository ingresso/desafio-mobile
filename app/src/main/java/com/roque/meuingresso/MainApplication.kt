package com.roque.meuingresso

import android.app.Application
import com.roque.meuingresso.di.networkModule
import com.roque.meuingresso.di.repositoryModule
import com.roque.meuingresso.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class MainApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@MainApplication)
            modules(listOf(networkModule, repositoryModule, viewModelModule))
        }
    }
}