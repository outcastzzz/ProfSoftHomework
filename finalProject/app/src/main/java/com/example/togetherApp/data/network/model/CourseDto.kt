package com.example.togetherApp.data.network.model

import com.google.gson.annotations.SerializedName

data class ListOfCourseDto(
    @SerializedName("data")
    val data: List<CourseDto>
)

data class SingleCourseDto(
    @SerializedName("data")
    val data: CourseDto
)

data class CourseDto(
    @SerializedName("id")
    val id: String,
    @SerializedName("title")
    val title: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("tags")
    val tags: List<String>,
    @SerializedName("text")
    val text: List<ContentDto>
)