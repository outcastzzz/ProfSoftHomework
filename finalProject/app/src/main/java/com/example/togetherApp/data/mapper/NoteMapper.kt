package com.example.togetherApp.data.mapper

import com.example.togetherApp.data.local.database.models.NoteCommentDbo
import com.example.togetherApp.data.local.database.models.NoteDbo
import com.example.togetherApp.data.network.model.ListOfNotesDto
import com.example.togetherApp.data.network.model.NoteCommentDto
import com.example.togetherApp.data.network.model.NoteDto
import com.example.togetherApp.data.network.model.PublishNoteDto
import com.example.togetherApp.data.network.model.SingleNoteDto
import com.example.togetherApp.domain.entity.get.Note
import com.example.togetherApp.domain.entity.get.NoteComment
import com.example.togetherApp.domain.entity.send.PublishNote

internal fun ListOfNotesDto.toEntity(): List<Note> = data.map { it.toEntity() }

internal fun SingleNoteDto.toEntity(): Note = data.toEntity()

internal fun NoteDto.toEntity(): Note = Note(
    id,
    title,
    content.map {
        it.toEntity()
    },
    author.toEntity(),
    date,
    comments.map { it.toEntity() }
)

internal fun NoteCommentDto.toEntity(): NoteComment = NoteComment(id, author.toEntity(), text)

internal fun NoteDbo.toEntity(): Note = Note(
    id = id,
    title = title,
    content = listOf(content.toEntity()),
    author = author.toEntity(),
    date = date,
    comments = comments.map {
        it.toEntity()
    }
)

internal fun NoteCommentDbo.toEntity(): NoteComment = NoteComment(
    id, author.toEntity(), text
)

internal fun Note.toDbo(): NoteDbo = NoteDbo(
    id = id,
    title = title,
    content = content[0].toDbo(),
    author = author.toDbo(),
    date = date,
    comments = comments.map {
        it.toDbo()
    }
)

internal fun NoteComment.toDbo(): NoteCommentDbo = NoteCommentDbo(
    id, author.toDbo(), text
)

internal fun PublishNote.toDto(): PublishNoteDto = PublishNoteDto(
    title, content.toDto()
)