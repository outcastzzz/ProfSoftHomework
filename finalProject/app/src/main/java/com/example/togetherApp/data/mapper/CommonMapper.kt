package com.example.togetherApp.data.mapper

import com.example.togetherApp.data.local.database.models.common.AuthorDbo
import com.example.togetherApp.data.local.database.models.common.ContentDbo
import com.example.togetherApp.data.network.model.AuthorDto
import com.example.togetherApp.data.network.model.ContentDto
import com.example.togetherApp.data.network.model.MessageDto
import com.example.togetherApp.domain.entity.common.Author
import com.example.togetherApp.domain.entity.common.Content
import com.example.togetherApp.domain.entity.send.Message

internal fun ContentDto.toEntity(): Content = Content(text, image)

internal fun Content.toDto(): ContentDto = ContentDto(text ?: "", image ?: "")

internal fun Content.toDbo(): ContentDbo = ContentDbo(text = text ?: "", image = image ?: "")

internal fun ContentDbo.toEntity(): Content = Content(text, image)

internal fun AuthorDto.toEntity(): Author = Author(id, name, surname, avatar, role)

internal fun Author.toDto(): AuthorDto = AuthorDto(id, name, surname, avatar, role)

internal fun Author.toDbo(): AuthorDbo = AuthorDbo(id, name, surname, avatar, role)

internal fun AuthorDbo.toEntity(): Author = Author(id, name, surname, avatar, role)

internal fun Message.toDto(): MessageDto = MessageDto(text)









