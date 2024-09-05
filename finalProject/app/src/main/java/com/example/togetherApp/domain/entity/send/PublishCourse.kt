package com.example.togetherApp.domain.entity.send

import com.example.togetherApp.data.network.model.ContentDto

data class PublishCourse(
    val title: String,
    val description: String,
    val tags: List<String>,
    val text: List<ContentDto>
)