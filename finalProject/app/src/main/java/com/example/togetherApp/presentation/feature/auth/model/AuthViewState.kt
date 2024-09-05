package com.example.togetherApp.presentation.feature.auth.model

data class AuthViewState(
    val phoneValue: String = "",
    val passwordValue: String = "",
    val isLoading: Boolean = false,
    val isError: Boolean = false,
)