package com.example.togetherApp.presentation.feature.chat.model

import coil.ImageLoader
import com.example.togetherApp.domain.entity.get.ChatItem

data class ChatViewState(
    val messages: List<ChatItem> = emptyList(),
    val searchInput: String = "",
    val messageInput: String = "",
    val profileId: String = "",
    val imageLoader: ImageLoader? = null,
    val isError: Boolean = false,
    val isLoading: Boolean = false,
)