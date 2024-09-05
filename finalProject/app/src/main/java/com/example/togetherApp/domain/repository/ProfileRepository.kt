package com.example.togetherApp.domain.repository

import com.example.togetherApp.domain.entity.get.OtherUser
import com.example.togetherApp.domain.entity.get.Profile
import com.example.togetherApp.domain.entity.send.PhoneVisibility

interface ProfileRepository {

    suspend fun getProfile(): Profile

    suspend fun getProfileById(userId: String): Profile

    suspend fun getAllUsersProfiles(): List<OtherUser>

    suspend fun changeUserRole(passwordHashed: String): Profile

    suspend fun changePhoneVisibility(visibility: PhoneVisibility): Profile

}