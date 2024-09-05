package com.example.togetherApp.domain.entity.get

import com.example.togetherApp.domain.entity.common.Author

data class ChatItem(
    val id: String? = "",
    val date: String,
    val author: Author,
    val message: String
)