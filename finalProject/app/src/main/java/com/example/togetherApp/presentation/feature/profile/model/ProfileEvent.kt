package com.example.togetherApp.presentation.feature.profile.model

sealed class ProfileEvent {
    data object AllUsersClicked: ProfileEvent()
    data class ChangePhoneVisibility(val newValue: Boolean): ProfileEvent()
    data object UserCoursesClicked: ProfileEvent()
    data object UserNotesClicked: ProfileEvent()
    data object LogoutClicked: ProfileEvent()
}