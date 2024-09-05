package com.example.togetherApp.domain.repository

import com.example.togetherApp.domain.entity.get.Token
import com.example.togetherApp.domain.entity.send.RegisterRequest
import com.example.togetherApp.domain.entity.send.UserData
import kotlinx.coroutines.flow.Flow

interface AuthRepository {

    suspend fun requestAuth(userData: UserData): Flow<Token>

    suspend fun requestRegister(userData: RegisterRequest): Flow<Token>

    suspend fun saveUserData(userData: UserData)

    suspend fun getUserData(): Flow<UserData>

    suspend fun clearUserData()

}