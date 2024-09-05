package com.example.togetherApp.data.network.model

import com.google.gson.annotations.SerializedName

data class ListOfNotesDto(
    @SerializedName("data")
    val data: List<NoteDto>
)

data class SingleNoteDto(
    @SerializedName("data")
    val data: NoteDto
)

data class NoteDto(
    @SerializedName("id")
    val id: String,
    @SerializedName("title")
    val title: String,
    @SerializedName("content")
    val content: List<ContentDto>,
    @SerializedName("author")
    val author: AuthorDto,
    @SerializedName("date")
    val date: String,
    @SerializedName("comments")
    val comments: List<NoteCommentDto>
)

data class NoteCommentDto(
    @SerializedName("id")
    val id: String,
    @SerializedName("author")
    val author: AuthorDto,
    @SerializedName("text")
    val text: String
)