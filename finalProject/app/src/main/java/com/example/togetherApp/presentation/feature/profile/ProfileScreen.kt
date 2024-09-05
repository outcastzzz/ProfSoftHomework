package com.example.togetherApp.presentation.feature.profile

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.example.togetherApp.presentation.feature.profile.model.ProfileAction
import com.example.togetherApp.presentation.navigation.InnerScreens

@Composable
fun ProfileScreen(
    navController: NavController,
    onLogoutClicked: () -> Unit,
) {

    val profileViewModel: ProfileViewModel = hiltViewModel()

    val viewState by profileViewModel.viewStates().collectAsStateWithLifecycle()
    val viewAction by profileViewModel.viewActions().collectAsStateWithLifecycle(null)

    ProfileView(viewState = viewState) { event ->
        profileViewModel.obtainEvent(event)
    }

    when(viewAction) {
        ProfileAction.OpenUserCourses -> {
            navController.navigate(InnerScreens.AllCourses.title)
            profileViewModel.clearAction()
        }
        ProfileAction.OpenAllUsers -> {

            profileViewModel.clearAction()
        }
        ProfileAction.OpenUserNotes -> {

            profileViewModel.clearAction()
        }
        ProfileAction.Logout -> {
            onLogoutClicked()
            profileViewModel.clearAction()
        }
        null -> {}
    }

}