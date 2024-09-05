package com.example.togetherApp.presentation.feature.courses.model

sealed class CoursesEvent {
    class SearchInputChanged(val newValue: String): CoursesEvent()
    data object ClickBack: CoursesEvent()
}