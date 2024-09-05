package com.example.togetherApp.presentation.feature.splash.model

sealed class SplashEvent {
    data object CheckToken: SplashEvent()
}