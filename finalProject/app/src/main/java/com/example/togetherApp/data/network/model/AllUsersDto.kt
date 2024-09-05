package com.example.togetherApp.data.network.model

import com.google.gson.annotations.SerializedName

data class AllUsersDto(
    @SerializedName("data")
    val data: List<AllUsersDataDto>
)

data class AllUsersDataDto(
    @SerializedName("id")
    val id: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("surname")
    val surname: String,
    @SerializedName("avatar")
    val avatar: String
)