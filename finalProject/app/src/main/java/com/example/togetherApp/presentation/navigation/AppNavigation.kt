package com.example.togetherApp.presentation.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.togetherApp.presentation.feature.auth.AuthScreen
import com.example.togetherApp.presentation.feature.register.RegisterScreen
import com.example.togetherApp.presentation.feature.splash.SplashScreen
import com.example.togetherApp.presentation.navigation.mainNavigation.MainScreenHost

@Composable
internal fun AppNavigation(
    navController: NavHostController = rememberNavController()
) {

    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentScreen = backStackEntry?.destination?.route ?: AppScreens.Register.title

    CompositionLocalProvider(
        LocalNavHost provides navController
    ) {
        NavHost(
            modifier = Modifier
                .fillMaxSize(),
            navController = navController,
            startDestination = AppScreens.Splash.title
        ) {
            composable(
                route = AppScreens.Splash.title,
//                enterTransition = {
//                    slideIntoContainer(
//                        AnimatedContentTransitionScope.SlideDirection.Start,
//                        animationSpec = tween(700)
//                    )
//                },
//                exitTransition = {
//                    slideOutOfContainer(
//                        AnimatedContentTransitionScope.SlideDirection.Start,
//                        animationSpec = tween(700)
//                    )
//                },
//                popEnterTransition = {
//                    slideIntoContainer(
//                        AnimatedContentTransitionScope.SlideDirection.End,
//                        animationSpec = tween(700)
//                    )
//                },
//                popExitTransition = {
//                    slideOutOfContainer(
//                        AnimatedContentTransitionScope.SlideDirection.End,
//                        animationSpec = tween(700)
//                    )
//                }
            ) {
                SplashScreen(navController = navController)
            }
            composable(
                route = AppScreens.Auth.title,
            ) {
                AuthScreen(navController = navController)
            }
            composable(
                route = AppScreens.Register.title,
            ) {
                RegisterScreen(navController = navController)
            }
            composable(
                route = AppScreens.Main.title
            ) {
                MainScreenHost()
            }
        }
    }
}