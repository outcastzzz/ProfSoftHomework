package com.example.togetherApp.presentation.feature.auth

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.example.togetherApp.data.network.TokenManager
import com.example.togetherApp.domain.entity.send.UserData
import com.example.togetherApp.domain.useCase.RequestAuthUseCase
import com.example.togetherApp.presentation.core.BaseViewModel
import com.example.togetherApp.presentation.feature.auth.model.AuthAction
import com.example.togetherApp.presentation.feature.auth.model.AuthEvent
import com.example.togetherApp.presentation.feature.auth.model.AuthViewState
import com.example.togetherApp.presentation.ui.extensions.md5
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val requestAuthUseCase: RequestAuthUseCase,
    private val tokenManager: TokenManager,
) : BaseViewModel<AuthViewState, AuthAction, AuthEvent>(initialState = AuthViewState()) {

    override fun obtainEvent(viewEvent: AuthEvent) {
        when (viewEvent) {
            AuthEvent.LoginClicked -> {
                requestAuth(viewState.phoneValue, viewState.passwordValue)
            }
            is AuthEvent.PasswordChanged -> viewState =
                viewState.copy(passwordValue = viewEvent.newValue)

            is AuthEvent.PhoneChanged -> viewState =
                viewState.copy(phoneValue = viewEvent.newValue)

            AuthEvent.SignUpClicked -> viewAction = AuthAction.OpenRegisterScreen
            AuthEvent.ClearInputs -> viewState =
                viewState.copy(phoneValue = "", passwordValue = "", isError = false)
        }
    }

    private fun requestAuth(
        phone: String,
        password: String
    ) {
        viewState = viewState.copy(isLoading = true)
        viewModelScope.launch(Dispatchers.IO) {
            runCatching {
                val modifiedPassword = password.md5()
                val userData = UserData(phone, modifiedPassword)
                requestAuthUseCase(userData).collect { token ->
                    tokenManager.saveToken(token.token)
                }
            }
                .onSuccess {
                    viewState = viewState.copy(isLoading = false, isError = false)
                    viewAction = AuthAction.OpenMainScreen
                }
                .onFailure {
                    Log.d("errorTag", "ERROR: ${it.message}")
                    viewState = viewState.copy(isLoading = false, isError = true)
                }
        }
    }

}