package com.example.togetherApp.domain.useCase

import com.example.togetherApp.domain.entity.get.Course
import com.example.togetherApp.domain.repository.CourseRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import javax.inject.Inject

interface GetCourseByIdUseCase {
    suspend operator fun invoke(courseId: String): Flow<Course>
}

class GetCourseByIdUseCaseImpl @Inject constructor(
    private val courseRepository: CourseRepository
): GetCourseByIdUseCase {

    override suspend fun invoke(courseId: String): Flow<Course> = flowOf(
        courseRepository.getCourseById(courseId)
    )

}