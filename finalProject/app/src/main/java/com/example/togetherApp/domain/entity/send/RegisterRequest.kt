package com.example.togetherApp.domain.entity.send

data class RegisterRequest(
    val phone: String,
    val passwordHashed: String,
    val name: String,
    val surname: String,
    val avatar: String = "",
)