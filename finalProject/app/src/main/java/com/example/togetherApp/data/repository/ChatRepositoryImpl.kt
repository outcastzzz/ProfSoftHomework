package com.example.togetherApp.data.repository

import com.example.togetherApp.data.mapper.toEntity
import com.example.togetherApp.data.network.apiService.ChatApiService
import com.example.togetherApp.domain.entity.get.ChatItem
import com.example.togetherApp.domain.entity.send.Message
import com.example.togetherApp.domain.repository.ChatRepository
import javax.inject.Inject

class ChatRepositoryImpl @Inject constructor(
    private val chatApiService: ChatApiService
): ChatRepository {

    override suspend fun getAllMessages(): List<ChatItem> {
        return chatApiService.getAllMessages().toEntity()
    }

    override suspend fun sendMessage(message: Message): ChatItem {
        return chatApiService.sendMessage(message).toEntity()
    }
}