package com.example.togetherApp.data.network.model

import com.google.gson.annotations.SerializedName

data class PublishNoteDto(
    @SerializedName("title")
    val title: String,
    @SerializedName("content")
    val content: ContentDto
)