package com.example.togetherApp.presentation.feature.main.model

sealed class MainAction {
    data object OpenAllCourses: MainAction()
    data object OpenUserNotes: MainAction()
    data object OpenCommunityNotes: MainAction()
    data object OpenAuth: MainAction()
}