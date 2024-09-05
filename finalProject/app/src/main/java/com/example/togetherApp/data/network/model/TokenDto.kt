package com.example.togetherApp.data.network.model

import com.google.gson.annotations.SerializedName

data class TokenDto(
    @SerializedName("data")
    val data: DataDto
)

data class DataDto(
    @SerializedName("token")
    val token: String,
)