package com.example.togetherApp.presentation.feature.main.model

import coil.ImageLoader
import com.example.togetherApp.domain.entity.get.Course
import com.example.togetherApp.domain.entity.get.Note

data class MainViewState(
    val inputValue: String = "",
    val listOfCourses: List<Course>? = null,
    val userLastNote: Note? = null,
    val communityLastNote: Note? = null,
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val imageLoader: ImageLoader? = null
)