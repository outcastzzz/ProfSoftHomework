package com.example.togetherApp.data.mapper

import com.example.togetherApp.data.network.model.ChatDto
import com.example.togetherApp.data.network.model.ListOfChatDto
import com.example.togetherApp.data.network.model.SingleChatDto
import com.example.togetherApp.domain.entity.get.ChatItem

internal fun ListOfChatDto.toEntity(): List<ChatItem> = data.map { it.toEntity() }

internal fun SingleChatDto.toEntity(): ChatItem = data.toEntity()

internal fun ChatDto.toEntity(): ChatItem = ChatItem(
    id, date, author, message
)