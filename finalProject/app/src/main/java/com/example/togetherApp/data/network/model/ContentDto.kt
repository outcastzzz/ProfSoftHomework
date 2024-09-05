package com.example.togetherApp.data.network.model

import com.google.gson.annotations.SerializedName

data class ContentDto(
    @SerializedName("text")
    val text: String,
    @SerializedName("image")
    val image: String
)