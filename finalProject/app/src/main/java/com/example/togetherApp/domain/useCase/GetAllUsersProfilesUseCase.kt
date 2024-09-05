package com.example.togetherApp.domain.useCase

import com.example.togetherApp.domain.entity.get.OtherUser
import com.example.togetherApp.domain.repository.ProfileRepository
import javax.inject.Inject

interface GetAllUsersProfilesUseCase {
    suspend operator fun invoke(): List<OtherUser>
}

class GetAllUsersProfilesUseCaseImpl @Inject constructor(
    private val profileRepository: ProfileRepository
): GetAllUsersProfilesUseCase {

    override suspend operator fun invoke(): List<OtherUser> = profileRepository
        .getAllUsersProfiles()

}