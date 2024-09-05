package com.example.togetherApp.domain.useCase

import com.example.togetherApp.domain.entity.get.Token
import com.example.togetherApp.domain.entity.send.RegisterRequest
import com.example.togetherApp.domain.repository.AuthRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface RequestRegisterUseCase {
    suspend operator fun invoke(userData: RegisterRequest): Flow<Token>
}

class RequestRegisterUseCaseImpl @Inject constructor(
    private val repository: AuthRepository
): RequestRegisterUseCase {
    override suspend operator fun invoke(userData: RegisterRequest): Flow<Token> = repository
        .requestRegister(userData)
}