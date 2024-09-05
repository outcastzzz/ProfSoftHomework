package com.example.togetherApp.presentation.feature.register

import androidx.lifecycle.viewModelScope
import com.example.togetherApp.data.network.TokenManager
import com.example.togetherApp.domain.entity.send.RegisterRequest
import com.example.togetherApp.domain.useCase.RequestRegisterUseCase
import com.example.togetherApp.presentation.core.BaseViewModel
import com.example.togetherApp.presentation.feature.register.model.RegisterAction
import com.example.togetherApp.presentation.feature.register.model.RegisterEvent
import com.example.togetherApp.presentation.feature.register.model.RegisterViewState
import com.example.togetherApp.presentation.ui.extensions.md5
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val requestRegisterUseCase: RequestRegisterUseCase,
    private val tokenManager: TokenManager
) : BaseViewModel<RegisterViewState, RegisterAction, RegisterEvent>(initialState = RegisterViewState()) {

    override fun obtainEvent(viewEvent: RegisterEvent) {
        when (viewEvent) {
            RegisterEvent.AuthClicked -> viewAction = RegisterAction.OpenAuthScreen

            RegisterEvent.RegisterClicked -> requestRegister(
                viewState.nameValue,
                viewState.surnameValue,
                viewState.phoneValue,
                viewState.passwordValue
            )

            RegisterEvent.ClearInputs -> viewState =
                viewState.copy(
                    nameValue = "",
                    surnameValue = "",
                    phoneValue = "",
                    passwordValue = "",
                    isError = false
                )

            is RegisterEvent.NameChanged -> viewState =
                viewState.copy(nameValue = viewEvent.newValue)

            is RegisterEvent.PasswordChanged -> viewState =
                viewState.copy(passwordValue = viewEvent.newValue)

            is RegisterEvent.PhoneChanged -> viewState =
                viewState.copy(phoneValue = viewEvent.newValue)

            is RegisterEvent.SurnameChanged -> viewState =
                viewState.copy(surnameValue = viewEvent.newValue)

        }
    }

    private fun requestRegister(
        name: String,
        surname: String,
        phone: String,
        password: String,
    ) {
        viewState = viewState.copy(isLoading = true)
        viewModelScope.launch(Dispatchers.IO) {
            runCatching {
                val modifiedPassword = password.md5()
                val userData = RegisterRequest(
                    phone = phone,
                    passwordHashed = modifiedPassword,
                    name = name,
                    surname = surname)
                requestRegisterUseCase(userData).collect { token ->
                    tokenManager.saveToken(token.token)
                }
            }
                .onSuccess {
                    viewState = viewState.copy(isLoading = false, isError = false)
                    viewAction = RegisterAction.OpenMainScreen
                }
                .onFailure {
                    viewState = viewState.copy(isLoading = false, isError = true)
                }
        }

    }

}