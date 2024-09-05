package com.example.togetherApp.presentation.feature.profile.model

sealed class ProfileAction {
    data object OpenAllUsers: ProfileAction()
    data object OpenUserCourses: ProfileAction()
    data object OpenUserNotes: ProfileAction()
    data object Logout: ProfileAction()
}