package com.st18apps.giphytest.di

import com.st18apps.giphytest.data.network.APIInterface
import com.st18apps.giphytest.data.network.RemoteDataSource
import com.st18apps.giphytest.data.repository.Repository
import org.koin.dsl.module

val giphyModule = module {

    single { provideRetrofit(okHttpClient = get(), APIInterface.BASE_URL) }

    factory { createWebService<APIInterface>(retrofit = get()) }

    single { RemoteDataSource(apiInterface = get()) }

    single { Repository(remoteSource = get()) }
}