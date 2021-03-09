package com.st18apps.giphytest.data.network

import com.st18apps.giphytest.data.model.GifResult
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface APIInterface {
    @GET("search")
    suspend fun search(
        @Query("api_key") apiKey: String = KEY,
        @Query("q") query: String?,
        @Query("limit") limit: String = DEFAULT_LIMIT,
        @Query("rating") rating: String = GENERAL
    ): Response<GifResult>

    companion object {
        const val BASE_URL = "https://api.giphy.com/v1/gifs/"
        const val KEY = "n5UNOt8t7Gcn6tOjOmyFBUYxRV8wI00o"
        const val DEFAULT_LIMIT = "100"
        const val GENERAL = "g" //general audiences
    }
}