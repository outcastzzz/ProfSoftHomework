package com.example.togetherApp.domain.useCase

import com.example.togetherApp.domain.entity.get.ChatItem
import com.example.togetherApp.domain.repository.ChatRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import javax.inject.Inject

interface GetAllMessagesUseCase {
    suspend operator fun invoke(): Flow<List<ChatItem>>
}

class GetAllMessagesUseCaseImpl @Inject constructor(
    private val chatRepository: ChatRepository
): GetAllMessagesUseCase {
    override suspend fun invoke(): Flow<List<ChatItem>> = flowOf(
        chatRepository.getAllMessages()
    )
}