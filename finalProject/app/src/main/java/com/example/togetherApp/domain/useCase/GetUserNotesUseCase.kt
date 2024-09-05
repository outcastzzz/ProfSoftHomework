package com.example.togetherApp.domain.useCase

import com.example.togetherApp.domain.entity.get.Note
import com.example.togetherApp.domain.repository.NotesRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface GetUserNotesUseCase {
    suspend operator fun invoke(): Flow<List<Note>>
}

class GetUserNotesUseCaseImpl @Inject constructor(
    private val notesRepository: NotesRepository
): GetUserNotesUseCase {
    override suspend fun invoke(): Flow<List<Note>> = notesRepository
        .getUserNotes()
}