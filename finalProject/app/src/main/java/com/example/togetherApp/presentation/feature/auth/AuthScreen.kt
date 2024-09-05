package com.example.togetherApp.presentation.feature.auth

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.example.togetherApp.presentation.feature.auth.model.AuthAction
import com.example.togetherApp.presentation.navigation.AppScreens

@Composable
fun AuthScreen(navController: NavController) {

    val authViewModel: AuthViewModel = hiltViewModel()

    val viewState by authViewModel.viewStates().collectAsStateWithLifecycle()
    val viewAction by authViewModel.viewActions().collectAsStateWithLifecycle(null)

    AuthView(viewState = viewState) { event ->
        authViewModel.obtainEvent(event)
    }

    when(viewAction) {
        AuthAction.OpenMainScreen -> {
            navController.navigate(AppScreens.Main.title)
            authViewModel.clearAction()
        }
        AuthAction.OpenRegisterScreen -> {
            navController.navigate(AppScreens.Register.title)
            authViewModel.clearAction()
        }
        null -> {}
    }

}