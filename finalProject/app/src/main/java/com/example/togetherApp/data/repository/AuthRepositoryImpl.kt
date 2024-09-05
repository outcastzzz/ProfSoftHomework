package com.example.togetherApp.data.repository

import com.example.togetherApp.data.local.database.dao.UserDao
import com.example.togetherApp.data.mapper.toDbo
import com.example.togetherApp.data.mapper.toDto
import com.example.togetherApp.data.mapper.toEntity
import com.example.togetherApp.data.network.apiService.AuthApiService
import com.example.togetherApp.domain.entity.get.Token
import com.example.togetherApp.domain.entity.send.RegisterRequest
import com.example.togetherApp.domain.entity.send.UserData
import com.example.togetherApp.domain.repository.AuthRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val authApiService: AuthApiService,
    private val userDao: UserDao
): AuthRepository {

    override suspend fun requestAuth(userData: UserData): Flow<Token> {
        return flowOf(authApiService.requestAuth(userData.toDto()).toEntity())
    }

    override suspend fun requestRegister(userData: RegisterRequest): Flow<Token> {
        return flowOf(authApiService.requestRegister(userData.toDto()).toEntity())
    }

    override suspend fun saveUserData(userData: UserData) = userDao
        .saveUserData(userData.toDbo())

    override suspend fun getUserData(): Flow<UserData> = userDao
        .getUserData().map { dbo ->
            dbo.toEntity()
        }

    override suspend fun clearUserData() = userDao
        .clearUserData()

}