package com.example.togetherApp.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import com.example.togetherApp.data.network.TokenManager
import com.example.togetherApp.data.network.apiService.AuthApiService
import com.example.togetherApp.data.network.apiService.ChatApiService
import com.example.togetherApp.data.network.apiService.CourseApiService
import com.example.togetherApp.data.network.apiService.NotesApiService
import com.example.togetherApp.data.network.apiService.ProfileApiService
import com.example.togetherApp.data.network.interceptor.AuthInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "data_store")

@Module
@InstallIn(SingletonComponent::class)
interface NetworkModule {

    companion object {

        private const val BASE_URL = "http://profsoft.ddns.net:8080/"

        @Singleton
        @Provides
        fun provideTokenManager(@ApplicationContext context: Context): TokenManager =
            TokenManager(context)

        @Singleton
        @Provides
        fun provideOkHttpClient(
            authInterceptor: AuthInterceptor,
        ): OkHttpClient {
            val loggingInterceptor = HttpLoggingInterceptor()
            loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

            return OkHttpClient.Builder()
                .addInterceptor(authInterceptor)
                .addInterceptor(loggingInterceptor)
                .build()
        }

        @Singleton
        @Provides
        fun provideAuthInterceptor(tokenManager: TokenManager): AuthInterceptor =
            AuthInterceptor(tokenManager)

        @Singleton
        @Provides
        fun provideRetrofitBuilder(): Retrofit.Builder =
            Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())

        @Singleton
        @Provides
        fun provideAuthAPIService(retrofit: Retrofit.Builder): AuthApiService =
            retrofit
                .build()
                .create(AuthApiService::class.java)

        @Singleton
        @Provides
        fun provideChatAPIService(
            okHttpClient: OkHttpClient,
            retrofit: Retrofit.Builder
        ): ChatApiService =
            retrofit
                .client(okHttpClient)
                .build()
                .create(ChatApiService::class.java)

        @Singleton
        @Provides
        fun provideCourseAPIService(
            okHttpClient: OkHttpClient,
            retrofit: Retrofit.Builder
        ): CourseApiService =
            retrofit
                .client(okHttpClient)
                .build()
                .create(CourseApiService::class.java)

        @Singleton
        @Provides
        fun provideNotesAPIService(
            okHttpClient: OkHttpClient,
            retrofit: Retrofit.Builder
        ): NotesApiService =
            retrofit
                .client(okHttpClient)
                .build()
                .create(NotesApiService::class.java)


        @Singleton
        @Provides
        fun provideProfileAPIService(
            okHttpClient: OkHttpClient,
            retrofit: Retrofit.Builder
        ): ProfileApiService =
            retrofit
                .client(okHttpClient)
                .build()
                .create(ProfileApiService::class.java)

    }

}