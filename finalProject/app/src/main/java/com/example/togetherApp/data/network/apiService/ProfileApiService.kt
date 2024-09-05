package com.example.togetherApp.data.network.apiService

import com.example.togetherApp.data.network.model.AllUsersDto
import com.example.togetherApp.data.network.model.PhoneVisibilityDto
import com.example.togetherApp.data.network.model.SingleProfileDto
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.PUT
import retrofit2.http.Path

interface ProfileApiService {

    @GET("api/profile")
    suspend fun getProfile(): SingleProfileDto

    @GET("api/profile/{userId}")
    suspend fun getProfileById(
        @Path("userId") userId: String,
    ): SingleProfileDto

    @GET("api/profile/all")
    suspend fun getAllProfiles(): AllUsersDto

    @PUT("api/profile/role")
    suspend fun changeUserRole(
        @Body passwordHashed: String,
    ): SingleProfileDto

    @PUT("api/profile/phone_visibility")
    suspend fun changePhoneVisibility(
        @Body visibility: PhoneVisibilityDto,
    ): SingleProfileDto

}