package com.example.chapter03_6.di

import com.example.chapter03_6.remote.MainService
import com.example.chapter03_6.remote.repository.MainRepository
import com.example.chapter03_6.remote.repository.MainRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object MainRepositoryModule {

    @ViewModelScoped
    @Provides
    fun providesMainRepository(mainService: MainService): MainRepository {
        return MainRepositoryImpl(mainService)
    }
}