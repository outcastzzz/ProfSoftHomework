package com.example.togetherApp.di

import com.example.togetherApp.domain.useCase.ChangePhoneVisibilityUseCase
import com.example.togetherApp.domain.useCase.ChangePhoneVisibilityUseCaseImpl
import com.example.togetherApp.domain.useCase.ChangeUserRoleUseCase
import com.example.togetherApp.domain.useCase.ChangeUserRoleUseCaseImpl
import com.example.togetherApp.domain.useCase.GetAllMessagesUseCase
import com.example.togetherApp.domain.useCase.GetAllMessagesUseCaseImpl
import com.example.togetherApp.domain.useCase.GetAllUsersProfilesUseCase
import com.example.togetherApp.domain.useCase.GetAllUsersProfilesUseCaseImpl
import com.example.togetherApp.domain.useCase.GetCommunityNotesUseCase
import com.example.togetherApp.domain.useCase.GetCommunityNotesUseCaseImpl
import com.example.togetherApp.domain.useCase.GetCourseByIdUseCase
import com.example.togetherApp.domain.useCase.GetCourseByIdUseCaseImpl
import com.example.togetherApp.domain.useCase.GetListOfCoursesUseCase
import com.example.togetherApp.domain.useCase.GetListOfCoursesUseCaseImpl
import com.example.togetherApp.domain.useCase.GetNoteByIdUseCase
import com.example.togetherApp.domain.useCase.GetNoteByIdUseCaseImpl
import com.example.togetherApp.domain.useCase.GetProfileByIdUseCase
import com.example.togetherApp.domain.useCase.GetProfileByIdUseCaseImpl
import com.example.togetherApp.domain.useCase.GetProfileUseCase
import com.example.togetherApp.domain.useCase.GetProfileUseCaseImpl
import com.example.togetherApp.domain.useCase.GetUserNotesUseCase
import com.example.togetherApp.domain.useCase.GetUserNotesUseCaseImpl
import com.example.togetherApp.domain.useCase.PublishCommentUseCase
import com.example.togetherApp.domain.useCase.PublishCommentUseCaseImpl
import com.example.togetherApp.domain.useCase.PublishCourseUseCase
import com.example.togetherApp.domain.useCase.PublishCourseUseCaseImpl
import com.example.togetherApp.domain.useCase.PublishNoteUseCase
import com.example.togetherApp.domain.useCase.PublishNoteUseCaseImpl
import com.example.togetherApp.domain.useCase.RequestAuthUseCase
import com.example.togetherApp.domain.useCase.RequestAuthUseCaseImpl
import com.example.togetherApp.domain.useCase.RequestRegisterUseCase
import com.example.togetherApp.domain.useCase.RequestRegisterUseCaseImpl
import com.example.togetherApp.domain.useCase.SendMessageUseCase
import com.example.togetherApp.domain.useCase.SendMessageUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface UseCaseModule {

    @Binds
    fun bindRequestAuthUseCase(
        impl: RequestAuthUseCaseImpl
    ): RequestAuthUseCase

    @Binds
    fun bindRequestRegisterUseCase(
        impl: RequestRegisterUseCaseImpl
    ): RequestRegisterUseCase

    @Binds
    fun bindChangePhoneVisibilityUseCase(
        impl: ChangePhoneVisibilityUseCaseImpl
    ): ChangePhoneVisibilityUseCase

    @Binds
    fun bindChangeUserRoleUseCase(
        impl: ChangeUserRoleUseCaseImpl
    ): ChangeUserRoleUseCase

    @Binds
    fun bindGetAllMessagesUseCase(
        impl: GetAllMessagesUseCaseImpl
    ): GetAllMessagesUseCase

    @Binds
    fun bindGetAllUsersProfilesUseCase(
        impl: GetAllUsersProfilesUseCaseImpl
    ): GetAllUsersProfilesUseCase

    @Binds
    fun bindGetListOfCoursesUseCase(
        impl: GetListOfCoursesUseCaseImpl
    ): GetListOfCoursesUseCase

    @Binds
    fun bindGetCommunityNotesUseCase(
        impl: GetCommunityNotesUseCaseImpl
    ): GetCommunityNotesUseCase

    @Binds
    fun bindGetUserNotesUseCase(
        impl: GetUserNotesUseCaseImpl
    ): GetUserNotesUseCase

    @Binds
    fun bindGetCourseByIdUseCase(
        impl: GetCourseByIdUseCaseImpl
    ): GetCourseByIdUseCase

    @Binds
    fun bindGetNoteByIdUseCase(
        impl: GetNoteByIdUseCaseImpl
    ): GetNoteByIdUseCase

    @Binds
    fun bindProfileByIdUseCase(
        impl: GetProfileByIdUseCaseImpl
    ): GetProfileByIdUseCase

    @Binds
    fun bindGetProfileUseCase(
        impl: GetProfileUseCaseImpl
    ): GetProfileUseCase

    @Binds
    fun bindPublishCommentUseCase(
        impl: PublishCommentUseCaseImpl
    ): PublishCommentUseCase

    @Binds
    fun bindPublishCourseUseCase(
        impl: PublishCourseUseCaseImpl
    ): PublishCourseUseCase

    @Binds
    fun bindPublishNoteUseCase(
        impl: PublishNoteUseCaseImpl
    ): PublishNoteUseCase

    @Binds
    fun bindSendMessageUseCase(
        impl: SendMessageUseCaseImpl
    ): SendMessageUseCase

}