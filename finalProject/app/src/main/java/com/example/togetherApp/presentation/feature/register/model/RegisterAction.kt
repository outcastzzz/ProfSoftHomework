package com.example.togetherApp.presentation.feature.register.model

sealed class RegisterAction {
    data object OpenMainScreen: RegisterAction()
    data object OpenAuthScreen: RegisterAction()
}