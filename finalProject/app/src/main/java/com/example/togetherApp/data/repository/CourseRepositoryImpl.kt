package com.example.togetherApp.data.repository

import com.example.togetherApp.data.mapper.toDto
import com.example.togetherApp.data.mapper.toEntity
import com.example.togetherApp.data.network.apiService.CourseApiService
import com.example.togetherApp.domain.entity.get.Course
import com.example.togetherApp.domain.entity.send.PublishCourse
import com.example.togetherApp.domain.repository.CourseRepository
import javax.inject.Inject

class CourseRepositoryImpl @Inject constructor(
    private val courseApiService: CourseApiService
) : CourseRepository {

    override suspend fun getListOfCourses(): List<Course> = courseApiService
        .getListOfCourses().toEntity()


    override suspend fun publishCourse(course: PublishCourse): Course = courseApiService
        .publishCourse(course.toDto()).toEntity()


    override suspend fun getCourseById(courseId: String): Course = courseApiService
        .getCourseById(courseId).toEntity()

}