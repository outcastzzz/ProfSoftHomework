package com.example.togetherApp.domain.useCase

import com.example.togetherApp.domain.entity.get.Course
import com.example.togetherApp.domain.entity.send.PublishCourse
import com.example.togetherApp.domain.repository.CourseRepository
import javax.inject.Inject

interface PublishCourseUseCase {
    suspend operator fun invoke(course: PublishCourse): Course
}

class PublishCourseUseCaseImpl @Inject constructor(
    private val courseRepository: CourseRepository
): PublishCourseUseCase {

    override suspend fun invoke(course: PublishCourse): Course = courseRepository
        .publishCourse(course)

}