package com.example.togetherApp.presentation.feature.auth.model

sealed class AuthEvent {
    class PhoneChanged(val newValue: String): AuthEvent()
    class PasswordChanged(val newValue: String) : AuthEvent()
    data object SignUpClicked: AuthEvent()
    data object LoginClicked: AuthEvent()
    data object ClearInputs: AuthEvent()
}