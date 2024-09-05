package com.example.togetherApp.data.mapper

import com.example.togetherApp.data.network.model.AllUsersDataDto
import com.example.togetherApp.data.network.model.AllUsersDto
import com.example.togetherApp.domain.entity.get.OtherUser

internal fun AllUsersDto.toEntity(): List<OtherUser> = data.map { it.toEntity() }

internal fun AllUsersDataDto.toEntity(): OtherUser = OtherUser(id, name, surname, avatar)
