package com.example.togetherApp.data.network.model

import com.example.togetherApp.domain.entity.common.Author
import com.google.gson.annotations.SerializedName

data class ListOfChatDto(
    @SerializedName("data")
    val data: List<ChatDto>
)

data class SingleChatDto(
    @SerializedName("data")
    val data: ChatDto
)

data class ChatDto(
    @SerializedName("is")
    val id: String,
    @SerializedName("date")
    val date: String,
    @SerializedName("author")
    val author: Author,
    @SerializedName("message")
    val message: String,
)

