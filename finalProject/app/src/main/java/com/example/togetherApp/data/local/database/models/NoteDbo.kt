package com.example.togetherApp.data.local.database.models

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.togetherApp.data.local.database.models.common.AuthorDbo
import com.example.togetherApp.data.local.database.models.common.ContentDbo

@Entity(tableName = "notes_table")
data class NoteDbo(
    @PrimaryKey
    val id: String,
    val title: String,
    @Embedded(prefix = "note_content_")
    val content: ContentDbo,
    @Embedded(prefix = "note_author_")
    val author: AuthorDbo,
    val date: String,
    val comments: List<NoteCommentDbo>
)

@Entity
data class NoteCommentDbo(
    val id: String,
    @Embedded(prefix = "comment_author_")
    val author: AuthorDbo,
    val text: String
)