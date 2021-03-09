package com.st18apps.giphytest.data.repository

import com.st18apps.giphytest.data.network.RemoteDataSource

class Repository(private val remoteSource: RemoteDataSource) {
    suspend fun search(query: String) = remoteSource.search(query = query).data
}