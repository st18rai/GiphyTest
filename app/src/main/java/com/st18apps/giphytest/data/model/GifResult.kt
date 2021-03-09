package com.st18apps.giphytest.data.model

import com.google.gson.annotations.SerializedName

data class GifResult(
    @SerializedName("data")
    val gifData: List<Data>
)