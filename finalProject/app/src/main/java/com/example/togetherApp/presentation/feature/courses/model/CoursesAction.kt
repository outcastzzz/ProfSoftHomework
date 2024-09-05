package com.example.togetherApp.presentation.feature.courses.model

sealed class CoursesAction {
    data object ClickCourse: CoursesAction()
    data object ClickBack: CoursesAction()
}