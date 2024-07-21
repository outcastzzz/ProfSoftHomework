package com.example.lesson6.di

import com.example.lesson6.data.network.ApiFactory
import com.example.lesson6.data.network.ApiService
import com.example.lesson6.data.repository.MainRepositoryImpl
import com.example.lesson6.domain.repository.MainRepository
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
interface DataModule {

    @Binds
    @ApplicationScope
    fun bindMainRepository(impl: MainRepositoryImpl): MainRepository

    companion object {

        @Provides
        @ApplicationScope
        fun provideApiService(): ApiService {
            return ApiFactory.apiService
        }

    }

}