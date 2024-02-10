package com.example.chapter03_7.repository

import com.example.chapter03_7.model.ContentEntity
import kotlinx.coroutines.flow.Flow

interface ContentRepository {

    fun loadList(): Flow<List<ContentEntity>>

    suspend fun insert(item: ContentEntity)
}