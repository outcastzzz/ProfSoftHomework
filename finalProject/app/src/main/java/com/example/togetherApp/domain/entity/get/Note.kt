package com.example.togetherApp.domain.entity.get

import com.example.togetherApp.domain.entity.common.Author
import com.example.togetherApp.domain.entity.common.Content

data class Note(
    val id: String,
    val title: String,
    val content: List<Content>,
    val author: Author,
    val date: String,
    val comments: List<NoteComment>
)

data class NoteComment(
    val id: String,
    val author: Author,
    val text: String,
)