package com.example.togetherApp.domain.useCase

import com.example.togetherApp.domain.entity.get.Note
import com.example.togetherApp.domain.repository.NotesRepository
import javax.inject.Inject

interface GetNoteByIdUseCase {
    suspend operator fun invoke(noteId: String): Note
}

class GetNoteByIdUseCaseImpl @Inject constructor(
    private val notesRepository: NotesRepository
): GetNoteByIdUseCase {

    override suspend fun invoke(noteId: String): Note = notesRepository
        .getNoteById(noteId)

}