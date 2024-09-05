package com.example.togetherApp.data.local.database.models.common

import androidx.room.Entity

@Entity
data class ContentDbo(
    val text: String,
    val image: String,
)
