package com.example.togetherApp.domain.useCase

import com.example.togetherApp.domain.entity.get.Token
import com.example.togetherApp.domain.entity.send.UserData
import com.example.togetherApp.domain.repository.AuthRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface RequestAuthUseCase {
    suspend operator fun invoke(userData: UserData): Flow<Token>
}

class RequestAuthUseCaseImpl @Inject constructor(
    private val repository: AuthRepository
): RequestAuthUseCase  {
    override suspend fun invoke(userData: UserData): Flow<Token> = repository
        .requestAuth(userData)
}