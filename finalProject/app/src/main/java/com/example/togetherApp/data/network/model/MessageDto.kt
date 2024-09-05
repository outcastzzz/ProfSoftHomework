package com.example.togetherApp.data.network.model

import com.google.gson.annotations.SerializedName

data class MessageDto(
    @SerializedName("text")
    val text: String
)
