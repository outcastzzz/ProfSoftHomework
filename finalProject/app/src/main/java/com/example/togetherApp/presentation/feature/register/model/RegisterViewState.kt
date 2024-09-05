package com.example.togetherApp.presentation.feature.register.model

data class RegisterViewState(
    val nameValue: String = "",
    val surnameValue: String = "",
    val phoneValue: String = "",
    val passwordValue: String = "",
    val isError: Boolean = false,
    val isLoading: Boolean = false,
)