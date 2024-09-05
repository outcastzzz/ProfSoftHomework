package com.example.togetherApp.presentation.feature.splash

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.example.togetherApp.presentation.feature.splash.model.SplashAction
import com.example.togetherApp.presentation.navigation.AppScreens

@Composable
fun SplashScreen(navController: NavController) {

    val splashViewModel: SplashViewModel = hiltViewModel()

    val viewAction by splashViewModel.viewActions().collectAsStateWithLifecycle(null)

    SplashView { event ->
        splashViewModel.obtainEvent(event)
    }

    when(viewAction) {
        SplashAction.OpenMain -> {
            navController.navigate(AppScreens.Main.title)
            splashViewModel.clearAction()
        }
        SplashAction.OpenRegister -> {
            navController.navigate(AppScreens.Auth.title)
            splashViewModel.clearAction()
        }
        null -> {}
    }

}