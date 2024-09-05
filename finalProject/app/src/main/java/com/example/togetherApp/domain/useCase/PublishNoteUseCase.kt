package com.example.togetherApp.domain.useCase

import com.example.togetherApp.domain.entity.get.Note
import com.example.togetherApp.domain.entity.send.PublishNote
import com.example.togetherApp.domain.repository.NotesRepository
import javax.inject.Inject

interface PublishNoteUseCase {
    suspend operator fun invoke(note: PublishNote): Note
}

class PublishNoteUseCaseImpl @Inject constructor(
    private val notesRepository: NotesRepository
): PublishNoteUseCase {

    override suspend fun invoke(note: PublishNote): Note = notesRepository
        .publishNote(note)

}