package com.example.togetherApp.domain.repository

import com.example.togetherApp.domain.entity.get.ChatItem
import com.example.togetherApp.domain.entity.send.Message

interface ChatRepository {

    suspend fun getAllMessages(): List<ChatItem>

    suspend fun sendMessage(message: Message): ChatItem

}