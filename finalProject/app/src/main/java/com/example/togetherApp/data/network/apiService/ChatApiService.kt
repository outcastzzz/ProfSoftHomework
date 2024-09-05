package com.example.togetherApp.data.network.apiService

import com.example.togetherApp.data.network.model.ListOfChatDto
import com.example.togetherApp.data.network.model.SingleChatDto
import com.example.togetherApp.domain.entity.send.Message
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ChatApiService {

    @GET("api/chat")
    suspend fun getAllMessages(): ListOfChatDto

    @POST("api/chat")
    suspend fun sendMessage(
        @Body text: Message,
    ): SingleChatDto

}