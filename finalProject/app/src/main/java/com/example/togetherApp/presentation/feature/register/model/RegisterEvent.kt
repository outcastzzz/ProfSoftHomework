package com.example.togetherApp.presentation.feature.register.model

sealed class RegisterEvent {
    class NameChanged(val newValue: String): RegisterEvent()
    class SurnameChanged(val newValue: String): RegisterEvent()
    class PhoneChanged(val newValue: String): RegisterEvent()
    class PasswordChanged(val newValue: String): RegisterEvent()
    data object AuthClicked: RegisterEvent()
    data object RegisterClicked: RegisterEvent()
    data object ClearInputs: RegisterEvent()
}