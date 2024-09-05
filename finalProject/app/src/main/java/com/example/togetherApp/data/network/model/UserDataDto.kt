package com.example.togetherApp.data.network.model

import com.google.gson.annotations.SerializedName

data class UserDataDto(
    @SerializedName("phone")
    val phone: String,
    @SerializedName("passwordHashed")
    val passwordHashed: String
)
