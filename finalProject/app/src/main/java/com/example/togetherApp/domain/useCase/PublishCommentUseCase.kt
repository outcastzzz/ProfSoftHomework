package com.example.togetherApp.domain.useCase

import com.example.togetherApp.domain.entity.get.Note
import com.example.togetherApp.domain.entity.send.Message
import com.example.togetherApp.domain.repository.NotesRepository
import javax.inject.Inject

interface PublishCommentUseCase {
    suspend operator fun invoke(noteId: String, comment: Message): Note
}

class PublishCommentUseCaseImpl @Inject constructor(
    private val notesRepository: NotesRepository
): PublishCommentUseCase {

    override suspend fun invoke(noteId: String, comment: Message): Note = notesRepository
        .publishComment(noteId, comment)

}