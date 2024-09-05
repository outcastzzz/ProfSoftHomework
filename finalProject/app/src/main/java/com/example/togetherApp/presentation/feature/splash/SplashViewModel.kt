package com.example.togetherApp.presentation.feature.splash

import androidx.lifecycle.viewModelScope
import com.example.togetherApp.data.network.TokenManager
import com.example.togetherApp.presentation.core.BaseViewModel
import com.example.togetherApp.presentation.feature.splash.model.SplashAction
import com.example.togetherApp.presentation.feature.splash.model.SplashEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val tokenManager: TokenManager,
): BaseViewModel<Any, SplashAction, SplashEvent>(initialState = Any()) {

    override fun obtainEvent(viewEvent: SplashEvent) {
         when(viewEvent) {
            SplashEvent.CheckToken -> checkToken()
        }
    }

    private fun checkToken() {
        viewModelScope.launch {
            tokenManager.getToken().collect { token ->
                viewAction = if (token != null) {
                    SplashAction.OpenMain
                } else {
                    SplashAction.OpenRegister
                }
            }
        }
    }
}