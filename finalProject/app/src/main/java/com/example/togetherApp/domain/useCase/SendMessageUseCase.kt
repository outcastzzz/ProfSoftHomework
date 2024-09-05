package com.example.togetherApp.domain.useCase

import com.example.togetherApp.domain.entity.get.ChatItem
import com.example.togetherApp.domain.entity.send.Message
import com.example.togetherApp.domain.repository.ChatRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import javax.inject.Inject

interface SendMessageUseCase {
    suspend operator fun invoke(message: Message): Flow<ChatItem>
}

class SendMessageUseCaseImpl @Inject constructor(
    private val chatRepository: ChatRepository
): SendMessageUseCase {
    override suspend fun invoke(message: Message): Flow<ChatItem> = flowOf(
        chatRepository.sendMessage(message)
    )
}