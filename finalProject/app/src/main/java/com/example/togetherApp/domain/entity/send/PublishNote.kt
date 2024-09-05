package com.example.togetherApp.domain.entity.send

import com.example.togetherApp.domain.entity.common.Content

data class PublishNote(
    val title: String,
    val content: Content,
)