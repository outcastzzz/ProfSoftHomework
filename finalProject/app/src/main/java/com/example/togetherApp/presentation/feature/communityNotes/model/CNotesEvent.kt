package com.example.togetherApp.presentation.feature.communityNotes.model

sealed class CNotesEvent {
    class SearchInputChanged(val newValue: String): CNotesEvent()
    data object ClickBack: CNotesEvent()
}