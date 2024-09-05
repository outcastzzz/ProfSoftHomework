package com.example.togetherApp.presentation.feature.communityNotes.model

sealed class CNotesAction {
    data object ClickNote: CNotesAction()
    data object ClickBack: CNotesAction()
}