package com.example.togetherApp.data.network.model

import com.google.gson.annotations.SerializedName

data class AuthorDto(
    @SerializedName("id")
    val id: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("surname")
    val surname: String,
    @SerializedName("avatar")
    val avatar: String,
    @SerializedName("role")
    val role: Int
)