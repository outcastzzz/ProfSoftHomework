package com.example.togetherApp.domain.entity.get

import com.example.togetherApp.domain.entity.common.Content

data class Course(
    val id: String,
    val title: String,
    val description: String,
    val tags: List<String>,
    val text: List<Content>
)
