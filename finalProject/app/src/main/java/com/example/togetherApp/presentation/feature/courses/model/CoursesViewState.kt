package com.example.togetherApp.presentation.feature.courses.model

import com.example.togetherApp.domain.entity.get.Course

data class CoursesViewState(
    val courses: List<Course> = emptyList(),
    val searchInput: String = "",
    val isError: Boolean = false,
    val isLoading: Boolean = false,
)