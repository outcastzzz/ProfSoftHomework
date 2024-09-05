package com.example.togetherApp.domain.useCase

import com.example.togetherApp.domain.entity.get.Profile
import com.example.togetherApp.domain.repository.ProfileRepository
import javax.inject.Inject

interface ChangeUserRoleUseCase {
    suspend operator fun invoke(passwordHashed: String): Profile
}

class ChangeUserRoleUseCaseImpl @Inject constructor(
    private val profileRepository: ProfileRepository
): ChangeUserRoleUseCase {

    override suspend operator fun invoke(passwordHashed: String): Profile = profileRepository
        .changeUserRole(passwordHashed)

}