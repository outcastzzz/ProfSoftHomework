package com.example.togetherApp.domain.repository

import com.example.togetherApp.domain.entity.get.Course
import com.example.togetherApp.domain.entity.send.PublishCourse

interface CourseRepository {

    suspend fun getListOfCourses(): List<Course>

    suspend fun publishCourse(course: PublishCourse): Course

    suspend fun getCourseById(courseId: String): Course

}