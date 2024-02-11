package com.example.chapter03_8.di

import com.example.chapter03_8.data.source.remote.api.ContentService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ServiceModule {

    @Singleton
    @Provides
    fun providesContentService(retrofit: Retrofit): ContentService {
        return retrofit.create(ContentService::class.java)
    }
}