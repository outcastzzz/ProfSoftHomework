package com.example.togetherApp.presentation.feature.chat.model

import com.example.togetherApp.domain.entity.send.Message

sealed class ChatEvent {
    class SendMessage(val message: Message): ChatEvent()
    class SearchInputChanged(val newValue: String): ChatEvent()
    class MessageInputChanged(val newValue: String): ChatEvent()
    data object ClickRefresh: ChatEvent()
}