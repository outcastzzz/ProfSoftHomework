package com.example.togetherApp.data.local.database.models.common

import androidx.room.Entity

@Entity
data class AuthorDbo(
    val id: String,
    val name: String,
    val surname: String,
    val avatar: String,
    val role: Int,
)