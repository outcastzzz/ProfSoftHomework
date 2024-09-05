package com.example.togetherApp.data.network.apiService

import com.example.togetherApp.data.network.model.CourseDto
import com.example.togetherApp.data.network.model.ListOfCourseDto
import com.example.togetherApp.data.network.model.PublishCourseDto
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface CourseApiService {

    @GET("api/courses")
    suspend fun getListOfCourses(): ListOfCourseDto

    @POST("api/courses")
    suspend fun publishCourse(
        @Body course: PublishCourseDto,
    ): CourseDto

    @GET("api/courses/{courseId}")
    suspend fun getCourseById(
        @Path("courseId") courseId: String,
    ): CourseDto

}