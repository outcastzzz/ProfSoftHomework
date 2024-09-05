package com.example.togetherApp.domain.useCase

import com.example.togetherApp.domain.entity.get.Course
import com.example.togetherApp.domain.repository.CourseRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import javax.inject.Inject

interface GetListOfCoursesUseCase {
    suspend operator fun invoke(): Flow<List<Course>>
}

class GetListOfCoursesUseCaseImpl @Inject constructor(
    private val courseRepository: CourseRepository
): GetListOfCoursesUseCase {
    override suspend fun invoke(): Flow<List<Course>> = flowOf(
        courseRepository.getListOfCourses()
    )
}