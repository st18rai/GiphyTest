package com.st18apps.giphytest

import android.app.Application
import com.st18apps.giphytest.di.giphyModule
import com.st18apps.giphytest.di.networkModule
import com.st18apps.giphytest.di.viewModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(applicationContext)
            modules(listOf(giphyModule, networkModule, viewModule))
        }
    }
}