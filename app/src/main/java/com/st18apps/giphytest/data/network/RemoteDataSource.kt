package com.st18apps.giphytest.data.network

import com.st18apps.giphytest.base.BaseDataSource

class RemoteDataSource(private val apiInterface: APIInterface) : BaseDataSource() {

    suspend fun search(query: String?) = getResult {
        apiInterface.search(query = query)
    }
}