package com.example.togetherApp.presentation.feature.communityNotes.model

import coil.ImageLoader
import com.example.togetherApp.domain.entity.get.Note

data class CNotesViewState(
    val communityNotes: List<Note> = emptyList(),
    val searchInput: String = "",
    val isError: Boolean = false,
    val isLoading: Boolean = false,
    val imageLoader: ImageLoader? = null
)