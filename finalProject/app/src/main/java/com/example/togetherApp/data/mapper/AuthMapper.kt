package com.example.togetherApp.data.mapper

import com.example.togetherApp.data.local.database.models.UserDataDbo
import com.example.togetherApp.data.network.model.PhoneVisibilityDto
import com.example.togetherApp.data.network.model.RegisterRequestDto
import com.example.togetherApp.data.network.model.TokenDto
import com.example.togetherApp.data.network.model.UserDataDto
import com.example.togetherApp.domain.entity.get.Token
import com.example.togetherApp.domain.entity.send.PhoneVisibility
import com.example.togetherApp.domain.entity.send.RegisterRequest
import com.example.togetherApp.domain.entity.send.UserData

fun TokenDto.toEntity(): Token = Token(
    token = data.token
)

fun UserData.toDbo(): UserDataDbo = UserDataDbo(
    phone = phone, passwordHashed = passwordHashed
)

fun UserDataDbo.toEntity(): UserData = UserData(
    phone, passwordHashed
)

fun PhoneVisibility.toDto(): PhoneVisibilityDto = PhoneVisibilityDto(
    isVisible = isVisible
)

fun UserData.toDto(): UserDataDto = UserDataDto(
    phone = phone,
    passwordHashed = passwordHashed
)

fun RegisterRequest.toDto(): RegisterRequestDto = RegisterRequestDto(
    phone, passwordHashed, name, surname, avatar
)

