package com.example.togetherApp.data.mapper

import com.example.togetherApp.data.network.model.CourseDto
import com.example.togetherApp.data.network.model.ListOfCourseDto
import com.example.togetherApp.data.network.model.PublishCourseDto
import com.example.togetherApp.data.network.model.SingleCourseDto
import com.example.togetherApp.domain.entity.get.Course
import com.example.togetherApp.domain.entity.send.PublishCourse

internal fun ListOfCourseDto.toEntity(): List<Course> = data.map { it.toEntity() }

internal fun SingleCourseDto.toEntity(): Course = data.toEntity()

internal fun CourseDto.toEntity(): Course = Course(
    id,
    title,
    description,
    tags,
    text.map { it.toEntity() }
)

internal fun PublishCourse.toDto(): PublishCourseDto = PublishCourseDto(
    title,
    description,
    tags,
    text
)