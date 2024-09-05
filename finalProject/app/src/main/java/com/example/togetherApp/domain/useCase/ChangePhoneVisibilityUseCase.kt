package com.example.togetherApp.domain.useCase

import com.example.togetherApp.domain.entity.get.Profile
import com.example.togetherApp.domain.entity.send.PhoneVisibility
import com.example.togetherApp.domain.repository.ProfileRepository
import javax.inject.Inject

interface ChangePhoneVisibilityUseCase {
    suspend operator fun invoke(visibility: PhoneVisibility): Profile
}

class ChangePhoneVisibilityUseCaseImpl @Inject constructor(
    private val profileRepository: ProfileRepository
): ChangePhoneVisibilityUseCase {

    override suspend operator fun invoke(visibility: PhoneVisibility): Profile = profileRepository
        .changePhoneVisibility(visibility)

}