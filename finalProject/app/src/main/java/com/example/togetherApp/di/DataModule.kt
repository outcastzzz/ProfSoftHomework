package com.example.togetherApp.di

import android.content.Context
import com.example.togetherApp.data.local.database.AppDatabase
import com.example.togetherApp.data.repository.AuthRepositoryImpl
import com.example.togetherApp.data.repository.ChatRepositoryImpl
import com.example.togetherApp.data.repository.CourseRepositoryImpl
import com.example.togetherApp.data.repository.NotesRepositoryImpl
import com.example.togetherApp.data.repository.ProfileRepositoryImpl
import com.example.togetherApp.domain.repository.AuthRepository
import com.example.togetherApp.domain.repository.ChatRepository
import com.example.togetherApp.domain.repository.CourseRepository
import com.example.togetherApp.domain.repository.NotesRepository
import com.example.togetherApp.domain.repository.ProfileRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface DataModule {

    @Binds
    fun bindAuthRepository(impl: AuthRepositoryImpl): AuthRepository

    @Binds
    fun bindChatRepository(impl: ChatRepositoryImpl): ChatRepository

    @Binds
    fun bindCourseRepository(impl: CourseRepositoryImpl): CourseRepository

    @Binds
    fun bindNotesRepository(impl: NotesRepositoryImpl): NotesRepository

    @Binds
    fun bindProfileRepository(impl: ProfileRepositoryImpl): ProfileRepository

    companion object {

        @Provides
        fun provideAppDatabase(
            @ApplicationContext context: Context
        ): AppDatabase = AppDatabase.getInstance(context)

        @Provides
        fun provideUserDao(database: AppDatabase) = database.userDao()

        @Provides
        fun provideNotesDao(database: AppDatabase) = database.notesDao()

    }

}