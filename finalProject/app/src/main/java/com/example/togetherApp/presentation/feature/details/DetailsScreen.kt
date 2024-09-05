package com.example.togetherApp.presentation.feature.details

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.example.togetherApp.presentation.feature.details.model.DetailsAction

@Composable
fun CourseDetailsScreen(navController: NavController) {

    val detailsViewModel: DetailsViewModel = hiltViewModel()

    val viewState by detailsViewModel.viewStates().collectAsStateWithLifecycle()
    val viewAction by detailsViewModel.viewActions().collectAsStateWithLifecycle(initialValue = null)

    CourseDetailsView(viewState = viewState) { event ->
        detailsViewModel.obtainEvent(event)
    }

    when(viewAction) {
        DetailsAction.ClickBack -> {
            navController.popBackStack()
            detailsViewModel.clearAction()
        }
        null -> {}
    }

}