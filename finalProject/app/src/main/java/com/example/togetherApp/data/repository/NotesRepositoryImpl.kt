package com.example.togetherApp.data.repository

import com.example.togetherApp.data.local.database.dao.NotesDao
import com.example.togetherApp.data.mapper.toDbo
import com.example.togetherApp.data.mapper.toDto
import com.example.togetherApp.data.mapper.toEntity
import com.example.togetherApp.data.network.apiService.NotesApiService
import com.example.togetherApp.domain.entity.get.Note
import com.example.togetherApp.domain.entity.send.Message
import com.example.togetherApp.domain.entity.send.PublishNote
import com.example.togetherApp.domain.repository.NotesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class NotesRepositoryImpl @Inject constructor(
    private val notesApiService: NotesApiService,
    private val notesDao: NotesDao
): NotesRepository {

    override suspend fun getCommunityNotes(): List<Note> = notesApiService
        .getCommunityNotes().toEntity()

    override fun getUserNotes(): Flow<List<Note>> = notesDao
        .getUserNotes().map { list ->
            list.map { dbo ->
                dbo.toEntity()
            }
        }

    override suspend fun saveUserNote(note: Note) = notesDao
        .saveNote(note.toDbo())

    override suspend fun publishNote(note: PublishNote): Note = notesApiService
        .publishNote(note.toDto()).toEntity()

    override suspend fun getNoteById(noteId: String): Note = notesApiService
        .getNoteById(noteId).toEntity()

    override suspend fun publishComment(
        noteId: String,
        comment: Message,
    ): Note = notesApiService
        .publishComment(
            noteId,
            comment.toDto(),
        ).toEntity()

}