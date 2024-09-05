package com.example.togetherApp.domain.useCase

import com.example.togetherApp.domain.entity.get.Profile
import com.example.togetherApp.domain.repository.ProfileRepository
import javax.inject.Inject

interface GetProfileByIdUseCase {
    suspend operator fun invoke(userId: String): Profile
}

class GetProfileByIdUseCaseImpl @Inject constructor(
    private val profileRepository: ProfileRepository
): GetProfileByIdUseCase {

    override suspend operator fun invoke(userId: String): Profile = profileRepository
        .getProfileById(userId)

}