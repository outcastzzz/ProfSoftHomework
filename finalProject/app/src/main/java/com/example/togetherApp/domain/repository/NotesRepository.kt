package com.example.togetherApp.domain.repository

import com.example.togetherApp.domain.entity.get.Note
import com.example.togetherApp.domain.entity.send.Message
import com.example.togetherApp.domain.entity.send.PublishNote
import kotlinx.coroutines.flow.Flow

interface NotesRepository {

    suspend fun getCommunityNotes(): List<Note>

    fun getUserNotes(): Flow<List<Note>>

    suspend fun saveUserNote(note: Note)

    suspend fun publishNote(note: PublishNote): Note

    suspend fun getNoteById(noteId: String): Note

    suspend fun publishComment(
        noteId: String,
        comment: Message,
    ): Note

}
