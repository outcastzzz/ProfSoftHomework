package com.example.togetherApp.presentation.feature.auth.model

sealed class AuthAction {
    data object OpenMainScreen: AuthAction()
    data object OpenRegisterScreen: AuthAction()
}