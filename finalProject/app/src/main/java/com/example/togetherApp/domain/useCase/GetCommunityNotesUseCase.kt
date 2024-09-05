package com.example.togetherApp.domain.useCase

import com.example.togetherApp.domain.entity.get.Note
import com.example.togetherApp.domain.repository.NotesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import javax.inject.Inject

interface GetCommunityNotesUseCase {
    suspend operator fun invoke(): Flow<List<Note>>
}

class GetCommunityNotesUseCaseImpl @Inject constructor(
    private val notesRepository: NotesRepository
): GetCommunityNotesUseCase {
    override suspend operator fun invoke(): Flow<List<Note>> = flowOf(
        notesRepository.getCommunityNotes()
    )
}