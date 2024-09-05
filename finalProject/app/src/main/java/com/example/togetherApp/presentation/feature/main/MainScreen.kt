package com.example.togetherApp.presentation.feature.main

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.example.togetherApp.presentation.feature.main.model.MainAction
import com.example.togetherApp.presentation.navigation.InnerScreens

@Composable
fun MainScreen(
    navController: NavController,
    onAuthReturned: () -> Unit,
) {

    val mainViewModel: MainViewModel = hiltViewModel()

    val viewState by mainViewModel.viewStates().collectAsStateWithLifecycle()
    val viewAction by mainViewModel.viewActions().collectAsStateWithLifecycle(null)

    MainView(viewState = viewState) { event ->
        mainViewModel.obtainEvent(event)
    }

    when(viewAction) {
        MainAction.OpenAllCourses -> {
            navController.navigate(InnerScreens.AllCourses.title)
            mainViewModel.clearAction()
        }
        MainAction.OpenCommunityNotes -> {
            navController.navigate(InnerScreens.CommunityNotes.title)
            mainViewModel.clearAction()
        }
        MainAction.OpenUserNotes -> {
            mainViewModel.clearAction()
        }
        MainAction.OpenAuth -> {
            onAuthReturned()
            mainViewModel.clearAction()
        }
        null -> {}
    }

}