package com.example.togetherApp.data.network.model

import com.google.gson.annotations.SerializedName

data class SingleProfileDto(
    @SerializedName("data")
    val data: ProfileDto
)

data class ProfileDto(
    @SerializedName("id")
    val id: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("surname")
    val surname: String,
    @SerializedName("avatar")
    val avatar: String,
    @SerializedName("role")
    val role: Int,
    @SerializedName("phone")
    val phone: String,
    @SerializedName("registerDate")
    val registerDate: String,
    @SerializedName("courses")
    val courses: List<CourseDto>,
    @SerializedName("notes")
    val notes: List<NoteDto>
)