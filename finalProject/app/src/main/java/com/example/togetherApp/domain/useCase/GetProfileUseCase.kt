package com.example.togetherApp.domain.useCase

import com.example.togetherApp.domain.entity.get.Profile
import com.example.togetherApp.domain.repository.ProfileRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import javax.inject.Inject

interface GetProfileUseCase {
    suspend operator fun invoke(): Flow<Profile>
}

class GetProfileUseCaseImpl @Inject constructor(
    private val profileRepository: ProfileRepository
): GetProfileUseCase {

    override suspend operator fun invoke(): Flow<Profile> = flowOf(
        profileRepository.getProfile()
    )

}