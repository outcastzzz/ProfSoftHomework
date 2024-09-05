package com.example.togetherApp.presentation.feature.chat

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController

@Composable
fun ChatScreen(navController: NavController) {

    val chatViewModel: ChatViewModel = hiltViewModel()

    val viewState by chatViewModel.viewStates().collectAsStateWithLifecycle()
    val viewAction by chatViewModel.viewActions().collectAsStateWithLifecycle(null)

    ChatView(viewState = viewState) { event ->
        chatViewModel.obtainEvent(event)
    }

}