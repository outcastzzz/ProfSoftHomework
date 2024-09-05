package com.example.togetherApp.data.network.apiService

import com.example.togetherApp.data.network.interceptor.SkipAuth
import com.example.togetherApp.data.network.model.RegisterRequestDto
import com.example.togetherApp.data.network.model.TokenDto
import com.example.togetherApp.data.network.model.UserDataDto
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthApiService {

    @POST("api/auth")
    @SkipAuth
    suspend fun requestAuth(@Body userData: UserDataDto): TokenDto

    @POST("api/register")
    @SkipAuth
    suspend fun requestRegister(@Body registerRequest: RegisterRequestDto): TokenDto

}