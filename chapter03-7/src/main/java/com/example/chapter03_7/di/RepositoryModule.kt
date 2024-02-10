package com.example.chapter03_7.di

import com.example.chapter03_7.data.dao.ContentDao
import com.example.chapter03_7.repository.ContentRepository
import com.example.chapter03_7.repository.ContentRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object RepositoryModule {

    @Provides
    @ViewModelScoped
    fun providesContentRepository(contentDao: ContentDao): ContentRepository {
        return ContentRepositoryImpl(contentDao)
    }
}