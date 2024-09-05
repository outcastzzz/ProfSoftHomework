package com.example.togetherApp.presentation.feature.register

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.example.togetherApp.presentation.feature.register.model.RegisterAction
import com.example.togetherApp.presentation.navigation.AppScreens

@Composable
fun RegisterScreen(navController: NavController) {

    val registerViewModel: RegisterViewModel = hiltViewModel()

    val viewState by registerViewModel.viewStates().collectAsStateWithLifecycle()
    val viewAction by registerViewModel.viewActions().collectAsStateWithLifecycle(null)

    RegisterView(viewState = viewState) { event ->
        registerViewModel.obtainEvent(event)
    }

    when(viewAction) {
        RegisterAction.OpenAuthScreen -> {
            navController.navigate(AppScreens.Auth.title)
            registerViewModel.clearAction()
        }
        RegisterAction.OpenMainScreen -> {
            navController.navigate(AppScreens.Main.title)
            registerViewModel.clearAction()
        }
        null -> {}
    }
}