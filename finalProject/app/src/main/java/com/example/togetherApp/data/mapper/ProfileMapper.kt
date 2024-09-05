package com.example.togetherApp.data.mapper

import com.example.togetherApp.data.network.model.ProfileDto
import com.example.togetherApp.data.network.model.SingleProfileDto
import com.example.togetherApp.domain.entity.get.Profile

internal fun SingleProfileDto.toEntity(): Profile = data.toEntity()

internal fun ProfileDto.toEntity(): Profile = Profile(
    id = id,
    name = name,
    surname = surname,
    avatar = avatar,
    role = role,
    phone = phone,
    registerDate = registerDate,
    courses = courses.map { it.toEntity() },
    notes = notes.map { it.toEntity() }
)


