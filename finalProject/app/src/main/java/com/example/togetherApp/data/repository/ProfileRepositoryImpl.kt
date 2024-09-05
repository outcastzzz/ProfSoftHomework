package com.example.togetherApp.data.repository

import com.example.togetherApp.data.mapper.toDto
import com.example.togetherApp.data.mapper.toEntity
import com.example.togetherApp.data.network.apiService.ProfileApiService
import com.example.togetherApp.domain.entity.get.OtherUser
import com.example.togetherApp.domain.entity.get.Profile
import com.example.togetherApp.domain.entity.send.PhoneVisibility
import com.example.togetherApp.domain.repository.ProfileRepository
import javax.inject.Inject

class ProfileRepositoryImpl @Inject constructor(
    private val profileApiService: ProfileApiService
): ProfileRepository {

    override suspend fun getProfile(): Profile = profileApiService
        .getProfile().toEntity()

    override suspend fun getProfileById(userId: String): Profile = profileApiService
        .getProfileById(userId).toEntity()

    override suspend fun getAllUsersProfiles(): List<OtherUser> = profileApiService
        .getAllProfiles().toEntity()

    override suspend fun changeUserRole(passwordHashed: String): Profile = profileApiService
        .changeUserRole(passwordHashed).toEntity()

    override suspend fun changePhoneVisibility(visibility: PhoneVisibility): Profile = profileApiService
        .changePhoneVisibility(visibility.toDto()).toEntity()

}