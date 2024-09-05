package com.example.togetherApp.presentation.feature.communityNotes

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.example.togetherApp.presentation.feature.communityNotes.model.CNotesAction
import com.example.togetherApp.presentation.navigation.InnerScreens

@Composable
fun CNotesScreen(navController: NavController) {

    val cNotesViewModel: CNotesViewModel = hiltViewModel()

    val viewState by cNotesViewModel.viewStates().collectAsStateWithLifecycle()
    val viewAction by cNotesViewModel.viewActions().collectAsStateWithLifecycle(null)

    CNotesView(viewState = viewState) { event ->
        cNotesViewModel.obtainEvent(event)
    }

    when(viewAction) {
        CNotesAction.ClickBack -> {
            navController.popBackStack()
            cNotesViewModel.clearAction()
        }
        CNotesAction.ClickNote -> {
            navController.navigate(InnerScreens.NoteDetails.title)
            cNotesViewModel.clearAction()
        }
        null -> {}
    }

}