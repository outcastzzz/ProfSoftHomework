package com.example.togetherApp.presentation.feature.splash.model

sealed class SplashAction {
    data object OpenRegister: SplashAction()
    data object OpenMain: SplashAction()
}