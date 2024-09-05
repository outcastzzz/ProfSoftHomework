package com.example.togetherApp.data.network.model

import com.google.gson.annotations.SerializedName

data class PublishCourseDto(
    @SerializedName("title")
    val title: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("tags")
    val tags: List<String>,
    @SerializedName("text")
    val text: List<ContentDto>
)