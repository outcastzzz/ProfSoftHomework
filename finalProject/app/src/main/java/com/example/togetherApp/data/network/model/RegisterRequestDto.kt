package com.example.togetherApp.data.network.model

import com.google.gson.annotations.SerializedName

data class RegisterRequestDto(
    @SerializedName("phone")
    val phone: String,
    @SerializedName("passwordHashed")
    val passwordHashed: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("surname")
    val surname: String,
    @SerializedName("avatar")
    val avatar: String = "",
)
