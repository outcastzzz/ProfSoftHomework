package com.example.togetherApp.data.network.apiService

import com.example.togetherApp.data.network.model.ListOfNotesDto
import com.example.togetherApp.data.network.model.MessageDto
import com.example.togetherApp.data.network.model.PublishNoteDto
import com.example.togetherApp.data.network.model.SingleNoteDto
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface NotesApiService {

    @GET("api/community_notes")
    suspend fun getCommunityNotes(): ListOfNotesDto

    @POST("api/community_notes")
    suspend fun publishNote(
        note: PublishNoteDto,
    ): SingleNoteDto

    @GET("api/community_notes/{noteId}")
    suspend fun getNoteById(
        @Path("noteId") noteId: String,
    ): SingleNoteDto

    @POST("api/community_notes/comment/{noteId}")
    suspend fun publishComment(
        @Path("noteId") noteId: String,
        @Body text: MessageDto,
    ): SingleNoteDto

}